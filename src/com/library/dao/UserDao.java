package com.library.dao;

import com.library.model.User;

import java.sql.SQLException;

public interface UserDao {
    public boolean checkAdminRole(User user) throws SQLException;
    public boolean checkUserExist(User user) throws SQLException;
}
