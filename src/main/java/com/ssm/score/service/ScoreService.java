package com.ssm.score.service;

import com.ssm.common.global.BaseListVO;
import com.ssm.score.dto.ScoreDTO;
import com.ssm.score.dto.ScoreListDTO;
import com.ssm.score.vo.ScoreListVO;
import com.ssm.score.vo.ScoreVO;

public interface ScoreService {

    int addScore(ScoreDTO scoreDTO);

    int deleteScore(int scoreId);

    int updateScore(ScoreDTO score);

    ScoreVO getScore(int scoreId);

    BaseListVO<ScoreListVO> getScores(ScoreListDTO scoreListDTO);
    
}
