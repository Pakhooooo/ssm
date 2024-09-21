package com.ssm.user.controller;

import com.ssm.common.global.Result;
import com.ssm.user.entity.LoginRequest;
import com.ssm.user.service.UserLoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Tag(name = "User Management", description = "Operations related to user management")
public class UserLoginController {
    
    private UserLoginService userLoginService;

    @Autowired
    public void setUserLoginService(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @PostMapping(value = "/auth/user/login")
    public Result userLogin(@Valid @RequestBody LoginRequest loginRequest) {
        return userLoginService.login(loginRequest.getUserName(), loginRequest.getPassword());
    }
}
