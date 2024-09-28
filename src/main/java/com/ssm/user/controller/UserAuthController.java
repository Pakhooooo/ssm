package com.ssm.user.controller;

import com.ssm.common.global.Result;
import com.ssm.user.dto.LoginRequestDTO;
import com.ssm.user.dto.UserPasswordDTO;
import com.ssm.user.service.UserAuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result userLogin(@Valid @RequestBody LoginRequestDTO loginRequest) {
        return userAuthService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }
    
    @PostMapping(value = "/user/logout")
    public Result userLogout(@RequestHeader("Authorization") String authorizationHeader) {
        // 从请求头中获取 JWT Token
        String token = authorizationHeader.replace("Bearer ", "");
        return userAuthService.logout(token);
    }
    
    @GetMapping(value = "/user/refresh/token")
    public Result userRefreshToken(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        return userAuthService.refreshToken(token);
    }

    @PutMapping(value = "/user/password/change")
    public Result changePassword(@RequestHeader("Authorization") String authorizationHeader, @RequestBody UserPasswordDTO userPasswordDTO) {
        String token = authorizationHeader.replace("Bearer ", "");
        userAuthService.updatePassword(token, userPasswordDTO.getOldPassword(), userPasswordDTO.getNewPassword());
        return Result.success(new JSONObject(), "密码修改成功");
    }
}
