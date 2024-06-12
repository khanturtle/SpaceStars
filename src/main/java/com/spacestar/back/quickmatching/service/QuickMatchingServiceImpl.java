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

    @Override
    public void enterQuickMatching(String uuid, QuickMatchingEnterReqDto reqDto) {
        redisTemplate.opsForZSet().add(reqDto.getGameName(), uuid, System.currentTimeMillis());
        doQuickMatch(reqDto.getGameName(), uuid);
    }

    public void doQuickMatch(String gameName, String uuid) {
        Set<ZSetOperations.TypedTuple<String>> gameData = redisTemplate.opsForZSet().rangeWithScores(gameName, 0, -1);
        int maxScore = 0;
        int score = 0;
        String matchedMemberUuid = null;
        assert gameData != null;
        //대기중인 사용자가 2명 이상일 경우 실행
        if (gameData.size() >= 2) {
            for (ZSetOperations.TypedTuple<String> tuple : gameData) {
                String matchMemberUuid = tuple.getValue();
                score = calculateScore(uuid, matchMemberUuid);
                if (score > maxScore && score >= 50) {
                    maxScore = score;
                    matchedMemberUuid = matchMemberUuid;
                }
            }
        }
        if (matchedMemberUuid != null) {
            enterMatchQueue(uuid, matchedMemberUuid);
            sendComment(gameName);
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

    //SSE
    private final HashMap<String, Set<SseEmitter>> container = new HashMap<>();

    @Override
    public SseEmitter connect(final String articleId) {
        SseEmitter sseEmitter = new SseEmitter(300_000L);

        final SseEmitter.SseEventBuilder sseEventBuilder = SseEmitter.event()
                .name("connect")
                .data("connected!")
                .reconnectTime(3000L);

        sendEvent(sseEmitter, sseEventBuilder);

        Set<SseEmitter> sseEmitters = container.getOrDefault(articleId, new HashSet<>());
        sseEmitters.add(sseEmitter);
        container.put(articleId, sseEmitters);
        sseEmitter.onCompletion(() -> {
            sseEmitters.remove(sseEmitter);
        });
        return sseEmitter;
    }

    @Override
    public void acceptQuickMatch(String uuid) {
        Set<ZSetOperations.TypedTuple<String>> quickMatchingMembers = redisTemplate.opsForZSet().rangeWithScores("QuickMatching", 0, -1);
        assert quickMatchingMembers != null;

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

    @Override
    public void rejectQuickMatch(String uuid) {
        Set<ZSetOperations.TypedTuple<String>> quickMatchingMembers = redisTemplate.opsForZSet().rangeWithScores("QuickMatching", 0, -1);
        assert quickMatchingMembers != null;

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

    @Override
    public QuickMatchingResDto completeQuickMatch(String uuid, QuickMatchingEnterReqDto reqDto) {
        Set<ZSetOperations.TypedTuple<String>> quickMatchingMembers = redisTemplate.opsForZSet().rangeWithScores("QuickMatching", 0, -1);
        assert quickMatchingMembers != null;

        for (ZSetOperations.TypedTuple<String> tuple : quickMatchingMembers) {
            try {
                Map<String, Object> data = objectMapper.readValue(tuple.getValue(), Map.class);
                if (uuid.equals(data.get("matchFromMember")) || uuid.equals(data.get("matchToMember"))) {
                    if (data.get("matchFromMemberStatus").equals("ACCEPTED") && data.get("matchToMemberStatus").equals("ACCEPTED")) {
                        if (tuple.getValue() != null) {
                            redisTemplate.opsForZSet().remove("QuickMatching", tuple.getValue());
                        }
                        redisTemplate.opsForZSet().remove(reqDto.getGameName(), data.get("matchFromMember"));
                        return QuickMatchingResDto.builder().memberUuid(uuid).build();
                    } else {
                        if (tuple.getValue() != null) {
                            redisTemplate.opsForZSet().remove("QuickMatching", tuple.getValue());
                        }
                        return null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void quitQuickMatching(String uuid,QuickMatchingEnterReqDto reqDto) {
        Double score = redisTemplate.opsForZSet().score(reqDto.getGameName(), uuid);

        if (score != null) {
            redisTemplate.opsForZSet().remove(reqDto.getGameName(), uuid);
        }else throw new GlobalException(ResponseStatus.WAITING_MEMBER_NOT_EXIST);
    }


    private static void sendEvent(final SseEmitter sseEmitter,
                                  final SseEmitter.SseEventBuilder sseEventBuilder) {
        try {
            sseEmitter.send(sseEventBuilder);
        } catch (IOException e) {
            sseEmitter.complete();
        }
    }

    public void sendComment(String gameName) {
        Set<SseEmitter> sseEmitters = container.getOrDefault(gameName, new HashSet<>());

        final SseEmitter.SseEventBuilder sseEventBuilder = SseEmitter.event()
                .name("Matched")
                .data("매치 되었습니다.")
                .reconnectTime(3000L);

        sseEmitters.forEach(sseEmitter -> sendEvent(sseEmitter, sseEventBuilder));
    }
}