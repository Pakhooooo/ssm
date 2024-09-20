package com.ssm.user.controller;

import com.ssm.common.global.Result;
import com.ssm.user.entity.User;
import com.ssm.user.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserRegisterController {
    
    private UserRegisterService userRegisterService;

    @Autowired
    public void setUserRegisterService(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @PostMapping(value = "/auth/user/register")
    public Result<String> userRegister(@Valid @RequestBody User user) {
        // userService.userRegister(user);
        return Result.success("用户注册成功");
    }
    
}
