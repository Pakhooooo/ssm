package com.ssm.score.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.common.global.BaseListVO;
import com.ssm.register.mapper.RegisterMapper;
import com.ssm.register.po.Register;
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
import tk.mybatis.mapper.entity.Example;

@Service
public class ScoreServiceImpl implements ScoreService {
    
    private final ScoreMapper scoreMapper;
    
    private final RegisterMapper registerMapper;

    private final UserInfoMapper userInfoMapper;

    @Autowired
    public ScoreServiceImpl(ScoreMapper scoreMapper, RegisterMapper registerMapper, UserInfoMapper userInfoMapper) {
        this.scoreMapper = scoreMapper;
        this.registerMapper = registerMapper;
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public int addScore(ScoreDTO scoreDTO) {
        User user = userInfoMapper.getUserByName(scoreDTO.getRealName());
        if (user == null) {
            throw new RuntimeException("无法获取到该参赛者的信息，请核对后重试");
        }

        verify(user.getId(), scoreDTO);
        
        Score score = new Score();
        score.setUserId(user.getId());
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
        verify(score.getUserId(), score);
        
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
    
    private void verify(int userId, ScoreDTO scoreDTO) {
        Example example = new Example(Register.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delStatus", 0);
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("competitionId", scoreDTO.getCompetitionId());
        criteria.andEqualTo("auditStatus", "APPROVED");

        int registerCount = registerMapper.selectCountByExample(example);
        if (registerCount == 0) {
            throw new RuntimeException("该人员尚未报名比赛或比赛报名未审核通过");
        }

        Score queryScore = new Score();
        queryScore.setUserId(userId);
        queryScore.setCompetitionId(scoreDTO.getCompetitionId());
        queryScore.setDelStatus(0);
        int count = scoreMapper.selectCount(queryScore);
        if (count > 0) {
            throw new RuntimeException("该人员的比赛成绩已存在，请勿重复操作");
        }
    }
}
