package com.ssm.common.service;

import com.ssm.common.util.RedisUtils;
import com.ssm.user.entity.User;
import com.ssm.user.entity.UserRole;
import com.ssm.user.mapper.UserAuthMapper;
import com.ssm.user.mapper.UserRoleMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final RedisUtils redisUtils;
    private final UserAuthMapper userAuthMapper;
    private final UserRoleMapper userRoleMapper;

    @Autowired
    public CustomUserDetailsService(RedisUtils redisUtils, UserAuthMapper userAuthMapper, UserRoleMapper userRoleMapper) {
        this.redisUtils = redisUtils;
        this.userAuthMapper = userAuthMapper;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 尝试从 Redis 获取用户信息
        String cacheKey = getCacheKey(userName);
        JSONObject userObject = (JSONObject) redisUtils.get(cacheKey);

        if (userObject == null) {
            userObject = loadUserFromDatabase(userName);
            // 缓存用户数据到 Redis（设置过期时间）
            redisUtils.set(cacheKey, userObject.toString(), 1800);
        }

        return createSpringSecurityUser(userObject);
    }

    private String getCacheKey(String userName) {
        return "user:info:" + userName;
    }

    private JSONObject loadUserFromDatabase(String userName) {
        User user = userAuthMapper.findUserByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("该账号不存在: " + userName);
        }

        JSONObject userObject = new JSONObject(user);
        List<UserRole> userRoles = userRoleMapper.getUserRoleByUserId(user.getId());
        JSONArray rolesArray = new JSONArray(userRoles);
        userObject.put("roles", rolesArray);

        return userObject;
    }

    private UserDetails createSpringSecurityUser(JSONObject userObject) {
        String username = userObject.get("userName").toString();
        String password = userObject.get("password").toString();

        // 解析 roles 为 GrantedAuthority 列表
        List<GrantedAuthority> authorities = userObject.optJSONArray("roles")
                .toList()
                .stream()
                .map(role -> new SimpleGrantedAuthority(((JSONObject) role).getString("roleName")))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(username, password, authorities);
    }
}
