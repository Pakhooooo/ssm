package com.ssm.competition.service;

import com.ssm.competition.dto.CompetitionDTO;
import com.ssm.competition.po.Competition;
import com.ssm.competition.vo.CompetitionVO;

import java.util.List;

public interface CompetitionService {
    
    int addCompetition(Competition competition) throws Exception;
    
    int deleteCompetition(int competitionId);
    
    int updateCompetition(CompetitionDTO competition);
    
    CompetitionVO getCompetition(int competitionId);
    
    List<CompetitionVO> getCompetitions();
}
