package com.whu.dao;

import com.whu.utils.DataSourceUtils;
import com.whu.vo.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.taglibs.standard.tag.common.sql.DataSourceUtil;

import java.sql.SQLException;

public class UserDao {
    public Boolean login(User user) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from user where username = ? and password = ?";
        Object query = runner.query(sql, new ScalarHandler(), user.getUsername(), user.getPassword());
        if (query == null) {
            return false;
        }
        return true;
    }
}
