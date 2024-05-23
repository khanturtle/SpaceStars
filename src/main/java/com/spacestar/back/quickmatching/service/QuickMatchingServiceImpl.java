package com.spacestar.back.quickmatching.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class QuickMatchingServiceImpl implements QuickMatchingService{
    private final RedisTemplate<String,String> redisTemplate;
    @Override
    public void enterQuickMatching(String uuid) {
        String matchingQueue = "QuickMatchingQueue";
        redisTemplate.opsForZSet().add(matchingQueue, uuid,System.currentTimeMillis());
    }
}
