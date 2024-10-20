package com.ssm.user.service.impl;

import com.ssm.common.exception.AlreadyExistsException;
import com.ssm.user.mapper.UserRegisterMapper;
import com.ssm.user.po.User;
import com.ssm.user.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

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

    private static final Pattern PHONE_PATTERN = Pattern.compile("^1[3-9]\\d{9}$");
    
    // 正则表达式验证用户名（小写字母，长度限制）
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-z]{3,20}$");

    private static final Pattern REAL_NAME_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z]+$");

    // 正则表达式验证密码（字母、数字、特殊字符，长度限制）
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@#$%^&*()_+={}\\[\\]:;\"'<>,.?~`-]{6,20}$");

    @Override
    public int userRegister(User user) {
        if (!USERNAME_PATTERN.matcher(user.getUsername()).matches()) {
            throw new RuntimeException("用户名格式不正确");
        }
        if (!PASSWORD_PATTERN.matcher(user.getPassword()).matches()) {
            throw new RuntimeException("密码格式不正确");
        }

        if (!PHONE_PATTERN.matcher(user.getPhone()).matches()) {
            throw new RuntimeException("手机号码格式不正确");
        }
        if (!REAL_NAME_PATTERN.matcher(user.getRealName()).matches()) {
            throw new RuntimeException("真实姓名格式不正确，必须为中文或英文字母");
        }
        
        User originalUser = userRegisterMapper.getUserByUsername(user.getUsername());
        if (originalUser != null) {
            throw new AlreadyExistsException("用户名 " + user.getUsername() + " 已经存在");
        }

        originalUser = userRegisterMapper.getUserByPhone(user.getPhone());
        if (originalUser != null) {
            throw new AlreadyExistsException("手机号码 " + user.getPhone() + " 已经存在");
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRegisterMapper.insertSelective(user);
    }

}
