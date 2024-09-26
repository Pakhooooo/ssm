package com.ssm.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.user.dto.UserDTO;
import com.ssm.user.dto.UserListDTO;
import com.ssm.user.mapper.UserInfoMapper;
import com.ssm.user.po.User;
import com.ssm.user.service.UserInfoService;
import com.ssm.user.vo.UserListVO;
import com.ssm.user.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public JSONObject getUserList(UserListDTO userListDTO) {
        PageHelper.startPage(userListDTO.getPageNum(), userListDTO.getPageSize());
        PageInfo<UserListVO> pageInfo = new PageInfo<>(userInfoMapper.getUserList());
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", pageInfo.getTotal());
        jsonObject.put("list", pageInfo.getList());
        return jsonObject;
    }

    @Override
    public void deleteUserInfoById(int userId) {
        userInfoMapper.deleteUserInfoById(userId);
    }

    @Override
    public void updateUserInfoById(int userId, UserDTO userDTO) {
        User user = userInfoMapper.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("未查询到当前用户");
        }
        
        user.setAge(userDTO.getAge() == null ? user.getAge() : userDTO.getAge());
        user.setSex(StringUtils.isEmpty(userDTO.getSex()) ? user.getSex() : userDTO.getSex());
        user.setRealName(StringUtils.isEmpty(userDTO.getRealName()) ? user.getRealName() : userDTO.getRealName());
        user.setPhoneNumber(StringUtils.isEmpty(userDTO.getPhoneNumber()) ? user.getPhoneNumber() : userDTO.getPhoneNumber());
        user.setUpdateTime(new Date());
        
        userInfoMapper.updateUserInfo(user);
    }
}
