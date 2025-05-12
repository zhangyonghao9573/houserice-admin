package com.luobida.houserice.admin.common.cache.DistributedCache;

import com.alibaba.fastjson2.JSON;
import com.luobida.houserice.admin.common.cache.ICache;
import com.luobida.houserice.admin.toolkit.FastJson2Util;
import lombok.NonNull;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangyonghao
 * 抽象分布式缓存接口
 */
public interface DistributedCache extends ICache {
    public <T> T get(@NonNull String key, Class<T> clazz);

    public void put(@NonNull String key, Object value);

    public void put(String key, Object value, long timeout);

    public void put(String key, Object value, long timeout, TimeUnit timeUnit);

    public Boolean delete(@NonNull String key);

    public Boolean hasKey(@NonNull String key);

    public Object getInstance();
}
