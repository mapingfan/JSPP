package com.whu.service;

import com.whu.dao.UserDao;
import com.whu.vo.User;

import java.sql.SQLException;

public class UserService {
    public Boolean login(User user) throws SQLException {
        UserDao dao = new UserDao();
        Boolean flag = dao.login(user);
        return flag;
    }
}
