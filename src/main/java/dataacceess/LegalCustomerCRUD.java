package dataacceess;


import com.sun.org.apache.xpath.internal.operations.And;
import exception.SqlException;
import model.LegalCustomer;
import org.hibernate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LegalCustomerCRUD {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.err.println("mysql jdbc driver not found");
        }
    }

    public static LegalCustomer saveLegalCustomer(LegalCustomer legalCustomer) throws SqlException {
        SessionFactory sf = SqlConnect.createSessionFactory();
        Session session = sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.save(legalCustomer);
            tx.commit();
        } finally {

            session.close();
        }
        return legalCustomer;
    }

    public static boolean existsLegalCustomerWithEconomicCode(String economicCode) throws SqlException {
        SessionFactory sessionFactory = SqlConnect.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("SELECT legal.economicCode FROM LegalCustomer legal where legal.economicCode = :ecoCode");
            query.setParameter("ecoCode", economicCode);
            List economic = query.list();
            if (economic.isEmpty()) {
                return false;
            } else {
                return true;
            }
        } finally {
            session.close();
        }
    }

    public static boolean existsLegalEconomicCode(String economicCode, int id) throws SqlException {
        SessionFactory sessionFactory = SqlConnect.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("SELECT legal.economicCode FROM LegalCustomer legal where legal.economicCode =:ecoCode and legal.id <> :idNumber ");
            query.setParameter("ecoCode", economicCode);
            query.setParameter("idNumber", id);
            List ecoList = query.list();
            if (ecoList.isEmpty()) {
                return false;
            } else {
                return true;
            }

        } finally {
            session.close();
        }
    }

    public static List<LegalCustomer> searchLegalCustomer(LegalCustomer legalCustomer) {
        List<LegalCustomer> legalCustomers = new ArrayList<LegalCustomer>();
        SessionFactory sessionFactory = SqlConnect.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("select legal From LegalCustomer legal WHERE 1=1 "
                    + (legalCustomer.getCompanyName().length() > 0 ? " AND legal.companyName  = :name " : "")
                    + (legalCustomer.getEconomicCode().length() > 0 ? " AND legal.economicCode =:eco " : "")
                    + (legalCustomer.getCustomerNumber().length() > 0 ? " AND legal.customerNumber=:customerNo " : ""));
            if (legalCustomer.getCompanyName().length() > 0) {
                query.setParameter("name", legalCustomer.getCompanyName());
            }
            if (legalCustomer.getEconomicCode().length() > 0) {
                query.setParameter("eco", legalCustomer.getEconomicCode());
            }
            if (legalCustomer.getCustomerNumber().length() > 0) {
                query.setParameter("customerNo", legalCustomer.getCustomerNumber());
            }
            return query.list();

        } finally {

            session.close();
        }
    }

    public static LegalCustomer loadLegalCustomer(int id) throws SqlException {
        SessionFactory sessionFactory = SqlConnect.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            LegalCustomer legalCustomer = session.get(LegalCustomer.class, id);

            return legalCustomer;
        } finally {

            session.close();
        }
    }

    public static LegalCustomer updateLegalCustomer(LegalCustomer legalCustomer) throws SqlException {
        SessionFactory sessionFactory = SqlConnect.createSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            Transaction transaction = session.beginTransaction();
            LegalCustomer legalCustomer1 = session.load(LegalCustomer.class, legalCustomer.getId());
            legalCustomer1.setCompanyName(legalCustomer.getCompanyName());
            legalCustomer1.setEconomicCode(legalCustomer.getEconomicCode());
            legalCustomer1.setRegistrationDate(legalCustomer.getRegistrationDate());
            session.update(legalCustomer1);
            transaction.commit();
            return legalCustomer1;
        } finally {
            session.close();
        }
    }

    public static void deleteLegalCustomer(int id) throws SqlException {
        SessionFactory sessionFactory = SqlConnect.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            LegalCustomer legalCustomer = session.load(LegalCustomer.class, id);
            session.delete(legalCustomer);
            transaction.commit();

        }
        finally {
            session.close();
        }
    }
}