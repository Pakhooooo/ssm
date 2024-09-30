package com.ssm.register.mapper;

import com.ssm.register.po.Register;
import com.ssm.register.vo.RegisterListVO;
import com.ssm.register.vo.RegisterVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RegisterMapper extends Mapper<Register> {

    RegisterVO getRegister(int registerId);

    List<RegisterListVO> getRegisters();

    void deleteRegisterByCompetitionId(int competitionId);
}
