package com.ssm.user.controller;

import com.ssm.common.global.Result;
import com.ssm.user.dto.UserRoleDTO;
import com.ssm.user.service.UserRoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "User Role Permission Management", description = "Operations related to user role permission management")
public class UserRoleController {
    
    private UserRoleService userRoleService;

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/user/role/add")
    public Result addUserRole(@RequestBody UserRoleDTO userRoleDTO) {
        int flag = userRoleService.saveUserRole(userRoleDTO);
        if (flag == 0) {
            Result.error("保存用户角色失败");
        }

        return Result.success("保存用户角色成功");
    }
    
}
