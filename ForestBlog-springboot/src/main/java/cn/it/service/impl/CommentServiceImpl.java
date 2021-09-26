package cn.it.service.impl;

import cn.it.entity.Article;
import cn.it.entity.Comment;
import cn.it.enums.ArticleStatus;
import cn.it.mapper.ArticleMapper;
import cn.it.mapper.CommentMapper;
import cn.it.service.CommentService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luis
 * @date 2021/4/22 21:08
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public void insertComment(Comment comment) {

    }

    @Override
    public List<Comment> listCommentByArticleId(Integer articleId) {
        return null;
    }

    @Override
    public Comment getCommentById(Integer id) {
        return null;
    }

    @Override
    public PageInfo<Comment> listCommentByPage(Integer pageIndex, Integer pageSize) {
        return null;
    }

    @Override
    public List<Comment> listComment() {
        return null;
    }

    @Override
    public void deleteComment(Integer id) {

    }

    @Override
    public void updateComment(Comment comment) {

    }

    @Override
    public Integer countComment() {
        return null;
    }

    @Override
    public List<Comment> listRecentComment(Integer limit) {
        List<Comment> commentList = null;
        try {
            commentList = commentMapper.listRecentComment(limit);
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

    @Override
    public List<Comment> listChildComment(Integer id) {
        return null;
    }
}
