package main.java.com.library.dao;

import main.java.com.library.model.User;

import java.sql.SQLException;

public interface UserDao {
    public boolean checkAdminRole(User user) throws Exception;
    public boolean checkUserExist(User user) throws Exception;
}
