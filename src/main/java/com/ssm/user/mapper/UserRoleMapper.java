package com.ssm.user.mapper;

import com.ssm.user.po.UserRole;
import com.ssm.user.vo.UserRoleListVO;
import tk.mybatis.mapper.common.Mapper;

public interface UserRoleMapper extends Mapper<UserRole> {
    
    UserRoleListVO getUserRoles();
    
}
