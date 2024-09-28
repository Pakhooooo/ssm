package com.ssm.register.mapper;

import com.ssm.register.po.Register;
import com.ssm.register.vo.RegisterListVO;
import com.ssm.register.vo.RegisterVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegisterMapper extends tk.mybatis.mapper.common.Mapper<Register> {

    RegisterVO getRegister(int registerId);

    List<RegisterListVO> getRegisters();
    
}
