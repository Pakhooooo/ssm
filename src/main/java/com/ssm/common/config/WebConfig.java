package com.ssm.common.config;

import com.ssm.common.interceptor.JwtTokenInterceptor;
import com.ssm.common.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final UserInterceptor userInterceptor;
    private final JwtTokenInterceptor jwtTokenInterceptor;

    @Autowired
    public WebConfig(UserInterceptor userInterceptor, JwtTokenInterceptor jwtTokenInterceptor) {
        this.userInterceptor = userInterceptor;
        this.jwtTokenInterceptor = jwtTokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor).addPathPatterns("/user/**");
        
        // 配置拦截器应用排除更新 JWT Token 请求
        registry.addInterceptor(jwtTokenInterceptor).excludePathPatterns("/auth/**", "/swagger-ui/**", "/v3/api-docs/**", "/user/logout");
    }
    
}
