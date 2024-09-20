package com.ssm.user.service;

import com.ssm.user.entity.User;

public interface UserLoginService {
    
    User userLogin(String username, String password);
    
}
