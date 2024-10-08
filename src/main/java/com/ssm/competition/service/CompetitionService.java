package com.ssm.competition.service;

import com.ssm.common.global.BaseListVO;
import com.ssm.competition.dto.CompetitionDTO;
import com.ssm.competition.dto.CompetitionListDTO;
import com.ssm.competition.po.Competition;
import com.ssm.competition.vo.CompetitionListVO;
import com.ssm.competition.vo.CompetitionVO;

public interface CompetitionService {
    
    int addCompetition(Competition competition);
    
    void deleteCompetition(int competitionId);
    
    int updateCompetition(CompetitionDTO competition);
    
    CompetitionVO getCompetition(int competitionId);
    
    BaseListVO<CompetitionListVO> getCompetitions(CompetitionListDTO competitionListDTO);
}
