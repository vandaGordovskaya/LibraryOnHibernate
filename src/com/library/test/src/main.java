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
        Book book = new Book();
        book.setId(3);
        book.setName("updatedBookViaTest");
        BookDaoImpl bookDao = new BookDaoImpl();
        //bookDao.updateBook(book);
        //AuthorDaoImpl authorDao = new AuthorDaoImpl();
       //authorDao.removeAuthor(16);
    }
}
