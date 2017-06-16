package main.java.com.library.dao;

import main.java.com.library.model.Author;
import main.java.com.library.model.Book;

import java.util.List;
import java.util.Set;

public interface BookDao {
    public void addBook(Book book, Set<Author> authors) throws Exception;
    public void updateBook(Book book) throws Exception;
    public void removeBook(long id) throws Exception;
    public void removeAuthorsFromBook(Book book, Set<Author> authors) throws Exception;
    public Book getBookById(long id) throws Exception;
    public Set listBooks() throws Exception;
    public Set getAuthorsByBook(long id) throws Exception;
    public void associateAuthToBook(Book book, Set<Author> authors) throws Exception;
}
