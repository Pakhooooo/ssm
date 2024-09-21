package com.ssm.user.service;

import com.ssm.common.global.Result;

public interface UserAuthService {

    Result login(String username, String password);
    
    Result logout(String token);
}
