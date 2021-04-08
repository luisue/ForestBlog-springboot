package cn.it.mapper;

import cn.it.entity.Tag;

import java.util.List;

/**
 * @author luis
 * @date 2021/4/7 15:13
 */
public interface TagMapper {
    /**
     * 根据ID删除
     *
     * @param tagId 标签ID
     * @return 影响行数
     */
    int deleteById(Integer tagId);

    /**
     * 添加
     *
     * @param tag 标签
     * @return 影响行数
     */
    int insert(Tag tag);

    /**
     * 根据ID查询
     *
     * @param tagId 标签ID
     * @return 标签
     */
    Tag getTagById(Integer tagId);

    /**
     * 更新
     * @param tag 标签
     * @return 影响行数
     */
    int update(Tag tag);

    /**
     * 获得标签总数
     *
     * @return 数量
     */
    Integer countTag() ;

    /**
     * 获得标签列表
     *
     * @return 列表
     */
    List<Tag> listTag() ;


    /**
     * 根据标签名获取标签
     *
     * @param name 名称
     * @return 标签
     */
    Tag  getTagByName(String name) ;
}
