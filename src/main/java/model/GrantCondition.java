package model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "grantCondition")
public class GrantCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "minContractDuration")
    private int minContractDuration;
    @Column(name = "maxContractDuration")
    private int maxContractDuration;
    @Column(name = "minContractAmount")
    private BigDecimal minContractAmount;
    @Column(name = "maxContractAmount")
    private BigDecimal maxContractAmount;

    public GrantCondition() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinContractDuration() {
        return minContractDuration;
    }

    public void setMinContractDuration(int minContractDuration) {
        this.minContractDuration = minContractDuration;
    }

    public int getMaxContractDuration() {
        return maxContractDuration;
    }

    public void setMaxContractDuration(int maxContractDuration) {
        this.maxContractDuration = maxContractDuration;
    }

    public BigDecimal getMinContractAmount() {
        return minContractAmount;
    }

    public void setMinContractAmount(BigDecimal minContractAmount) {
        this.minContractAmount = minContractAmount;
    }

    public BigDecimal getMaxContractAmount() {
        return maxContractAmount;
    }

    public void setMaxContractAmount(BigDecimal maxContractAmount) {
        this.maxContractAmount = maxContractAmount;
    }
}

