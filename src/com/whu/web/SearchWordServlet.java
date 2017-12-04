package com.whu.web;

import com.google.gson.Gson;
import com.whu.domain.Product;
import com.whu.service.ProductService;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Servlet9", urlPatterns = {"/searchWord"})
public class SearchWordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String word = request.getParameter("word");
        ProductService service = new ProductService();
        List<Product> productList = null;
        try {
            productList = service.findProductByWord(word);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //json转换工具。对象或集合转换为json；
        /*JSONArray fromObject = JSONArray.fromObject(productList);
        String str = fromObject.toString();
        System.out.println(str);*/
        Gson gson = new Gson();
        String json = gson.toJson(productList);
        System.out.println(json);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
