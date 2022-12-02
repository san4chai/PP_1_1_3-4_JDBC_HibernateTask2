package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";


    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/mydbtest");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "");

                Configuration configuration = new Configuration();

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

//    private static final SessionFactory sessionFactory;
//    static {
//        try {
//            Properties prop = new Properties();
//            prop.setProperty("hibernate.connection.url", "jdbc:mysql://192.168.0.101:3306/go");
//            prop.setProperty("hibernate.connection.username", "go");
//            prop.setProperty("hibernate.connection.password", "go");
//            prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
//
//            prop.setProperty("hibernate.hbm2ddl.auto", "create");
//
//            sessionFactory = (SessionFactory) new org.hibernate.cfg.Configuration()
//                    .addProperties(prop)
//                    //.addPackage("com.kat")
//                    .addAnnotatedClass(User.class)
//                    .buildSessionFactory()
//            ;
//        }
//        catch (Exception ex) {
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//    public static Session getSession() throws HibernateException {
//        return sessionFactory.openSession();
//    }
}
