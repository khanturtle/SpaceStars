package com.spacestar.back.quickmatching.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;
import com.spacestar.back.quickmatching.domain.QuickMatchStatus;
import com.spacestar.back.quickmatching.domain.QuickMatching;
import com.spacestar.back.quickmatching.dto.QuickMatchingEnterReqDto;
import com.spacestar.back.quickmatching.dto.QuickMatchingResDto;
import com.spacestar.back.quickmatching.repository.QuickMatchingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class QuickMatchingServiceImpl implements QuickMatchingService {

    private final RedisTemplate<String, String> redisTemplate;
    private final QuickMatchingRepository quickMatchingRepository;
    private final ObjectMapper objectMapper;
    //SSE
    private final HashMap<String, Set<SseEmitter>> container = new HashMap<>();
    @Override
    public void enterQuickMatching(String uuid, QuickMatchingEnterReqDto reqDto) {
        redisTemplate.opsForZSet().add(reqDto.getGameName(), uuid, System.currentTimeMillis());
        doQuickMatch(reqDto.getGameName(), uuid);
    }

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
                if (score > maxScore && score >= 50) {
                    maxScore = score;
                    matchedMemberUuid = matchMemberUuid;
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
    private int calculateScore(String matchFromMember, String matchToMember) {
        //todo kafka 되면 유저 정보 토대로 점수 계산
        return 50;
    }

    //수락 대기 큐 진입
    public void enterMatchQueue(String matchFromMember, String matchToMember) {
        QuickMatching quickMatching = QuickMatching.builder()
                .id(matchFromMember + matchToMember)
                .matchFromMember(matchFromMember)
                .matchToMember(matchToMember)
                .matchFromMemberStatus(QuickMatchStatus.WAIT)
                .matchToMemberStatus(QuickMatchStatus.WAIT)
                .build();
        quickMatchingRepository.save(quickMatching);
    }


    @Override
    public SseEmitter connect(QuickMatchingEnterReqDto reqDto,String uuid) {
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
        acceptMatchingStatus(uuid,quickMatchingMembers);

    }
    private void acceptMatchingStatus(String uuid, Set<ZSetOperations.TypedTuple<String>> quickMatchingMembers) {
        for (ZSetOperations.TypedTuple<String> tuple : quickMatchingMembers) {
            try {
                Map<String, Object> data = objectMapper.readValue(tuple.getValue(), Map.class);

                if (uuid.equals(data.get("matchFromMember"))) {
                    data.put("matchFromMemberStatus", "ACCEPTED");
                    String updatedValue = objectMapper.writeValueAsString(data);
                    redisTemplate.opsForZSet().remove("QuickMatching", tuple.getValue());
                    redisTemplate.opsForZSet().add("QuickMatching", updatedValue, tuple.getScore());
                } else if (uuid.equals(data.get("matchToMember"))) {
                    data.put("matchToMemberStatus", "ACCEPTED");
                    String updatedValue = objectMapper.writeValueAsString(data);
                    redisTemplate.opsForZSet().remove("QuickMatching", tuple.getValue());
                    redisTemplate.opsForZSet().add("QuickMatching", updatedValue, tuple.getScore());
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
        rejectMatchingStatus(uuid,quickMatchingMembers);
    }
    private void rejectMatchingStatus(String uuid, Set<ZSetOperations.TypedTuple<String>> quickMatchingMembers) {
        for (ZSetOperations.TypedTuple<String> tuple : quickMatchingMembers) {
            try {
                Map<String, Object> data = objectMapper.readValue(tuple.getValue(), Map.class);

                if (uuid.equals(data.get("matchFromMember"))) {
                    data.put("matchFromMemberStatus", "REJECT");
                    String updatedValue = objectMapper.writeValueAsString(data);
                    redisTemplate.opsForZSet().remove("QuickMatching", tuple.getValue());
                    redisTemplate.opsForZSet().add("QuickMatching", updatedValue, tuple.getScore());
                } else if (uuid.equals(data.get("matchToMember"))) {
                    data.put("matchToMemberStatus", "REJECT");
                    String updatedValue = objectMapper.writeValueAsString(data);
                    redisTemplate.opsForZSet().remove("QuickMatching", tuple.getValue());
                    redisTemplate.opsForZSet().add("QuickMatching", updatedValue, tuple.getScore());
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
                redisTemplate.opsForZSet().add(reqDto.getGameName(), (String) data.get("matchFromMember"), System.currentTimeMillis()-10000);
                redisTemplate.opsForZSet().add(reqDto.getGameName(), (String) data.get("matchToMember"), System.currentTimeMillis()-10000);
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
}