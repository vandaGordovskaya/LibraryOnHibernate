package main.java.com.library.test.src;

import main.java.com.library.dao.*;
import main.java.com.library.model.Author;
import main.java.com.library.model.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

class TestAuthor {
/*
        @Test
        public static void testAddAuthor () throws Exception {
            Author author = new Author("newAuthor");
            AuthorDao authorDao = new AuthorDaoImpl();

            authorDao.addAuthor(author);

            assertEquals("newAuthor", authorDao.getAuthorById(getAuthorIdByName(author.getName())));
        }

        @Test
        public static void testDeleteAuthor() throws Exception {
            Author author = new Author("deleteAuthor");
            AuthorDao authorDao = new AuthorDaoImpl();

            authorDao.addAuthor(author);

            authorDao.removeAuthor(getAuthorIdByName(author.getName()));

            assertEquals(null, authorDao.getAuthorById(getAuthorIdByName(author.getName())));
        }

        public static long getAuthorIdByName(String authorName) {
            long id = -1;
            Session session = null;

            try{
                session = HibernateUtil.getSessionFactory().openSession();
                Criteria cr = session.createCriteria(Author.class);
                cr.add(Restrictions.eq("name", authorName));
                List results = cr.list();
                for(Object findAuthor: results) {
                    Author newAuthor = (Author) findAuthor;
                    id = newAuthor.getId();
                    }
                } finally{
                    if (session != null && session.isOpen()) {
                        session.close();
                    }
                }
                return id;
        }

    public static void main(String[] args) throws Exception {
            testAddAuthor();
            testDeleteAuthor();
    }
   // }
/*

        BookDao bookDao = new BookDaoImpl();
        AuthorDao authorDao = new AuthorDaoImpl();
        Book book = new Book("12333");
        book.setId(2);

        Set<Author> authors = new HashSet();
        Author newAuthor = new Author("HibernateAuthor3");
        newAuthor.setId(1);
        //Author newAuthor2 = new Author("HibernateAuthor2");
        //newAuthor2.setId(100001l);
        authors.add(newAuthor);
        //authors.add(newAuthor2);

        //bookDao.addBook(book, authors);*/

       /* bookDao.removeAuthorsFromBook(book, authors);



       // Author authors = new Author();

        /*Set authors = authorDao.getBooksByAuthor(1l);
        Iterator iterator = authors.iterator();
        while (iterator.hasNext()) {
            System.out.println("Iterator loop: " + iterator.next());
        }

        //System.out.println(authorDao.getAuthorById(123l));

//        if (userDao.checkAdminRole(user)){
//            System.out.println(userDao.checkAdminRole(user));
       //     System.out.println("Yes");
      //  } else {
       //     System.out.println("Not exist");
      //  }
        //bookDao.updateBook(book);
        /*AuthorDaoImpl authorDao = new AuthorDaoImpl();
        Author author = new Author();
        author.setId(11);
        List<Author> authList = new ArrayList<>();
        authList.add(author);
        bookDao.removeAuthorsFromBook(book, authList);
       //authorDao.removeAuthor(16);*/


}
