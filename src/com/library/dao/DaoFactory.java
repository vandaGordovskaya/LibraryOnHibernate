package com.library.dao;

public class DaoFactory {
    private static DaoFactory instance = new DaoFactory();
    private DaoFactory() {}

    public BookDao getBookDao() {
        return new BookDaoImpl();
    }

    public AuthorDao getAuthorDao() {
        return new AuthorDaoImpl();
    }

    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

    public static DaoFactory getInstance() {
        return instance;
    }
}
