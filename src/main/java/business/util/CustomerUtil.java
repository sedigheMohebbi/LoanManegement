package business.util;


import dataacceess.CustomerCRUD;
import exception.SqlException;
import model.Customer;

public class CustomerUtil {
    static final Boolean lock = true;

    public static String generateCustomerNumber() throws SqlException {

        synchronized (lock) {
            Customer customer = CustomerCRUD.getLastCustomer();
            if (customer == null) {
                return String.valueOf(1);
            }
            int lastCustomer = Integer.parseInt(customer.getCustomerNumber());
            lastCustomer += 1;
            return String.valueOf(lastCustomer);
        }
    }
}
