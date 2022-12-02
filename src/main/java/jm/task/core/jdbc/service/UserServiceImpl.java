package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import javax.transaction.*;
import java.sql.*;
import java.util.List;

public class UserServiceImpl extends Util implements UserService {
    UserDao userDaoHibernate = new UserDaoHibernateImpl();
    Connection connection = getConnection();
    User user = new User();

    @Override
    public void createUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = "CREATE TABLE Users" +
                "(Id INT(19) AUTO_INCREMENT PRIMARY KEY," +
                "Name VARCHAR(45)," +
                "LastName VARCHAR(45)," +
                "age INT(3))";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public void dropUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = "DROP TABLE Users";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        org.hibernate.Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        org.hibernate.Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            List<User> users = session.createQuery("from User").getResultList();
            return users;
        }
    }

    public void cleanUsersTable() {
        org.hibernate.Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
