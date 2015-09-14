package business;

import business.util.CustomerUtil;
import dataacceess.LoanTypeCRUD;
import dataacceess.RealCustomerCRUD;
import exception.SqlException;
import exception.ValidationException;
import model.GrantCondition;
import model.LoanType;
import model.RealCustomer;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class LoanTypeBiz {
    private final static Logger logger = Logger.getLogger(LoanTypeBiz.class);

    private static LoanTypeBiz ourInstance = new LoanTypeBiz();

    public static LoanTypeBiz getInstance() {
        return ourInstance;
    }

    private LoanTypeBiz() {
    }

    public LoanType createAndSaveLoanType(String loanTypeName, BigDecimal interestRate, String[] names
            , String[] minContractDurations, String[] maxContractDurations, String[] minContractAmounts, String[] maxContractAmounts)
            throws SqlException, ValidationException {
        try {


            LoanType loanType = new LoanType();
            loanType.setName(loanTypeName);
            loanType.setInterestRate(interestRate);
            Set<GrantCondition> grantConditions = new HashSet<GrantCondition>();
            for (int i = 0; i < minContractAmounts.length; i++) {
                GrantCondition grantCondition = new GrantCondition();
                grantCondition.setName(names[i]);
                grantCondition.setMinContractDuration(Integer.parseInt(minContractDurations[i]));
                grantCondition.setMaxContractDuration(Integer.parseInt(maxContractDurations[i]));
                grantCondition.setMinContractAmount(new BigDecimal(minContractAmounts[i]));
                grantCondition.setMaxContractAmount(new BigDecimal(maxContractAmounts[i]));
                grantConditions.add(grantCondition);
            }
            loanType.setGrantConditions(grantConditions);
            validateLoanType(loanType);
           LoanType loanType1= LoanTypeCRUD.saveLoanType(loanType);
            logger.info("Loan file saved successfully.");
            return loanType1;
        } catch (NumberFormatException e) {
            logger.error("Number Format Exception in save");
            throw new ValidationException("Number Format Exception ");
        }
    }

    public void validateLoanType(LoanType loanType) throws ValidationException {
        if (loanType.getName() == null || loanType.getName().length() == 0) {
            logger.error("name has no value ");
            throw new ValidationException(" name has no value");
        }
        if ((loanType.getInterestRate() == null || loanType.getInterestRate().compareTo(BigDecimal.ZERO) < 0)) {
            logger.error("Interest Rate has no value ");
            throw new ValidationException(" Interest Rate has no value");
        }
        if (loanType.getGrantConditions() == null || loanType.getGrantConditions().isEmpty()) {
            logger.error("Grant Condition  has no Value");
            throw new ValidationException("Grant Condition  has no Value");
        }
        for (GrantCondition grantCondition : loanType.getGrantConditions()) {
            if (grantCondition.getName() == null || grantCondition.getName().length() == 0) {
                logger.error("name has no value");
                throw new ValidationException(" name has no value");
            }
            if (grantCondition.getMinContractDuration() <= 0) {
                logger.error("Duration should not be negative ");
                throw new ValidationException("Duration should not be negative");
            }
            if (grantCondition.getMaxContractDuration() <= 0) {
                logger.error("Duration should not be negative");
                throw new ValidationException("Duration should not be negative");
            }
            if (grantCondition.getMinContractAmount().compareTo(BigDecimal.ZERO) < 0) {
                logger.error("Amount should not be negative");
                throw new ValidationException("Amount should not be negative");
            }
            if (grantCondition.getMaxContractAmount().compareTo(BigDecimal.ZERO) < 0) {
                logger.error("Amount should not be negative");
                throw new ValidationException("Amount should not be negative");
            }
            if (grantCondition.getMaxContractAmount().compareTo(grantCondition.getMinContractAmount()) < 0) {
                logger.error("Min Contract Amount should be less than Min Contract Amount");
                throw new ValidationException(" Min Contract Amount should be less than Min Contract Amount ");
            }
            if (grantCondition.getMaxContractDuration() < (grantCondition.getMinContractDuration())) {
                logger.error("Min Contract Duration should be less than Min Contract Duration ");
                throw new ValidationException("Min Contract Duration should be less than Min Contract Duration ");
            }

        }
        logger.info("validate Loan Type");
    }
}
