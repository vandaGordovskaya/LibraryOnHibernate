package main.java.com.library.dao;

import main.java.com.library.model.Author;
import main.java.com.library.model.Book;

import java.util.List;
import java.util.Set;

public interface AuthorDao {
    public void addAuthor(Author author) throws Exception;
    public void updateAuthor(long auth_id, Author author) throws Exception;
    public void removeAuthor(long id) throws Exception;
    public Author getAuthorById(long id) throws Exception;
    public Set listAuthors() throws Exception;
    public Set getBooksByAuthor(long id) throws Exception;
}
