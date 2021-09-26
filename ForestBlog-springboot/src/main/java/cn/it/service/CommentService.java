package cn.it.service;

import cn.it.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author luis
 * @date 2021/9/15 21:08
 */
public interface CommentService {
    /**
     * 获取最近评论
     * @param limit
     * @return
     */
    List<Comment> listRecentComment(@Param(value = "limit") Integer limit);
}
