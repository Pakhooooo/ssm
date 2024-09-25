package com.ssm.user.mapper;

import com.ssm.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import javax.websocket.server.PathParam;
import java.util.List;

@Mapper
public interface UserInfoMapper {
    
    UserVO getUserInfoById(int userId);

    List<UserVO> getUserList();
    
    void deleteUserInfoById(int userId);
    
    void updateUserPasswordById(@PathParam("userId") int userId, @PathParam("password") String password);
}
