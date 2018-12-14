package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Auction;
import model.Item;

public class ItemDao {
    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();

        String sql = "SELECT ItemID, ItemName, ItemType, Description, Quantity FROM Item";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }
        
        return items;
    }

    public List<Item> getBestsellerItems() {
        List<Item> items = new ArrayList<>();

        String sql = "SELECT ItemID, ItemName, ItemType, Description, Quantity"
                  + " FROM Item"
                  + " WHERE NumSold > (SELECT AVG(NumSold) FROM Item)"
                  + " ORDER BY NumSold DESC";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }

        return items;
    }

    public List<Item> getSummaryListing(String searchBy, String searchKeyword) {
        List<Item> items = new ArrayList<>();

        String sql;
        if (searchBy.equals("name"))
            sql = "SELECT ItemID, ItemName, ItemType, Description, TotalRevenue" +
                 " FROM Item LEFT JOIN" +
                 " (SELECT ItemID, SUM(CurrentHighestBidPrice) AS TotalRevenue" +
                 "  FROM Auction" +
                 "  WHERE SaleStatus = 'Paid' GROUP BY ItemID" +
                 " ) AS A USING (ItemID)" +
                 " WHERE MATCH (ItemName) AGAINST (? IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION)";
        else if (searchBy.equals("type"))
            sql = "SELECT ItemID, ItemName, ItemType, Description, TotalRevenue" +
                 " FROM Item LEFT JOIN" +
                 " (SELECT ItemID, SUM(CurrentHighestBidPrice) AS TotalRevenue" +
                 "  FROM Auction" +
                 "  WHERE SaleStatus = 'Paid' GROUP BY ItemID" +
                 " ) AS A USING (ItemID)" +
                 " WHERE ItemType = ?";
        else
            sql = "SELECT ItemID, ItemName, ItemType, Description, TotalRevenue" +
                 " FROM Item INNER JOIN" +
                 " (SELECT ItemID, SUM(CurrentHighestBidPrice) AS TotalRevenue" +
                 "  FROM Auction INNER JOIN VendorUser ON Seller = Username" +
                 "  WHERE (FirstName LIKE CONCAT('%', ?, '%') OR LastName LIKE CONCAT('%', ?, '%')) AND SaleStatus = 'Paid' GROUP BY ItemID" +
                 " ) AS A USING (ItemID)";
        
        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            if (searchBy.equals("name") || searchBy.equals("type"))
                statement.setString(1, searchKeyword);
            else {
                statement.setString(1, searchKeyword);
                statement.setString(2, searchKeyword);
            }
            
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                    item.setRevenue(rs.getBigDecimal(5));
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }

        return items;
    }

    public List<Item> getItemSuggestions(String customerID) {
        List<Item> items = new ArrayList<>();
        
        // suggestions are based on previous bids placed by the customer
        String sqlGetNames = "SELECT DISTINCT ItemName" +
                            " FROM Item AS I" +
                            " WHERE Quantity > 0 AND EXISTS" +
                            " (SELECT 1" +
                            "  FROM Bid INNER JOIN Auction AS A USING (AuctionID)" +
                            "  WHERE A.ItemID = I.ItemID AND CustomerID = ?)";
	
        String sqlGetSuggestions = "SELECT ItemID, ItemName, ItemType, Description, Quantity" +
                                  " FROM Item" +
                                  " WHERE MATCH (ItemName) AGAINST (? IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION)";
        
        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statementGetNames = conn.prepareStatement(sqlGetNames);
                PreparedStatement statementGetSuggestions = conn.prepareStatement(sqlGetSuggestions);
        ) {
            statementGetNames.setString(1, customerID);
            
            List<String> names = new ArrayList<>();
            try (ResultSet rs = statementGetNames.executeQuery()) {
                while (rs.next())
                    names.add(rs.getString(1));
            }
            
            statementGetSuggestions.setString(1, String.join(" ", names));
            try (ResultSet rs = statementGetSuggestions.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }
        
        return items;
    }

    public List<Object> getItemsBySeller(String sellerID) {
        List<Object> data = new ArrayList<>();
        List<Auction> auctions = new ArrayList<>();
        List<Item> items = new ArrayList<>();

        String sql = "SELECT AuctionID, MinBidPrice, CurrentHighestBidPrice, NumCopies, ItemID, ItemName, ItemType, OpenDate, EndDate" +
                    " FROM Auction INNER JOIN Item USING (ItemID)" +
                    " WHERE Seller = ?";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, sellerID);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Auction auction = new Auction();
                    auction.setAuctionID(rs.getInt(1));
                    auction.setMinBidPrice(rs.getBigDecimal(2));
                    auction.setCurrentHighestBidPrice(rs.getBigDecimal(3));
                    auction.setNumCopies(rs.getInt(4));
                    auction.setOpenDate(rs.getDate(8));
                    auction.setEndDate(rs.getDate(9));
                    auctions.add(auction);

                    Item item = new Item();
                    item.setItemID(rs.getInt(5));
                    item.setName(rs.getString(6));
                    item.setType(rs.getString(7));
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }

        data.add(auctions);
        data.add(items);
        return data;
    }

    public List<Item> getItemTypes() {
        List<Item> items = new ArrayList<>();

        String sql = "SELECT DISTINCT ItemType FROM Item";
        
        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item();
                    item.setType(rs.getString(1));
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }

        return items;
    }

    public List<Object> getItemsByName(String itemName) {
        List<Object> data = new ArrayList<>();
        List<Auction> auctions = new ArrayList<>();
        List<Item> items = new ArrayList<>();

        String sql = "SELECT AuctionID, MinBidPrice, CurrentHighestBidPrice, NumCopies, ItemID, ItemName, ItemType, OpenDate, EndDate" +
                    " FROM Auction INNER JOIN Item USING (ItemID)" +
                    " WHERE ItemName LIKE CONCAT('%', ?, '%')";
        
        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, itemName);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Auction auction = new Auction();
                    auction.setAuctionID(rs.getInt(1));
                    auction.setMinBidPrice(rs.getBigDecimal(2));
                    auction.setCurrentHighestBidPrice(rs.getBigDecimal(3));
                    auction.setNumCopies(rs.getInt(4));
                    auction.setOpenDate(rs.getDate(8));
                    auction.setEndDate(rs.getDate(9));
                    auctions.add(auction);

                    Item item = new Item();
                    item.setItemID(rs.getInt(5));
                    item.setName(rs.getString(6));
                    item.setType(rs.getString(7));
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }

        data.add(auctions);
        data.add(items);
        return data;
    }

    public List<Object> getItemsByType(String itemType) {
        List<Object> data = new ArrayList<>();
        List<Auction> auctions = new ArrayList<>();
        List<Item> items = new ArrayList<>();

        String sql = "SELECT AuctionID, MinBidPrice, CurrentHighestBidPrice, NumCopies, ItemID, ItemName, ItemType, OpenDate, EndDate" +
                    " FROM Auction INNER JOIN Item USING (ItemID)" +
                    " WHERE ItemType = ?";
        
        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, itemType);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Auction auction = new Auction();
                    auction.setAuctionID(rs.getInt(1));
                    auction.setMinBidPrice(rs.getBigDecimal(2));
                    auction.setCurrentHighestBidPrice(rs.getBigDecimal(3));
                    auction.setNumCopies(rs.getInt(4));
                    auction.setOpenDate(rs.getDate(8));
                    auction.setEndDate(rs.getDate(9));
                    auctions.add(auction);

                    Item item = new Item();
                    item.setItemID(rs.getInt(5));
                    item.setName(rs.getString(6));
                    item.setType(rs.getString(7));
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }

        data.add(auctions);
        data.add(items);
        return data;
    }

    public List<Item> getBestsellersForCustomer(String customerID) {
        List<Item> items = new ArrayList<>();

        String sql = "SELECT ItemID, ItemName, ItemType, Description, Quantity, NumSold" +
                    " FROM Item INNER JOIN Auction USING (ItemID)" +
                    " WHERE Seller = ? AND NumSold > (SELECT AVG(NumSold) FROM Item)" +
                    " ORDER BY NumSold DESC";
        
        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, customerID);
            
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                    item.setNumSold(rs.getInt(6));
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }
        
        return items;
    }
    
    public Item getItemFromAuctionID(int auctionID) {
        Item item = null;
        
        String sql = "SELECT ItemID, ItemName, ItemType, Description" +
                    " FROM Item INNER JOIN Auction USING (ItemID)" +
                    " WHERE AuctionID = ?";
        
        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setInt(1, auctionID);
            
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next())
                    item = new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }
        
        return item;
    }
}
