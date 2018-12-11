package model;

import java.math.BigDecimal;
import java.sql.Date;

/*
 * This class is a representation of the Employee table from the schema
 */
public class Employee extends VendorUser {
    private String ssn;
    private Date startDate;
    private EmployeeLevel level;
    private BigDecimal hourlyRate;

    public Employee(String username, String email, EmployeeLevel level) {
        super(username, email);
        this.level = level;
    }
    public Employee(String username, String password, String firstName, String lastName, String address, String city,
                    String state, String zipCode, String telephone, String email, String ssn,
                    EmployeeLevel level, BigDecimal hourlyRate) {
        super(username, password, firstName, lastName, address, city, state, zipCode, telephone, email);
        this.ssn = ssn;
        this.level = level;
        this.hourlyRate = hourlyRate;
    }
    public String getSsn() {
        return ssn;
    }
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public EmployeeLevel getLevel() {
        return level;
    }
    public void setLevel(EmployeeLevel level) {
        this.level = level;
    }
    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }
    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
