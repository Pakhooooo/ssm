package com.ssm.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssm.common.global.Result;
import com.ssm.user.dto.UserDTO;
import com.ssm.user.dto.UserListDTO;
import com.ssm.user.service.UserInfoService;
import com.ssm.user.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Tag(name = "User Management", description = "Operations related to user management")
public class UserInfoController {

    private UserInfoService userInfoService;

    @Autowired
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Operation(summary = "查询用户信息")
    @GetMapping(value = "/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id")
    public Result getUserInfo(
            @Parameter(description = "用户ID", example = "1", required = true)
            @PathVariable @NotNull(message = "用户ID不能为空") int userId) {
        UserVO userVO = userInfoService.getUserInfoById(userId);
        return Result.success(userVO, "用户信息查询成功");
    }
    
    @PutMapping(value = "/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id")
    public Result updateInformation(@PathVariable @NotNull(message = "用户ID不能为空") int userId, @RequestBody UserDTO userDTO) {
        userInfoService.updateUserInfoById(userId, userDTO);
        return Result.success(new ObjectMapper().createObjectNode(), "个人信息修改成功");
    }

    @DeleteMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('user:delete')")
    public Result deleteUser(@PathVariable @NotNull(message = "用户ID不能为空") int userId) {
        userInfoService.deleteUserInfoById(userId);
        return Result.success(new ObjectMapper().createObjectNode(), "删除用户成功");
    }

    @PostMapping(value = "/users")
    @PreAuthorize("hasAuthority('user:list')")
    public Result getUserList(@Valid @RequestBody UserListDTO userListDTO) {
        return Result.success(userInfoService.getUserList(userListDTO), "用户列表查询成功");
    }

}

