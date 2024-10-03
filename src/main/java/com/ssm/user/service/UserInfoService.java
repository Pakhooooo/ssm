package com.ssm.user.service;

import com.ssm.common.global.BaseListVO;
import com.ssm.user.dto.UserDTO;
import com.ssm.user.dto.UserListDTO;
import com.ssm.user.vo.UserListVO;
import com.ssm.user.vo.UserVO;

public interface UserInfoService {
    
    UserVO getUserInfoById(int userId);

    BaseListVO<UserListVO> getUserList(UserListDTO userListDTO);
    
    void deleteUserInfoById(int userId);
    
    void updateUserInfoById(int userId, UserDTO userDTO);
}
