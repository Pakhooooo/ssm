package com.ssm.user.service.impl;

import com.ssm.user.mapper.UserInfoMapper;
import com.ssm.user.service.UserInfoService;
import com.ssm.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    
    private UserInfoMapper userInfoMapper;

    @Autowired
    public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public UserVO getUserInfoById(int userId) {
        return userInfoMapper.getUserInfoById(userId);
    }
}
