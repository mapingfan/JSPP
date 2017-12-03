package com.whu.web;

import com.whu.domain.Category;
import com.whu.domain.Product;
import com.whu.service.AdminProductService;
import com.whu.vo.Condition;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Servlet7",urlPatterns = {"/adminSearchProductList"})
public class AdminSearchProductListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //  查询条件封装到condition实体里；
//        传递封装好的条件；
        Map<String, String[]> parameterMap = request.getParameterMap();
        Condition condition = new Condition();
        try {
            BeanUtils.populate(condition, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        AdminProductService service = new AdminProductService();
        List<Category> categoryList = null;
        try {
            categoryList = service.findAllCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Product> productList = null;
        try {
            productList = service.findProductByCondition(condition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("condition", condition);
        request.setAttribute("productlist", productList);
        request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
