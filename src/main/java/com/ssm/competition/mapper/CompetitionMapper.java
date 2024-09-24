package com.ssm.competition.mapper;

import com.ssm.competition.po.Competition;
import com.ssm.competition.vo.CompetitionVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CompetitionMapper extends Mapper<Competition> {

    CompetitionVO getCompetition(int competitionId);

    List<CompetitionVO> getCompetitions();
    
}
