package com.whu.filter;

import com.whu.service.UserService;
import com.whu.vo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@WebFilter(filterName = "Filter", urlPatterns = {"/*"})
public class MyFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        Cookie[] cookies = request.getCookies();
        if (request.getSession().getAttribute("user") == null) {
                User user = new User();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    user.setUsername(cookie.getValue());
                }
                if (cookie.getName().equals("password")) {
                    user.setPassword(cookie.getValue());
                }
            }
            if (user.getUsername() == null || user.getPassword() == null) {
                user = null;
            }
            if (user != null) {
                UserService userService = new UserService();
                try {
                    if (userService.login(user) == false) {
                        user = null;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            request.getSession().setAttribute("user", user);
            chain.doFilter(request, resp);
        } else {
            chain.doFilter(request, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

class EnhancedRequest extends HttpServletRequestWrapper {

    HttpServletRequest request;

    public EnhancedRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }
//    如果有必须要需要重写别的方法，否则还是调用装饰前的request对象的方法；

    @Override
    public String getParameter(String name) {
        String parameter = request.getParameter(name);
        String result = null;
        try {
            result = new String(parameter.getBytes("iso8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
