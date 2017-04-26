package com.library.web.controllers;

import com.library.dao.AuthorDao;
import com.library.dao.BookDao;
import com.library.dao.DaoFactory;
import com.library.model.Author;
import com.library.model.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Vanda on 30.03.2017.
 */
public class DeleteAuthor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //doPost(req, resp);
        AuthorDao authorDao = DaoFactory.getInstance().getAuthorDao();
        BookDao bookDao = DaoFactory.getInstance().getBookDao();

        String idTemp = req.getParameter("authorId");
        int id = Integer.parseInt(idTemp);
        try {
            List<Book> books = bookDao.listBooks();
            List<Book> authorBooks = authorDao.getBooksByAuthor(id);
            Iterator<Book> iteratorBooks = books.iterator();
            Iterator<Book> iteratorAuthBooks = authorBooks.iterator();
            //authorDao.removeAuthor(id);
            while(iteratorAuthBooks.hasNext()) {
                Book authBook = iteratorAuthBooks.next();
                while(iteratorBooks.hasNext()) {
                    Book libraryBook = iteratorBooks.next();
                    if(authBook.getName().equals(libraryBook.getName())) {
                        List<Author> bookAuthors = bookDao.getAuthorsByBook(libraryBook.getId());
                        if(bookAuthors.size() > 1){
                            authorDao.removeAuthor(id);
                            resp.sendRedirect("./authors");
                        } else {
                            req.setAttribute("errorMsg", "This author cannot be deleted because it single author for one of the book!");
                            getServletContext().getRequestDispatcher("/jsp/authors.jsp").forward(req, resp);
                        }
                    }
                }
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
