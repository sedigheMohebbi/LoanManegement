package business;


import exception.SqlException;
import exception.ValidationException;
import model.LegalCustomer;

public class LegalCustomerBiz {
    private static LegalCustomerBiz ourInstance = new LegalCustomerBiz();

    public static LegalCustomerBiz getInstance() {
        return ourInstance;
    }

    private LegalCustomerBiz() {
    }
    public LegalCustomer createAndSaveLegalCustomer(String name, String date, String economicCode) throws SqlException, ValidationException {
        LegalCustomer legalCustomer = new LegalCustomer();
        legalCustomer.setCompanyName(name);
        legalCustomer.setEconomicCode(economicCode);
        legalCustomer.setRegistrationDate(date);
        legalCustomer.setCustomerNumber(CustomerUtil.generateCustomerNumber());
        validateLegalCustomer(legalCustomer, true);
        return LegalCustomerCRUD.saveLegalCustomer(legalCustomer);
    }

}
