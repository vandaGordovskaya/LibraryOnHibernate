package com.library.web.controllers;

import com.library.dao.AuthorDao;
import com.library.dao.DaoFactory;
import com.library.model.Author;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Vanda on 11.03.2017.
 */
public class CreateAuthor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthorDao authorDao = DaoFactory.getInstance().getAuthorDao();
        int id = Integer.parseInt(req.getParameter("authorId"));
        String authorName = req.getParameter("authorName");

        Author newAuthor = new Author();
        newAuthor.setName(authorName);
        newAuthor.setId(id);
        try {
            authorDao.addAuthor(newAuthor);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("./authors");
    }
}
