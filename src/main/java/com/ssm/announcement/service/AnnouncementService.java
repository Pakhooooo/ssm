package com.ssm.announcement.service;

import com.ssm.announcement.dto.AnnouncementDTO;
import com.ssm.announcement.dto.AnnouncementListDTO;
import com.ssm.announcement.po.Announcement;
import com.ssm.announcement.vo.AnnouncementVO;
import org.json.JSONObject;

public interface AnnouncementService {

    int addAnnouncement(Announcement announcement);

    void deleteAnnouncement(int announcementId);

    int updateAnnouncement(AnnouncementDTO announcement);

    AnnouncementVO getAnnouncement(int announcementId);

    JSONObject getAnnouncements(AnnouncementListDTO announcementList);
    
}
