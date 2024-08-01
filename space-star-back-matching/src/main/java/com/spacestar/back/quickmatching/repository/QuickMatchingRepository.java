package com.spacestar.back.quickmatching.repository;

import com.spacestar.back.quickmatching.domain.QuickMatching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QuickMatchingRepository {
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String KEY = "QuickMatching";

    public QuickMatchingRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(QuickMatching quickMatching) {
        redisTemplate.opsForZSet().add(KEY,  quickMatching,System.currentTimeMillis());
    }
}