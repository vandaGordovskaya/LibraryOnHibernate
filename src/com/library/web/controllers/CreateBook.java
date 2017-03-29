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
        try {
            BookDao bookDao = DaoFactory.getInstance().getBookDao();

            AuthorDao authorDao = DaoFactory.getInstance().getAuthorDao();
            List<Author> authors = authorDao.listAuthors();
            req.setAttribute("listAuthorsFromDB", authors);

            int id = Integer.parseInt(req.getParameter("bookId"));
            String bookName = req.getParameter("bookName");
            String[] getAuthors = req.getParameterValues("getListAuthors");

            List<Author> addedAuthors = new ArrayList<Author>();
            Book newBook = new Book();
            newBook.setName(bookName);
            newBook.setId(id);


            for(Author findAuthor : authors) {
                String name = findAuthor.getName();
                for(int i = 0; i < getAuthors.length; i++) {
                    if(name.equals(getAuthors[i])) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/jsp/createBook.jsp").forward(req, resp);
        resp.sendRedirect("./books");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
