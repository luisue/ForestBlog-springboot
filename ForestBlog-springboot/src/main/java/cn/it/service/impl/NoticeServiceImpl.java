package cn.it.service.impl;

import cn.it.entity.Notice;
import cn.it.mapper.NoticeMapper;
import cn.it.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luis
 * @date 2021/3/30 16:14
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> listNotice(Integer status) {
        return noticeMapper.listNotice(status);
    }

    @Override
    public void insertNotice(Notice notice) {

    }

    @Override
    public void deleteNotice(Integer id) {

    }

    @Override
    public void updateNotice(Notice notice) {

    }

    @Override
    public Notice getNoticeById(Integer id) {
        return null;
    }
}
