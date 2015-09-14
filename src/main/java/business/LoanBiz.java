package business;

import dataacceess.LoanCRUD;
import dataacceess.LoanTypeCRUD;
import dataacceess.RealCustomerCRUD;
import exception.SqlException;
import exception.ValidationException;
import model.GrantCondition;
import model.Loan;
import model.LoanType;
import model.RealCustomer;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class LoanBiz {
    private final static Logger logger = Logger.getLogger(LoanBiz.class);
    private static LoanBiz ourInstance = new LoanBiz();

    public static LoanBiz getInstance() {
        return ourInstance;
    }

    private LoanBiz() {
    }

    public RealCustomer findCustomer(String customerNumber) throws ValidationException {
        RealCustomer realCustomer = RealCustomerCRUD.findRealCustomerWithCustomerNumber(customerNumber);
        if (realCustomer == null) {
            logger.error("Invalid Customer Number " + customerNumber);

            throw new ValidationException("Invalid Customer Number " + customerNumber);
        }
        return realCustomer;
    }

    public List<LoanType> loadAllLoanTypes() {
        List<LoanType> loanTypes = LoanTypeCRUD.loadLoanTypeName();
        logger.info("loan type loade successfully.");
        return loanTypes;
    }

    public Loan saveLoanFile(String customerNumber, int loanTypeId, int durationOfContract, BigDecimal amountOfContract) throws ValidationException, SqlException {
        RealCustomer realCustomer = findCustomer(customerNumber);
        Loan loan = new Loan();
        loan.setRealCustomer(realCustomer);
        LoanType loanType = LoanTypeCRUD.loadLoanType(loanTypeId);
        loan.setLoanType(loanType);
        Set<GrantCondition> grantConditions = loanType.getGrantConditions();
        for (GrantCondition grantCondition : grantConditions) {
            if (grantCondition.getMaxContractDuration() > durationOfContract &&
                    grantCondition.getMinContractDuration() <= durationOfContract &&
                    grantCondition.getMaxContractAmount().compareTo(amountOfContract) == 1 &&
                    grantCondition.getMinContractAmount().compareTo(amountOfContract) == -1) {
                loan.setAmountOfContract(amountOfContract);
                loan.setDurationOfContract(durationOfContract);
            } else {
                logger.error("This is exception");
                throw new ValidationException("Loan File is Not Consistent With Any Grant Conditions Of Loan Type:  " + loanType.getName());
            }
        }
        LoanCRUD.saveLoan(loan);
        logger.info("Loan file saved successfully.");
        return loan;
    }

}
