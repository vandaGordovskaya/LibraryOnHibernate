package com.library.dao;

import com.library.model.Author;
import com.library.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BookDaoImpl implements BookDao {

    private Connection connection;
    private static BookDaoImpl instance;


    public static synchronized BookDaoImpl getInstance() throws Exception {
        if (instance == null) {
            instance = new BookDaoImpl();
        }
        return instance;
    }


    public List<Book> listBooks() throws SQLException {
        List<Book> listBook = new ArrayList<Book>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT id, name FROM BOOKS");
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString(2));

                listBook.add(book);
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
        return listBook;
    }


    public List<Author> getAuthorsByBook(int id) throws SQLException {
        List<Author> bookAuth = new ArrayList<Author>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT AUTHORS.NAME, AUTHORS.ID FROM AUTHORS "
                    + "LEFT JOIN BOOK_AUTH ON AUTHORS.ID=BOOK_AUTH.AUTH_ID "
                    + "LEFT JOIN BOOKS ON BOOK_AUTH.BOOK_ID=BOOKS.ID "
                    + "WHERE BOOKS.ID=" + id + " ORDER BY AUTHORS.NAME;");
            while (rs.next()) {
                Author authors = new Author();
                authors.setName(rs.getString(1));
                authors.setName(rs.getString(2));

                bookAuth.add(authors);
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
        return bookAuth;
    }

    public void addBook(Book book, List<Author> authors) throws SQLException {
        Statement stmt = null;
        String INSERT_BOOK = "INSERT INTO BOOKS VALUES (?,?);";
        String ASSOCIATE_AUTHORS = "INSERT INTO BOOK_AUTH(BOOK_ID, AUTH_ID) VALUES (?, ?)";
        try {
            connection = ConnectionFactory.getConnection();
            stmt = connection.createStatement();
            PreparedStatement pstmt = connection.prepareStatement(INSERT_BOOK);
            pstmt.setInt(1, book.getId());
            pstmt.setString(2, book.getName());
            pstmt.executeUpdate();
            for(Author author : authors) {
                pstmt = connection.prepareStatement(ASSOCIATE_AUTHORS);
                pstmt.setInt(1, book.getId());
                pstmt.setInt(2, author.getId());
                pstmt.executeUpdate();
            }
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(stmt != null) {
                stmt.close();
            }
        }
    }

    public void removeBook(int id) throws SQLException{
        Statement stmt = null;
        try {
            connection = ConnectionFactory.getConnection();
            stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM BOOKS WHERE ID=" + id);
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(stmt != null) {
                stmt.close();
            }
        }
    }

    public void removeAuthorsFromBook(Book book, List<Author> authors) throws SQLException {
        Statement stmt = null;
        String REMOVE_AUTHOR = "DELETE FROM BOOK_AUTH WHERE BOOK_ID=? AND AUTH_ID=?";
        try {
            connection = ConnectionFactory.getConnection();
            stmt = connection.createStatement();
            for(Author author : authors) {
                PreparedStatement pstmt = connection.prepareStatement(REMOVE_AUTHOR);
                pstmt.setInt(1, book.getId());
                pstmt.setInt(2, author.getId());
                pstmt.executeUpdate();
            }
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(stmt != null) {
                stmt.close();
            }
        }
    }

    public void updateBook(Book book, List<Author> authors) throws SQLException {
        Statement stmt = null;
        String UPDATE_BOOK = "UPDATE BOOKS SET NAME=? WHERE ID=?";
        String ASSOCIATE_AUTHORS = "INSERT INTO BOOK_AUTH(BOOK_ID, AUTH_ID) VALUES (?, ?)";

        try{
            connection = ConnectionFactory.getConnection();
            stmt = connection.createStatement();
            PreparedStatement pstmt = connection.prepareStatement(UPDATE_BOOK);
            pstmt.setString(1, book.getName());
            pstmt.setInt(2, book.getId());
            pstmt.executeUpdate();
            for(Author author : authors) {
                pstmt = connection.prepareStatement(ASSOCIATE_AUTHORS);
                pstmt.setInt(1, book.getId());
                pstmt.setInt(2, author.getId());
                pstmt.executeUpdate();
            }
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(stmt != null) {
                stmt.close();
            }
        }
    }

    public Book getBookById(int id) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;

        Book book = new Book();

        try{
            connection = ConnectionFactory.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery( "SELECT NAME FROM BOOKS "
                    + "WHERE ID=" + id);
            while (rs.next()) {
                book.setName(rs.getString(1));
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
        return book;
    }

}
