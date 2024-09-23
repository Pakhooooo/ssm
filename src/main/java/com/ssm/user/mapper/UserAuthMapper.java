package com.ssm.user.mapper;

import com.ssm.user.entity.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserAuthMapper extends Mapper<User> {
    
    User findUserByUserName(String userName);
    
}
