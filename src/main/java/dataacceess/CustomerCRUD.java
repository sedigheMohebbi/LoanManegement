package dataacceess;

import exception.SqlException;
import model.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CustomerCRUD {



    public static Customer getLastCustomer() throws SqlException {
        try {
            Connection connection=SqlConnect.getInstance().getConn();
            Statement sqlStatement = connection.createStatement();
            ResultSet resultSet = sqlStatement.executeQuery("SELECT *  FROM customer WHERE  id=(select max(id) from customer)");
            if (!resultSet.next()) {
                return null;
            }
            Customer customer = new Customer();
            customer.setCustomerNumber(resultSet.getString("customerNumber"));
            customer.setId(resultSet.getInt("id"));
        //    connection.close();
            return customer;


        } catch (SQLException e) {
            throw new SqlException("Error at legal customer save Exception", e);
        }
    }
}
