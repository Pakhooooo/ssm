package com.ssm.user.service;

import com.ssm.user.dto.UserRoleDTO;
import com.ssm.user.po.UserRole;
import com.ssm.user.vo.UserRoleListVO;

public interface UserRoleService {

    int addUserRole(UserRole userRole);

    int deleteUserRole(int userRoleId);

    int editUserRole(UserRoleDTO userRoleDTO);

    UserRoleListVO getUserRoles();
    
}
