package com.ssm.user.service;

import com.ssm.common.global.BaseListVO;
import com.ssm.user.dto.RoleDTO;
import com.ssm.user.dto.RoleListDTO;
import com.ssm.user.po.Role;
import com.ssm.user.vo.RoleListVO;

public interface RoleService {
    
    int addRole(Role role);
    
    int deleteRole(int roleId);
    
    int updateRole(RoleDTO role);

    BaseListVO<RoleListVO> getRoles(RoleListDTO roleListDTO);
    
}
