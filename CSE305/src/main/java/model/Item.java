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
<<<<<<< HEAD
    private int soldPrice;

    public int getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(int soldPrice) {
        this.soldPrice = soldPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

=======
    private BigDecimal soldPrice;
    
>>>>>>> 0e271dbbd3fe2d279204cbce55827b937c5c198c
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
<<<<<<< HEAD

=======
    public String getDescription() {
        return description;
    }
    public BigDecimal getSoldPrice() {
        return soldPrice;
    }
    public void setSoldPrice(BigDecimal soldPrice) {
        this.soldPrice = soldPrice;
    }
>>>>>>> 0e271dbbd3fe2d279204cbce55827b937c5c198c
}
