package com.ssm.competition.controller;

import com.ssm.common.global.Result;
import com.ssm.competition.dto.CompetitionDTO;
import com.ssm.competition.po.Competition;
import com.ssm.competition.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompetitionController {
    
    private CompetitionService competitionService;

    @Autowired
    public void setCompetitionService(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }
    
    @PostMapping(value = "/competition/add")
    public Result addCompetition(@RequestBody Competition competition) throws Exception {
        int flag = competitionService.addCompetition(competition);
        if (flag == 0) {
            Result.error("新增赛事失败");
        }
        
        return Result.success("新增赛事成功");
    }

    @DeleteMapping(value = "/competition/{competitionId}")
    public Result deleteCompetition(@PathVariable int competitionId) {
        int flag = competitionService.deleteCompetition(competitionId);
        if (flag == 0) {
            Result.error("赛事删除失败");
        }

        return Result.success("赛事删除成功");
    }
    
    @PutMapping(value = "/competition/update")
    public Result updateCompetition(@RequestBody CompetitionDTO competition) {
        int flag = competitionService.updateCompetition(competition);
        if (flag == 0) {
            Result.error("赛事修改失败");
        }

        return Result.success("赛事修改成功");
    }
    
    @GetMapping(value = "/competition/{competitionId}")
    public Result getCompetition(@PathVariable int competitionId) {
        return Result.success(competitionService.getCompetition(competitionId), "赛事查询成功");
    }

    @GetMapping(value = "/competitions")
    public Result getCompetitions() {
        return Result.success(competitionService.getCompetitions(), "赛事查询成功");
    }
    
}
