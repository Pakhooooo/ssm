package com.ssm.register.service;

import com.ssm.register.dto.RegisterDTO;
import com.ssm.register.dto.RegisterListDTO;
import com.ssm.register.po.Register;
import com.ssm.register.vo.RegisterVO;
import org.json.JSONObject;

public interface RegisterService {

    int addRegister(Register register);

    int deleteRegister(int registerId);

    int updateRegister(RegisterDTO register);

    RegisterVO getRegister(int registerId);

    JSONObject getRegisters(RegisterListDTO registerListDTO);
    
}
