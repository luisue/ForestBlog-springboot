package cn.it.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author luis
 * @date 2021/4/8 21:41
 */
@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {
    private String user = "user";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //根据session的用户判断角色权限，根据权限转发不同的页面
        if (request.getSession().getAttribute(user) == null) {
            response.sendRedirect("/login");
            return false;
        }

        return super.preHandle(request, response, handler);
    }
}
