package com.ssm.user.mapper;

import com.ssm.user.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAuthMapper {
    
    User findUserWithRolesAndPermissions(String userName);
    
}
