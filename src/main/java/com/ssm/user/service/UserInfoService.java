package com.ssm.user.service;

import com.ssm.user.dto.QueryUserListDTO;
import com.ssm.user.dto.UserDTO;
import com.ssm.user.vo.UserVO;
import org.json.JSONObject;

public interface UserInfoService {
    
    UserVO getUserInfoById(int userId);

    JSONObject getUserList(QueryUserListDTO queryUserListDTO);
    
    void deleteUserInfoById(int userId);
    
    void updateUserInfoById(int userId, UserDTO userDTO);
}
