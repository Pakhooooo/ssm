package com.ssm.user.service.impl;

import com.ssm.common.exception.AlreadyExistsException;
import com.ssm.user.dto.UserRoleDTO;
import com.ssm.user.mapper.UserRoleMapper;
import com.ssm.user.po.UserRole;
import com.ssm.user.service.UserRoleService;
import com.ssm.user.vo.UserRoleListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleMapper userRoleMapper;

    @Autowired
    public void setUserRoleMapper(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public int addUserRole(UserRole userRole) {
        try {
            return userRoleMapper.insertSelective(userRole);
        } catch (DuplicateKeyException e) {
            throw new AlreadyExistsException("该用户角色已存在，请勿重复操作");
        }
    }

    @Override
    public int deleteUserRole(int userRoleId) {
        UserRole userRole = new UserRole();
        userRole.setId(userRoleId);
        userRole.setDelStatus(1);
        try {
            return userRoleMapper.updateByPrimaryKeySelective(userRole);
        } catch (DuplicateKeyException e) {
            return userRoleMapper.deleteByPrimaryKey(userRole);
        }
    }

    @Override
    public int updateUserRole(UserRoleDTO userRoleDTO) {
        UserRole userRole = new UserRole();
        userRole.setId(userRoleDTO.getId());
        userRole.setRoleId(userRoleDTO.getRoleId());
        try {
            return userRoleMapper.updateByPrimaryKeySelective(userRole);
        } catch (DuplicateKeyException e) {
            throw new AlreadyExistsException("该用户角色已存在，请勿重复操作");
        }
    }

    @Override
    public UserRoleListVO getUserRoles() {
        return userRoleMapper.getUserRoles();
    }
}
