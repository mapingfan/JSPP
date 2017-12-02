package com.whu.service;

import com.whu.dao.ProductDao;
import com.whu.domain.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    public List<Product> findAllProduct() throws SQLException {
        ProductDao productDao = new ProductDao();
        List<Product> productList = productDao.findAllProduct();
        return productList;
    }
}
