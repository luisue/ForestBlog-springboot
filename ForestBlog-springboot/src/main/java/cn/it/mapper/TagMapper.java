package cn.it.mapper;

import cn.it.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author luis
 * @date 2021/9/15 20:36
 */
public interface TagMapper {
    /**
     * 获取所有标签
     * @return
     */
    List<Tag> listTag();
}
