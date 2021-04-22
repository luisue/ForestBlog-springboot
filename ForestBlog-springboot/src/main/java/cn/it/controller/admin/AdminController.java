package cn.it.controller.admin;

import cn.it.entity.User;
import cn.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

import static cn.it.util.MyUtils.getIpAddr;

/**
 * @author luis
 * @date 2021/4/8 21:27
 */
@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    /**
     * 后台首页
     * @param model
     * @return
     */
    @RequestMapping("/admin")
    public String index(Model model) {
        //TODO
        //文章列表
        //评论列表
        return "Admin/index";
    }

    /**
     * 登录验证
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/loginVerify",method = RequestMethod.POST)
    @ResponseBody
    public HashMap loginVerify(HttpServletRequest request, HttpServletResponse response) {
        //存储返回信息
        HashMap<String, Object> result = new HashMap<>();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //是否记住登录状态
        String rememberme = request.getParameter("rememberme");
        //通过用户名查找判断用户是否存在
        User user = userService.getUserByNameOrEmail(username);

        if (user == null) {
            result.put("code", 0);
            result.put("msg", "用户名无效");
        } else if(!user.getUserPass().equals(password)) {
            result.put("code",0);
            result.put("msg", "密码错误");
        } else {
            //登录成功
            result.put("code",1);
            result.put("msg", "");
            //添加session
            request.getSession().setAttribute("user",user);
            //添加cookie
            //是否记住密码
            if (rememberme != null) {
                Cookie nameCookie = new Cookie("username",username);
                //设置nameCookie有效期
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                Cookie pwdCookie = new Cookie("password", password);
                //设置pwdCookie有效期
                pwdCookie.setMaxAge(60 * 60 * 24  * 3);

                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);

                //设置当前登录时间
                user.setUserLastLoginTime(new Date());
                //设置当前登录ip
                user.setUserLastLoginIp(getIpAddr(request));
                //更新用户信息
                userService.updateUser(user);
            }

        }

        return result;
    }
}
