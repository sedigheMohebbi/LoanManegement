package dataacceess;


import exception.SqlException;
import model.LegalCustomer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
        }  finally {
            session.close();
        }
        return legalCustomer;
    }

    public static boolean existsLegalCustomerWithEconomicCode(String economicCode) throws SqlException {
        try {
            // Connection connection = DriverManager.getConnection(CustomerCRUD.CONNECTION_URL, CustomerCRUD.USER, CustomerCRUD.PASSWORD);
            Connection connection = SqlConnect.getInstance().getConn();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT economicCode FROM legalcustomer WHERE economicCode=?");
            preparedStatement.setString(1, economicCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            throw new SqlException("Exception", e);
        }
    }

    public static boolean existsLegalEconomicCode(String economicCode, int id) throws SqlException {
        try {
            //  Connection connection = DriverManager.getConnection(CustomerCRUD.CONNECTION_URL, CustomerCRUD.USER, CustomerCRUD.PASSWORD);
            Connection connection = SqlConnect.getInstance().getConn();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT economicCode FROM legalcustomer WHERE economicCode=? AND id<>?");
            preparedStatement.setString(1, economicCode);
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new SqlException("EXCEPTION", e);
        }
    }

    public static List<LegalCustomer> searchLegalCustomer(LegalCustomer legalCustomer) {
        List<LegalCustomer> legalCustomers = new ArrayList<LegalCustomer>();
        try {
            //Connection connection = DriverManager.getConnection(CustomerCRUD.CONNECTION_URL, CustomerCRUD.USER, CustomerCRUD.PASSWORD);
            Connection connection = SqlConnect.getInstance().getConn();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM legalcustomer inner join customer on legalcustomer.id=customer.id\n" +
                    " WHERE 1=1 " +
                    (legalCustomer.getCompanyName().length() > 0 ? " AND companyName = ?" : "") +// meghdar gereft
                    (legalCustomer.getEconomicCode().length() > 0 ? " AND economicCode = ?" : "") +
                    (legalCustomer.getCustomerNumber().length() > 0 ? "AND customerNumber = ?" : ""));
            int index = 1;
            if (legalCustomer.getCompanyName().length() > 0) {
                preparedStatement.setString(index, legalCustomer.getCompanyName());
                index++;
            }

            if (legalCustomer.getEconomicCode().length() > 0) {
                preparedStatement.setString(index, legalCustomer.getEconomicCode());
                index++;
            }
            if (legalCustomer.getCustomerNumber().length() > 0) {
                preparedStatement.setString(index, legalCustomer.getCustomerNumber());

            }
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                LegalCustomer legalCustomer1 = new LegalCustomer();
                legalCustomer1.setCompanyName(resultSet.getString("companyName"));
                legalCustomer1.setEconomicCode(resultSet.getString("economicCode"));
                legalCustomer1.setCustomerNumber(resultSet.getString("customerNumber"));
                legalCustomer1.setRegistrationDate(resultSet.getString("registrationDate"));
                legalCustomer1.setId(resultSet.getInt("id"));
                legalCustomers.add(legalCustomer1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return legalCustomers;
    }

    public static LegalCustomer loadLegalCustomer(int id) throws SqlException {
        try {

            Connection connection = SqlConnect.getInstance().getConn();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from customer INNER join legalcustomer on customer.id=legalcustomer.id WHERE legalcustomer.id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            LegalCustomer legalCustomer = new LegalCustomer();
            resultSet.first();
            legalCustomer.setCompanyName(resultSet.getString("companyName"));
            legalCustomer.setEconomicCode(resultSet.getString("economicCode"));
            legalCustomer.setRegistrationDate(resultSet.getString("registrationDate"));
            legalCustomer.setId(resultSet.getInt("id"));
            legalCustomer.setCustomerNumber(resultSet.getString("customerNumber"));

            return legalCustomer;


        } catch (SQLException e) {
            throw new SqlException("SQL EXCEPTION", e);
        }

    }

    public static LegalCustomer updateLegalCustomer(LegalCustomer legalCustomer) throws SqlException {
        try {


            Connection connection = SqlConnect.getInstance().getConn();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE legalcustomer SET companyName=?,economicCode=?,registrationDate=? WHERE id=?");
            preparedStatement.setString(1, legalCustomer.getCompanyName());
            preparedStatement.setString(2, legalCustomer.getEconomicCode());
            preparedStatement.setString(3, legalCustomer.getRegistrationDate());
            preparedStatement.setInt(4, legalCustomer.getId());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new SqlException("EXception", e);
        }
        return loadLegalCustomer(legalCustomer.getId());
    }

    public static void deleteLegalCustomer(int id) throws SqlException {
        Connection connection = SqlConnect.getInstance().getConn();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM legalCustomer WHERE id=? ");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM customer WHERE id=?");
            preparedStatement1.setInt(1, id);
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            throw new SqlException("SQL EXCEPTION in delete", e);
        }

    }
}