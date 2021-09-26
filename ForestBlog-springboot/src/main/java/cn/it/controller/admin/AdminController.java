package cn.it.controller.admin;

import cn.it.entity.Article;
import cn.it.entity.Comment;
import cn.it.entity.User;
import cn.it.service.ArticleService;
import cn.it.service.CommentService;
import cn.it.service.UserService;
import cn.it.util.TokenUtils;
import cn.it.util.annotation.UserLoginToken;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.it.util.MyUtils.getIpAddr;

/**
 * @author luis
 * @date 2021/9/18 20:25
 */
@Controller
public class AdminController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    /**
     * 后台首页
     * @param model
     * @return
     */
    @UserLoginToken
    @RequestMapping("/admin")
    public String index(Model model) {
        //最近文章列表
        List<Article> articleList = articleService.listRecentArticle(5);
        model.addAttribute("articleList", articleList);
        //最近评论列表
        List<Comment> commentList = commentService.listRecentComment(5);
        model.addAttribute("commentList", commentList);
        return "Admin/index";
    }

    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/login")
    public String loginPage() {
        return "Admin/login";
    }

    /**
     * 登录验证
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/loginVerify.do",method = RequestMethod.POST)
    @ResponseBody
    public String loginVerify(HttpServletRequest request, HttpServletResponse response)  {
        Map<String, Object> map = new HashMap<String, Object>();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");
        User user = userService.getUserByNameOrEmail(username);

        if(user==null) {
            map.put("code",0);
            map.put("msg","用户名无效！");
        } else if(!user.getUserPass().equals(password)) {
            map.put("code",0);
            map.put("msg","密码错误！");
        } else {
            //登录成功
            map.put("code",1);
            map.put("msg","");
            //添加session
            request.getSession().setAttribute("user", user);
            //添加cookie
            if(rememberme!=null) {
                //创建两个Cookie对象
                Cookie nameCookie = new Cookie("username", username);
                //设置Cookie的有效期为3天
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                Cookie pwdCookie = new Cookie("password", password);
                pwdCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
            user.setUserLastLoginTime(new Date());
            user.setUserLastLoginIp(getIpAddr(request));
            userService.updateUser(user);

        }
        String result = JSONObject.fromObject(map).toString();
        return result;
    }

    /**
     * jwt登录验证
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/loginVerify",method = RequestMethod.POST)
    @ResponseBody
    public HashMap login(HttpServletRequest request, HttpServletResponse response){
        //存储返回信息
        HashMap<String, Object> map = new HashMap<>();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");
        User user = userService.getUserByNameOrEmail(username);

        if (user == null) {
            map.put("code",0);
            map.put("msg", "用户名无效");
        } else if (!password.equals(user.getUserPass())) {
            map.put("code",0);
            map.put("msg", "密码错误");
        } else {
            //登录成功
            map.put("code",1);
            map.put("msg", "");
            //将用户对象添加进session
            request.getSession().setAttribute("user",user);
            //记住密码，添加cookie
            if (rememberme != null) {
                Cookie nameCookie = new Cookie("username", username);
                //设置有效期1天
                nameCookie.setMaxAge(60 * 60 * 24);
                Cookie pwdCookie = new Cookie("password", password);
                nameCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);

                //设置Token
                String token = TokenUtils.getToken(user);
                Cookie tokenCookie = new Cookie("token", token);
                nameCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(tokenCookie);

            }
            user.setUserLastLoginTime(new Date());
            user.setUserLastLoginIp(getIpAddr(request));
            userService.updateUser(user);
        }

        return map;
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/admin/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/login";
    }

}
