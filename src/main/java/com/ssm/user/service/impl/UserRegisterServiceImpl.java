package com.ssm.user.service.impl;

import com.ssm.user.entity.User;
import com.ssm.user.mapper.UserRegisterMapper;
import com.ssm.user.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {
    
    private UserRegisterMapper userRegisterMapper;

    @Autowired
    public void setUserRegisterMapper(UserRegisterMapper userRegisterMapper) {
        this.userRegisterMapper = userRegisterMapper;
    }

    @Override
    public int userRegister(User user) {
        return userRegisterMapper.save(user);
    }
}
