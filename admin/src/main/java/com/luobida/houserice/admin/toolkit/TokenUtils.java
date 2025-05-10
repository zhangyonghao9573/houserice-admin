package com.luobida.houserice.admin.toolkit;

import cn.hutool.Hutool;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;

/**
 * token工具类
 */
public class TokenUtils {
    private static final String SECRET = "houserice";
    private static final String PREFIX = "bareer ";
    /**
     * 生成token
     */
    public static String generateToken(String payload) {
        return PREFIX + JWT.create()
                .setPayload("user", payload+System.currentTimeMillis())
                .setKey(SECRET.getBytes())
                .sign();
    }
}
