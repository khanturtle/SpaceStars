package com.spacestar.back.quickmatching.service;

import com.spacestar.back.quickmatching.domain.QuickMatchStatus;
import com.spacestar.back.quickmatching.domain.QuickMatching;
import com.spacestar.back.quickmatching.dto.QuickMatchingEnterReqDto;
import com.spacestar.back.quickmatching.repository.QuickMatchingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class QuickMatchingServiceImpl implements QuickMatchingService {

    private final RedisTemplate<String, String> redisTemplate;
    private final QuickMatchingRepository quickMatchingRepository;

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
                if (score > maxScore && score>=50) {
                    maxScore = score;
                    matchedMemberUuid = matchMemberUuid;
                }
            }
        }
        if(matchedMemberUuid!=null){
            enterMatchQueue(matchedMemberUuid,uuid);
        }
    }

    //사용자 간에 매치 점수 계산
    private int calculateScore(String matchFromMember, String matchToMember) {
        //todo kafka 되면 유저 정보 토대로 점수 계산
        return 40;
    }

    //수락 대기 큐 진입
    public void enterMatchQueue(String matchFromMember, String matchToMember) {
        QuickMatching quickMatching = QuickMatching.builder()
                .id(matchFromMember + matchToMember)
                .matchFromMember(matchFromMember)
                .matchToMember(matchToMember)
                .matchToMemberStatus(QuickMatchStatus.WAIT)
                .matchFromMemberStatus(QuickMatchStatus.WAIT)
                .build();
        quickMatchingRepository.save(quickMatching);
    }
}