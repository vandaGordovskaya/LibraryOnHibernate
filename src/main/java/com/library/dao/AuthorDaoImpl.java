package main.java.com.library.dao;

import main.java.com.library.model.Author;
import main.java.com.library.model.Book;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuthorDaoImpl implements AuthorDao{

    @Override
    public void addAuthor(Author author) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(author);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void updateAuthor(long auth_id, Author author) throws Exception {

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Author updatedAuthor = (Author) session.load(Author.class, auth_id);
            updatedAuthor.setName(author.getName());
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void removeAuthor(long id) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Author author = new Author();
            author.setId(id);
            session.delete(author);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Author getAuthorById(long id) throws Exception {
        Session session = null;
        Author author;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            author = (Author) session.get(Author.class, id);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return author;
    }

    @Override
    public Set listAuthors() throws Exception {
        Session session = null;
        List authors = new ArrayList<Author>();
        Set setAuthors = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            authors = session.createCriteria(Author.class).list();
            setAuthors = new HashSet(authors);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return setAuthors;

    }

    @Override
    public Set getBooksByAuthor(long id) throws Exception {
        Session session = null;
        Author author;
        Set books = new HashSet();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            author = (Author) session.get(Author.class, id);
            books = author.getBooks();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return books;
    }
}
