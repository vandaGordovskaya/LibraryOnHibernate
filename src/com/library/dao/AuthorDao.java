package com.library.dao;

import com.library.model.Author;
import com.library.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface AuthorDao {
    public void addAuthor(Author author) throws SQLException;
    public void updateAuthor(int id, Author author) throws SQLException;
    public void removeAuthor(int id) throws SQLException;
    public Author getAuthorById(int id) throws SQLException;
    public List<Author> listAuthors() throws SQLException;
    public List<Book> getBooksByAuthor(int id) throws SQLException;
}
