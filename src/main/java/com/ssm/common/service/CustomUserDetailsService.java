package com.ssm.common.service;

import com.ssm.common.util.RedisUtils;
import com.ssm.user.po.User;
import com.ssm.user.po.UserRole;
import com.ssm.user.mapper.UserAuthMapper;
import com.ssm.user.mapper.UserRoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        String cacheKey = "user:info:" + userName;
        String userJson = redisUtils.get(cacheKey);

        JSONObject userObject;
        if (StringUtils.isEmpty(userJson)) {
            userObject = loadUserFromDatabase(userName);
            // 缓存用户数据到 Redis（设置过期时间）
            redisUtils.set(cacheKey, userObject.toString(), 1800);
        } else {
            userObject = new JSONObject(userJson);
        }

        return createSpringSecurityUser(userObject);
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

        List<GrantedAuthority> authorities = new ArrayList<>();
        JSONArray rolesArray = userObject.optJSONArray("roles");

        for (int i = 0; i < rolesArray.length(); i++) {
            JSONObject roleObject = rolesArray.optJSONObject(i);
            // 获取 roleName 并创建 SimpleGrantedAuthority 对象
            String roleName = roleObject.optString("roleName");
            authorities.add(new SimpleGrantedAuthority(roleName));
        }

        return new org.springframework.security.core.userdetails.User(username, password, authorities);
    }
}
