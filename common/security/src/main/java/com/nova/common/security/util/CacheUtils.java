package com.nova.common.security.util;

import com.nova.service.uac.api.entity.User;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class CacheUtils {
    private static final long EXPIRE_INTERVAL = 30 * 24 * 60 * 60;
    private static CacheUtils INST;

    private RedisTemplate redisTemplate;

    public CacheUtils(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        INST = this;
    }

    public static User fetchUser(String userId) {
        RedisTemplate redisTemplate = INST.redisTemplate;
        User user = (User) redisTemplate.opsForValue().get(userKeyFor(userId));
        return user;
    }

    public static void cacheUser(User user) {
        // 先删除旧的数据
        deleteUser(user.getId());
        RedisTemplate redisTemplate = INST.redisTemplate;
        redisTemplate.opsForValue().set(userKeyFor(user.getId()), user, EXPIRE_INTERVAL, TimeUnit.SECONDS);
    }

    public static void deleteUser(String userId) {
        RedisTemplate redisTemplate = INST.redisTemplate;
        redisTemplate.delete(userKeyFor(userId));
    }

    private static String userKeyFor(String userId) {
        StringBuilder sb = new StringBuilder();
        sb.append("auth:user:").append(userId);
        final String key = sb.toString();
        return key;
    }
}
