package com.ssm.user.mapper;

import com.ssm.user.po.UserRole;
import com.ssm.user.vo.UserRoleVO;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;
import java.util.Set;

public interface UserRoleMapper extends Mapper<UserRole>, InsertListMapper<UserRole> {
    
    List<UserRoleVO> getUserRoleByUserId(int userId);
    
    Set<Integer> getUserRoleIdsByUserId(int userId);

    void deleteUserRoleByUserId(Integer userId);
}
