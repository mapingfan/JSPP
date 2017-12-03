package com.whu.dao;

import com.whu.domain.Category;
import com.whu.domain.Product;
import com.whu.utils.DataSourceUtils;
import com.whu.vo.Condition;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminProductDao {
    public List<Product> findAllProduct() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product";
        List<Product> productList = queryRunner.query(sql, new BeanListHandler<>(Product.class));
        return productList;
    }

    public List<Category> findAllCategory() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from category";
        List<Category> categoryList = queryRunner.query(sql, new BeanListHandler<>(Category.class));
        return categoryList;
    }

    public void addProduct(Product product) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into product values (?,?,?,?,?,?,?,?,?,?)";
        queryRunner.update(sql, product.getPid(), product.getPname(),
                            product.getMarket_price(), product.getShop_price(),
                            product.getPimage(), product.getPdate(),
                            product.getIs_hot(), product.getPdesc(),product.getPflag(), product.getCid());

    }

    public void delProductByPid(String pid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = " delete from product where pid = ?";
        queryRunner.update(sql, pid);
    }

    public void delproductBYPids(String[] checks) throws SQLException {
        for (int i = 0; i < checks.length; i++) {
            delProductByPid(checks[i]);
        }
    }

    public Product findProductByPid(String pid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product where pid = ?";
        Product product = queryRunner.query(sql, new BeanHandler<>(Product.class), pid);
        return product;
    }

    public void updateProduct(Product product) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update product set pname = ?, market_price = ?, shop_price = ?, " +
                "pimage = ?, pdate=?, is_hot =?, pdesc=?,pflag=?,cid=? where pid =?";

        queryRunner.update(sql,product.getPname(),
                product.getMarket_price(), product.getShop_price(),
                product.getPimage(), product.getPdate(),
                product.getIs_hot(), product.getPdesc(), product.getPflag(), product.getCid(), product.getPid());
    }

    public List<Product> findProductByCondition(Condition condition) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product where 1=1";
        List<String> conditionList = new ArrayList<>();
        if (condition != null) {
            if (condition.getPname() != null && condition.getPname().trim().length() != 0) {
                sql += " and pname like ? ";
                conditionList.add("%" + condition.getPname().trim() + "%");
            }
            if (condition.getIs_hot() != null && condition.getIs_hot().trim().length() != 0) {
                sql += " and is_hot = ? ";
                conditionList.add(condition.getIs_hot());
            }
            if (condition.getCid() != null && condition.getCid().trim().length() != 0) {
                sql += " and cid = ? ";
                conditionList.add(condition.getCid());
            }
        }
        List<Product> productList = runner.query(sql, new BeanListHandler<>(Product.class), conditionList.toArray());
        return productList;
    }
}
