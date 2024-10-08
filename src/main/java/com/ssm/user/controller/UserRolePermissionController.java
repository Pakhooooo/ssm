package com.ssm.user.controller;

import com.ssm.user.service.UserRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRolePermissionController {
    
    private UserRolePermissionService userRolePermissionService;

    @Autowired
    public void setUserRolePermissionService(UserRolePermissionService userRolePermissionService) {
        this.userRolePermissionService = userRolePermissionService;
    }
    
    
}
