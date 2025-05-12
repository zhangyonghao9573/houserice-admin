package com.luobida.houserice.admin.common.cache.DistributedCache;

import com.alibaba.fastjson2.JSON;
import com.luobida.houserice.admin.common.cache.RedisDistributedProperties;
import com.luobida.houserice.admin.toolkit.FastJson2Util;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangyonghao
 * redis模版代理类
 */
@RequiredArgsConstructor
@Component
public class StringRedisTemplateProxy implements DistributedCache{

    private final StringRedisTemplate stringRedisTemplate;
    private final RedisDistributedProperties redisProperties;
    @Override
    public <T> T get(@NonNull String key, Class<T> clazz) {
        String value = stringRedisTemplate.opsForValue().get(key);
        if (String.class.isAssignableFrom(clazz)) {
            return (T) value;
        }
        return JSON.parseObject(value, FastJson2Util.buildType(clazz));
    }

    @Override
    public void put(@NonNull String key, Object value) {
        put(key, value, redisProperties.getValueTimeout());
    }

    public void put(String key, Object value, long timeout) {
        put(key, value, timeout, redisProperties.getValueTimeUnit());
    }

    public void put(String key, Object value, long timeout, TimeUnit timeUnit) {
        String actual = value instanceof String ? (String) value : JSON.toJSONString(value);
        stringRedisTemplate.opsForValue().set(key, actual, timeout, timeUnit);
    }

    @Override
    public Boolean delete(@NonNull String key) {
        return stringRedisTemplate.delete(key);
    }

    @Override
    public Boolean hasKey(@NonNull String key) {
        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public Object getInstance() {
        return stringRedisTemplate;
    }
}
