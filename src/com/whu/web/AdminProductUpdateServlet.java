package com.whu.web;

import com.whu.domain.Product;
import com.whu.service.AdminProductService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "Servlet6", urlPatterns = {"/adminProductUpdate"})
public class AdminProductUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> requestParameterMap = request.getParameterMap();
        Product product = new Product();
        //2.封装数据;
        try {
            BeanUtils.populate(product, requestParameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //手动设置表单中没有的数据
        product.setPimage("products/1/c_0001.jpg");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String pdate = simpleDateFormat.format(new Date());
        product.setPdate(pdate);
        product.setPflag(0);
        //3.传递数据给service；
        AdminProductService adminProductService = new AdminProductService();
        try {
            adminProductService.updateProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/adminProductList");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
