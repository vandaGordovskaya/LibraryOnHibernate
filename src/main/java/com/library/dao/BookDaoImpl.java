package main.java.com.library.dao;

import main.java.com.library.model.Author;
import main.java.com.library.model.Book;
import org.hibernate.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BookDaoImpl implements BookDao {
    @Override
    public void addBook(Book book, Set<Author> authors) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(book);
            session.getTransaction().commit();
            session.beginTransaction();
            associateAuthToBook(book, authors);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void updateBook(Book book) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Book newBook = (Book) session.load(Book.class, book.getId());
            newBook.setName(book.getName());
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void removeBook(long id) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Book newBook = (Book) session.load(Book.class, id);
            session.delete(newBook);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void removeAuthorsFromBook(Book book, Set<Author> authors) throws Exception {
        long bookId = book.getId();
        Session session = null;
        Set updatedAuthorsSet = new HashSet();
        Set associatedAuthorsSet = new HashSet();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Book newBook = (Book) session.load(Book.class, book.getId());
            associatedAuthorsSet = getAuthorsByBook(book.getId());
            for(Author associatedAuthor: authors){
                Author authorForAssociation = (Author) session.load(Author.class, associatedAuthor.getId());
                updatedAuthorsSet.add(authorForAssociation);
            }
            associatedAuthorsSet.removeAll(updatedAuthorsSet);
            newBook.setAuthors(associatedAuthorsSet);
            session.merge(newBook);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Book getBookById(long id) throws Exception {
        Session session = null;
        Book book = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            book = (Book) session.get(Book.class, id);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return book;
    }

    @Override
    public Set listBooks() throws Exception {
        Session session = null;
        List books = new ArrayList<Book>();
        Set listBooks = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            books = session.createCriteria(Book.class).list();
            listBooks = new HashSet(books);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return listBooks;
    }

    @Override
    public Set getAuthorsByBook(long id) throws Exception {
        Set associatedAuthors = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Book book = (Book) session.load(Book.class, id);
            associatedAuthors = book.getAuthors();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return  associatedAuthors;
    }

    @Override
    public void associateAuthToBook(Book book, Set<Author> authors) throws Exception {
        long bookId = book.getId();
        Session session = null;
        Set<Author> newAuthors = new HashSet<Author>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Book bookForAssociation = (Book) session.load(Book.class, bookId);
            for(Author associatedAuthor: authors){
                Author authorForAssociation = (Author) session.load(Author.class, associatedAuthor.getId());
                newAuthors.add(authorForAssociation);
            }
            bookForAssociation.setAuthors(newAuthors);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
