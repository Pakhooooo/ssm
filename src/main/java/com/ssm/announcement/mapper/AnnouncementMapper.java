package com.ssm.announcement.mapper;

import com.ssm.announcement.po.Announcement;
import com.ssm.announcement.vo.AnnouncementVO;
import com.ssm.competition.vo.CompetitionListVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AnnouncementMapper extends Mapper<Announcement> {
    
    void deleteAnnouncementById(int announcementId);

    List<CompetitionListVO> getAnnouncements();

    AnnouncementVO getAnnouncement(int announcementId);
}
