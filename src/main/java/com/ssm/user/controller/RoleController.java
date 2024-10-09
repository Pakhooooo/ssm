package com.ssm.user.controller;

import com.ssm.common.global.Result;
import com.ssm.user.dto.RoleDTO;
import com.ssm.user.dto.RoleListDTO;
import com.ssm.user.po.Role;
import com.ssm.user.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "User Role Permission Management", description = "Operations related to user role permission management")
public class RoleController {
    
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/role/add")
    public Result addRole(@RequestBody Role role) {
        int flag = roleService.addRole(role);
        if (flag == 0) {
            Result.error("新增角色失败");
        }

        return Result.success("新增角色成功");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/role/{roleId}")
    public Result deleteRole(@PathVariable int roleId) {
        int flag = roleService.deleteRole(roleId);
        if (flag == 0) {
            Result.error("删除角色失败");
        }

        return Result.success("删除角色成功");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/role/update")
    public Result updateRole(@RequestBody RoleDTO roleDTO) {
        int flag = roleService.updateRole(roleDTO);
        if (flag == 0) {
            Result.error("修改角色失败");
        }

        return Result.success("修改角色成功");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/roles")
    public Result getRoles(@RequestBody RoleListDTO roleListDTO) {
        return Result.success(roleService.getRoles(roleListDTO), "角色列表查询成功");
    }
    
}
