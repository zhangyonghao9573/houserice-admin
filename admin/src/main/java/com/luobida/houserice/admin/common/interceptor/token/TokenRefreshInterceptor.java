package com.luobida.houserice.admin.common.interceptor.token;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;
import java.util.concurrent.TimeUnit;


/**
 * @author zhangyonghao
 * token刷新拦截器，用户刷新token有效期
 */
@AllArgsConstructor
@Component
public class TokenRefreshInterceptor implements HandlerInterceptor {
    private final StringRedisTemplate redisTemplate;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String acceessToken = request.getHeader("auth");
        if (StringUtils.hasLength(acceessToken)) {
            Optional.ofNullable(redisTemplate.opsForValue().get(acceessToken)).orElseThrow(()->new RuntimeException("token无效"));
            redisTemplate.expire(acceessToken, 30L, TimeUnit.MINUTES);
        }
        return true;
    }
}
