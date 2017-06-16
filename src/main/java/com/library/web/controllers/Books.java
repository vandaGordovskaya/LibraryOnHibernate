package main.java.com.library.web.controllers;

import main.java.com.library.dao.*;
import main.java.com.library.model.Author;
import main.java.com.library.model.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Books extends HttpServlet {
    private BookDao bookDao;
    private AuthorDao authorDao;

    public Books() {
        super();
        bookDao = new BookDaoImpl();
        authorDao = new AuthorDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if("updateBook".equalsIgnoreCase(action)) {
            prepareToUpdate(req, resp);
        } else if("createBook".equalsIgnoreCase(action)) {
            prepareToCreate(req, resp);
        } else if("bookData".equalsIgnoreCase(action)) {
            bookData(req, resp);
        } else if("deleteBook".equalsIgnoreCase(action)) {
            deleteBook(req, resp);
        } else {
            listBooks(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if("updateBook".equalsIgnoreCase(action)) {
            updateBook(req, resp);
        } else if("createBook".equalsIgnoreCase(action)) {
            createBook(req, resp);
        }
    }

    private void listBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            bookDao = DaoFactory.getInstance().getBookDao();
            Set<Book> books = bookDao.listBooks();
            req.setAttribute("listBooks", books);
            getServletContext().getRequestDispatcher("/jsp/books.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepareToUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookDao = DaoFactory.getInstance().getBookDao();
        authorDao = DaoFactory.getInstance().getAuthorDao();

        String idTemp = req.getParameter("bookId");
        String updatedName = req.getParameter("updatedBookName");
        int id = Integer.parseInt(idTemp);
        String bookName = req.getParameter("bookName");
        req.setAttribute("bookName", bookName);
        req.setAttribute("bookId", id);
        //get list Authors from the system

        Set<Author> authors = null;
        try {
            authors = authorDao.listAuthors();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //get associated Authors
        Set<Author> bookAuthors = null;
        try {
            bookAuthors = bookDao.getAuthorsByBook(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("associatedAuthors", bookAuthors);

        //find Authors which are not associated with the Book
        Set<Author> notAssociatedAuthors = authors;
        notAssociatedAuthors.removeAll(bookAuthors);
        req.setAttribute("notAssociatedAuthors", notAssociatedAuthors);
        getServletContext().getRequestDispatcher("/jsp/updateBook.jsp").forward(req, resp);
    }


    private void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDao bookDao = DaoFactory.getInstance().getBookDao();

        String idTemp = req.getParameter("bookId");
        String updatedName = req.getParameter("updatedBookName");
        int id = Integer.parseInt(idTemp);
        String bookName = req.getParameter("bookName");
        req.setAttribute("bookName", bookName);
        req.setAttribute("bookId", id);
        String[] associatedAuthors = req.getParameterValues("associatedAuthors");
        req.setAttribute("associatedAuthors", associatedAuthors);
        String[] notAssociatedAuthors = req.getParameterValues("notAssociatedAuthors");
        req.setAttribute("notAssociatedAuthors", notAssociatedAuthors);

        //get associated Authors
        Set<Author> bookAuthors = null;
        try {
            bookAuthors = bookDao.getAuthorsByBook(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //validation Book must have at least one Author
        String[] removeAuth = req.getParameterValues("disassociateAuthors");
        String[] associateAuth = req.getParameterValues("associateAuthors");

        if(removeAuth != null) {
            if(associateAuth != null) {
                Book updatedBook = updateBookName(id, updatedName);
                disassociateAuthFromBook(updatedBook, removeAuth);
                associateAuthWithBook(updatedBook, associateAuth);
                resp.sendRedirect("./books");
            } else {
                if (removeAuth.length == bookAuthors.size()) {
                    req.setAttribute("errorMsg", "Book must have at least one Author!");
                    getServletContext().getRequestDispatcher("/jsp/updateBook.jsp").forward(req, resp);
                } else {
                    Book updatedBook = updateBookName(id, updatedName);
                    disassociateAuthFromBook(updatedBook, removeAuth);
                    resp.sendRedirect("./books");
                }
            }
        } else if(associateAuth != null) {
            Book updatedBook = updateBookName(id, updatedName);
            associateAuthWithBook(updatedBook, associateAuth);

            resp.sendRedirect("./books");
        } else {
            Book updatedBook = updateBookName(id, updatedName);
            resp.sendRedirect("./books");
        }
    }

    private void prepareToCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthorDao authorDao = DaoFactory.getInstance().getAuthorDao();
        try {
            Set<Author> authors = authorDao.listAuthors();
            req.setAttribute("listAuthorsFromDB", authors);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/jsp/createBook.jsp").forward(req, resp);
    }

    private void createBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDao bookDao = DaoFactory.getInstance().getBookDao();
        AuthorDao authorDao = DaoFactory.getInstance().getAuthorDao();
        int id = Integer.parseInt(req.getParameter("bookId"));
        String bookName = req.getParameter("bookName");
        String[] getAuthors = req.getParameterValues("getListAuthors");

        try {
            Set<Author> authors = authorDao.listAuthors();

            Set<Author> addedAuthors = new HashSet<Author>();
            Book newBook = new Book();
            newBook.setName(bookName);
            newBook.setId(id);

            for(Author findAuthor : authors) {
                for(String gettedAuth : getAuthors) {
                    if(gettedAuth.equals(findAuthor.getName())) {
                        Author newAuthor = new Author();
                        newAuthor.setId(findAuthor.getId());
                        newAuthor.setName(findAuthor.getName());
                        addedAuthors.add(newAuthor);
                    }
                }
            }
            bookDao.addBook(newBook, addedAuthors);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("./books");
    }


    private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookDao = DaoFactory.getInstance().getBookDao();

        String idTemp = req.getParameter("bookId");
        int id = Integer.parseInt(idTemp);
        try {
            //Book bookToRemove = bookDao.getBookById(id);
            bookDao.removeBook(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("./books");
    }

    private void bookData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookDao = DaoFactory.getInstance().getBookDao();
        int id = Integer.parseInt(req.getParameter("bookId"));
        String bookName = req.getParameter("bookName");
        try {
            Set<Author> authors = bookDao.getAuthorsByBook(id);
            req.setAttribute("listAuthors", authors);
            req.setAttribute("bookName", bookName);

            getServletContext().getRequestDispatcher("/jsp/bookData.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Book updateBookName(int id, String name) {
        bookDao = DaoFactory.getInstance().getBookDao();
        Book updatedBook = new Book();
        updatedBook.setId(id);
        updatedBook.setName(name);
        try {
            bookDao.updateBook(updatedBook);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return updatedBook;
    }

    private void associateAuthWithBook(Book book, String[] associateAuth) {
        bookDao = DaoFactory.getInstance().getBookDao();
        Set<Author> addedAuthors = new HashSet<Author>();
        for (int i = 0; i < associateAuth.length; i++) {
            Author newAuthor = new Author();
            newAuthor.setId(Integer.parseInt(associateAuth[i]));
            addedAuthors.add(newAuthor);
        }
        try {
            bookDao.associateAuthToBook(book, addedAuthors);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void disassociateAuthFromBook(Book book, String[] removeAuth) {
        bookDao = DaoFactory.getInstance().getBookDao();
        Set<Author> deleteAuthors = new HashSet<Author>();
        for (int i = 0; i < removeAuth.length; i++) {
            Author newAuthor = new Author();
            newAuthor.setId(Integer.parseInt(removeAuth[i]));
            deleteAuthors.add(newAuthor);
        }
        try {
            bookDao.removeAuthorsFromBook(book, deleteAuthors);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
