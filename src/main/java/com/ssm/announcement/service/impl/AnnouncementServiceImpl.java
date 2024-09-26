package com.ssm.announcement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.announcement.dto.AnnouncementDTO;
import com.ssm.announcement.dto.AnnouncementListDTO;
import com.ssm.announcement.mapper.AnnouncementMapper;
import com.ssm.announcement.po.Announcement;
import com.ssm.announcement.service.AnnouncementService;
import com.ssm.announcement.vo.AnnouncementVO;
import com.ssm.competition.vo.CompetitionListVO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    
    private AnnouncementMapper announcementMapper;

    @Autowired
    public void setAnnouncementMapper(AnnouncementMapper announcementMapper) {
        this.announcementMapper = announcementMapper;
    }

    @Override
    public int addAnnouncement(Announcement announcement) {
        Announcement queryAnnouncement = new Announcement();
        queryAnnouncement.setDelStatus(0);
        queryAnnouncement.setAnnouncementTitle(announcement.getAnnouncementTitle());
        int flag = announcementMapper.selectCount(queryAnnouncement);
        if (flag > 0) {
            throw new RuntimeException("公告标题： " + announcement.getAnnouncementTitle() + " 已经存在");
        }
        
        return announcementMapper.insert(announcement);
    }

    @Override
    public void deleteAnnouncement(int announcementId) {
        announcementMapper.deleteAnnouncementById(announcementId);
    }

    @Override
    public int updateAnnouncement(AnnouncementDTO announcementDTO) {
        return 0;
    }

    @Override
    public AnnouncementVO getAnnouncement(int announcementId) {
        return null;
    }

    @Override
    public JSONObject getAnnouncements(AnnouncementListDTO announcementListDTO) {
        PageHelper.startPage(announcementListDTO.getPageNum(), announcementListDTO.getPageSize());
        PageInfo<CompetitionListVO> pageInfo = new PageInfo<>(announcementMapper.getAnnouncements());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", pageInfo.getTotal());
        jsonObject.put("list", pageInfo.getList());
        return jsonObject;
    }
}
