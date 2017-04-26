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
 * Created by Vanda on 05.04.2017.
 */
public class UpdateAuthor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthorDao authorDao = DaoFactory.getInstance().getAuthorDao();

        String idTemp = req.getParameter("authorId");
        String updatedName = req.getParameter("updatedAuthorName");
        String authorName = req.getParameter("authorName");
        int id = Integer.parseInt(idTemp);
        req.setAttribute("authorName", authorName);
        req.setAttribute("authorId", id);

        Author updatedAuthor = new Author();
        updatedAuthor.setId(id);
        updatedAuthor.setName(updatedName);
        try {
            authorDao.updateAuthor(updatedAuthor);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/jsp/updateAuthor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthorDao authorDao = DaoFactory.getInstance().getAuthorDao();

        String idTemp = req.getParameter("authorId");
        String updatedName = req.getParameter("updatedAuthorName");
        String authorName = req.getParameter("authorName");
        int id = Integer.parseInt(idTemp);
        req.setAttribute("authorName", authorName);
        req.setAttribute("authorId", id);

        Author updatedAuthor = new Author();
        updatedAuthor.setId(id);
        updatedAuthor.setName(updatedName);
        try {
            authorDao.updateAuthor(updatedAuthor);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("./authors");
    }
}
