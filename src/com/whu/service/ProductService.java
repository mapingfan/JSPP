package com.whu.service;

import com.whu.dao.ProductDao;
import com.whu.domain.Product;
import com.whu.vo.PageBean;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    public List<Product> findAllProduct() throws SQLException {
        ProductDao productDao = new ProductDao();
        List<Product> productList = productDao.findAllProduct();
        return productList;
    }


    public PageBean finPageBean(int currentPage, int currentCount) throws SQLException {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setCurrentCount(currentCount);
        ProductDao dao = new ProductDao();
        int totalCount = dao.getTotalCount();
        pageBean.setTotalCount(totalCount);
        int totalPage = totalCount / currentCount == 0 ? totalCount / currentCount : totalCount / currentCount + 1;
        pageBean.setTotalPage(totalPage);
        List<Product> products = dao.findProductForPageBean(currentPage,currentCount);
        pageBean.setProductList(products);
        return pageBean;
    }

    public List<Product> findProductByWord(String word) throws SQLException {
        ProductDao dao = new ProductDao();
        List<Product> productList = dao.findProductByWord(word);
        return productList;
    }
}
