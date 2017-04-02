package com.library.dao;

import com.library.model.Author;
import com.library.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    public void addBook(Book book, List<Author> authors) throws SQLException;
    public void updateBook(Book book, List<Author> authors) throws SQLException;
    public void removeBook(int id) throws SQLException;
    public void removeAuthorsFromBook(Book book, List<Author> authors) throws SQLException;
    public Book getBookById(int id) throws SQLException;
    public List<Book> listBooks() throws SQLException;
    public List<Author> getAuthorsByBook(int id) throws SQLException;
}
