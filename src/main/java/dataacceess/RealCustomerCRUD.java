package dataacceess;


import exception.SqlException;
import model.RealCustomer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.ArrayList;
import java.util.List;

public class RealCustomerCRUD {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.err.println("mysql jdbc driver not found");
        }
    }

    public static RealCustomer saveRealCustomer(RealCustomer realCustomer) throws SqlException {
        SessionFactory sessionFactory = SqlConnect.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(realCustomer);
            transaction.commit();
        } finally {
            session.close();
        }
        return realCustomer;
    }

    public static boolean existRealCustomerWithNationalCode(String nationalCode) throws SqlException {
        SessionFactory sessionFactory = SqlConnect.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("SELECT realCustomer.nationalCode FROM RealCustomer realCustomer WHERE realCustomer.nationalCode = :nationalCode");
            query.setParameter("nationalCode", nationalCode);
            if (query.list().isEmpty()) {
                return false;
            } else {
                return true;
            }


        } finally {
            session.close();
        }
    }

    public static boolean existsRealCustomerNationalCode(String nationalCode, int id) throws SqlException {
        SessionFactory sessionFactory = SqlConnect.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("SELECT realCustomer.nationalCode FROM RealCustomer realCustomer WHERE realCustomer.nationalCode = :nationalCode AND realCustomer.id <> :id");
            query.setParameter("nationalCode", nationalCode);
            query.setParameter("id", id);
            if (query.list().isEmpty()) {
                return false;
            } else return true;
        } finally {
            session.close();
        }
    }

    public static List<RealCustomer> searchRealCustomer(RealCustomer realCustomer) {
        List<RealCustomer> realCustomers = new ArrayList<RealCustomer>();
        SessionFactory sessionFactory = SqlConnect.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("SELECT realCustomer from RealCustomer realCustomer where 1=1" +
                    (realCustomer.getFirstName().length() > 0 ? " AND realCustomer.fistName=:name" : "") +
                    (realCustomer.getLastName().length() > 0 ? " AND realCustomer.lastName=:lastName" : "") +
                    (realCustomer.getNationalCode().length() > 0 ? " AND realCustomer.nationalCode=:nationalCode" : "") +
                    (realCustomer.getCustomerNumber().length() > 0 ? " AND realCustomer.customerNumber=:customerNumber" : ""));
            if (realCustomer.getFirstName().length() > 0) {
                query.setParameter("name", realCustomer.getFirstName());
            }
            if (realCustomer.getLastName().length() > 0) {
                query.setParameter("lastName", realCustomer.getLastName());
            }
            if (realCustomer.getNationalCode().length() > 0) {
                query.setParameter("nationalCode", realCustomer.getNationalCode());
            }
            if (realCustomer.getNationalCode().length() > 0) {
                query.setParameter("customerNumber", realCustomer.getCustomerNumber());
            }

            return query.list();

        } finally {
            session.close();
        }
    }

    public static RealCustomer loadRealCustomer(int id) throws SqlException {
        SessionFactory sessionFactory = SqlConnect.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            RealCustomer realCustomer = session.get(RealCustomer.class, id);

            return realCustomer;
        } finally {

            session.close();
        }
    }


    public static RealCustomer updateRealCustomer(RealCustomer realCustomer) throws SqlException {
        SessionFactory sessionFactory = SqlConnect.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            RealCustomer realCustomer1 = session.get(realCustomer.getClass(), realCustomer.getId());
            realCustomer1.setFirstName(realCustomer.getFirstName());
            realCustomer1.setLastName(realCustomer.getLastName());
            realCustomer1.setFatherName(realCustomer.getFatherName());
            realCustomer1.setNationalCode(realCustomer.getNationalCode());
            realCustomer1.setBirthDate(realCustomer.getBirthDate());
            transaction.commit();
            return realCustomer1;

        } finally {
            session.close();
        }
    }

    public static void deleteRealCustomer(int id) throws SqlException {
        SessionFactory sessionFactory = SqlConnect.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            RealCustomer realCustomer = session.get(RealCustomer.class, id);
            session.delete(realCustomer);
            transaction.commit();
        } finally {
            session.close();
        }
    }

    public static RealCustomer findRealCustomerWithCustomerNumber(int customerNumber) {
        SessionFactory sessionFactory = SqlConnect.createSessionFactory();
        Session session = sessionFactory.openSession();
        RealCustomer realCustomer ;
        try {
            Query query = session.createQuery("SELECT realCustomer From RealCustomer realCustomer  WHERE realCustomer.customerNumber= :customerNumber");
            query.setParameter("customerNumber", customerNumber);
            if (query.list().isEmpty()) {
                return null;
            } else {
                realCustomer = (RealCustomer) query.list().get(0);
                return realCustomer;
            }
        } finally {
            session.close();
        }
    }


}
