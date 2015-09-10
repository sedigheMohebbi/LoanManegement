package business;

import business.util.CustomerUtil;
import dataacceess.RealCustomerCRUD;
import exception.SqlException;
import exception.ValidationException;
import model.GrantCondition;
import model.LoanType;
import model.RealCustomer;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class LoanTypeBiz {
    private static LoanTypeBiz ourInstance = new LoanTypeBiz();

    public static LoanTypeBiz getInstance() {
        return ourInstance;
    }

    private LoanTypeBiz() {
    }

    public LoanType createAndSaveLoanType(String loanTypeName, BigDecimal interestRate, String name
            , int minContractDuration, int maxContractDuration, BigDecimal minContractAmount, BigDecimal maxContractAmount)
            throws SqlException, ValidationException {
        LoanType loanType = new LoanType();
        loanType.setName(loanTypeName);
        loanType.setInterestRate(interestRate);
        Set<GrantCondition> grantConditions = new HashSet<GrantCondition>();
        GrantCondition grantCondition = new GrantCondition();
        grantCondition.setName(name);
        grantCondition.setMinContractDuration(minContractDuration);
        grantCondition.setMaxContractDuration(maxContractDuration);
        grantCondition.setMinContractAmount(minContractAmount);
        grantCondition.setMaxContractAmount(maxContractAmount);
        grantConditions.add(grantCondition);
        loanType.setGrantConditions(grantConditions);

      //todo  validateRealCustomer(realCustomer, true);
       // return RealCustomerCRUD.saveRealCustomer(realCustomer);

    }

}
