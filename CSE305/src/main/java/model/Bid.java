package model;

import java.math.BigDecimal;
import java.sql.Date;

/*
 * This class is a representation of the Bid table from the schema
 */
public class Bid {
    private int bidID;
    private String customerID;
    private int auctionID;
    private Date bidTime;
    private BigDecimal bidPrice;
    
    public int getBidID() {
        return bidID;
    }
    public void setBidID(int bidID) {
        this.bidID = bidID;
    }
    public String getCustomerID() {
        return customerID;
    }
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    public int getAuctionID() {
        return auctionID;
    }
    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }
    public Date getBidTime() {
        return bidTime;
    }
    public void setBidTime(Date bidTime) {
        this.bidTime = bidTime;
    }
    public BigDecimal getBidPrice() {
        return bidPrice;
    }
    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }
}
