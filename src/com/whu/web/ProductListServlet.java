package com.whu.web;

import com.whu.domain.Product;
import com.whu.service.ProductService;
import com.whu.vo.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductListServlet",urlPatterns = {"/productList"})
public class ProductListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = new ProductService();
        PageBean pageBean = null;
        List<Product> productList = null;
        int currentPage ;
        String currentPageStr = request.getParameter("currentPage");
        if (currentPageStr == null) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(currentPageStr);
        }
        int currentCount = 12;
        try {
            pageBean = productService.finPageBean(currentPage, currentCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            productList = productService.findAllProduct();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("productList", productList);
        request.setAttribute("pageBean", pageBean);
        request.getRequestDispatcher("/product_list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
