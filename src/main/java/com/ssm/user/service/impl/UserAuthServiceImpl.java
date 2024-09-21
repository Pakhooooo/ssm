package com.ssm.user.service.impl;

import com.ssm.common.global.Result;
import com.ssm.common.util.JwtTokenProvider;
import com.ssm.common.util.RedisUtils;
import com.ssm.user.service.UserAuthService;
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
            jsonObject.put("token", jwtToken);

            String redisKey = "auth:token:" + username;
            redisUtils.set(redisKey, jsonObject.toString(), 600);
            
            return Result.success(jsonObject, "登录成功");
            
        } catch (AuthenticationException e) {
            return Result.error("用户名或密码错误", HttpStatus.UNAUTHORIZED.value());
        }
    }

    @Override
    public Result logout(String token) {
        // 检查 Token 是否为空或已被列入黑名单
        if (token.isEmpty() || jwtTokenProvider.isTokenBlacklisted(token)) {
            return Result.error("Token 已失效或无效", HttpStatus.UNAUTHORIZED.value());
        }

        // 从 Token 中提取用户名
        String username = jwtTokenProvider.getUsername(token);

        // 删除 Redis 中的 Refresh Token
        redisUtils.delete("auth:token:" + username);

        // 将 JWT Token 加入黑名单
        long expiration = jwtTokenProvider.getExpirationFromToken(token); // 获取 Token 剩余有效期
        jwtTokenProvider.addToBlacklist(token, expiration);
        
        return Result.success("登出成功");
    }
}
