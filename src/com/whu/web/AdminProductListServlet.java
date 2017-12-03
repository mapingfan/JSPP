package com.whu.web;

import com.whu.domain.Product;
import com.whu.service.AdminProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Servlet", urlPatterns = {"/adminProductList"})
public class AdminProductListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        传递请求;
        AdminProductService adminProductService = new AdminProductService();
        List<Product> productList = null;
        try {
            productList = adminProductService.findAllProduct();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("productlist", productList);
        request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
