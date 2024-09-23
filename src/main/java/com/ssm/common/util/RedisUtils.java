package com.ssm.common.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public void set(String key, String value, long duration) {
        redisTemplate.opsForValue().set(key, value, duration, TimeUnit.SECONDS);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
    
    public void expire(String key, long duration) {
        redisTemplate.expire(key, duration, TimeUnit.SECONDS);
    }
}
