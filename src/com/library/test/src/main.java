package com.library.test.src;

import com.library.dao.AuthorDaoImpl;
import com.library.dao.BookDao;
import com.library.dao.BookDaoImpl;
import com.library.dao.UserDaoImpl;
import com.library.model.Author;
import com.library.model.Book;
import com.library.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class main {
    public static void main(String[] args) throws SQLException {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User();
        user.setName("vreader");
        user.setPassword("3e4");
        //if (userDao.checkUserExist("vadmin")){
            System.out.println(userDao.checkAdminRole(user));
        //} else {
        //    System.out.println("No");
        //}
        //bookDao.updateBook(book);
        /*AuthorDaoImpl authorDao = new AuthorDaoImpl();
        Author author = new Author();
        author.setId(11);
        List<Author> authList = new ArrayList<>();
        authList.add(author);
        bookDao.removeAuthorsFromBook(book, authList);
       //authorDao.removeAuthor(16);*/
    }
}
