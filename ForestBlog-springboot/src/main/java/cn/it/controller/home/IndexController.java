package cn.it.controller.home;

import cn.it.entity.Article;
import cn.it.entity.Notice;
import cn.it.enums.ArticleStatus;
import cn.it.enums.NoticeStatus;
import cn.it.service.ArticleService;
import cn.it.service.NoticeService;
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

        System.out.println("git test");
        System.out.println("git test");
        System.out.println("git test");
        System.out.println("git test");
        System.out.println("git test");

        return "Home/index";
    }
}
