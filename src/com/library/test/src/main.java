package com.library.test.src;

import com.library.dao.AuthorDaoImpl;
import com.library.dao.BookDao;
import com.library.dao.BookDaoImpl;
import com.library.model.Author;
import com.library.model.Book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class main {
public static void main(String[] args) throws SQLException {
    BookDaoImpl bookDao = new BookDaoImpl();
    Book book = new Book();
    book.setId(7);
    book.setName("7777");

    AuthorDaoImpl authorDao = new AuthorDaoImpl();
    Author author1 = new Author();
    author1.setId(15);
    author1.setName("createdAuth1");

    Author author2 = new Author();
    author2.setId(16);
    author2.setName("createdAuth2");

    authorDao.addAuthor(author1);
    authorDao.addAuthor(author2);
    List<Author> authors = new ArrayList<>();
    authors.add(author1);
    authors.add(author2);
    bookDao.addBook(book, authors);
    }
}
