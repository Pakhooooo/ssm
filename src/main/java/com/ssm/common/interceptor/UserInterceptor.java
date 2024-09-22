package com.ssm.common.interceptor;

import com.ssm.user.service.UserInfoService;
import com.ssm.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {

    private UserInfoService userInfoService;

    @Autowired
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        String[] urlParts = url.split("/");

        for (int i = 0; i < urlParts.length; i++) {
            if ("user".equals(urlParts[i]) && i + 1 < urlParts.length) {
                String userId = urlParts[i + 1];

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String currentUsername = authentication.getName();
                UserVO userVO = userInfoService.getUserByUserName(currentUsername);

                // 检查是否为管理员
                if (userVO.getRole().equals("ADMIN")) {
                    return true; // 允许访问
                } else if (userVO.getUserId().toString().equals(userId)) {
                    return true; // 允许查询自己的信息
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "无权限访问");
                    return false; // 拒绝访问
                }
            }
        }
        
        return true;
    }

}
