package com.library.web.controllers;

import com.library.dao.BookDao;
import com.library.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Vanda on 19.03.2017.
 */
public class DeleteBook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDao bookDao = DaoFactory.getInstance().getBookDao();

        String idTemp = req.getParameter("bookId");
        int id = Integer.parseInt(idTemp);
        try {
            bookDao.removeBook(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //getServletContext().getRequestDispatcher("/jsp/books.jsp").forward(req, resp);
        resp.sendRedirect("./books");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
