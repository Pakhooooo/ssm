package com.ssm.user.service.impl;

import com.ssm.user.dto.UserRoleDTO;
import com.ssm.user.mapper.UserRoleMapper;
import com.ssm.user.po.UserRole;
import com.ssm.user.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleMapper userRoleMapper;

    @Autowired
    public void setUserRoleMapper(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public int saveUserRole(UserRoleDTO userRoleDTO) {
        Integer userId = userRoleDTO.getUserId();
        Set<Integer> roleIds = userRoleDTO.getRoleIds();
        if (roleIds.isEmpty()) {
            throw new RuntimeException("用户必须包含一个角色");
        }

        Set<Integer> oldRoles = userRoleMapper.getUserRoleIdsByUserId(userId);
        if (oldRoles.isEmpty()) {
            List<UserRole> userRoles = getUserRoles(userId, roleIds);
            return userRoleMapper.insertList(userRoles);
        }

        userRoleMapper.deleteUserRoleByUserId(userId);

        List<UserRole> userRoles = getUserRoles(userId, roleIds);
        return userRoleMapper.insertList(userRoles);
    }

    private List<UserRole> getUserRoles(Integer userId, Set<Integer> roleIds) {
        return roleIds.stream().map(roleId -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRole.setCreateTime(new Date());
            userRole.setUpdateTime(new Date());
            userRole.setDelStatus(0);
            return userRole;
        }).collect(Collectors.toList());
    }
    
}
