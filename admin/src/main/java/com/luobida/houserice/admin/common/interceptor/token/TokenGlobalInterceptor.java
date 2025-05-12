package com.luobida.houserice.admin.common.interceptor.token;

import com.alibaba.fastjson2.JSON;
import com.luobida.houserice.admin.common.config.BlanklistProperties;
import com.luobida.houserice.admin.common.user.UserInfoDTO;
import com.luobida.houserice.admin.toolkit.FastJson2Util;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

/**
 * @author zhangyonghao
 * token全局拦截器，拦截白名单外的请求
 */
@Data
@Component
public class TokenGlobalInterceptor implements HandlerInterceptor {
    private final BlanklistProperties blanklistProperties;
    private final StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String acceessToken = request.getHeader("auth");
        if (!StringUtils.hasLength(acceessToken) && !isBlankUrl(request)) {
            return false;
        }
        if (StringUtils.hasLength(acceessToken)) {
            String TokenStr = redisTemplate.opsForValue().get(acceessToken);
            UserInfoDTO userInfoDTO = JSON.parseObject(TokenStr, FastJson2Util.buildType(UserInfoDTO.class));
            userInfoDTO.setAccessToken(acceessToken);

        }
        return true;
    }

    /**
     * 判断请求是否是白名单请求
     * @return
     */
    private boolean isBlankUrl(HttpServletRequest request) {
        for (String str : blanklistProperties.getBlanklist()) {
            if (str.equals(request.getRequestURI())&&request.getMethod().equals("POST")){
                return true;
            }
        }
        return false;
    }
}
