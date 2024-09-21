package com.ssm.user.controller;

import com.ssm.common.global.Result;
import com.ssm.user.entity.LoginRequest;
import com.ssm.user.service.UserAuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Tag(name = "User Management", description = "Operations related to user management")
public class UserAuthController {
    
    private UserAuthService userAuthService;

    @Autowired
    public void setUserAuthService(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @PostMapping(value = "/auth/user/login")
    public Result userLogin(@Valid @RequestBody LoginRequest loginRequest) {
        return userAuthService.login(loginRequest.getUserName(), loginRequest.getPassword());
    }
    
    @PostMapping(value = "/user/logout")
    public Result userLogout(@RequestHeader("Authorization") String authorizationHeader) {
        // 从请求头中获取 JWT Token
        String token = authorizationHeader.replace("Bearer ", "");
        return userAuthService.logout(token);
    }
}
