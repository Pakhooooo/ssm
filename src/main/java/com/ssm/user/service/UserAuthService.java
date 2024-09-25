package com.ssm.user.service;

import com.ssm.common.global.Result;

public interface UserAuthService {

    Result login(String username, String password);
    
    Result logout(String token);
    
    Result refreshToken(String token);

    void updatePassword(String token, String oldPassword, String newPassword);
}
