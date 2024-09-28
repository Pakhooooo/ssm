package com.ssm.score.service;

import com.ssm.score.dto.ScoreDTO;
import com.ssm.score.dto.ScoreListDTO;
import com.ssm.score.po.Score;
import com.ssm.score.vo.ScoreVO;
import org.json.JSONObject;

public interface ScoreService {

    int addScore(Score score);

    int deleteScore(int scoreId);

    int updateScore(ScoreDTO score);

    ScoreVO getScore(int scoreId);

    JSONObject getScores(ScoreListDTO scoreListDTO);
    
}
