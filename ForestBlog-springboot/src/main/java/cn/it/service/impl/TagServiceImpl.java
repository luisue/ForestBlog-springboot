package cn.it.service.impl;

import cn.it.entity.Tag;
import cn.it.mapper.TagMapper;
import cn.it.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luis
 * @date 2021/4/7 15:01
 */
@Service
@Slf4j
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public Integer countTag() {
        return null;
    }

    @Override
    public List<Tag> listTag() {
        List<Tag> tagList = null;
        try {
            tagList = tagMapper.listTag();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得所有标签失败, cause:{}", e);
        }
        return tagList;
    }

    @Override
    public List<Tag> listTagWithCount() {
        return null;
    }

    @Override
    public Tag getTagById(Integer id) {
        return null;
    }

    @Override
    public Tag insertTag(Tag tag) {
        return null;
    }

    @Override
    public void updateTag(Tag tag) {

    }

    @Override
    public void deleteTag(Integer id) {

    }

    @Override
    public Tag getTagByName(String name) {
        return null;
    }

    @Override
    public List<Tag> listTagByArticleId(Integer articleId) {
        return null;
    }
}
