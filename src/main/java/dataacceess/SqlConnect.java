package dataacceess;


import model.*;
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



    private static SessionFactory sessionFactory = null;

    static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure("/hibernate.cfg.xml");
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(LegalCustomer.class);
            configuration.addAnnotatedClass(RealCustomer.class);
            configuration.addAnnotatedClass(GrantCondition.class);
            configuration.addAnnotatedClass(LoanType.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

}
