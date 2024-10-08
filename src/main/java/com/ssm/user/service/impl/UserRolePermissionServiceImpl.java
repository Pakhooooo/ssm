package com.ssm.user.service.impl;

import com.ssm.user.mapper.UserRolePermissionMapper;
import com.ssm.user.service.UserRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRolePermissionServiceImpl implements UserRolePermissionService {
    
    private UserRolePermissionMapper userRolePermissionMapper;

    @Autowired
    public void setUserRolePermissionMapper(UserRolePermissionMapper userRolePermissionMapper) {
        this.userRolePermissionMapper = userRolePermissionMapper;
    }
}
