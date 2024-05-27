package com.spacestar.back.quickmatching.service;

import com.spacestar.back.quickmatching.dto.QuickMatchingEnterReqDto;
import com.spacestar.back.quickmatching.vo.QuickMatchingEnterReqVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class QuickMatchingServiceImpl implements QuickMatchingService{
    private final RedisTemplate<String,String> redisTemplate;
    @Override
    public void enterQuickMatching(String uuid, QuickMatchingEnterReqDto reqDto) {
        String gameName = reqDto.getGameName();
        redisTemplate.opsForZSet().add(gameName, uuid,System.currentTimeMillis());
        Long queueMemberCount = redisTemplate.opsForZSet().zCard(gameName);

        if (queueMemberCount >= 5) {

            List<String> userListInRedis = new ArrayList<>(Objects.requireNonNull(redisTemplate.opsForZSet().range(gameName, 0, queueMemberCount)));

            //todo 가장 먼저 큐에 들어온 인원을 매칭->성향에 따라 매칭 하도록 개선
            String firstMemberUuid = userListInRedis.get(0); // 첫 번째 멤버의 UUID
            String secondMemberUuid = userListInRedis.get(1);

            deleteRedisMatchLists(userListInRedis, reqDto.getGameName());

            return;
        }
    }

    private void deleteRedisMatchLists(List<String> queueMembers,String redisKey){
        for(String queMember:queueMembers){
            redisTemplate.opsForZSet().remove(redisKey,queMember);
        }
    }
}
