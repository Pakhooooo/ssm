package com.ssm.user.service.impl;

import com.ssm.common.exception.AuthFailureException;
import com.ssm.common.global.SecurityUser;
import com.ssm.common.util.JwtTokenProvider;
import com.ssm.common.util.RedisUtils;
import com.ssm.user.mapper.UserInfoMapper;
import com.ssm.user.service.UserInfoService;
import com.ssm.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    
    private final RedisUtils redisUtils;
    
    private final UserInfoMapper userInfoMapper;
    
    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;
    
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserInfoServiceImpl(RedisUtils redisUtils, UserInfoMapper userInfoMapper, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.redisUtils = redisUtils;
        this.userInfoMapper = userInfoMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserVO getUserInfoById(int userId) {
        return userInfoMapper.getUserInfoById(userId);
    }

    @Override
    public List<UserVO> getUserList() {
        return userInfoMapper.getUserList();
    }

    @Override
    public void deleteUserInfoById(int userId) {
        userInfoMapper.deleteUserInfoById(userId);
    }

    @Override
    public void updatePassword(String token, String oldPassword, String newPassword) {
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(securityUser.getUsername(), oldPassword));
        } catch (AuthenticationException e) {
            throw new AuthFailureException("旧密码错误");
        }
        
        userInfoMapper.updateUserPasswordById(securityUser.getId(), passwordEncoder.encode(newPassword));
        
        // 将 JWT Token 加入黑名单
        long expiration = jwtTokenProvider.getExpirationFromToken(token);
        jwtTokenProvider.addToBlacklist(token, expiration);
        
        redisUtils.delete("user:info:" + securityUser.getUsername());
        redisUtils.delete("auth:token:" + securityUser.getUsername());
    }
}
