package com.ssm.competition.controller;

import com.ssm.common.global.Result;
import com.ssm.competition.dto.CompetitionDTO;
import com.ssm.competition.dto.CompetitionListDTO;
import com.ssm.competition.po.Competition;
import com.ssm.competition.service.CompetitionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Tag(name = "Competition Management", description = "Operations related to competition management")
public class CompetitionController {
    
    private CompetitionService competitionService;

    @Autowired
    public void setCompetitionService(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }
    
    @PostMapping(value = "/competition/add")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('competition:add')")
    public Result addCompetition(@RequestBody Competition competition) {
        int flag = competitionService.addCompetition(competition);
        if (flag == 0) {
            Result.error("新增比赛信息失败");
        }
        
        return Result.success("新增比赛信息成功");
    }

    @DeleteMapping(value = "/competition/{competitionId}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('competition:delete')")
    public Result deleteCompetition(@PathVariable int competitionId) {
        competitionService.deleteCompetition(competitionId);
        return Result.success("比赛信息删除成功");
    }
    
    @PutMapping(value = "/competition/update")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('competition:update')")
    public Result updateCompetition(@RequestBody CompetitionDTO competition) {
        int flag = competitionService.updateCompetition(competition);
        if (flag == 0) {
            Result.error("比赛信息修改失败");
        }

        return Result.success("比赛信息修改成功");
    }
    
    @GetMapping(value = "/competition/{competitionId}")
    public Result getCompetition(@PathVariable int competitionId) {
        return Result.success(competitionService.getCompetition(competitionId), "比赛信息查询成功");
    }

    @PostMapping(value = "/competitions")
    public Result getCompetitions(@Valid @RequestBody CompetitionListDTO competitionListDTO) {
        return Result.success(competitionService.getCompetitions(competitionListDTO), "比赛信息列表查询成功");
    }
    
}
