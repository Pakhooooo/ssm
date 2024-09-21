package com.ssm.user.mapper;

import com.ssm.user.entity.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserLoginMapper extends Mapper<User> {
    
    User getUserByUserName(String userName);
    
}
