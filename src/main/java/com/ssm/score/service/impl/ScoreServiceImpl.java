package com.ssm.score.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.common.global.BaseListVO;
import com.ssm.score.dto.ScoreDTO;
import com.ssm.score.dto.ScoreListDTO;
import com.ssm.score.mapper.ScoreMapper;
import com.ssm.score.po.Score;
import com.ssm.score.service.ScoreService;
import com.ssm.score.vo.ScoreListVO;
import com.ssm.score.vo.ScoreVO;
import com.ssm.user.mapper.UserInfoMapper;
import com.ssm.user.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService {
    
    private ScoreMapper scoreMapper;

    private UserInfoMapper userInfoMapper;

    @Autowired
    public void setScoreMapper(ScoreMapper scoreMapper) {
        this.scoreMapper = scoreMapper;
    }

    @Autowired
    public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public int addScore(ScoreDTO scoreDTO) {
        User user = userInfoMapper.getUserByName(scoreDTO.getRegisterName());
        if (user == null) {
            throw new RuntimeException("无法获取到该参赛者的信息，请核对后重试");
        }
        
        Score queryScore = new Score();
        queryScore.setUserId(scoreDTO.getUserId());
        queryScore.setCompetitionId(scoreDTO.getCompetitionId());
        queryScore.setDelStatus(0);
        int count = scoreMapper.selectCount(queryScore);
        if (count > 0) {
            throw new RuntimeException("该人员的比赛成绩已存在，请勿重复操作");
        }

        Score score = new Score();
        score.setUserId(scoreDTO.getUserId());
        score.setCompetitionId(scoreDTO.getCompetitionId());
        score.setCompetitionScore(scoreDTO.getCompetitionScore());
        score.setCompetitionRank(scoreDTO.getCompetitionRank());
        return scoreMapper.insertSelective(score);
    }

    @Override
    public int deleteScore(int scoreId) {
        Score score = new Score();
        score.setId(scoreId);
        score.setDelStatus(1);
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
        
        return scoreMapper.updateByPrimaryKeySelective(updateScore);
    }

    @Override
    public ScoreVO getScore(int scoreId) {
        return scoreMapper.getScore(scoreId);
    }

    @Override
    public BaseListVO<ScoreListVO> getScores(ScoreListDTO scoreListDTO) {
        PageHelper.startPage(scoreListDTO.getPageNum(), scoreListDTO.getPageSize());
        PageInfo<ScoreListVO> pageInfo = new PageInfo<>(scoreMapper.getScores());

        BaseListVO<ScoreListVO> baseListVO = new BaseListVO<>();
        baseListVO.setTotal(pageInfo.getTotal());
        baseListVO.setList(pageInfo.getList());
        return baseListVO;
    }
}
