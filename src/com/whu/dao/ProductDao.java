package com.whu.dao;

import com.whu.domain.Product;
import com.whu.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDao {

    public List<Product> findAllProduct() throws SQLException {
        //访问数据库取数据
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product";
        List<Product> productList = queryRunner.query(sql, new BeanListHandler<>(Product.class));
        return productList;
    }

    public int getTotalCount() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from product";
        Long count = (Long) queryRunner.query(sql, new ScalarHandler());
        return count.intValue();
    }


    public List<Product> findProductForPageBean(int currentPage, int currentCount) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "Select * from product limit ?,?";
        List<Product> productList = queryRunner.query(sql, new BeanListHandler<>(Product.class), (currentPage - 1) * currentCount, currentCount);
        return productList;
    }
}
