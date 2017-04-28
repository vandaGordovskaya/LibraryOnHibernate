package com.library.web.controllers;

import com.library.dao.*;
import com.library.model.Author;
import com.library.model.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class Authors extends HttpServlet {
    private BookDao bookDao;
    private AuthorDao authorDao;

    public Authors() {
        super();
        bookDao = new BookDaoImpl();
        authorDao = new AuthorDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if("updateAuthor".equalsIgnoreCase(action)) {
            prepareToUpdateAuthor(req, resp);
        } else if("createAuthor".equalsIgnoreCase(action)) {
            prepareToCreateAuthor(req, resp);
        } else if("authorData".equalsIgnoreCase(action)) {
            authorData(req, resp);
        } else if("deleteAuthor".equalsIgnoreCase(action)) {
            deleteAuthor(req, resp);
        } else {
            listAuthors(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if("updateAuthor".equalsIgnoreCase(action)) {
            updateAuthor(req, resp);
        } else if("createAuthor".equalsIgnoreCase(action)) {
            createAuthor(req, resp);
        }
    }

    private void listAuthors(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            authorDao = DaoFactory.getInstance().getAuthorDao();
            List<Author> authors = authorDao.listAuthors();
            req.getServletContext().setAttribute("listAuthors", authors);
            getServletContext().getRequestDispatcher("/jsp/authors.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepareToCreateAuthor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorDao = DaoFactory.getInstance().getAuthorDao();
        getServletContext().getRequestDispatcher("/jsp/createAuthor.jsp").forward(req, resp);
    }


    private void createAuthor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorDao = DaoFactory.getInstance().getAuthorDao();
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

    private void prepareToUpdateAuthor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorDao = DaoFactory.getInstance().getAuthorDao();

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

    private void updateAuthor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorDao = DaoFactory.getInstance().getAuthorDao();

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

    private void deleteAuthor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorDao = DaoFactory.getInstance().getAuthorDao();
        bookDao = DaoFactory.getInstance().getBookDao();

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

    private void authorData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            authorDao = DaoFactory.getInstance().getAuthorDao();
            int id = Integer.parseInt(req.getParameter("authorId"));
            String authorName = req.getParameter("authorName");
            List<Book> listBooks = authorDao.getBooksByAuthor(id);
            req.setAttribute("listBooks", listBooks);
            req.setAttribute("authorName", authorName);

            getServletContext().getRequestDispatcher("/jsp/authorData.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
