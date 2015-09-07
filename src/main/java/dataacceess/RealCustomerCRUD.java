package dataacceess;


import exception.SqlException;
import model.RealCustomer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        try {
            //  Connection connection = DriverManager.getConnection(CustomerCRUD.CONNECTION_URL, CustomerCRUD.USER, CustomerCRUD.PASSWORD);
            Connection connection = SqlConnect.getInstance().getConn();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer (customerNumber) VALUES (?)");
            preparedStatement.setString(1, realCustomer.getCustomerNumber());
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT id from customer WHERE customerNumber = ?");
            preparedStatement1.setString(1, realCustomer.getCustomerNumber());
            ResultSet resultSet = preparedStatement1.executeQuery();
            resultSet.first();
            PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO realCustomer (firstName,lastName,fatherName,birthDate,nationalCode ,id) VALUES (?,?,?,?,?,?)");
            preparedStatement2.setString(1, realCustomer.getFirstName());
            preparedStatement2.setString(2, realCustomer.getLastName());
            preparedStatement2.setString(3, realCustomer.getFatherName());
            preparedStatement2.setString(4, realCustomer.getBirthDate());
            preparedStatement2.setString(5, realCustomer.getNationalCode());
            preparedStatement2.setInt(6, resultSet.getInt("id"));
            preparedStatement2.executeUpdate();

            //connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return realCustomer;
    }

    public static boolean existRealCustomerWithNationalCode(String nationalCode) throws SqlException {
        try {
            // Connection connection = DriverManager.getConnection(CustomerCRUD.CONNECTION_URL, CustomerCRUD.USER, CustomerCRUD.PASSWORD);
            Connection connection = SqlConnect.getInstance().getConn();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT nationalCode FROM realcustomer WHERE nationalCode=?");
            preparedStatement.setString(1, nationalCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new SqlException("EXCEPTION", e);
        }
    }

    public static boolean existsRealCustomerNationalCode(String nationalCode, int id) throws SqlException {
        try {
            //     Connection connection = DriverManager.getConnection(CustomerCRUD.CONNECTION_URL, CustomerCRUD.USER, CustomerCRUD.PASSWORD);
            Connection connection = SqlConnect.getInstance().getConn();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT nationalCode FROM realcustomer WHERE nationalCode=? AND id<>? ");
            preparedStatement.setString(1, nationalCode);
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new SqlException("Exception", e);
        }
    }

    public static List<RealCustomer> searchRealCustomer(RealCustomer realCustomer) {
        List<RealCustomer> realCustomers = new ArrayList<RealCustomer>();
        try {
            Connection connection = SqlConnect.getInstance().getConn();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM realcustomer inner join customer on realcustomer.id=customer.id\n" +
                    " WHERE 1=1 " +
                    (realCustomer.getFirstName().length() > 0 ? " AND firstName = ?" : "") +// meghdar gereft
                    (realCustomer.getLastName().length() > 0 ? " AND lastName = ?" : "") +
                    (realCustomer.getNationalCode().length() > 0 ? " AND nationalCode = ?" : "") +
                    (realCustomer.getCustomerNumber().length() > 0 ? "AND customerNumber = ?" : ""));
            int index = 1;
            if (realCustomer.getFirstName().length() > 0) {
                preparedStatement.setString(index, realCustomer.getFirstName());
                index++;
            }
            if (realCustomer.getLastName().length() > 0) {
                preparedStatement.setString(index, realCustomer.getLastName());
                index++;
            }
            if (realCustomer.getNationalCode().length() > 0) {
                preparedStatement.setString(index, realCustomer.getNationalCode());
                index++;
            }
            if (realCustomer.getCustomerNumber().length() > 0) {
                preparedStatement.setString(index, realCustomer.getCustomerNumber());
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RealCustomer realCustomer1 = new RealCustomer();
                realCustomer1.setFirstName(resultSet.getString("firstName"));
                realCustomer1.setLastName(resultSet.getString("lastName"));
                realCustomer1.setFatherName(resultSet.getString("fatherName"));
                realCustomer1.setBirthDate(resultSet.getString("birthDate"));
                realCustomer1.setNationalCode(resultSet.getString("nationalCode"));
                realCustomer1.setId(resultSet.getInt("id"));
                realCustomer1.setCustomerNumber(resultSet.getString("customerNumber"));
                realCustomers.add(realCustomer1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return realCustomers;
    }

    public static RealCustomer loadRealCustomer(int id) throws SqlException {
        try {

            Connection connection = SqlConnect.getInstance().getConn();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from customer INNER join realcustomer on customer.id=realcustomer.id WHERE realcustomer.id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            RealCustomer realCustomer = new RealCustomer();
            resultSet.first();
            realCustomer.setFirstName(resultSet.getString("firstName"));
            realCustomer.setLastName(resultSet.getString("lastName"));
            realCustomer.setFatherName(resultSet.getString("fatherName"));
            realCustomer.setNationalCode(resultSet.getString("nationalCode"));
            realCustomer.setBirthDate(resultSet.getString("birthDate"));
            realCustomer.setId(resultSet.getInt("id"));
            realCustomer.setCustomerNumber(resultSet.getString("customerNumber"));

            return realCustomer;


        } catch (SQLException e) {
            throw new SqlException("SQL EXCEPTION", e);
        }

    }
    public static RealCustomer updateRealCustomer(RealCustomer realCustomer) throws SqlException {
        try {


            Connection connection = SqlConnect.getInstance().getConn();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE realcustomer SET firstName=?,lastName=?,fatherName=?,nationalCode=?,birthDate=? WHERE id=?");
            preparedStatement.setString(1, realCustomer.getFirstName());
            preparedStatement.setString(2, realCustomer.getLastName());
            preparedStatement.setString(3, realCustomer.getFatherName());
            preparedStatement.setString(4, realCustomer.getNationalCode());
            preparedStatement.setString(5, realCustomer.getBirthDate());
            preparedStatement.setInt(6, realCustomer.getId());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new SqlException("EXception", e);
        }
        return loadRealCustomer(realCustomer.getId());
    }
    public static void deleteRealCustomer(int id) throws SqlException {
        Connection connection = SqlConnect.getInstance().getConn();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM realcustomer WHERE id=? ");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM customer WHERE id=?");
            preparedStatement1.setInt(1,id);
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            throw new SqlException("SQL EXCEPTION in delete", e);
        }

    }


}
