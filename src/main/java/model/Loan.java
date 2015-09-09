package model;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "durationOfContract")
    private int durationOfContract;
    @Column(name = "amountOfContract")
    private BigDecimal amountOfContract;
    @Column(name = "loanTypeId")
    private LoanType loanType;
    @Column(name = "realCustomerId")
    private RealCustomer realCustomer;

    public Loan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDurationOfContract() {
        return durationOfContract;
    }

    public void setDurationOfContract(int durationOfContract) {
        this.durationOfContract = durationOfContract;
    }

    public BigDecimal getAmountOfContract() {
        return amountOfContract;
    }

    public void setAmountOfContract(BigDecimal amountOfContract) {
        this.amountOfContract = amountOfContract;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "loanTypeId")
    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "customerId")
    public RealCustomer getRealCustomer() {
        return realCustomer;
    }

    public void setRealCustomer(RealCustomer realCustomer) {
        this.realCustomer = realCustomer;
    }
}
