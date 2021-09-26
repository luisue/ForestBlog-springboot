package cn.it.service.impl;

import cn.it.entity.Article;
import cn.it.entity.Category;
import cn.it.mapper.ArticleCategoryRefMapper;
import cn.it.mapper.ArticleMapper;
import cn.it.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author luis
 * @date 2021/3/28 17:14
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleCategoryRefMapper articleCategoryRefMapper;

    @Override
    public Integer countArticle(Integer status) {
        return null;
    }

    @Override
    public Integer countArticleComment() {
        return null;
    }

    @Override
    public Integer countArticleView() {
        return null;
    }

    @Override
    public Integer countArticleByCategoryId(Integer categoryId) {
        return null;
    }

    @Override
    public Integer countArticleByTagId(Integer tagId) {
        return null;
    }

    @Override
    public List<Article> listArticle(HashMap<String, Object> criteria) {
        return null;
    }

    @Override
    public List<Article> listRecentArticle(Integer limit) {
        return null;
    }

    @Override
    public void updateArticleDetail(Article article) {

    }

    @Override
    public void updateArticle(Article article) {

    }

    @Override
    public void deleteArticleBatch(List<Integer> ids) {

    }

    @Override
    public void deleteArticle(Integer id) {

    }

    @Override
    public PageInfo<Article> pageArticle(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Article> articleList = articleMapper.findAll(criteria);

        for (int i = 0; i < articleList.size(); i++) {
            //给每个article封装categoryList
            List<Category> categoryList = articleCategoryRefMapper.listCategoryByArticleId(articleList.get(i).getArticleId());
            if (categoryList == null || categoryList.size() == 0) {
                //创建一个新的List集合，并为其添加一个未分类的Category
                categoryList = new ArrayList<>();
                categoryList.add(Category.Default());
            }
            articleList.get(i).setCategoryList(categoryList);
        }
        return new PageInfo<>(articleList);
    }

    @Override
    public Article getArticleByStatusAndId(Integer status, Integer id) {
        return null;
    }

    @Override
    public List<Article> listArticleByViewCount(Integer limit) {
        return null;
    }

    @Override
    public Article getAfterArticle(Integer id) {
        return null;
    }

    @Override
    public Article getPreArticle(Integer id) {
        return null;
    }

    @Override
    public List<Article> listRandomArticle(Integer limit) {
        return articleMapper.listRandomArticle(limit);
    }

    @Override
    public List<Article> listArticleByCommentCount(Integer limit) {
        return articleMapper.listArticleByCommentCount(limit);
    }

    @Override
    public void insertArticle(Article article) {

    }

    @Override
    public void updateCommentCount(Integer articleId) {

    }

    @Override
    public Article getLastUpdateArticle() {
        return null;
    }

    @Override
    public List<Article> listArticleByCategoryId(Integer cateId, Integer limit) {
        return null;
    }

    @Override
    public List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit) {
        return null;
    }

    @Override
    public List<Integer> listCategoryIdByArticleId(Integer articleId) {
        return null;
    }

    @Override
    public List<Article> listAllNotWithContent() {
        return null;
    }
}
