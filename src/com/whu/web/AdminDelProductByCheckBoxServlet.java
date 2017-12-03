package com.whu.web;

import com.whu.service.AdminProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet(name = "Servlet4", urlPatterns = {"/adminDelProductByCheckBox"})
public class AdminDelProductByCheckBoxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] checks = request.getParameterValues("check");
        if (checks == null) {
            response.sendRedirect(request.getContextPath() + "/adminProductList");
            return;
        }
        AdminProductService service = new AdminProductService();
        try {
            service.delproductBYPids(checks);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/adminProductList");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
