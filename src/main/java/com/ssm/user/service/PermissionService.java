package com.ssm.user.service;

import com.ssm.common.global.BaseListVO;
import com.ssm.user.dto.PermissionDTO;
import com.ssm.user.dto.PermissionListDTO;
import com.ssm.user.po.Permission;
import com.ssm.user.vo.PermissionListVO;

public interface PermissionService {

    int addPermission(Permission permission);

    int deletePermission(int permissionId);

    int updatePermission(PermissionDTO permissionDTO);

    BaseListVO<PermissionListVO> getPermissions(PermissionListDTO permissionListDTO);
    
}
