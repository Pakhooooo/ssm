package com.ssm.competition.service.impl;

import com.ssm.competition.dto.CompetitionDTO;
import com.ssm.competition.mapper.CompetitionMapper;
import com.ssm.competition.po.Competition;
import com.ssm.competition.service.CompetitionService;
import com.ssm.competition.vo.CompetitionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    
    private CompetitionMapper competitionMapper;

    @Autowired
    public void setCompetitionMapper(CompetitionMapper competitionMapper) {
        this.competitionMapper = competitionMapper;
    }

    @Override
    public int addCompetition(Competition competition) throws Exception {
        Competition queryObject = new Competition();
        queryObject.setDelStatus(0);
        queryObject.setCompetitionName(competition.getCompetitionName());
        int count = competitionMapper.selectCount(queryObject);
        if (count > 0) {
            throw new Exception("比赛名称： " + competition.getCompetitionName() + " 已经存在");
        }
        
        return competitionMapper.insert(competition);
    }

    @Override
    public int deleteCompetition(int competitionId) {
        Competition competition = new Competition();
        competition.setId(competitionId);
        competition.setDelStatus(1);
        return competitionMapper.updateByPrimaryKeySelective(competition);
    }

    @Override
    public int updateCompetition(CompetitionDTO competition) {
        Competition updateObject = new Competition();
        updateObject.setId(competition.getId());
        updateObject.setCompetitionName(competition.getCompetitionName());
        updateObject.setCompetitionDate(competition.getCompetitionDate());
        updateObject.setCompetitionLocation(competition.getCompetitionLocation());
        updateObject.setCompetitionPersonNumber(competition.getCompetitionPersonNumber());
        updateObject.setCompetitionDescription(competition.getCompetitionDescription());
        updateObject.setUpdateTime(new Date());
        
        return competitionMapper.updateByPrimaryKey(updateObject);
    }

    @Override
    public CompetitionVO getCompetition(int competitionId) {
        return competitionMapper.getCompetition(competitionId);
    }

    @Override
    public List<CompetitionVO> getCompetitions() {
        return competitionMapper.getCompetitions();
    }
}
