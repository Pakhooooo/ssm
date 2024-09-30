package com.ssm.competition.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.competition.dto.CompetitionDTO;
import com.ssm.competition.dto.CompetitionListDTO;
import com.ssm.competition.mapper.CompetitionMapper;
import com.ssm.competition.po.Competition;
import com.ssm.competition.service.CompetitionService;
import com.ssm.competition.vo.CompetitionListVO;
import com.ssm.competition.vo.CompetitionVO;
import com.ssm.register.mapper.RegisterMapper;
import com.ssm.score.mapper.ScoreMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final ScoreMapper scoreMapper;

    private final RegisterMapper registerMapper;
    
    private final CompetitionMapper competitionMapper;

    @Autowired
    public CompetitionServiceImpl(ScoreMapper scoreMapper, RegisterMapper registerMapper, CompetitionMapper competitionMapper) {
        this.scoreMapper = scoreMapper;
        this.registerMapper = registerMapper;
        this.competitionMapper = competitionMapper;
    }

    @Override
    public int addCompetition(Competition competition) {
        Competition queryObject = new Competition();
        queryObject.setDelStatus(0);
        queryObject.setCompetitionName(competition.getCompetitionName());
        int count = competitionMapper.selectCount(queryObject);
        if (count > 0) {
            throw new RuntimeException("比赛名称：" + competition.getCompetitionName() + " 已经存在");
        }
        
        return competitionMapper.insertSelective(competition);
    }

    @Override
    @Transactional
    public void deleteCompetition(int competitionId) {
        Competition competition = new Competition();
        competition.setId(competitionId);
        competition.setDelStatus(1);
        competition.setUpdateTime(new Date());
        competitionMapper.updateByPrimaryKeySelective(competition);

        scoreMapper.deleteScoreByCompetitionId(competitionId);
        registerMapper.deleteRegisterByCompetitionId(competitionId);
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
        
        return competitionMapper.updateByPrimaryKeySelective(updateObject);
    }

    @Override
    public CompetitionVO getCompetition(int competitionId) {
        return competitionMapper.getCompetition(competitionId);
    }

    @Override
    public JSONObject getCompetitions(CompetitionListDTO competitionListDTO) {
        PageHelper.startPage(competitionListDTO.getPageNum(), competitionListDTO.getPageSize());
        PageInfo<CompetitionListVO> pageInfo = new PageInfo<>(competitionMapper.getCompetitions());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", pageInfo.getTotal());
        jsonObject.put("list", pageInfo.getList());
        return jsonObject;
    }
}
