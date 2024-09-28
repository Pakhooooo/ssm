package com.ssm.common.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssm.common.global.SecurityUser;
import com.ssm.common.util.RedisUtils;
import com.ssm.user.mapper.UserAuthMapper;
import com.ssm.user.po.Permission;
import com.ssm.user.po.Role;
import com.ssm.user.po.User;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final RedisUtils redisUtils;
    private final UserAuthMapper userAuthMapper;

    @Autowired
    public CustomUserDetailsService(RedisUtils redisUtils, UserAuthMapper userAuthMapper) {
        this.redisUtils = redisUtils;
        this.userAuthMapper = userAuthMapper;
    }

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 尝试从 Redis 获取用户信息
        String cacheKey = "user:info:" + username;
        String userJson = redisUtils.get(cacheKey);

        User user;
        if (StringUtils.isEmpty(userJson)) {
            user = loadUserFromDatabase(username);
            // 缓存用户数据到 Redis
            redisUtils.set(cacheKey, new JSONObject(user).toString(), 1800);
        } else {
            user = new ObjectMapper().readValue(userJson, User.class);
        }

        // 加载用户角色和权限
        Set<GrantedAuthority> authorities = new HashSet<>();

        // 加载用户的角色
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));

            // 加载每个角色的权限
            for (Permission permission : role.getPermissions()) {
                authorities.add(new SimpleGrantedAuthority(permission.getPermissionKey()));
            }
        }

        // 返回 Spring Security 所需的 UserDetails
        return new SecurityUser(
                user.getUsername(),
                user.getPassword(),
                authorities,
                user.getId());
    }

    private User loadUserFromDatabase(String username) {
        User user = userAuthMapper.findUserWithRolesAndPermissions(username);
        if (user == null) {
            throw new UsernameNotFoundException("该账号不存在: " + username);
        }

        return user;
    }
}
