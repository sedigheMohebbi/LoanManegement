package model;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "loanType")
public class LoanType {
    @Column(name = "name")
    private String name;
    @Column(name = "interestRate")
    private BigDecimal interestRate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "loanTypeId")
    private Set<GrantCondition> grantConditions;

    public LoanType() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Set<GrantCondition> getGrantConditions() {
        return grantConditions;
    }

    public void setGrantConditions(Set<GrantCondition> grantConditions) {
        this.grantConditions = grantConditions;
    }


}
