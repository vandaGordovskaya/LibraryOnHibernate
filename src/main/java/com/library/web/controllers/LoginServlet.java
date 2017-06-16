package main.java.com.library.web.controllers;

import main.java.com.library.dao.DaoFactory;
import main.java.com.library.dao.UserDao;
import main.java.com.library.model.User;

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
        user.setPass(pass);

        try {
            if(userDao.checkUserExist(user)) {
                    if(userDao.checkAdminRole(user)) {
                        HttpSession session = req.getSession();
                        session.setAttribute("userRole", "admin");
                    } else {
                        HttpSession session = req.getSession();
                        session.setAttribute("userRole", "read");
                    }
                    getServletContext().getRequestDispatcher("/jsp/welcomePage.jsp").forward(req, resp);
            } else {
                req.setAttribute("errorMsg", "This User is not authorized: " + userName);
                getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}