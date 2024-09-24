package com.ssm.user.service.impl;

import com.ssm.user.mapper.UserPermissionMapper;
import com.ssm.user.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPermissionServiceImpl implements UserPermissionService {
    
    private UserPermissionMapper userPermissionMapper;

    @Autowired
    public void setUserPermissionMapper(UserPermissionMapper userPermissionMapper) {
        this.userPermissionMapper = userPermissionMapper;
    }
}
