package com.ssm.user.dao;

import com.ssm.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    
    void saveUser(User user);
    
}
