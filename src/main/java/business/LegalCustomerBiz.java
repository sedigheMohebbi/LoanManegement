package business;


import business.util.CustomerUtil;
import dataacceess.LegalCustomerCRUD;
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
    public void validateLegalCustomer(LegalCustomer legalCustomer, boolean add) throws ValidationException, SqlException {

        if (legalCustomer.getCompanyName() == null || legalCustomer.getCompanyName().length() == 0) {
            throw new ValidationException("company name has no value");
        }
        if (legalCustomer.getRegistrationDate() == null || legalCustomer.getRegistrationDate().length() == 0) {
            throw new ValidationException("Registration Date has no value");
        }
        if (legalCustomer.getEconomicCode() == null || legalCustomer.getRegistrationDate().length() == 0) {
            throw new ValidationException("Registration Date has no value");
        }
        boolean economicExists;
        if (add) {
            economicExists = LegalCustomerCRUD.existsLegalCustomerWithEconomicCode(legalCustomer.getEconomicCode());
        } else {
            economicExists = LegalCustomerCRUD.existsLegalEconomicCode(legalCustomer.getEconomicCode(), legalCustomer.getId());
        }
        if (economicExists) {
            throw new ValidationException("Economic code exists");
        }
        if (!legalCustomer.getEconomicCode().matches("[0-9]{5}")) {
            throw new ValidationException("economic code is invalid");
        }
        if (!legalCustomer.getRegistrationDate().matches("[1-9][0-9]{3}/[0-1][0-9]/[0-3][0-9]")) {
            throw new ValidationException("Registration date is invalid");
        }
    }


}
