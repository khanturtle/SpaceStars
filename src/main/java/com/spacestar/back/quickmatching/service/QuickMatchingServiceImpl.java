package com.spacestar.back.quickmatching.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;
import com.spacestar.back.quickmatching.converter.QuickMatchingConverter;
import com.spacestar.back.quickmatching.domain.QuickMatching;
import com.spacestar.back.quickmatching.dto.QuickMatchingEnterReqDto;
import com.spacestar.back.quickmatching.dto.QuickMatchingResDto;
import com.spacestar.back.quickmatching.repository.QuickMatchingRepository;
import com.spacestar.back.quickmatching.dto.res.AuthResDto;
import com.spacestar.back.quickmatching.dto.res.ProfileResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class QuickMatchingServiceImpl implements QuickMatchingService {
    private final RestTemplate restTemplate;
    private final RedisTemplate<String, String> redisTemplate;
    private final QuickMatchingRepository quickMatchingRepository;
    private final ObjectMapper objectMapper;

    @Value("${spring.application.profile-url}")
    private String profileUrl;
    @Value("${spring.application.auth-url}")
    private String authUrl;
    //SSE
    private final HashMap<String, Set<SseEmitter>> container = new HashMap<>();

    private final long tenSecBefore = System.currentTimeMillis()-10000;
    //대기큐진입
    @Override
    public void enterQuickMatching(String uuid, QuickMatchingEnterReqDto reqDto) {
        redisTemplate.opsForZSet().add(reqDto.getGameName(), uuid, System.currentTimeMillis());
        doQuickMatch(reqDto.getGameName(), uuid);
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
            for (ZSetOperations.TypedTuple<String> tuple : waitingMembers) {
                String matchMemberUuid = tuple.getValue();
                score = calculateScore(uuid, matchMemberUuid);
                if (score > maxScore) {
                    maxScore = score;
                    if(maxScore>=60){
                        matchedMemberUuid = matchMemberUuid;
                    }
                }
            }
        }
        //매치된 사람 있으면 대기큐에서 제거 후 수락큐로 진입
        //+ SSE로 매치 되었다고 알려줌
        if (matchedMemberUuid != null) {
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
//        score += mainGameScore(getProfile(matchFromMember).getMainGameId(), getProfile(matchToMember).getMainGameId());
//        score += gamePreferenceScore(getProfile(matchFromMember).getGamePreferenceId(), getProfile(matchToMember).getGamePreferenceId());
        score += ageScore(getAuth(matchFromMember).getAge(), getAuth(matchToMember).getAge());
        score += mbtiScore(getProfile(matchFromMember).getMbtiId(), getProfile(matchToMember).getMbtiId());
        score += genderScore(getAuth(matchFromMember).getGender(), getAuth(matchToMember).getGender());
        //신고 당한 횟수 만큼 점수 깎기
        score -= (getProfile(matchFromMember).getReportCount() + getProfile(matchToMember).getReportCount());
        return score;
    }

    private int genderScore(String myGender, String yourGender) {
        int score = 0;
        if(myGender.equals("OTHERS")||yourGender.equals("OTHERS")){
            return score;
        }
        if(!myGender.equals(yourGender)){
            score = 10;
        }
        return score;
    }

    private int ageScore(int myAge, int yourAge) {
        int maxScore = 40; // 최대 점수
        int ageDifference = Math.abs(myAge - yourAge); // 나이 차이

        // 나이 차이가 0이면 최대 점수를 반환하고, 차이가 커질수록 점수가 줄어듭니다.
        // 예를 들어, 나이 차이가 1이면 36점, 차이가 2이면 32점 등으로 계산합니다.
        int score = maxScore - (ageDifference * 4);

        // 최소 점수는 0으로 설정
        if (score < 0) {
            score = 0;
        }

        return score;
    }

    //RestTemplate으로 프로필 서비스 호출
    private ProfileResDto getProfile(String memberUuid) {
        String url = profileUrl + memberUuid;
        return restTemplate.exchange(url, HttpMethod.GET, null, ProfileResDto.class).getBody();
    }
    //RestTemplate으로 Auth 서비스 호출
    private AuthResDto getAuth(String memberUuid) {
        String url = authUrl + memberUuid;
        return restTemplate.exchange(url, HttpMethod.GET, null, AuthResDto.class).getBody();
    }

    //수락 대기 큐 진입
    public void enterMatchQueue(String matchFromMember, String matchToMember) {
        QuickMatching quickMatching = QuickMatchingConverter.toEntity(matchFromMember,matchToMember);
        quickMatchingRepository.save(quickMatching);
    }

    //UUID로 SSE 연결
    @Override
    public SseEmitter connect(QuickMatchingEnterReqDto reqDto, String uuid) {
        SseEmitter sseEmitter = new SseEmitter(300_000L);

        final SseEmitter.SseEventBuilder sseEventBuilder = SseEmitter.event()
                .name("connect")
                .data("connected!")
                .reconnectTime(3000L);

        sendEvent(sseEmitter, sseEventBuilder);

        Set<SseEmitter> sseEmitters = container.getOrDefault(uuid, new HashSet<>());

        sseEmitters.add(sseEmitter);
        container.put(uuid, sseEmitters);
        sseEmitter.onCompletion(() -> {
            sseEmitters.remove(sseEmitter);
        });
        return sseEmitter;
    }

    //수락처리
    @Override
    public void acceptQuickMatch(String uuid) {
        Set<ZSetOperations.TypedTuple<String>> quickMatchingMembers = redisTemplate.opsForZSet().rangeWithScores("QuickMatching", 0, -1);
        assert quickMatchingMembers != null;
        acceptMatchingStatus(uuid, quickMatchingMembers);
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

    //거절처리
    @Override
    public void rejectQuickMatch(String uuid) {
        Set<ZSetOperations.TypedTuple<String>> quickMatchingMembers = redisTemplate.opsForZSet().rangeWithScores("QuickMatching", 0, -1);
        assert quickMatchingMembers != null;
        rejectMatchingStatus(uuid, quickMatchingMembers);
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

    @Override
    public void quitQuickMatching(String uuid, QuickMatchingEnterReqDto reqDto) {
        Double score = redisTemplate.opsForZSet().score(reqDto.getGameName(), uuid);

        if (score != null) {
            redisTemplate.opsForZSet().remove(reqDto.getGameName(), uuid);
        } else throw new GlobalException(ResponseStatus.WAITING_MEMBER_NOT_EXIST);
    }


    private static void sendEvent(final SseEmitter sseEmitter,
                                  final SseEmitter.SseEventBuilder sseEventBuilder) {
        try {
            sseEmitter.send(sseEventBuilder);
        } catch (IOException e) {
            sseEmitter.complete();
        }
    }

    public void sendComment(String uuid) {
        Set<SseEmitter> sseEmitters = container.getOrDefault(uuid, new HashSet<>());

        final SseEmitter.SseEventBuilder sseEventBuilder = SseEmitter.event()
                .name("Matched")
                .data("매치 되었습니다.")
                .reconnectTime(3000L);

        sseEmitters.forEach(sseEmitter -> sendEvent(sseEmitter, sseEventBuilder));
    }


    public int mbtiScore(long myMbtiId, long yourMbtiId) {
        if (myMbtiId == 0L || yourMbtiId == 0L) return 0;
        String myMbtiName = toMbtiName((int) myMbtiId);
        String yourMbtiName = toMbtiName((int) yourMbtiId);
        int score = 0;

        switch (myMbtiName) {
            case "INFP":
                if (yourMbtiName.equals("ENFJ") || yourMbtiName.equals("ENTJ")) score = 20;
                else if (yourMbtiName.equals("INFP") || yourMbtiName.equals("ENFP") || yourMbtiName.equals("INFJ") || yourMbtiName.equals("INTJ") ||
                        yourMbtiName.equals("INTP") || yourMbtiName.equals("ENTP")) score = 16;
                else score = 4;
                break;
            case "ENFP":
                if (yourMbtiName.equals("ENFJ") || yourMbtiName.equals("ENTJ")) score = 20;
                else if (yourMbtiName.equals("INFP") || yourMbtiName.equals("ENFP") || yourMbtiName.equals("INFJ") || yourMbtiName.equals("INTJ") ||
                        yourMbtiName.equals("INTP") || yourMbtiName.equals("ENTP")) score = 16;
                else score = 4;
                break;
            case "INFJ":
                if (yourMbtiName.equals("ENFP") || yourMbtiName.equals("ENTP")) score = 20;
                else if (yourMbtiName.equals("INFP") || yourMbtiName.equals("INFJ") || yourMbtiName.equals("ENFJ") || yourMbtiName.equals("INTJ") ||
                        yourMbtiName.equals("ENTJ") || yourMbtiName.equals("INTP")) score = 16;
                else score = 4;
                break;
            case "ENFJ":
                if (yourMbtiName.equals("INFP") || yourMbtiName.equals("ISFP")) score = 20;
                else if (yourMbtiName.equals("ENFP") || yourMbtiName.equals("INFJ") || yourMbtiName.equals("ENFJ") || yourMbtiName.equals("INTJ") ||
                        yourMbtiName.equals("ENTJ") || yourMbtiName.equals("INTP") || yourMbtiName.equals("ENTP"))
                    score = 16;
                else score = 4;
                break;
            case "INTJ":
                if (yourMbtiName.equals("ENFP") || yourMbtiName.equals("ENTP")) score = 20;
                else if (yourMbtiName.equals("INFP") || yourMbtiName.equals("INFJ") || yourMbtiName.equals("ENFJ") || yourMbtiName.equals("INTJ") ||
                        yourMbtiName.equals("ENTJ") || yourMbtiName.equals("INTP") || yourMbtiName.equals("ENTP"))
                    score = 16;
                else if (yourMbtiName.equals("ISFP") || yourMbtiName.equals("ESFP") || yourMbtiName.equals("ISTP") || yourMbtiName.equals("ESTP"))
                    score = 12;
                else score = 8;
                break;
            case "ENTJ":
                if (yourMbtiName.equals("INFP") || yourMbtiName.equals("INTP")) score = 20;
                else if (yourMbtiName.equals("ENFP") || yourMbtiName.equals("INFJ") || yourMbtiName.equals("ENFJ") || yourMbtiName.equals("INTJ") ||
                        yourMbtiName.equals("ENTJ") || yourMbtiName.equals("ENTP")) score = 16;
                else score = 12;
                break;
            case "INTP":
                if (yourMbtiName.equals("ENTJ") || yourMbtiName.equals("ESTJ")) score = 20;
                else if (yourMbtiName.equals("INFP") || yourMbtiName.equals("ENFP") || yourMbtiName.equals("INFJ") || yourMbtiName.equals("ENFJ") ||
                        yourMbtiName.equals("INTJ") || yourMbtiName.equals("INTP") || yourMbtiName.equals("ENTP"))
                    score = 16;
                else if (yourMbtiName.equals("ISFP") || yourMbtiName.equals("ESFP") || yourMbtiName.equals("ISTP") || yourMbtiName.equals("ESTP"))
                    score = 12;
                else score = 8;
                break;
            case "ENTP":
                if (yourMbtiName.equals("INFJ") || yourMbtiName.equals("INTJ")) score = 20;
                else if (yourMbtiName.equals("INFP") || yourMbtiName.equals("ENFP") || yourMbtiName.equals("ENFJ") || yourMbtiName.equals("ENTJ") ||
                        yourMbtiName.equals("INTP") || yourMbtiName.equals("ENTP")) score = 16;
                else if (yourMbtiName.equals("ISFP") || yourMbtiName.equals("ESFP") || yourMbtiName.equals("ISTP") || yourMbtiName.equals("ESTP"))
                    score = 12;
                else score = 8;
                break;
            case "ISFP":
                if (yourMbtiName.equals("ENFJ") || yourMbtiName.equals("ESFJ") || yourMbtiName.equals("ESTJ"))
                    score = 20;
                else if (yourMbtiName.equals("INTJ") || yourMbtiName.equals("ENTJ") || yourMbtiName.equals("INTP") || yourMbtiName.equals("ENTP") ||
                        yourMbtiName.equals("ISFJ") || yourMbtiName.equals("ISTJ")) score = 12;
                else if (yourMbtiName.equals("ISFP") || yourMbtiName.equals("ESFP") || yourMbtiName.equals("ISTP") || yourMbtiName.equals("ESTP"))
                    score = 8;
                else score = 4;
                break;
            case "ESFP":
                if (yourMbtiName.equals("ISFJ") || yourMbtiName.equals("ISTJ")) score = 20;
                else if (yourMbtiName.equals("INTJ") || yourMbtiName.equals("ENTJ") || yourMbtiName.equals("INTP") || yourMbtiName.equals("ENTP") ||
                        yourMbtiName.equals("ESFJ") || yourMbtiName.equals("ESTJ")) score = 12;
                else if (yourMbtiName.equals("ISFP") || yourMbtiName.equals("ESFP") || yourMbtiName.equals("ISTP") || yourMbtiName.equals("ESTP"))
                    score = 8;
                else score = 4;
                break;
            case "ISTP":
                if (yourMbtiName.equals("ESFJ") || yourMbtiName.equals("ESTJ")) score = 20;
                else if (yourMbtiName.equals("INTJ") || yourMbtiName.equals("ENTJ") || yourMbtiName.equals("INTP") || yourMbtiName.equals("ENTP") ||
                        yourMbtiName.equals("ISFJ") || yourMbtiName.equals("ISTJ")) score = 12;
                else if (yourMbtiName.equals("ISFP") || yourMbtiName.equals("ESFP") || yourMbtiName.equals("ISTP") || yourMbtiName.equals("ESTP"))
                    score = 8;
                else score = 4;
                break;
            case "ESTP":
                if (yourMbtiName.equals("ISFJ") || yourMbtiName.equals("ISTJ")) score = 20;
                else if (yourMbtiName.equals("INTJ") || yourMbtiName.equals("ENTJ") || yourMbtiName.equals("INTP") || yourMbtiName.equals("ENTP") ||
                        yourMbtiName.equals("ESFJ") || yourMbtiName.equals("ESTJ")) score = 12;
                else if (yourMbtiName.equals("ISFP") || yourMbtiName.equals("ESFP") || yourMbtiName.equals("ISTP") || yourMbtiName.equals("ESTP"))
                    score = 8;
                else score = 4;
                break;
            case "ISFJ":
                if (yourMbtiName.equals("ESFP") || yourMbtiName.equals("ESTP")) score = 20;
                else if (yourMbtiName.equals("ISFJ") || yourMbtiName.equals("ESFJ") || yourMbtiName.equals("ISTJ") || yourMbtiName.equals("ESTJ"))
                    score = 16;
                else if (yourMbtiName.equals("ENTJ") || yourMbtiName.equals("ISFP") || yourMbtiName.equals("ISTP"))
                    score = 12;
                else if (yourMbtiName.equals("INTJ") || yourMbtiName.equals("INTP") || yourMbtiName.equals("ENTP"))
                    score = 8;
                else score = 4;
                break;
            case "ESFJ":
                if (yourMbtiName.equals("ISFP") || yourMbtiName.equals("ISTP")) score = 20;
                else if (yourMbtiName.equals("ISFJ") || yourMbtiName.equals("ESFJ") || yourMbtiName.equals("ISTJ") || yourMbtiName.equals("ESTJ"))
                    score = 16;
                else if (yourMbtiName.equals("ENTJ") || yourMbtiName.equals("ESFP") || yourMbtiName.equals("ESTP"))
                    score = 12;
                else if (yourMbtiName.equals("INTJ") || yourMbtiName.equals("INTP") || yourMbtiName.equals("ENTP"))
                    score = 8;
                else score = 4;
                break;
            case "ISTJ":
                if (yourMbtiName.equals("ESFP") || yourMbtiName.equals("ESTP")) score = 20;
                else if (yourMbtiName.equals("ISFJ") || yourMbtiName.equals("ESFJ") || yourMbtiName.equals("ISTJ") || yourMbtiName.equals("ESTJ"))
                    score = 16;
                else if (yourMbtiName.equals("ENTJ") || yourMbtiName.equals("ISFP") || yourMbtiName.equals("ISTP"))
                    score = 12;
                else if (yourMbtiName.equals("INTJ") || yourMbtiName.equals("INTP") || yourMbtiName.equals("ENTP"))
                    score = 8;
                else score = 4;
                break;
            case "ESTJ":
                if (yourMbtiName.equals("ISFP") || yourMbtiName.equals("ISTP") || yourMbtiName.equals("INTP"))
                    score = 20;
                else if (yourMbtiName.equals("ISFJ") || yourMbtiName.equals("ESFJ") || yourMbtiName.equals("ISTJ") || yourMbtiName.equals("ESTJ"))
                    score = 16;
                else if (yourMbtiName.equals("ENTJ") || yourMbtiName.equals("ESFP") || yourMbtiName.equals("ESTP"))
                    score = 12;
                else if (yourMbtiName.equals("INTJ") || yourMbtiName.equals("ENTP")) score += 8;
                else score = 4;
                break;
        }
        return score;
    }

    private String toMbtiName(int mbtiID) {
        return switch (mbtiID) {
            case 1 -> "ISTJ";
            case 2 -> "ISFJ";
            case 3 -> "INFJ";
            case 4 -> "INTJ";
            case 5 -> "ISTP";
            case 6 -> "ISFP";
            case 7 -> "INFP";
            case 8 -> "INTP";
            case 9 -> "ESTP";
            case 10 -> "ESFP";
            case 11 -> "ENFP";
            case 12 -> "ENTP";
            case 13 -> "ESTJ";
            case 14 -> "ESFJ";
            case 15 -> "ENFJ";
            case 16 -> "ENTJ";
            default -> null;
        };
    }
}