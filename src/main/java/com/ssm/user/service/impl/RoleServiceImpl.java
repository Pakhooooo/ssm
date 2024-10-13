package com.ssm.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.common.exception.AlreadyExistsException;
import com.ssm.common.global.BaseListVO;
import com.ssm.common.util.RedisUtils;
import com.ssm.user.dto.RoleDTO;
import com.ssm.user.dto.RoleListDTO;
import com.ssm.user.mapper.RoleMapper;
import com.ssm.user.po.Role;
import com.ssm.user.service.RoleService;
import com.ssm.user.vo.RoleListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    
    private RoleMapper roleMapper;
    private RedisUtils redisUtils;

    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public int addRole(Role role) {
        try {
            role.setRoleCode(role.getRoleCode().toUpperCase());
            return roleMapper.insertSelective(role);
        } catch (DuplicateKeyException e) {
            throw new AlreadyExistsException("该角色已存在，请勿重复操作");
        }
    }

    @Override
    public int deleteRole(int roleId) {
        Role role = new Role();
        role.setId(roleId);
        role.setDelStatus(1);
        try {
            return roleMapper.updateByPrimaryKeySelective(role);
        } catch (DuplicateKeyException e) {
            return roleMapper.deleteByPrimaryKey(roleId);
        }
    }

    @Override
    public int updateRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setRoleName(roleDTO.getRoleName());
        try {
            return roleMapper.updateByPrimaryKeySelective(role);
        } catch (DuplicateKeyException e) {
            throw new AlreadyExistsException("该角色已存在，请勿重复操作");
        }
    }

    @Override
    public BaseListVO<RoleListVO> getRoles(RoleListDTO roleListDTO) {
        PageHelper.startPage(roleListDTO.getPageNum(), roleListDTO.getPageSize());
        PageInfo<RoleListVO> pageInfo = new PageInfo<>(roleMapper.getRoles());
        pageInfo.getList().forEach(roleListVO -> roleListVO.setPermissions(roleMapper.getRolePermissionByRoleId(roleListVO.getId())));

        BaseListVO<RoleListVO> baseListVO = new BaseListVO<>();
        baseListVO.setTotal(pageInfo.getTotal());
        baseListVO.setList(pageInfo.getList());
        return baseListVO;
    }

    @Autowired
    public void setRedisUtils(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }
}
