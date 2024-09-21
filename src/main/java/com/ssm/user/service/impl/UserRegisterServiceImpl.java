package com.ssm.user.service.impl;

import com.ssm.common.util.MD5Util;
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
    public int userRegister(User user) throws Exception {
        User originalUser = userRegisterMapper.getUserByUserName(user.getUserName());
        if (originalUser != null) {
            throw new Exception("用户名 " + user.getUserName() + " 已经存在");
        }

        originalUser = userRegisterMapper.getUserByPhoneNumber(user.getPhoneNumber());
        if (originalUser != null) {
            throw new Exception("手机号码 " + user.getPhoneNumber() + " 已经存在");
        }
        
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        return userRegisterMapper.save(user);
    }
}
