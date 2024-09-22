package com.ssm.user.service.impl;

import com.ssm.user.entity.User;
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
        User user = userInfoMapper.selectOne(new User(userId, 0));
        if (user == null) {
            return null;
        }

        return user.getUserVO();
    }

    @Override
    public UserVO getUserByUserName(String userName) {
        return userInfoMapper.getUserByUserName(userName);
    }
}
