package cn.it.controller.home;

import cn.it.entity.Article;
import cn.it.entity.Comment;
import cn.it.entity.Notice;
import cn.it.entity.Tag;
import cn.it.enums.ArticleStatus;
import cn.it.enums.NoticeStatus;
import cn.it.service.ArticleService;
import cn.it.service.CommentService;
import cn.it.service.NoticeService;
import cn.it.service.TagService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = {"/","/article"})
    public String index(@RequestParam(required = false,defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false,defaultValue = "10") Integer pageSize, Model model) {
        HashMap<String, Object> criteria = new HashMap<>(1);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());

        //文章列表
        PageInfo<Article> articleList = articleService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articleList);

        //公告
        List<Notice> noticeList = noticeService.listNotice(NoticeStatus.NORMAL.getValue());
        model.addAttribute("noticeList", noticeList);

        //侧边栏显示
        //标签列表显示
        List<Tag> allTagList = tagService.listTag();
        model.addAttribute("allTagList", allTagList);

        //最新评论
        List<Comment> recentCommentList = commentService.listRecentComment(10);
        model.addAttribute("recentCommentList", recentCommentList);
        //TODO
        model.addAttribute("pageUrlPrefix", "/article?pageIndex");

        return "Home/index";
    }

    @RequestMapping("/search")
    public String serach(
            @RequestParam("keywords") String keywords,
            @RequestParam(required = false,defaultValue = "1") Integer pageIndex,
            @RequestParam(required = false,defaultValue = "10") Integer pageSize,Model model) {

        //匹配含有关键字的文章
        HashMap<String, Object> criteria = new HashMap<>(2);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());
        criteria.put("keywords", keywords);
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);

        //侧边栏
        //标签列表显示
        List<Tag> allTagList = tagService.listTag();
        model.addAttribute("allTagList", allTagList);

        //获取随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList", randomArticleList);

        //获取热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);

        //最新评论
        List<Comment> recentCommentList = commentService.listRecentComment(10);
        model.addAttribute("recentCommentList", recentCommentList);
        //TODO
        model.addAttribute("pageUrlPrefix", "/search?pageIndex");

        return "Home/Page/search";
    }

    //TODO 404 500

}
