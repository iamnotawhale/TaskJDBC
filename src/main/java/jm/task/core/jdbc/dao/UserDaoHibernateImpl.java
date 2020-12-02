package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {

    private Session session = null;
    private Transaction transaction = null;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        session.createSQLQuery("CREATE TABLE IF NOT EXISTS userstable" +
                "(id BIGINT UNSIGNED NOT NULL auto_increment, " +
                " name VARCHAR(30), " +
                " lastName VARCHAR(30), " +
                " age TINYINT, " +
                " PRIMARY KEY (id))").executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        String sql = "DROP TABLE IF EXISTS userstable";

        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        User userToDelete = (User) session.get(User.class, id);
        session.delete(userToDelete);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        List<User> listOfUsers = (List<User>) session.createQuery("from User").list();

        transaction.commit();
        session.close();
        return listOfUsers;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        session.createQuery("delete from User").executeUpdate();

        transaction.commit();
        session.close();
    }
}
