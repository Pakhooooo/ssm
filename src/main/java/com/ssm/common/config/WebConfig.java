package com.ssm.common.config;

import com.ssm.common.interceptor.JwtTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final JwtTokenInterceptor jwtTokenInterceptor;

    @Autowired
    public WebConfig(JwtTokenInterceptor jwtTokenInterceptor) {
        this.jwtTokenInterceptor = jwtTokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 配置拦截器应用排除更新 JWT Token 请求
        registry.addInterceptor(jwtTokenInterceptor).excludePathPatterns("/auth/**", "/swagger-ui/**", "/v3/api-docs/**", "/user/login", "/user/**/password/change", "/user/logout");
    }
    
}
