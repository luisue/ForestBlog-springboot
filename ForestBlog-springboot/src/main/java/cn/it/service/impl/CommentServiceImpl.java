package cn.it.service.impl;

import cn.it.entity.Article;
import cn.it.entity.Comment;
import cn.it.enums.ArticleStatus;
import cn.it.mapper.ArticleMapper;
import cn.it.mapper.CommentMapper;
import cn.it.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luis
 * @date 2021/9/15 21:11
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Comment> listRecentComment(Integer limit) {
        List<Comment> commentList = null;
        try {
            commentList = commentMapper.listRecentComment(limit);
            //向获取的评论对象中加入非数据库字段article
            for (int i = 0; i < commentList.size(); i++) {
                Article article = articleMapper.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), commentList.get(i).getCommentArticleId());
                commentList.get(i).setArticle(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得最新评论失败, limit:{}, cause:{}", limit, e);
        }
        return commentList;
    }
}
