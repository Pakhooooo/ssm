package com.ssm.user.mapper;

import com.ssm.user.po.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    
    List<UserRole> getUserRoleByUserId(int userId);
    
}
