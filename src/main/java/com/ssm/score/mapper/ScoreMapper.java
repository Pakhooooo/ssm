package com.ssm.score.mapper;

import com.ssm.score.po.Score;
import com.ssm.score.vo.ScoreListVO;
import com.ssm.score.vo.ScoreVO;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Mapper
public interface ScoreMapper extends BaseMapper<Score> {

    ScoreVO getScore(int scoreId);

    List<ScoreListVO> getScores();
}
