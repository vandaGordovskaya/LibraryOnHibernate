package main.java.com.library.dao;

import main.java.com.library.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean checkAdminRole(User user) throws Exception {
        Session session = null;
        String admin = "admin";
        String role = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            List results = session.createCriteria(User.class)
                    .add(Restrictions.eq("name", user.getName()))
                    .add(Restrictions.eq("pass", user.getPass()))
                    .list();
            for(Object foundObject: results) {
                User foundUser = (User)foundObject;
                role = foundUser.getRole();
            }
            return role.equals(admin);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public boolean checkUserExist(User user) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            List results = session.createCriteria(User.class)
                    .add(Restrictions.eq("name", user.getName()))
                    .add(Restrictions.eq("pass", user.getPass()))
                    .list();
           return !results.isEmpty();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
