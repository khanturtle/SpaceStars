package com.spacestar.back.quickmatching.service;

import com.spacestar.back.quickmatching.dto.QuickMatchingEnterReqDto;
import com.spacestar.back.quickmatching.dto.WebSocketInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class QuickMatchingServiceImpl implements QuickMatchingService{

    private final RedisTemplate<String,String> redisTemplate;
    private final Map<String, WebSocketInfo> socketInfos = new ConcurrentHashMap<>();  // 소켓 연결 정보

    @Override
    public void enterQuickMatching(String uuid, String gameName) {
        redisTemplate.opsForZSet().add(gameName, uuid,System.currentTimeMillis());
    }

    @Override
    public void connectSocket(WebSocketSession socketSession) {
        socketInfos.put(socketSession.getId(), new WebSocketInfo(socketSession, 0));
    }
}
