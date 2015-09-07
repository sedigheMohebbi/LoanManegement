package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "legalcustomer")
@PrimaryKeyJoinColumn(name = "id")
public class LegalCustomer extends Customer {
    @Column(name = "companyName")
    private String companyName;
    @Column(name = "registrationDate")
    private String registrationDate;
    @Column(name = "economicCode")
    private String economicCode;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName.trim();
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate.trim();
    }

    public String getEconomicCode() {
        return economicCode;
    }

    public void setEconomicCode(String economicCode) {
        this.economicCode = economicCode;
    }
}
