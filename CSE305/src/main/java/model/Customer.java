package model;

import java.math.BigDecimal;

/*
 * This class is a representation of the Customer table from the schema
 */
public class Customer extends VendorUser {
    private BigDecimal rating;
    private String creditCardNum;
    private int itemsSold;
    private int itemsPurchased;
    
    public Customer() {
        super();
    }
    public Customer(String username, String email) {
        super(username, email);
    }
    public Customer(String username, String firstName, String lastName, String address, String city, String state, String zipCode, String email) {
        super(username, firstName, lastName, address, city, state, zipCode, email);
    }
    public Customer(String username, String password, String firstName, String lastName, String address, String city,
                    String state, String zipCode, String telephone, String email, String creditCardNum,
                    BigDecimal rating) {
        super(username, password, firstName, lastName, address, city, state, zipCode, telephone, email);
        this.rating = rating;
        this.creditCardNum = creditCardNum;
    }
    public BigDecimal getRating() {
        return rating;
    }
    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }
    public String getCreditCardNum() {
        return creditCardNum;
    }
    public void setCreditCardNum(String creditCardNum) {
        this.creditCardNum = creditCardNum;
    }
    public int getItemsSold() {
        return itemsSold;
    }
    public void setItemsSold(int itemsSold) {
        this.itemsSold = itemsSold;
    }
    public int getItemsPurchased() {
        return itemsPurchased;
    }
    public void setItemsPurchased(int itemsPurchased) {
        this.itemsPurchased = itemsPurchased;
    }
    @Override
    public String toString() {
            return "The Customer Details are: <br/>Customer ID=" + super.getUsername() + "<br/>First Name=" + super.getFirstName() + "<br/>Last Name=" + super.getLastName()
                 + "<br/>Address=" + super.getAddress() + "<br/>City=" + super.getCity() + "<br/>State=" + super.getState() + "<br/>Zip Code=" + super.getZipCode()
                 + "<br/>Telephone=" + super.getTelephone() + "<br/>Email=" + super.getEmail() + "<br/>Credit Card=" + creditCardNum + "<br/>Rating=" + rating
                 + "<br/>Items Sold=" + itemsSold + "<br/>Items Purchased=" + itemsPurchased;
    }	
}
