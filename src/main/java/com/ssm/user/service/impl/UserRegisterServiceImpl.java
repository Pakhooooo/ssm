package com.ssm.user.service.impl;

import com.ssm.user.entity.User;
import com.ssm.user.mapper.UserRegisterMapper;
import com.ssm.user.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

    private PasswordEncoder passwordEncoder;
    
    private UserRegisterMapper userRegisterMapper;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

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
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRegisterMapper.insert(user);
    }

}
