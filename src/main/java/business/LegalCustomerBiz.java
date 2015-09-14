package business;


import business.util.CustomerUtil;
import dataacceess.LegalCustomerCRUD;
import exception.SqlException;
import exception.ValidationException;
import model.LegalCustomer;
import model.RealCustomer;
import org.apache.log4j.Logger;

import java.util.List;

public class LegalCustomerBiz {
    private final static Logger logger = Logger.getLogger(LegalCustomer.class);
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
        logger.info("legal customer saved successfully.");
        return LegalCustomerCRUD.saveLegalCustomer(legalCustomer);
    }

    public void validateLegalCustomer(LegalCustomer legalCustomer, boolean add) throws ValidationException, SqlException {

        if (legalCustomer.getCompanyName() == null || legalCustomer.getCompanyName().length() == 0) {
            logger.error("company name has no value");
            throw new ValidationException("company name has no value");
        }
        if (legalCustomer.getRegistrationDate() == null || legalCustomer.getRegistrationDate().length() == 0) {
            logger.error("Registration Date has no value");
            throw new ValidationException("Registration Date has no value");
        }
        if (legalCustomer.getEconomicCode() == null || legalCustomer.getRegistrationDate().length() == 0) {
            logger.error("Registration Date has no value");
            throw new ValidationException("Registration Date has no value");
        }
        boolean economicExists;
        if (add) {
            economicExists = LegalCustomerCRUD.existsLegalCustomerWithEconomicCode(legalCustomer.getEconomicCode());
        } else {
            economicExists = LegalCustomerCRUD.existsLegalEconomicCode(legalCustomer.getEconomicCode(), legalCustomer.getId());
        }
        if (economicExists) {
            logger.error("Economic code exists");
            throw new ValidationException("Economic code exists");
        }
        if (!legalCustomer.getEconomicCode().matches("[0-9]{10}")) {
            logger.error("economic code is invalid");
            throw new ValidationException("economic code is invalid");
        }
        if (!legalCustomer.getRegistrationDate().matches("[1-9][0-9]{3}/[0-1][0-9]/[0-3][0-9]")) {
            logger.error("Registration date is invalid");
            throw new ValidationException("Registration date is invalid");
        }
    }

    public List<LegalCustomer> searchLegalCustomer(String companyName, String economicCode, String customerNumber) {
        LegalCustomer legalCustomer = new LegalCustomer();
        legalCustomer.setCompanyName(companyName);
        legalCustomer.setEconomicCode(economicCode);
        legalCustomer.setCustomerNumber(customerNumber);
        List<LegalCustomer> legalCustomers = LegalCustomerCRUD.searchLegalCustomer(legalCustomer);
        logger.info("Save Legal Customer successfully");
        return legalCustomers;
    }

    public LegalCustomer findLegalCustomer(int id) throws SqlException {
        return LegalCustomerCRUD.loadLegalCustomer(id);

    }

    public LegalCustomer updateLegal(String companyName, String economicCode, String registration, int id) throws SqlException, ValidationException {
        LegalCustomer legalCustomer = new LegalCustomer();
        legalCustomer.setCompanyName(companyName);
        legalCustomer.setEconomicCode(economicCode);
        legalCustomer.setRegistrationDate(registration);
        legalCustomer.setId(id);
        validateLegalCustomer(legalCustomer, false);
        LegalCustomer legalCustomer1 = LegalCustomerCRUD.updateLegalCustomer(legalCustomer);
        logger.info("Update Legal Customer successfully");
        return legalCustomer1;
    }

    public void deleteLegalCustomer(int id) throws SqlException {
        LegalCustomerCRUD.deleteLegalCustomer(id);
        logger.info("Delete Legal Customer successfully");
    }
}
