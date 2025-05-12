package com.luobida.houserice.admin.config;

import com.luobida.houserice.admin.common.interceptor.token.TokenGlobalInterceptor;
import com.luobida.houserice.admin.common.interceptor.token.TokenRefreshInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhangyonghao
 * 拦截器配置类
 */
@Configuration
@AllArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final TokenRefreshInterceptor tokenRefreshInterceptor;
    private final TokenGlobalInterceptor tokenGlobalInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenRefreshInterceptor)
                .addPathPatterns("/**")
                .order(10);

        registry.addInterceptor(tokenGlobalInterceptor)
                .addPathPatterns("/**")
                .order(20);
    }
}
