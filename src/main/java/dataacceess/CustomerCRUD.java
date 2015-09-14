package dataacceess;

import exception.SqlException;
import model.Customer;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.Query;

public class CustomerCRUD {
    private final static Logger logger = Logger.getLogger(Customer.class);
    public static Customer getLastCustomer() throws SqlException {
        SessionFactory sessionFactory = SqlConnect.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("SELECT customer  FROM Customer customer WHERE  customer.id=(select max(customer1.id) from Customer customer1)");
            Customer customer = (Customer) query.list().get(0);
            logger.info("Find Last Customer in DB successfully");
            return customer;
        } finally {
            session.close();
        }
    }
}
