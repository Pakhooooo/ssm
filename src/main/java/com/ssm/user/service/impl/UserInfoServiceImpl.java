package com.ssm.user.service.impl;

import com.ssm.user.mapper.UserInfoMapper;
import com.ssm.user.service.UserInfoService;
import com.ssm.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<UserVO> getUserList() {
        return userInfoMapper.getUserList();
    }

    @Override
    public void deleteUserInfoById(int userId) {
        userInfoMapper.deleteUserInfoById(userId);
    }
}
