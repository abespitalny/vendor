package model;

import java.math.BigDecimal;
import java.sql.Date;

/*
 * This class is a representation of the Auction table from the schema
 */
public class Auction {
    private int auctionID;
    private BigDecimal bidIncrement;
    private BigDecimal minBidPrice;
    private BigDecimal reservePrice;
    private BigDecimal currentHighestBidPrice;
    private BigDecimal currentMaxBidPrice;
    private int numCopies;
    private String seller;
    private String monitor;
    private int itemID;
    private Date openDate;
    private Date endDate;
    private int winningBidID;
    private SaleStatus status;
    private String itemName;
    
    public int getAuctionID() {
        return auctionID;
    }
    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }
    public BigDecimal getBidIncrement() {
        return bidIncrement;
    }
    public void setBidIncrement(BigDecimal bidIncrement) {
        this.bidIncrement = bidIncrement;
    }
    public BigDecimal getMinBidPrice() {
        return minBidPrice;
    }
    public void setMinBidPrice(BigDecimal minBidPrice) {
        this.minBidPrice = minBidPrice;
    }
    public BigDecimal getReservePrice() {
        return reservePrice;
    }
    public void setReservePrice(BigDecimal reservePrice) {
        this.reservePrice = reservePrice;
    }
    public BigDecimal getCurrentHighestBidPrice() {
        return currentHighestBidPrice;
    }
    public void setCurrentHighestBidPrice(BigDecimal currentHighestBidPrice) {
        this.currentHighestBidPrice = currentHighestBidPrice;
    }
    public BigDecimal getCurrentMaxBidPrice() {
        return currentMaxBidPrice;
    }
    public void setCurrentMaxBidPrice(BigDecimal currentMaxBidPrice) {
        this.currentMaxBidPrice = currentMaxBidPrice;
    }
    public int getNumCopies() {
        return numCopies;
    }
    public void setNumCopies(int numCopies) {
        this.numCopies = numCopies;
    }
    public String getSeller() {
        return seller;
    }
    public void setSeller(String seller) {
        this.seller = seller;
    }
    public String getMonitor() {
        return monitor;
    }
    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }
    public int getItemID() {
        return itemID;
    }
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
    public Date getOpenDate() {
        return openDate;
    }
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public int getWinningBidID() {
        return winningBidID;
    }
    public void setWinningBidID(int winningBidID) {
        this.winningBidID = winningBidID;
    }
    public SaleStatus getStatus() {
        return status;
    }
    public void setStatus(SaleStatus status) {
        this.status = status;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
