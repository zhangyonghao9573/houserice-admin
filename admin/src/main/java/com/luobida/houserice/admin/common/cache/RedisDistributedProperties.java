package com.luobida.houserice.admin.common.cache;

import lombok.Data;

import java.util.concurrent.TimeUnit;
@Data
public class RedisDistributedProperties {
    public static final String PREFIX = "framework.cache.redis";

    /**
     * Key 前缀
     */
    private String prefix = "";

    /**
     * Key 前缀字符集
     */
    private String prefixCharset = "UTF-8";

    /**
     * 默认超时时间
     */
    private Long valueTimeout = 30000L;

    /**
     * 时间单位
     */
    private TimeUnit valueTimeUnit = TimeUnit.MILLISECONDS;
}
