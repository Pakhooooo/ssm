package com.ssm.user.controller;

import com.ssm.common.global.Result;
import com.ssm.user.entity.User;
import com.ssm.user.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Validated
@RestController
public class UserLoginController {

    private UserLoginService userLoginService;

    @Autowired
    public void setUserLoginService(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @PostMapping(value = "/auth/user/login")
    public Result<User> userLogin(@RequestParam @NotBlank(message = "用户名不能为空") String userName,
                                  @RequestParam @NotBlank(message = "密码不能为空") String password) {
        userLoginService.userLogin(userName, password);
        return Result.success(null, "登录成功");
    }
}
