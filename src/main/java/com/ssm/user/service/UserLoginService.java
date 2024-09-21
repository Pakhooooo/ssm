package com.ssm.user.service;

import com.ssm.common.global.Result;

public interface UserLoginService {

    Result login(String username, String password);
    
}
