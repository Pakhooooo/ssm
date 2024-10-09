package com.ssm.announcement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.announcement.dto.AnnouncementDTO;
import com.ssm.announcement.dto.AnnouncementListDTO;
import com.ssm.announcement.mapper.AnnouncementMapper;
import com.ssm.announcement.po.Announcement;
import com.ssm.announcement.service.AnnouncementService;
import com.ssm.announcement.vo.AnnouncementVO;
import com.ssm.common.global.BaseListVO;
import com.ssm.competition.vo.CompetitionListVO;
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
            throw new RuntimeException("公告标题：" + announcement.getAnnouncementTitle() + " 已经存在");
        }
        
        return announcementMapper.insertSelective(announcement);
    }

    @Override
    public void deleteAnnouncement(int announcementId) {
        announcementMapper.deleteAnnouncementById(announcementId);
    }

    @Override
    public int updateAnnouncement(AnnouncementDTO announcement) {
        Announcement updateAnnouncement = new Announcement();
        updateAnnouncement.setId(announcement.getId());
        updateAnnouncement.setAnnouncementTitle(announcement.getAnnouncementTitle());
        updateAnnouncement.setAnnouncementContent(announcement.getAnnouncementContent());
        return announcementMapper.updateByPrimaryKeySelective(updateAnnouncement);
    }

    @Override
    public AnnouncementVO getAnnouncement(int announcementId) {
        return announcementMapper.getAnnouncement(announcementId);
    }

    @Override
    public BaseListVO<CompetitionListVO> getAnnouncements(AnnouncementListDTO announcementList) {
        PageHelper.startPage(announcementList.getPageNum(), announcementList.getPageSize());
        PageInfo<CompetitionListVO> pageInfo = new PageInfo<>(announcementMapper.getAnnouncements());

        BaseListVO<CompetitionListVO> baseListVO = new BaseListVO<>();
        baseListVO.setTotal(pageInfo.getTotal());
        baseListVO.setList(pageInfo.getList());
        
        return baseListVO;
    }
}
