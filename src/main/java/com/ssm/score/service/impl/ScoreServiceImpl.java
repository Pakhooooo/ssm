package com.ssm.score.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.score.dto.ScoreDTO;
import com.ssm.score.dto.ScoreListDTO;
import com.ssm.score.mapper.ScoreMapper;
import com.ssm.score.po.Score;
import com.ssm.score.service.ScoreService;
import com.ssm.score.vo.ScoreListVO;
import com.ssm.score.vo.ScoreVO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ScoreServiceImpl implements ScoreService {
    
    private ScoreMapper scoreMapper;

    @Autowired
    public void setScoreMapper(ScoreMapper scoreMapper) {
        this.scoreMapper = scoreMapper;
    }

    @Override
    public int addScore(Score score) {
        Score queryScore = new Score();
        queryScore.setUserId(score.getUserId());
        queryScore.setCompetitionId(score.getCompetitionId());
        queryScore.setDelStatus(0);
        int count = scoreMapper.selectCount(queryScore);
        if (count > 0) {
            throw new RuntimeException("该人员的比赛成绩已存在，请勿重复操作");
        }
        
        return scoreMapper.insertSelective(score);
    }

    @Override
    public int deleteScore(int scoreId) {
        Score score = new Score();
        score.setId(scoreId);
        score.setDelStatus(1);
        score.setUpdateTime(new Date());
        return scoreMapper.updateByPrimaryKeySelective(score);
    }

    @Override
    public int updateScore(ScoreDTO score) {
        Score updateScore = new Score();
        updateScore.setId(score.getId());
        updateScore.setUserId(score.getUserId());
        updateScore.setCompetitionId(score.getCompetitionId());
        updateScore.setCompetitionScore(score.getCompetitionScore());
        updateScore.setCompetitionRank(score.getCompetitionRank());
        updateScore.setUpdateTime(new Date());
        
        return scoreMapper.updateByPrimaryKeySelective(updateScore);
    }

    @Override
    public ScoreVO getScore(int scoreId) {
        return scoreMapper.getScore(scoreId);
    }

    @Override
    public JSONObject getScores(ScoreListDTO scoreListDTO) {
        PageHelper.startPage(scoreListDTO.getPageNum(), scoreListDTO.getPageSize());
        PageInfo<ScoreListVO> pageInfo = new PageInfo<>(scoreMapper.getScores());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", pageInfo.getTotal());
        jsonObject.put("list", pageInfo.getList());
        return jsonObject;
    }
}
