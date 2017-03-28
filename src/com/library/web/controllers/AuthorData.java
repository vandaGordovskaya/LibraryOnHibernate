package com.library.web.controllers;


import com.library.dao.AuthorDao;
import com.library.dao.DaoFactory;
import com.library.model.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AuthorData extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AuthorDao authorDao = DaoFactory.getInstance().getAuthorDao();
            int id = Integer.parseInt(req.getParameter("authorId"));
            String authorName = req.getParameter("authorName");
            List<Book> listBooks = authorDao.getBooksByAuthor(id);
            req.setAttribute("listBooks", listBooks);
            req.setAttribute("authorName", authorName);

            getServletContext().getRequestDispatcher("/jsp/authorData.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            doGet(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
