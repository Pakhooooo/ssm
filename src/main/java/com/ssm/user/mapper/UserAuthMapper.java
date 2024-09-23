package com.ssm.user.mapper;

import com.ssm.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAuthMapper {
    
    User findUserByUserName(String userName);
    
}
