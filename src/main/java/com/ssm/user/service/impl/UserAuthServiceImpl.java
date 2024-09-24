package com.ssm.user.service.impl;

import com.ssm.common.exception.AuthFailureException;
import com.ssm.common.global.Result;
import com.ssm.common.util.JwtTokenProvider;
import com.ssm.common.util.RedisUtils;
import com.ssm.user.service.UserAuthService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    private RedisUtils redisUtils;

    private JwtTokenProvider jwtTokenProvider;
    
    private AuthenticationManager authenticationManager;

    @Autowired
    public void setRedisUtils(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Autowired
    public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Result login(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtTokenProvider.generateToken(authentication);
            
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("authType", "Bearer");
            jsonObject.put("accessToken", jwtToken);

            String redisKey = "auth:token:" + username;
            redisUtils.set(redisKey, jsonObject.toString(), 1800);
            
            return Result.success(jsonObject, "登录成功");
            
        } catch (AuthenticationException e) {
            throw new AuthFailureException("用户名或密码错误");
        }
    }

    @Override
    public Result logout(String token) {
        // 从 Token 中提取用户名
        String username = jwtTokenProvider.getUsername(token);

        // 删除 Redis 中的 Refresh Token
        redisUtils.delete("auth:token:" + username);

        // 将 JWT Token 加入黑名单
        long expiration = jwtTokenProvider.getExpirationFromToken(token);
        jwtTokenProvider.addToBlacklist(token, expiration);
        
        redisUtils.delete("user:info:" + username);
        
        return Result.success("登出成功");
    }

    @Override
    public Result refreshToken(String token) {
        String username = jwtTokenProvider.getUsername(token);
        String newToken = redisUtils.get("auth:token:" + username);
        return Result.success(newToken, "Token refresh success");
    }
}
