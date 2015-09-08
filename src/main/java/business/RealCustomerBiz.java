package business;

import business.util.CustomerUtil;
import dataacceess.RealCustomerCRUD;
import exception.SqlException;
import exception.ValidationException;
import model.RealCustomer;

import java.util.List;

public class RealCustomerBiz {
    private static RealCustomerBiz ourInstance = new RealCustomerBiz();

    public static RealCustomerBiz getInstance() {
        return ourInstance;
    }

    private RealCustomerBiz() {
    }

    public RealCustomer createAndSaveRealCustomer(String firstName, String lastName, String nationalCode, String birthDate, String fatherName) throws SqlException, ValidationException {
        RealCustomer realCustomer = new RealCustomer();
        realCustomer.setFirstName(firstName);
        realCustomer.setLastName(lastName);
        realCustomer.setFatherName(fatherName);
        realCustomer.setNationalCode(nationalCode);
        realCustomer.setBirthDate(birthDate);
        realCustomer.setCustomerNumber(CustomerUtil.generateCustomerNumber());
        validateRealCustomer(realCustomer, true);
        return RealCustomerCRUD.saveRealCustomer(realCustomer);

    }

    public void validateRealCustomer(RealCustomer realCustomer, boolean add) throws ValidationException, SqlException {

        if (realCustomer.getFirstName() == null || realCustomer.getFirstName().length() == 0) {
            throw new ValidationException("first name has no value");
        }
        if (realCustomer.getLastName() == null || realCustomer.getLastName().length() == 0) {
            throw new ValidationException("last name has no value");
        }
        if (realCustomer.getFatherName() == null || realCustomer.getFatherName().length() == 0) {
            throw new ValidationException("father name has no value");
        }
        if (realCustomer.getBirthDate() == null || realCustomer.getBirthDate().length() == 0) {
            throw new ValidationException("Birth day has no value");
        }
        if (!realCustomer.getBirthDate().matches("[1-9][0-9]{3}/[0-1][0-9]/[0-3][0-9]")) {
            throw new ValidationException("Birth day is invalid");
        }
        if (realCustomer.getNationalCode() == null || realCustomer.getNationalCode().length() == 0) {
            throw new ValidationException("nation code is has no value");
        }
        if (!realCustomer.getNationalCode().matches("[0-9]{5}")) {
            throw new ValidationException("nation code is invalid");
        }
        boolean ExistsNationalCode;
        if (add) {
            ExistsNationalCode = RealCustomerCRUD.existRealCustomerWithNationalCode(realCustomer.getNationalCode());
        } else {
            ExistsNationalCode = RealCustomerCRUD.existsRealCustomerNationalCode(realCustomer.getNationalCode(), realCustomer.getId());
        }
        if (ExistsNationalCode) {
            throw new ValidationException("national code exists");
        }
    }

    public List<RealCustomer> searchRealCustomer(String firstName, String lastName, String nationalCode, String customerNumber) {
        RealCustomer realCustomer = new RealCustomer();
        realCustomer.setFirstName(firstName);
        realCustomer.setLastName(lastName);
        realCustomer.setNationalCode(nationalCode);
        realCustomer.setCustomerNumber(customerNumber);
        return RealCustomerCRUD.searchRealCustomer(realCustomer);
    }
    public RealCustomer findRealCustomer(int id) throws SqlException {
        return  RealCustomerCRUD.loadRealCustomer(id);

    }

    public RealCustomer updateRealCustomer(String firsName,String  lastName,String  fatherName, String nationalCode, String birthDate, int id) throws SqlException, ValidationException {
       RealCustomer realCustomer=new RealCustomer();
        realCustomer.setFirstName(firsName);
        realCustomer.setLastName(lastName);
        realCustomer.setFatherName(fatherName);
        realCustomer.setNationalCode(nationalCode);
        realCustomer.setBirthDate(birthDate);
        realCustomer.setId(id);
        validateRealCustomer(realCustomer,false);
        return RealCustomerCRUD.updateRealCustomer(realCustomer);
    }
    public void deleteRealCustomer(int id) throws SqlException {
       RealCustomerCRUD.deleteRealCustomer(id);
    }
}
