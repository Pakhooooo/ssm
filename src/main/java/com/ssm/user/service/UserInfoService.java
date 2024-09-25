package com.ssm.user.service;

import com.ssm.user.dto.UserDTO;
import com.ssm.user.vo.UserVO;

import java.util.List;

public interface UserInfoService {
    
    UserVO getUserInfoById(int userId);
    
    List<UserVO> getUserList();
    
    void deleteUserInfoById(int userId);
    
    void updateUserInfoById(int userId, UserDTO userDTO);
}
