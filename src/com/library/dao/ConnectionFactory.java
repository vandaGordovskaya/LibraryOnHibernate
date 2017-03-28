package com.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static ConnectionFactory instance = new ConnectionFactory();
    public static final String DB_URL = "jdbc:h2:~/test";
    public static final String LOGIN = "sa";
    public static final String PASSWORD = "";
    public static final String H2_DRIVER = "org.h2.Driver";

    private ConnectionFactory() {
        try {
            Class.forName(H2_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
        } catch(SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection() throws SQLException {
        return instance.createConnection();
    }

}
