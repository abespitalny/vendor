package model;

import java.math.BigDecimal;

/*
 * This class is a representation of the Item table from the schema
 */	 
public class Item {
    private int itemID;
    private String name;
    private String type;
    private String description;
    private int yearManufactured;
    private int numInStock;
    private int numSold;
    private BigDecimal soldPrice;

    public Item() {
        super();
    }
    public Item(int itemID, String name, String type, String description) {
        this.itemID = itemID;
        this.name = name;
        this.type = type;
        this.description = description;
    }    
    public Item(int itemID, String name, String type, String description, int numInStock) {
        this.itemID = itemID;
        this.name = name;
        this.type = type;
        this.description = description;
        this.numInStock = numInStock;
    }
    public int getItemID() {
        return itemID;
    }
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getYearManufactured() {
        return yearManufactured;
    }
    public void setYearManufactured(int yearManufactured) {
        this.yearManufactured = yearManufactured;
    }
    public int getNumInStock() {
        return numInStock;
    }
    public void setNumInStock(int numInStock) {
        this.numInStock = numInStock;
    }
    public int getNumSold() {
        return numSold;
    }
    public void setNumSold(int numSold) {
        this.numSold = numSold;
    }
    public String getDescription() {
        return description;
    }
    public BigDecimal getSoldPrice() {
        return soldPrice;
    }
    public void setSoldPrice(BigDecimal soldPrice) {
        this.soldPrice = soldPrice;
    }
}
