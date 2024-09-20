package com.ssm.user.controller;

import com.ssm.common.global.Result;
import com.ssm.user.entity.User;
import com.ssm.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    private UserService userService;

    @PostMapping
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user/register")
    public Result<User> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return Result.success(user, "用户注册成功");
    }
    
}
