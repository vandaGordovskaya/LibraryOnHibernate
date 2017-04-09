package com.library.dao;

import com.library.model.Author;
import com.library.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    private Connection connection;
    private static AuthorDaoImpl instance;


    public static synchronized AuthorDaoImpl getInstance() throws Exception {
        if (instance == null) {
            instance = new AuthorDaoImpl();
        }
        return instance;
    }

    public List<Author> listAuthors() throws SQLException {
        List<Author> listAuthors = new ArrayList<Author>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT id, name FROM AUTHORS");
            while (rs.next()) {
                Author author = new Author();
                author.setId(rs.getInt("id"));
                author.setName(rs.getString(2));

                listAuthors.add(author);
            }
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
        return listAuthors;
    }

    public List<Book> getBooksByAuthor(int id) throws SQLException {
        List<Book> authBooks = new ArrayList<Book>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT BOOKS.NAME FROM BOOKS "
                    + "LEFT JOIN BOOK_AUTH ON BOOKS.ID=BOOK_AUTH.BOOK_ID "
                    + "LEFT JOIN AUTHORS ON BOOK_AUTH.AUTH_ID=AUTHORS.ID "
                    + "WHERE AUTHORS.ID=" + id + " ORDER BY BOOKS.NAME;");
            while(rs.next()) {
                Book books = new Book();
                books.setName(rs.getString(1));

                authBooks.add(books);
            }

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
        return authBooks;
    }

    public void addAuthor(Author author) throws SQLException {
        Statement stmt = null;
        String INSERT_RECORD = "INSERT INTO AUTHORS VALUES (?,?);";

        try {
            connection = ConnectionFactory.getConnection();
            stmt = connection.createStatement();
            PreparedStatement pstmt = connection.prepareStatement(INSERT_RECORD);
            pstmt.setInt(1, author.getId());
            pstmt.setString(2, author.getName());
            pstmt.executeUpdate();
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(stmt != null) {
                stmt.close();
            }
        }
    }

    public void removeAuthor(int id) throws SQLException{
        Statement stmt = null;

        try {
            connection = ConnectionFactory.getConnection();
            stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM AUTHORS WHERE AUTHORS.ID=" + id);
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(stmt != null) {
                stmt.close();
            }
        }
    }

    public void updateAuthor(Author author) throws SQLException {
        Statement stmt = null;
        try{
            connection = ConnectionFactory.getConnection();
            stmt = connection.createStatement();
            int id = author.getId();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE AUTHORS SET "
                    + "NAME=? "
                    + "WHERE ID=" + id);
            pstmt.setString(1, author.getName());
            pstmt.executeUpdate();
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(stmt != null) {
                stmt.close();
            }
        }
    }

    public Author getAuthorById(int id) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        Author author = new Author();

        try{
            stmt = connection.createStatement();
            rs = stmt.executeQuery( "SELECT NAME FROM AUTHORS "
                    + "WHERE ID=" + id);
            while (rs.next()) {
                author.setName(rs.getString(1));
            }
        } finally {
            if(connection != null) {
                connection.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return author;
    }

}
