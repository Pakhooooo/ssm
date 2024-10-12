package com.ssm.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.common.exception.AlreadyExistsException;
import com.ssm.common.global.BaseListVO;
import com.ssm.user.dto.PermissionDTO;
import com.ssm.user.dto.PermissionListDTO;
import com.ssm.user.mapper.PermissionMapper;
import com.ssm.user.po.Permission;
import com.ssm.user.service.PermissionService;
import com.ssm.user.vo.PermissionListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {
    
    private PermissionMapper permissionMapper;

    @Autowired
    public void setPermissionMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public int addPermission(Permission permission) {
        try {
            return permissionMapper.insertSelective(permission);
        } catch (DuplicateKeyException e) {
            throw new AlreadyExistsException("该权限已存在，请勿重复操作");
        }
    }

    @Override
    public int deletePermission(int permissionId) {
        Permission permission = new Permission();
        permission.setId(permissionId);
        permission.setDelStatus(1);
        try {
            return permissionMapper.updateByPrimaryKeySelective(permission);
        } catch (DuplicateKeyException e) {
            return permissionMapper.deleteByPrimaryKey(permissionId);
        }
    }

    @Override
    public int updatePermission(PermissionDTO permissionDTO) {
        Permission permission = new Permission();
        permission.setId(permissionDTO.getPermissionId());
        permission.setPermissionName(permissionDTO.getPermissionName());
        try {
            return permissionMapper.updateByPrimaryKeySelective(permission);
        } catch (DuplicateKeyException e) {
            throw new AlreadyExistsException("该权限已存在，请勿重复操作");
        }
    }

    @Override
    public BaseListVO<PermissionListVO> getPermissions(PermissionListDTO permissionListDTO) {
        PageHelper.startPage(permissionListDTO.getPageNum(), permissionListDTO.getPageSize());
        PageInfo<PermissionListVO> pageInfo = new PageInfo<>(permissionMapper.getPermissions(permissionListDTO));

        BaseListVO<PermissionListVO> baseListVO = new BaseListVO<>();
        baseListVO.setTotal(pageInfo.getTotal());
        baseListVO.setList(pageInfo.getList());
        return baseListVO;
    }
}
