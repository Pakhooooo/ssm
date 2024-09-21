package com.ssm.user.service.impl;

import com.ssm.common.global.Result;
import com.ssm.common.util.JwtTokenProvider;
import com.ssm.common.util.RedisUtils;
import com.ssm.user.service.UserLoginService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private RedisUtils redisUtils;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    public void setRedisUtils(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Override
    public Result login(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtTokenProvider.generateToken(authentication);
            
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", jwtToken);

            String redisKey = "auth:token:" + username;
            redisUtils.set(redisKey, jsonObject.toString(), 600);
            
            return Result.success(jsonObject, "登录成功");
            
        } catch (AuthenticationException e) {
            return Result.error("用户名或密码错误", HttpStatus.UNAUTHORIZED.value());
        }
    }
}
