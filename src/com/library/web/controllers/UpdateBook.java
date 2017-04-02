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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vanda on 01.04.2017.
 */
public class UpdateBook extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BookDao bookDao = DaoFactory.getInstance().getBookDao();

            String idTemp = req.getParameter("bookId");
            String updatedName = req.getParameter("updatedBookName");
            String bookName = req.getParameter("bookName");
            int id = Integer.parseInt(idTemp);
            req.setAttribute("bookName", bookName);
            req.setAttribute("bookId", id);

            //get list Authors from the system
            AuthorDao authorDao = DaoFactory.getInstance().getAuthorDao();
            List<Author> authors = authorDao.listAuthors();

            Book updatedBook = new Book();
            updatedBook.setId(id);
            updatedBook.setName(updatedName);

            //get associated Authors
            List<Author> bookAuthors = bookDao.getAuthorsByBook(id);
            req.setAttribute("associatedAuthors", bookAuthors);

            //find Authors which are not associated with the Book
            List<Author> notAssociatedAuthors = authors;
            notAssociatedAuthors.removeAll(bookAuthors);
            req.setAttribute("notAssociatedAuthors", notAssociatedAuthors);

            //validation Book must have at least one Author
            String[] associateAuth = req.getParameterValues("associateAuthors");
            String[] removeAuth = req.getParameterValues("disassociateAuthors");
            if((associateAuth.equals(null)) && (removeAuth.length == bookAuthors.size())) {
                req.setAttribute("errorMsg", "Book must have at least one Author!");
                getServletContext().getRequestDispatcher("/jsp/updateBook.jsp").forward(req, resp);
            } else {
                //updating book with association Authors
                List<Author> addedAuthors = new ArrayList<>();
                if (!associateAuth.equals(null)) {
                    for (Author findAuthor : notAssociatedAuthors) {
                        for (String gettedAuth : associateAuth) {
                            if (gettedAuth.equals(findAuthor.getName())) {
                                Author newAuthor = new Author();
                                newAuthor.setId(findAuthor.getId());
                                newAuthor.setName(findAuthor.getName());
                                addedAuthors.add(newAuthor);
                            }
                        }
                    }
                    bookDao.updateBook(updatedBook, addedAuthors);
                }

                //remove Authors from the book

                List<Author> deleteAuthors = new ArrayList<Author>();
                if (!removeAuth.equals(null)) {
                    for (Author findAuthor : bookAuthors) {
                        for (String gettedAuth : removeAuth) {
                            if (gettedAuth.equals(findAuthor.getName())) {
                                Author newAuthor = new Author();
                                newAuthor.setId(findAuthor.getId());
                                newAuthor.setName(findAuthor.getName());
                                deleteAuthors.add(newAuthor);
                            }
                        }
                    }
                    bookDao.removeAuthorsFromBook(updatedBook, deleteAuthors);
                }
                getServletContext().getRequestDispatcher("/jsp/updateBook.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}