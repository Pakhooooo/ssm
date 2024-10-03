package com.ssm.register.service;

import com.ssm.common.global.BaseListVO;
import com.ssm.register.dto.RegisterDTO;
import com.ssm.register.dto.RegisterListDTO;
import com.ssm.register.po.Register;
import com.ssm.register.vo.RegisterListVO;
import com.ssm.register.vo.RegisterVO;

public interface RegisterService {

    int addRegister(Register register);

    int deleteRegister(int registerId);

    int updateRegister(RegisterDTO register);

    RegisterVO getRegister(int registerId);

    BaseListVO<RegisterListVO> getRegisters(RegisterListDTO registerListDTO);
    
}
