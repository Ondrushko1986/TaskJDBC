package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Session session;
    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserDaoHibernateImpl(Session session){
        this.session = session;
    }


    @Override
    public void createUsersTable() {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("create table if not exists hibernate_task_users " +
                "(id bigint auto_increment,  primary key (id)" +
                "name varchar(256), lastName varchar(256)," +
                "age tinyint)");
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS hibernate_task_users")
                .executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("DELETE FROM hibernate_task_users WHERE id = id")
                .executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public List getAllUsers() {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List users = session.createSQLQuery("SELECT * FROM hibernate_task_users").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("DELETE FROM hibernate_task_users")
                .executeUpdate();
        transaction.commit();
        session.close();
    }
}
