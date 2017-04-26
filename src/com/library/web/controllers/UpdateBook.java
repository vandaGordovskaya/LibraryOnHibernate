package com.library.web.controllers;

import com.library.dao.AuthorDao;
import com.library.dao.BookDao;
import com.library.dao.DaoFactory;
import com.library.model.Author;
import com.library.model.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vanda on 01.04.2017.
 */
public class UpdateBook extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDao bookDao = DaoFactory.getInstance().getBookDao();

        String idTemp = req.getParameter("bookId");
        String bookName = req.getParameter("bookName");
        int id = Integer.parseInt(idTemp);
        req.setAttribute("bookName", bookName);
        req.setAttribute("bookId", id);

        //get list Authors from the system
        AuthorDao authorDao = DaoFactory.getInstance().getAuthorDao();
        List<Author> authors = null;
        try {
            authors = authorDao.listAuthors();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //get associated Authors
        List<Author> bookAuthors = null;
        try {
            bookAuthors = bookDao.getAuthorsByBook(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("associatedAuthors", bookAuthors);

        //find Authors which are not associated with the Book
        List<Author> notAssociatedAuthors = authors;
        notAssociatedAuthors.removeAll(bookAuthors);
        req.setAttribute("notAssociatedAuthors", notAssociatedAuthors);

        getServletContext().getRequestDispatcher("/jsp/updateBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDao bookDao = DaoFactory.getInstance().getBookDao();

        String idTemp = req.getParameter("bookId");
        String updatedName = req.getParameter("updatedBookName");
        int id = Integer.parseInt(idTemp);
        String bookName = req.getParameter("bookName");
        req.setAttribute("bookName", bookName);
        req.setAttribute("bookId", id);
        String[] associatedAuthors = req.getParameterValues("associatedAuthors");
        req.setAttribute("associatedAuthors", associatedAuthors);
        String[] notAssociatedAuthors = req.getParameterValues("notAssociatedAuthors");
        req.setAttribute("notAssociatedAuthors", notAssociatedAuthors);

        //get associated Authors
        List<Author> bookAuthors = null;
        try {
            bookAuthors = bookDao.getAuthorsByBook(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //validation Book must have at least one Author
        String[] removeAuth = req.getParameterValues("disassociateAuthors");
        String[] associateAuth = req.getParameterValues("associateAuthors");

        if(removeAuth != null) {
            if(associateAuth != null) {
                Book updatedBook = updateBookName(id, updatedName);
                disassociateAuthFromBook(updatedBook, removeAuth);
                associateAuthWithBook(updatedBook, associateAuth);
                resp.sendRedirect("./books");
            } else {
                if (removeAuth.length == bookAuthors.size()) {
                    req.setAttribute("errorMsg", "Book must have at least one Author!");
                    getServletContext().getRequestDispatcher("/jsp/updateBook.jsp").forward(req, resp);
                } else {
                    Book updatedBook = updateBookName(id, updatedName);
                    disassociateAuthFromBook(updatedBook, removeAuth);
                    resp.sendRedirect("./books");
                }
            }
        } else if(associateAuth != null) {
            Book updatedBook = updateBookName(id, updatedName);
            associateAuthWithBook(updatedBook, associateAuth);

            resp.sendRedirect("./books");
        } else {
            Book updatedBook = updateBookName(id, updatedName);
            resp.sendRedirect("./books");
            }
        }

    protected Book updateBookName(int id, String name) {
        BookDao bookDao = DaoFactory.getInstance().getBookDao();
        Book updatedBook = new Book();
        updatedBook.setId(id);
        updatedBook.setName(name);
        try {
            bookDao.updateBook(updatedBook);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return updatedBook;
    }

    protected void associateAuthWithBook(Book book, String[] associateAuth) {
        BookDao bookDao = DaoFactory.getInstance().getBookDao();
        List<Author> addedAuthors = new ArrayList<>();
        for (int i = 0; i < associateAuth.length; i++) {
            Author newAuthor = new Author();
            newAuthor.setId(Integer.parseInt(associateAuth[i]));
            addedAuthors.add(newAuthor);
        }
        try {
            bookDao.associateAuthToBook(book, addedAuthors);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    protected void disassociateAuthFromBook(Book book, String[] removeAuth) {
        BookDao bookDao = DaoFactory.getInstance().getBookDao();
        List<Author> deleteAuthors = new ArrayList<>();
        for (int i = 0; i < removeAuth.length; i++) {
            Author newAuthor = new Author();
            newAuthor.setId(Integer.parseInt(removeAuth[i]));
            deleteAuthors.add(newAuthor);
        }
        try {
            bookDao.removeAuthorsFromBook(book, deleteAuthors);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}