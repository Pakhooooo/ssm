package com.ssm.score.mapper;

import com.ssm.score.po.Score;
import com.ssm.score.vo.ScoreListVO;
import com.ssm.score.vo.ScoreVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ScoreMapper extends Mapper<Score> {

    ScoreVO getScore(int scoreId);

    List<ScoreListVO> getScores();
}
