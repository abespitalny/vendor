package model;

import java.math.BigDecimal;

/*
 * This class is a representation of the VendorUser table from the schema
 */
public abstract class VendorUser {	
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String telephone;
    private String email;
    private BigDecimal revenue;

    public VendorUser() {
        super();
    }
    public VendorUser(String username, String email) {
        this.username = username;
        this.email = email;
    }
    public VendorUser(String username, String firstName, String lastName, String address, String city, String state, String zipCode, String telephone, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.telephone = telephone;
        this.email = email;
    }
    public VendorUser(String username, String firstName, String lastName, String address, String city, String state, String zipCode, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.email = email;
    }
    public VendorUser(String username, String password, String firstName, String lastName, String address, String city,
                      String state, String zipCode, String telephone, String email) {
        super();
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.telephone = telephone;
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public BigDecimal getRevenue() {
        return revenue;
    }
    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }
}
