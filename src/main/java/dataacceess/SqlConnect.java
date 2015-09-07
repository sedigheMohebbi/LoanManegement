package dataacceess;


import model.Customer;
import model.LegalCustomer;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SqlConnect {
    private Connection conn;

    public Connection getConn() {
        return conn;
    }

    private static SqlConnect ourInstance = new SqlConnect();

    public static SqlConnect getInstance() {
        return ourInstance;
    }

    private SqlConnect() {
        String CONNECTION_URL = "jdbc:mysql://localhost/customermanager?useUnicode=true&characterEncoding=UTF-8";
        String USER = "root";
        String PASSWORD = "123456q@";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            System.err.println("mysql jdbc driver not found");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static SessionFactory sessionFactory = null;
    static SessionFactory createSessionFactory() {
        if(sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure("/hibernate.cfg.xml");
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(LegalCustomer.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

}
