package com.whu.web;

import com.whu.domain.Category;
import com.whu.service.AdminProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "Servlet2", urlPatterns = {"/adminAddProductUI"})
public class adminAddProductUIServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获得商品类别
        AdminProductService service = new AdminProductService();
        List<Category> categoryList = null;
        try {
             categoryList = service.findAllCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/admin/product/add.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
