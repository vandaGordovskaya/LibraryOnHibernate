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
import java.util.List;

public class Books extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BookDao bookDao = DaoFactory.getInstance().getBookDao();
            List<Book> books = bookDao.listBooks();
            req.setAttribute("listBooks", books);
            AuthorDao authorDao = DaoFactory.getInstance().getAuthorDao();
            List<Author> authors = authorDao.listAuthors();
            req.getServletContext().setAttribute("listAuthors", authors);
            getServletContext().getRequestDispatcher("/jsp/books.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
