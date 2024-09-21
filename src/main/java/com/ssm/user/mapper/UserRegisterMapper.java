package com.ssm.user.mapper;

import com.ssm.user.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserRegisterMapper extends Mapper<User> {
    
    User getUserByUserName(@Param("userName") String userName);
    
    User getUserByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    
}
