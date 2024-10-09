package com.ssm.user.controller;

import com.ssm.common.global.Result;
import com.ssm.user.dto.RolePermissionDTO;
import com.ssm.user.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RolePermissionController {
    
    private RolePermissionService rolePermissionService;

    @Autowired
    public void setRolePermissionService(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/role/permission/add")
    public Result addRole(@RequestBody RolePermissionDTO rolePermissionDTO) {
        int flag = rolePermissionService.saveRolePermission(rolePermissionDTO);
        if (flag == 0) {
            Result.error("新增角色权限失败");
        }

        return Result.success("新增角色权限成功");
    }
}
