package com.ssm.common.service;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenCacheService {

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // 缓存解析后的 Token 信息
    public void cacheToken(String token, Claims claims, long expirationMillis) {
        String cacheKey = "auth:token:" + token;
        redisTemplate.opsForValue().set(cacheKey, claims, expirationMillis, TimeUnit.MILLISECONDS);
    }

    // 获取缓存的 Token 信息
    public Claims getCachedToken(String token) {
        String cacheKey = "auth:token:" + token;
        return (Claims) redisTemplate.opsForValue().get(cacheKey);
    }
    
}
