package com.ssm.competition.service;

import com.ssm.competition.dto.CompetitionDTO;
import com.ssm.competition.dto.CompetitionListDTO;
import com.ssm.competition.po.Competition;
import com.ssm.competition.vo.CompetitionVO;
import org.json.JSONObject;

public interface CompetitionService {
    
    int addCompetition(Competition competition);
    
    int deleteCompetition(int competitionId);
    
    int updateCompetition(CompetitionDTO competition);
    
    CompetitionVO getCompetition(int competitionId);
    
    JSONObject getCompetitions(CompetitionListDTO competitionListDTO);
}
