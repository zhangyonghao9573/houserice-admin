package com.luobida.houserice.admin.common.cache;

import lombok.NonNull;

/**
 * @author zhangyonghao
 * 抽象缓存
 */
public interface ICache {

    /**
     * 获取缓存
     */
    <T> T get(@NonNull String key, Class<T> clazz);

    /**
     * 放入缓存
     */
    void put(@NonNull String key, Object value);

    /**
     * 删除缓存
     */
    Boolean delete(@NonNull String key);

    /**
     * 判断 key 是否存在
     */
    Boolean hasKey(@NonNull String key);

    /**
     * 获取缓存组件实例
     */
    Object getInstance();
}
