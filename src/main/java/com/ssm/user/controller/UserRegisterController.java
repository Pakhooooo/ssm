package com.ssm.user.controller;

import com.ssm.common.global.Result;
import com.ssm.user.po.User;
import com.ssm.user.service.UserRegisterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Tag(name = "User Management", description = "Operations related to user management")
public class UserRegisterController {
    
    private UserRegisterService userRegisterService;

    @Autowired
    public void setUserRegisterService(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @PostMapping(value = "/auth/user/register")
    public Result userRegister(@Valid @RequestBody User user) throws Exception {
        int flag = userRegisterService.userRegister(user);
        if (flag == 1) {
            return Result.success("用户注册成功");
        }
        return Result.error("用户注册失败");
    }
    
}
