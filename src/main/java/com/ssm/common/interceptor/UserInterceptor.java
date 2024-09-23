package com.ssm.common.interceptor;

import com.ssm.common.util.RedisUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.acl.NotOwnerException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserInterceptor implements HandlerInterceptor {
    
    private RedisUtils redisUtils;

    @Autowired
    public void setRedisUtils(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = extractUserIdFromUrl(request.getRequestURI());
        if (userId == null) {
            return true; // URL 中不包含用户信息，不需拦截
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("未认证的请求");
        }

        String currentUsername = authentication.getName();
        JSONObject userObject = new JSONObject(redisUtils.get("user:info:" + currentUsername));

        // 获取用户角色
        List<String> roles = getUserRoles(userObject);

        // 检查是否为管理员或访问自己的信息
        if (isAdmin(roles) || isOwner(userObject, userId)) {
            return true;
        }

        throw new NotOwnerException();
    }

    /**
     * 从请求 URL 中提取用户 ID。
     */
    private String extractUserIdFromUrl(String url) {
        String[] urlParts = url.split("/");
        for (int i = 0; i < urlParts.length; i++) {
            if ("user".equals(urlParts[i]) && i + 1 < urlParts.length) {
                return urlParts[i + 1];
            }
        }
        return null; // 未找到用户 ID
    }

    /**
     * 从 JSONObject 中提取用户角色列表。
     */
    private List<String> getUserRoles(JSONObject userObject) {
        List<String> roles = new ArrayList<>();
        JSONArray rolesArray = userObject.optJSONArray("roles");

        for (int i = 0; i < rolesArray.length(); i++) {
            JSONObject roleObject = rolesArray.optJSONObject(i);
            String roleName = roleObject.optString("roleName");
            roles.add(roleName);
        }
        
        return roles;
    }

    /**
     * 检查是否为管理员角色。
     */
    private boolean isAdmin(List<String> roles) {
        return roles.contains("ADMIN");
    }

    /**
     * 检查当前用户是否为资源所有者。
     */
    private boolean isOwner(JSONObject userObject, String userId) {
        return userObject.optString("id").equals(userId);
    }

}
