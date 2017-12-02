package com.whu.dao;

import com.whu.domain.Product;
import com.whu.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDao {

    public List<Product> findAllProduct() throws SQLException {
        //访问数据库取数据
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product";
        List<Product> productList = queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
        return productList;
    }
}
