package com.spacestar.back.quickmatching.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spacestar.back.feignClient.dto.res.ProfileResDto;
import com.spacestar.back.feignClient.service.FeignClientService;
import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;
import com.spacestar.back.quickmatching.converter.QuickMatchingConverter;
import com.spacestar.back.quickmatching.domain.QuickMatching;
import com.spacestar.back.quickmatching.dto.req.QuickMatchingEnterReqDto;
import com.spacestar.back.quickmatching.dto.res.QuickMatchingResDto;
import com.spacestar.back.quickmatching.repository.MatchingScoresRepository;
import com.spacestar.back.quickmatching.repository.QuickMatchingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class QuickMatchingServiceImpl implements QuickMatchingService {
    private final RedisTemplate<String, String> redisTemplate;
    private final QuickMatchingRepository quickMatchingRepository;
    private final MatchingScoresRepository matchingScoresRepository;
    private final ObjectMapper objectMapper;
    //FeignClient
    private final FeignClientService feignClientService;
    //SSE
    private final ConcurrentHashMap<String, Set<SseEmitter>> container = new ConcurrentHashMap<>();

    private final long tenSecBefore = System.currentTimeMillis() - 10000;

    //대기큐진입
    @Override
    public void enterQuickMatching(String uuid, QuickMatchingEnterReqDto reqDto) {
        redisTemplate.opsForZSet().add(reqDto.getGameName(), uuid, System.currentTimeMillis());
        doQuickMatch(reqDto.getGameName(), uuid);
    }

    //UUID로 SSE 연결

    //수락처리
    @Override
    public void acceptQuickMatch(String uuid) {
        Set<ZSetOperations.TypedTuple<String>> quickMatchingMembers = redisTemplate.opsForZSet().rangeWithScores("QuickMatching", 0, -1);
        assert quickMatchingMembers != null;
        acceptMatchingStatus(uuid, quickMatchingMembers);
    }

    //거절처리
    @Override
    public void rejectQuickMatch(String uuid) {
        Set<ZSetOperations.TypedTuple<String>> quickMatchingMembers = redisTemplate.opsForZSet().rangeWithScores("QuickMatching", 0, -1);
        assert quickMatchingMembers != null;
        rejectMatchingStatus(uuid, quickMatchingMembers);
    }

    //큐 수락 여부 확인 후 수락한 사용자 대기열에서 삭제
    @Override
    public QuickMatchingResDto completeQuickMatch(String uuid, QuickMatchingEnterReqDto reqDto) {
        Set<ZSetOperations.TypedTuple<String>> quickMatchingMembers = redisTemplate.opsForZSet().rangeWithScores("QuickMatching", 0, -1);

        if (quickMatchingMembers != null) {
            for (ZSetOperations.TypedTuple<String> tuple : quickMatchingMembers) {
                return completeMatchForMember(uuid, reqDto, tuple);
            }
        }
        throw new GlobalException(ResponseStatus.WAITING_MEMBER_NOT_EXIST);
    }

    //큐 돌리는 도중 취소
    @Override
    public void quitQuickMatching(String uuid, QuickMatchingEnterReqDto reqDto) {
        Double score = redisTemplate.opsForZSet().score(reqDto.getGameName(), uuid);

        if (score != null) {
            redisTemplate.opsForZSet().remove(reqDto.getGameName(), uuid);
        } else throw new GlobalException(ResponseStatus.WAITING_MEMBER_NOT_EXIST);
    }



    //기존에 대기큐에 있던 사용자와 매칭 실시
    public void doQuickMatch(String gameName, String uuid) {
        Set<ZSetOperations.TypedTuple<String>> waitingMembers = redisTemplate.opsForZSet().rangeWithScores(gameName, 0, -1);
        int maxScore = 0;
        int score = 0;
        String matchedMemberUuid = null;
        assert waitingMembers != null;
        //대기중인 사용자가 2명 이상일 경우 실행
        if (waitingMembers.size() >= 2) {
            // Set을 List로 변환
            List<ZSetOperations.TypedTuple<String>> waitingMembersList = new ArrayList<>(waitingMembers);

            log.info("for문 진입 전");
            // 마지막 인덱스를 제외하고 순회
            for (int i = 0; i < waitingMembersList.size() - 1; i++) {
                ZSetOperations.TypedTuple<String> tuple = waitingMembersList.get(i);
                String matchMemberUuid = tuple.getValue();
                score = calculateScore(uuid, matchMemberUuid);
                // 점수에 대기시간 더해주기
                score += (int) (System.currentTimeMillis() - tuple.getScore()) / 10000;
                if (score > maxScore) {
                    maxScore = score;
                    if (maxScore >= 10) {
                        matchedMemberUuid = matchMemberUuid;
                    }
                }
                log.info("score = " + score);
            }
        }
        //매치된 사람 있으면 대기큐에서 제거 후 수락큐로 진입
        //+ SSE로 매치 되었다고 알려줌
        if (matchedMemberUuid != null) {
            log.info(uuid + "와 " + matchedMemberUuid + "가 매치되었습니다.");
            redisTemplate.opsForZSet().remove(gameName, uuid);
            redisTemplate.opsForZSet().remove(gameName, matchedMemberUuid);
            enterMatchQueue(uuid, matchedMemberUuid);
            sendComment(uuid);
            sendComment(matchedMemberUuid);
        }
    }

    //사용자 간에 매치 점수 계산
    //매칭 우선 순위 : 내가 하는 게임 >>>> 게임 성향 >> 연령대 >>>> mbti >>>>>>>성별
    private int calculateScore(String matchFromMember, String matchToMember) {
        int score = 0;

        ProfileResDto myProfile = feignClientService.getProfile(matchFromMember);
        ProfileResDto yourProfile = feignClientService.getProfile(matchToMember);
//        AuthResDto myAuth = feignClientService.getAuth(matchFromMember);
//        AuthResDto yourAuth = feignClientService.getAuth(matchToMember);
        //각각 메인게임 ID, 게임성향ID, MBTI ID가 존재할 때만 연산해서 점수 더해줌
        if (myProfile.getMainGameId() != null && yourProfile.getMainGameId() != null) {
            score += mainGameScore(myProfile.getMainGameId(), myProfile.getMainGameId());
        }
        if (myProfile.getGamePreferenceId() != null && yourProfile.getGamePreferenceId() != null) {
            score += gamePreferenceScore(myProfile.getGamePreferenceId(), myProfile.getGamePreferenceId());
        }
        if (myProfile.getMbtiId() != null && yourProfile.getMbtiId() != null)
            score += mbtiScore(myProfile.getMbtiId(), yourProfile.getMbtiId());

//        score += ageScore(myAuth.getAge(), yourAuth.getAge());
//        score += genderScore(myAuth.getGender(), yourAuth.getGender());

        //신고 당한 횟수 만큼 점수 깎기
        score -= (myProfile.getReportCount() + yourProfile.getReportCount());
        return score;
    }

    //메인 게임이 같으면 10점 추가
    private int mainGameScore(Long myMainGameId, Long yourMainGameId) {
        int score = 0;
        if (Objects.equals(myMainGameId, yourMainGameId)) {
            score += 10;
            return score;
        }
        return score;
    }

    private int genderScore(String myGender, String yourGender) {
        int score = 0;
        if (myGender.equals("OTHERS") || yourGender.equals("OTHERS")) {
            return score;
        }//이성이면 10점
        if (!myGender.equals(yourGender)) {
            score = 10;
        }
        return score;
    }

    private int ageScore(int myAge, int yourAge) {
        int maxScore = 24; // 최대 점수
        int ageDifference = Math.abs(myAge - yourAge); // 나이 차이
        // 나이 차이가 0이면 최대 점수를 반환하고, 차이가 커질수록 점수가 줄어듭니다.
        // 예를 들어, 나이 차이가 1이면 44점, 차이가 2이면 40점 등으로 계산합니다.
        int score = maxScore - (ageDifference * 2);
        // 최소 점수는 0으로 설정
        if (score < 0) {
            score = 0;
        }
        return score;
    }

    public int mbtiScore(long myMbtiId, long yourMbtiId) {
        return matchingScoresRepository.getScore(myMbtiId, yourMbtiId);
    }

    private int gamePreferenceScore(Long myGamePreferenceId, Long yourGamePreferenceId) {
        return matchingScoresRepository.getScore(myGamePreferenceId, yourGamePreferenceId);
    }

    //수락 대기 큐 진입
    public void enterMatchQueue(String matchFromMember, String matchToMember) {
        QuickMatching quickMatching = QuickMatchingConverter.toEntity(matchFromMember, matchToMember);
        quickMatchingRepository.save(quickMatching);
    }

    private QuickMatchingResDto completeMatchForMember(String uuid, QuickMatchingEnterReqDto reqDto, ZSetOperations.TypedTuple<String> tuple) {
        try {
            Map<String, Object> data = objectMapper.readValue(tuple.getValue(), Map.class);
            if (isMatchingCompleted(uuid, data)) {
                redisTemplate.opsForZSet().remove("QuickMatching", tuple.getValue());
                removeMatchedMembersFromQueue(reqDto, data);
                return QuickMatchingResDto.builder().memberUuid(uuid).build();
            } else {
                //수락큐에서 제거
                redisTemplate.opsForZSet().remove("QuickMatching", tuple.getValue());
                //다시 대기큐에 넣기
                redisTemplate.opsForZSet().add(reqDto.getGameName(), (String) data.get("matchFromMember"), tenSecBefore);
                redisTemplate.opsForZSet().add(reqDto.getGameName(), (String) data.get("matchToMember"), tenSecBefore);
            }
        } catch (IOException e) {
            log.error("Failed to complete quick match", e);
        }
        return null;
    }

    private void acceptMatchingStatus(String uuid, Set<ZSetOperations.TypedTuple<String>> quickMatchingMembers) {
        for (ZSetOperations.TypedTuple<String> tuple : quickMatchingMembers) {
            try {
                Map<String, Object> data = objectMapper.readValue(tuple.getValue(), Map.class);

                if (uuid.equals(data.get("matchFromMember"))) {
                    data.put("matchFromMemberStatus", "ACCEPTED");
                    String updatedValue = objectMapper.writeValueAsString(data);
                    redisTemplate.opsForZSet().remove("QuickMatching", tuple.getValue());
                    redisTemplate.opsForZSet().add("QuickMatching", updatedValue, tenSecBefore);
                } else if (uuid.equals(data.get("matchToMember"))) {
                    data.put("matchToMemberStatus", "ACCEPTED");
                    String updatedValue = objectMapper.writeValueAsString(data);
                    redisTemplate.opsForZSet().remove("QuickMatching", tuple.getValue());
                    redisTemplate.opsForZSet().add("QuickMatching", updatedValue, tenSecBefore);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void rejectMatchingStatus(String uuid, Set<ZSetOperations.TypedTuple<String>> quickMatchingMembers) {
        for (ZSetOperations.TypedTuple<String> tuple : quickMatchingMembers) {
            try {
                Map<String, Object> data = objectMapper.readValue(tuple.getValue(), Map.class);

                if (uuid.equals(data.get("matchFromMember"))) {
                    data.put("matchFromMemberStatus", "REJECT");
                    String updatedValue = objectMapper.writeValueAsString(data);
                    redisTemplate.opsForZSet().remove("QuickMatching", tuple.getValue());
                    redisTemplate.opsForZSet().add("QuickMatching", updatedValue, tenSecBefore);
                } else if (uuid.equals(data.get("matchToMember"))) {
                    data.put("matchToMemberStatus", "REJECT");
                    String updatedValue = objectMapper.writeValueAsString(data);
                    redisTemplate.opsForZSet().remove("QuickMatching", tuple.getValue());
                    redisTemplate.opsForZSet().add("QuickMatching", updatedValue, tenSecBefore);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //양쪽 다 수락 상태인지 확인
    private boolean isMatchingCompleted(String uuid, Map<String, Object> data) {
        return (uuid.equals(data.get("matchFromMember")) || uuid.equals(data.get("matchToMember"))) &&
                "ACCEPTED".equals(data.get("matchFromMemberStatus")) &&
                "ACCEPTED".equals(data.get("matchToMemberStatus"));
    }

    private void removeMatchedMembersFromQueue(QuickMatchingEnterReqDto reqDto, Map<String, Object> data) {
        redisTemplate.opsForZSet().remove(reqDto.getGameName(), data.get("matchFromMember"));
        redisTemplate.opsForZSet().remove(reqDto.getGameName(), data.get("matchToMember"));
    }

    //SSE
    @Override
    public SseEmitter connect(String uuid) {
        SseEmitter sseEmitter = new SseEmitter(300_000L);

        final SseEmitter.SseEventBuilder sseEventBuilder = SseEmitter.event()
                .name("connect")
                .data("connected!")
                .reconnectTime(300_000L);

        sendEvent(sseEmitter, sseEventBuilder);

        synchronized (container) {
            Set<SseEmitter> sseEmitters = container.getOrDefault(uuid, new HashSet<>());
            sseEmitters.add(sseEmitter);
            container.put(uuid, sseEmitters);
        }

        return sseEmitter;
    }

    //매치 되었을 때 메세지 전송
    public void sendComment(String uuid) {
        Set<SseEmitter> sseEmitters;
        synchronized (container) {
            sseEmitters = new HashSet<>(container.getOrDefault(uuid, Collections.emptySet()));
        }

        final SseEmitter.SseEventBuilder sseEventBuilder = SseEmitter.event()
                .data("매치 되었습니다.")
                .reconnectTime(30000L);

        sseEmitters.forEach(sseEmitter -> sendEvent(sseEmitter, sseEventBuilder));
    }

    private void sendEvent(SseEmitter emitter, SseEmitter.SseEventBuilder event) {
        try {
            emitter.send(event);
        } catch (IOException e) {
            emitter.completeWithError(e);
        }
    }

    @Override
    public String getStrings() {
        List<String> mentList = new ArrayList<>();
        mentList.add("더 정확한 매칭을 위해 추가 정보를 입력해보세요!");
        mentList.add("성향에 따라 더 나은 상대를 만날 수 있도록 정보를 업데이트 해주세요.");
        mentList.add("프로필을 업데이트하면 더 많은 사용자와 매칭될 수 있습니다!");
        mentList.add("더 많은 관심을 받고 싶다면 프로필을 완성해주세요!");
        mentList.add("정보를 추가하면 더 정확한 매칭을 돕습니다.");
        mentList.add("성향에 맞는 사용자와 더 가까워지려면 프로필을 최신으로 유지하세요.");
        mentList.add("정보를 갱신하면 더 나은 파트너를 찾을 수 있습니다.");
        mentList.add("더 많은 매칭을 원하신다면 프로필을 업데이트 해보세요!");
        mentList.add("프로필을 완성하면 보다 정확한 매칭을 찾을 수 있습니다.");
        mentList.add("성향에 딱 맞는 상대를 만나기 위해 프로필을 업데이트하세요!");

        Random random = new Random();
        // 랜덤으로 멘트 선택
        int index = random.nextInt(mentList.size());
        return mentList.get(index);
    }
}