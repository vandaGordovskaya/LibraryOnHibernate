package com.library.web.controllers;

import com.library.dao.DaoFactory;
import com.library.dao.UserDao;
import com.library.model.User;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String pass = req.getParameter("pass");
        req.setAttribute("userName", userName);
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        User user = new User();
        user.setName(userName);
        user.setPassword(pass);

        try {
            if(userDao.checkUserExist(user)) {
                    if(userDao.checkAdminRole(user)) {
                        req.getServletContext().setAttribute("userRole", "admin");
                    } else {
                        req.getServletContext().setAttribute("userRole", "read");
                    }
                    getServletContext().getRequestDispatcher("/jsp/welcomePage.jsp").forward(req, resp);
            } else {
                req.setAttribute("errorMsg", "This User is not authorized: " + userName);
                getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}