package com.ssm.score.controller;

import com.ssm.common.global.Result;
import com.ssm.score.dto.ScoreDTO;
import com.ssm.score.dto.ScoreListDTO;
import com.ssm.score.po.Score;
import com.ssm.score.service.ScoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Tag(name = "Score Management", description = "Operations related to score management")
public class ScoreController {
    
    private ScoreService scoreService;

    @Autowired
    public void setScoreService(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping(value = "/score/add")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('score:add')")
    public Result addScore(@RequestBody Score score) {
        int flag = scoreService.addScore(score);
        if (flag == 0) {
            Result.error("新增比赛成绩失败");
        }

        return Result.success("新增比赛成绩成功");
    }

    @DeleteMapping(value = "/score/{scoreId}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('score:delete')")
    public Result deleteScore(@PathVariable int scoreId) {
        int flag = scoreService.deleteScore(scoreId);
        if (flag == 0) {
            Result.error("比赛成绩删除失败");
        }

        return Result.success("比赛成绩删除成功");
    }

    @PutMapping(value = "/score/update")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('score:update')")
    public Result updateScore(@RequestBody ScoreDTO Score) {
        int flag = scoreService.updateScore(Score);
        if (flag == 0) {
            Result.error("比赛成绩修改失败");
        }

        return Result.success("比赛成绩修改成功");
    }

    @GetMapping(value = "/score/{scoreId}")
    public Result getScore(@PathVariable int scoreId) {
        return Result.success(scoreService.getScore(scoreId), "比赛成绩查询成功");
    }

    @PostMapping(value = "/scores")
    public Result getScores(@Valid @RequestBody ScoreListDTO scoreListDTO) {
        return Result.success(scoreService.getScores(scoreListDTO), "比赛成绩列表查询成功");
    }
    
}
