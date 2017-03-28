package com.library.web.controllers;

import com.library.dao.AuthorDao;
import com.library.dao.BookDao;
import com.library.dao.BookDaoImpl;
import com.library.dao.DaoFactory;
import com.library.model.Author;
import com.library.model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HomePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BookDao bookDao = DaoFactory.getInstance().getBookDao();
            List<Book> books = bookDao.listBooks();
            req.setAttribute("listBooks", books);

            AuthorDao authorDao = DaoFactory.getInstance().getAuthorDao();
            List<Author> authors = authorDao.listAuthors();
            req.setAttribute("listAuthors", authors);
            getServletContext().getRequestDispatcher("/jsp/homePage.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();

        }


        //getServletContext().getRequestDispatcher("/jsp/homePage.jsp").forward(req, resp);
       /* String action = req.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(req, resp);
                    break;
                case "/insert":
                    addBook(req, resp);
                    break;
                case "/delete":
                    deleteBook(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp);
                    break;
                case "/update":
                    updateBook(req, resp);
                    break;
                default:
                    listBook(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

   /* private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String bookId = req.getParameter("id");
        String bookName = req.getParameter("name");
        int b_ID = -1;
        if(bookId != null) {
            b_ID = Integer.parseInt(bookId);
        }

        BookDaoImpl bookForm = new BookDaoImpl();
        try{
            List<Book> books = BookDaoImpl.getInstance().listBooks();
            Book book = new Book();
            book.setName(bookName);
            book.setId(b_ID);

        } catch(SQLException e){
            throw new IOException(e.getMessage());
        } catch(Exception e) {
            throw new Exception(e);
        }
        req.setAttribute("bookForm", bookForm);
        req.setAttribute("book.id", bookId);
        req.setAttribute("book.name", bookName);
        getServletContext().getRequestDispatcher("/jsp/bookData.jsp").forward(req, resp);
    }*/

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            doGet(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*private void listBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        BookDaoImpl book = new BookDaoImpl();
        List<Book> listBook = book.listBooks();
        request.setAttribute("listBook", listBook);
        RequestDispatcher dispatcher = request.getRequestDispatcher("homePage.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BookDaoImpl book = new BookDaoImpl();
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = book.getBookById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);

    }

    private void addBook(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BookDaoImpl book = new BookDaoImpl();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        Book newBook = new Book();
        book.addBook(newBook);
        response.sendRedirect("list");
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        BookDaoImpl bookDAO = new BookDaoImpl();
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        bookDAO.updateBook(id, book);
        response.sendRedirect("list");
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        BookDaoImpl bookDAO = new BookDaoImpl();
        Book book = new Book();
        book.setId(id);
        bookDAO.removeBook(id);
        response.sendRedirect("list");

    }*/
}
