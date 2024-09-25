package com.ssm.user.mapper;

import com.ssm.user.po.User;
import org.apache.ibatis.annotations.Mapper;

import javax.websocket.server.PathParam;

@Mapper
public interface UserAuthMapper {
    
    User findUserWithRolesAndPermissions(String userName);

    void updateUserPasswordById(@PathParam("userId") int userId, @PathParam("password") String password);
}
