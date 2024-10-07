package com.ssm.announcement.service;

import com.ssm.announcement.dto.AnnouncementDTO;
import com.ssm.announcement.dto.AnnouncementListDTO;
import com.ssm.announcement.po.Announcement;
import com.ssm.announcement.vo.AnnouncementVO;
import com.ssm.common.global.BaseListVO;
import com.ssm.competition.vo.CompetitionListVO;

public interface AnnouncementService {

    int addAnnouncement(Announcement announcement);

    void deleteAnnouncement(int announcementId);

    int editAnnouncement(AnnouncementDTO announcement);

    AnnouncementVO getAnnouncement(int announcementId);

    BaseListVO<CompetitionListVO> getAnnouncements(AnnouncementListDTO announcementList);
    
}
