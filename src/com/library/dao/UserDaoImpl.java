package com.library.dao;

import com.library.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoImpl implements UserDao {
    private Connection connection;
    private static UserDaoImpl instance;

    public static synchronized UserDaoImpl getInstance() throws Exception {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public boolean checkAdminRole(User user) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        boolean isAdmin = true;
        String role = null;
        try{
            connection = ConnectionFactory.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT ROLE FROM USERS WHERE USER=" + user);
            role = rs.getString(1);
            isAdmin = role.equals("admin")?true:false;
            return isAdmin;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(rs != null) {
                rs.close();
            }
            if(stmt != null) {
                stmt.close();
            }
        }
    }
}
