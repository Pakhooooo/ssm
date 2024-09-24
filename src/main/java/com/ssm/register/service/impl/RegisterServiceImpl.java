package com.ssm.register.service.impl;

import com.ssm.register.mapper.RegisterMapper;
import com.ssm.register.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    private RegisterMapper registerMapper;

    @Autowired
    public void setRegisterMapper(RegisterMapper registerMapper) {
        this.registerMapper = registerMapper;
    }
}
