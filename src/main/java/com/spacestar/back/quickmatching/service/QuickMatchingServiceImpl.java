package com.spacestar.back.quickmatching.service;

import com.spacestar.back.quickmatching.dto.QuickMatchingEnterReqDto;
import com.spacestar.back.quickmatching.dto.WebSocketInfo;
import com.spacestar.back.quickmatching.dto.WebSocketMessageDto;
import com.spacestar.back.quickmatching.vo.QuickMatchingEnterReqVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.internal.util.CopyOnWriteLinkedHashMap;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class QuickMatchingServiceImpl implements QuickMatchingService{
    private final RedisTemplate<String,String> redisTemplate;
    private final Map<String, WebSocketInfo> socketInfos = new ConcurrentHashMap<>();  // 소켓 연결 정보
    private final Map<String,String> gameQueue = new CopyOnWriteLinkedHashMap<>();  // 사용자 대기열

    @Override
    public void connectSocket(WebSocketSession socketSession) {
        socketInfos.put(socketSession.getId(), new WebSocketInfo(socketSession, 0));
    }

    @Override
    public void waitForMatching(String socketSessionId, String uuid) {
            log.info("waitForMatching");
        socketInfos.get(socketSessionId).setMemberUuid(uuid);
    }

    private void deleteRedisMatchLists(List<String> queueMembers,String redisKey){
        for(String queMember:queueMembers){
            redisTemplate.opsForZSet().remove(redisKey,queMember);
        }
    }


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
}
