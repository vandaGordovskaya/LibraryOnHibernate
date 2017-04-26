package com.library.web.controllers;

import com.library.dao.AuthorDao;
import com.library.dao.AuthorDaoImpl;
import com.library.dao.BookDao;
import com.library.dao.DaoFactory;
import com.library.model.Author;
import com.library.model.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vanda on 11.03.2017.
 */
public class CreateBook extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthorDao authorDao = DaoFactory.getInstance().getAuthorDao();
        try {
            List<Author> authors = authorDao.listAuthors();
            req.setAttribute("listAuthorsFromDB", authors);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/jsp/createBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDao bookDao = DaoFactory.getInstance().getBookDao();
        AuthorDao authorDao = DaoFactory.getInstance().getAuthorDao();
        int id = Integer.parseInt(req.getParameter("bookId"));
        String bookName = req.getParameter("bookName");
        String[] getAuthors = req.getParameterValues("getListAuthors");

        try {
            List<Author> authors = authorDao.listAuthors();

            List<Author> addedAuthors = new ArrayList<Author>();
            Book newBook = new Book();
            newBook.setName(bookName);
            newBook.setId(id);

            for(Author findAuthor : authors) {
                for(String gettedAuth : getAuthors) {
                    if(gettedAuth.equals(findAuthor.getName())) {
                        Author newAuthor = new Author();
                        newAuthor.setId(findAuthor.getId());
                        newAuthor.setName(findAuthor.getName());
                        addedAuthors.add(newAuthor);
                    }
                }
            }
            bookDao.addBook(newBook, addedAuthors);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("./books");
    }
}
