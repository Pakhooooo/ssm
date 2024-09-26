package com.ssm.announcement.controller;

import com.ssm.announcement.dto.AnnouncementDTO;
import com.ssm.announcement.dto.AnnouncementListDTO;
import com.ssm.announcement.po.Announcement;
import com.ssm.announcement.service.AnnouncementService;
import com.ssm.common.global.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AnnouncementController {
    
    private AnnouncementService announcementService;

    @Autowired
    public void setAnnouncementService(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @PostMapping(value = "/announcement/add")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('announcement:add')")
    public Result addAnnouncement(@RequestBody Announcement announcement) {
        int flag = announcementService.addAnnouncement(announcement);
        if (flag == 0) {
            Result.error("新增公告信息失败");
        }

        return Result.success("新增公告信息成功");
    }

    @DeleteMapping(value = "/announcement/{announcementId}")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('announcement:delete')")
    public Result deleteAnnouncement(@PathVariable int announcementId) {
        announcementService.deleteAnnouncement(announcementId);
        return Result.success("公告信息删除成功");
    }

    @PutMapping(value = "/announcement/update")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('announcement:update')")
    public Result updateAnnouncement(@RequestBody AnnouncementDTO announcementDTO) {
        int flag = announcementService.updateAnnouncement(announcementDTO);
        if (flag == 0) {
            Result.error("公告信息修改失败");
        }

        return Result.success("公告信息修改成功");
    }

    @GetMapping(value = "/announcement/{announcementId}")
    public Result getAnnouncement(@PathVariable int announcementId) {
        return Result.success(announcementService.getAnnouncement(announcementId), "公告信息查询成功");
    }

    @GetMapping(value = "/announcements")
    public Result getAnnouncements(@Valid @RequestBody AnnouncementListDTO announcementListDTO) {
        return Result.success(announcementService.getAnnouncements(announcementListDTO), "公告信息列表查询成功");
    }
    
}
