package com.ssm.user.mapper;

import com.ssm.user.po.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserRegisterMapper extends Mapper<User> {
    
    User getUserByUsername(@Param("username") String username);
    
    User getUserByPhone(@Param("phone") String phone);
    
}
