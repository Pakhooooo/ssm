package com.ssm.register.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.common.global.BaseListVO;
import com.ssm.register.dto.RegisterDTO;
import com.ssm.register.dto.RegisterListDTO;
import com.ssm.register.mapper.RegisterMapper;
import com.ssm.register.po.Register;
import com.ssm.register.service.RegisterService;
import com.ssm.register.vo.RegisterListVO;
import com.ssm.register.vo.RegisterVO;
import com.ssm.user.mapper.UserInfoMapper;
import com.ssm.user.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {
    
    private RegisterMapper registerMapper;

    private UserInfoMapper userInfoMapper;

    @Autowired
    public void setRegisterMapper(RegisterMapper registerMapper) {
        this.registerMapper = registerMapper;
    }

    @Autowired
    public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public int addRegister(RegisterDTO registerDTO) {
        User user = userInfoMapper.getUserByName(registerDTO.getRegisterName());
        if (user == null) {
            throw new RuntimeException("无法获取到该参赛者的信息，请核对后重试");
        }
        
        Register queryObject = new Register();
        queryObject.setDelStatus(0);
        queryObject.setUserId(user.getId());
        int count = registerMapper.selectCount(queryObject);
        if (count > 0) {
            throw new RuntimeException("您已报名该比赛，请勿重复操作");
        }

        Register register = new Register();
        register.setUserId(user.getId());
        register.setCompetitionId(registerDTO.getCompetitionId());
        return registerMapper.insertSelective(register);
    }

    @Override
    public int deleteRegister(int registerId) {
        Register register = new Register();
        register.setId(registerId);
        register.setDelStatus(1);
        register.setUpdateTime(new Date());
        return registerMapper.updateByPrimaryKeySelective(register);
    }

    @Override
    public int editRegister(RegisterDTO register) {
        Register updateObject = new Register();
        updateObject.setId(register.getId());
        updateObject.setCompetitionId(register.getCompetitionId());
        updateObject.setUpdateTime(new Date());
        
        return registerMapper.updateByPrimaryKeySelective(updateObject);
    }

    @Override
    public RegisterVO getRegister(int registerId) {
        return registerMapper.getRegister(registerId);
    }

    @Override
    public BaseListVO<RegisterListVO> getRegisters(RegisterListDTO registerListDTO) {
        PageHelper.startPage(registerListDTO.getPageNum(), registerListDTO.getPageSize());
        PageInfo<RegisterListVO> pageInfo = new PageInfo<>(registerMapper.getRegisters());

        BaseListVO<RegisterListVO> baseListVO = new BaseListVO<>();
        baseListVO.setTotal(pageInfo.getTotal());
        baseListVO.setList(pageInfo.getList());
        return baseListVO;
    }

    @Override
    public List<Map<String, Object>> getCompetitionNames() {
        return registerMapper.getCompetitionNames();
    }
}
