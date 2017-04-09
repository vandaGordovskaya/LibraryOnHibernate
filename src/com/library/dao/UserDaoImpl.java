package com.library.dao;

import com.library.model.User;

import java.sql.*;

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
    public boolean checkUserExist(User user) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        boolean isExist = true;
        //int existing;
        try {
            connection = ConnectionFactory.getConnection();
            stmt = connection.createStatement();
            PreparedStatement pstmt = connection.prepareStatement("SELECT ID FROM USERS WHERE NAME=? AND PASS=?");
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            rs = pstmt.executeQuery();
                if (!rs.next()) {
                    isExist = false;
            }
            return isExist;
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

    @Override
    public boolean checkAdminRole(User user) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        boolean isAdmin = true;
        String role = null;
        try{
            connection = ConnectionFactory.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT ROLE FROM USERS WHERE NAME=\'" + user.getName() + "\' AND PASS=\'" + user.getPassword() + "\'");
            if (rs.next()) {
                role = rs.getString(1);
            }
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
