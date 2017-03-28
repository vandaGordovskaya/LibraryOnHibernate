package com.library.web.controllers;


import com.library.dao.BookDao;
import com.library.dao.DaoFactory;
import com.library.model.Author;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookData extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BookDao bookDao = DaoFactory.getInstance().getBookDao();
            int id = Integer.parseInt(req.getParameter("bookId"));
            String bookName = req.getParameter("bookName");
            List<Author> authors = bookDao.getAuthorsByBook(id);
            req.setAttribute("listAuthors", authors);
            req.setAttribute("bookName", bookName);

            getServletContext().getRequestDispatcher("/jsp/bookData.jsp").forward(req, resp);
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
