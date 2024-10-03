package com.ssm.user.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ssm.common.exception.AuthFailureException;
import com.ssm.common.global.Result;
import com.ssm.common.global.SecurityUser;
import com.ssm.common.util.JwtTokenProvider;
import com.ssm.common.util.RedisUtils;
import com.ssm.user.mapper.UserAuthMapper;
import com.ssm.user.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    private final RedisUtils redisUtils;

    private final UserAuthMapper userAuthMapper;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserAuthServiceImpl(RedisUtils redisUtils, UserAuthMapper userAuthMapper, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.redisUtils = redisUtils;
        this.userAuthMapper = userAuthMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Result login(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtTokenProvider.generateToken(authentication);

            ObjectNode objectNode = new ObjectMapper().createObjectNode();
            objectNode.put("authType", "Bearer");
            objectNode.put("accessToken", jwtToken);

            String redisKey = "auth:token:" + username;
            redisUtils.set(redisKey, objectNode.toString(), 1800);
            
            return Result.success(objectNode, "登录成功");
            
        } catch (AuthenticationException e) {
            throw new AuthFailureException("用户名或密码错误");
        }
    }

    @Override
    public Result logout(String token) {
        // 从 Token 中提取用户名
        String username = jwtTokenProvider.getUsername(token);

        redisUtils.delete("auth:token:" + token);
        redisUtils.delete("user:info:" + username);
        redisUtils.delete("auth:token:" + username);

        // 将 JWT Token 加入黑名单
        long expiration = jwtTokenProvider.getExpirationFromToken(token);
        jwtTokenProvider.addToBlacklist(token, expiration);
        
        return Result.success("登出成功");
    }

    @Override
    public Result refreshToken(String token) {
        String username = jwtTokenProvider.getUsername(token);
        String newToken = redisUtils.get("auth:token:" + username);
        return Result.success(newToken, "Token refresh success");
    }

    @Override
    public void updatePassword(String token, String oldPassword, String newPassword) {
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(securityUser.getUsername(), oldPassword));
        } catch (AuthenticationException e) {
            throw new AuthFailureException("旧密码错误");
        }

        userAuthMapper.updateUserPasswordById(securityUser.getId(), passwordEncoder.encode(newPassword));

        // 将 JWT Token 加入黑名单
        long expiration = jwtTokenProvider.getExpirationFromToken(token);
        jwtTokenProvider.addToBlacklist(token, expiration);

        redisUtils.delete("user:info:" + securityUser.getUsername());
        redisUtils.delete("auth:token:" + securityUser.getUsername());
    }
    
}
