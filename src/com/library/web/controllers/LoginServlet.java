package com.library.web.controllers;

import com.library.model.User;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String passw = req.getParameter("pass");
        req.setAttribute("userName", userName);

        User user = new User();
        if(Validate.checkRegUser(userName, passw).equals("admin")) {
            getServletContext().getRequestDispatcher("/jsp/welcomePage.jsp").forward(req, resp);
        } else if(Validate.checkRegUser(userName, passw).equals("reader")) {
            getServletContext().getRequestDispatcher("/jsp/welcomePage.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorMsg", "This User is not authorized: " + userName);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}