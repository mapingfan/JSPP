package com.whu.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlet8", urlPatterns = {"/ajaxServlet"})
public class AjaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        if (name.equals("张三")) {
            response.getWriter().write("{\"success\":\"可以注册\"}");
            return;
        } else {
            response.getWriter().write("{\"success\":\"不可以注册\"}");
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
