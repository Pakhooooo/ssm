package com.ssm.user.mapper;

import com.ssm.user.po.UserRole;
import com.ssm.user.vo.UserRoleListVO;
import com.ssm.user.vo.UserRoleVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserRoleMapper extends Mapper<UserRole> {
    
    UserRoleListVO getUserRoles();
    
    List<UserRoleVO> getUserRoleByUserId(int userId);
}
