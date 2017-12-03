package com.whu.service;

import com.whu.dao.AdminProductDao;
import com.whu.domain.Category;
import com.whu.domain.Product;

import java.sql.SQLException;
import java.util.List;

public class AdminProductService {
    /* 查询所有商品*/
    public List<Product> findAllProduct() throws SQLException {
        AdminProductDao adminProductDao = new AdminProductDao();
        List<Product> productList = adminProductDao.findAllProduct();
        return productList;
    }

    public List<Category> findAllCategory() throws SQLException {
        AdminProductDao dao = new AdminProductDao();
        List<Category> categoryList = dao.findAllCategory();
        return categoryList;
    }

    public void addProduct(Product product) throws SQLException {
        AdminProductDao dao = new AdminProductDao();
        dao.addProduct(product);
    }

    public void delProductByPid(String pid) throws SQLException {
        AdminProductDao dao = new AdminProductDao();
        dao.delProductByPid(pid);
    }

    public void delproductBYPids(String[] checks) throws SQLException {
        AdminProductDao dao = new AdminProductDao();
        dao.delproductBYPids(checks);
    }

    public Product findProductByPid(String pid) throws SQLException {
        AdminProductDao dao = new AdminProductDao();
        Product product = dao.findProductByPid(pid);
        return product;
    }

    public void updateProduct(Product product) throws SQLException {
        AdminProductDao dao = new AdminProductDao();
        dao.updateProduct(product);
    }
}
