package com.whu.web;

import com.whu.service.UserService;
import com.whu.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Servlet10",urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        request.getParameter("au")
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        UserService userService = new UserService();
        Boolean flag = false;
        try {
            flag = userService.login(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (flag == true) {
            if (request.getParameter("autologin") != null) {
                Cookie cookie_username = new Cookie("username", user.getUsername());
                Cookie cookie_password = new Cookie("password", user.getPassword());
                cookie_username.setMaxAge(10 * 60);
                cookie_password.setMaxAge(10 * 60);
                cookie_password.setPath("/");
                cookie_username.setPath("/");
                response.addCookie(cookie_username);
                response.addCookie(cookie_password);
            }
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("/index.jsp");
        } else {
//            提示密码错误;
            request.setAttribute("error","账号或者密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
