package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                    item.setTotalRevenue(rs.getBigDecimal(5));
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

	public List getItemsBySeller(String sellerID) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch items sold by a given seller, indicated by sellerID, must be implemented
		 * sellerID, which is the Sellers's ID who's items are fetched, is given as method parameter
		 * The bid and auction details of each of the items should also be fetched
		 * The bid details must have the highest current bid for the item
		 * The auction details must have the details about the auction in which the item is sold
		 * Each item record is required to be encapsulated as a "Item" class object and added to the "items" List
		 * Each bid record is required to be encapsulated as a "Bid" class object and added to the "bids" List
		 * Each auction record is required to be encapsulated as a "Auction" class object and added to the "auctions" List
		 * The items, bids and auctions Lists have to be added to the "output" List and returned
		 */

//		List output = new ArrayList();
//		List<Item> items = new ArrayList<Item>();
//		List<Bid> bids = new ArrayList<Bid>();
//		List<Auction> auctions = new ArrayList<Auction>();
//		
//		/*Sample data begins*/
//		for (int i = 0; i < 4; i++) {
//			Item item = new Item();
//			item.setItemID(123);
//			item.setDescription("sample description");
//			item.setType("BOOK");
//			item.setName("Sample Book");
//			items.add(item);
//			
//			Bid bid = new Bid();
//			bid.setCustomerID("123-12-1234");
//			bid.setBidPrice(120);
//			bids.add(bid);
//			
//			Auction auction = new Auction();
//			auction.setMinimumBid(100);
//			auction.setBidIncrement(10);
//			auction.setAuctionID(123);
//			auctions.add(auction);
//		}
//		/*Sample data ends*/
//		
//		output.add(items);
//		output.add(bids);
//		output.add(auctions);
//		
//		return output;
            return null;
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

	public List getItemsByName(String itemName) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * The itemName, which is the item's name on which the query has to be implemented, is given as method parameter
		 * Query to fetch items containing itemName in their name has to be implemented
		 * Each item's corresponding auction data also has to be fetched
		 * Each item record is required to be encapsulated as a "Item" class object and added to the "items" List
		 * Each auction record is required to be encapsulated as a "Auction" class object and added to the "auctions" List
		 * The items and auctions Lists are to be added to the "output" List and returned
		 */

//		List output = new ArrayList();
//		List<Item> items = new ArrayList<Item>();
//		List<Auction> auctions = new ArrayList<Auction>();
//		
//		/*Sample data begins*/
//		for (int i = 0; i < 4; i++) {
//			Item item = new Item();
//			item.setItemID(123);
//			item.setDescription("sample description");
//			item.setType("BOOK");
//			item.setName("Sample Book");
//			items.add(item);
//			
//			Auction auction = new Auction();
//			auction.setMinimumBid(100);
//			auction.setBidIncrement(10);
//			auctions.add(auction);
//		}
//		/*Sample data ends*/
//		
//		output.add(items);
//		output.add(auctions);
//		
//		return output;
            return null;
	}

	public List getItemsByType(String itemType) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * The itemType, which is the item's type on which the query has to be implemented, is given as method parameter
		 * Query to fetch items containing itemType as their type has to be implemented
		 * Each item's corresponding auction data also has to be fetched
		 * Each item record is required to be encapsulated as a "Item" class object and added to the "items" List
		 * Each auction record is required to be encapsulated as a "Auction" class object and added to the "auctions" List
		 * The items and auctions Lists are to be added to the "output" List and returned
		 */

//		List output = new ArrayList();
//		List<Item> items = new ArrayList<Item>();
//		List<Auction> auctions = new ArrayList<Auction>();
//				
//		/*Sample data begins*/
//		for (int i = 0; i < 4; i++) {
//			Item item = new Item();
//			item.setItemID(123);
//			item.setDescription("sample description");
//			item.setType("BOOK");
//			item.setName("Sample Book");
//			items.add(item);
//			
//			Auction auction = new Auction();
//			auction.setMinimumBid(100);
//			auction.setBidIncrement(10);
//			auctions.add(auction);
//		}
//		/*Sample data ends*/
//		
//		output.add(items);
//		output.add(auctions);
//		
//		return output;
            return null;
	}

	public List<Item> getBestsellersForCustomer(String customerID) {

		/*
		 * The students code to fetch data from the database will be written here.
		 * Each record is required to be encapsulated as a "Item" class object and added to the "items" ArrayList.
		 * Query to get the Best-seller list of items for a particular customer, indicated by the customerID, has to be implemented
		 * The customerID, which is the customer's ID for whom the Best-seller items have to be fetched, is given as method parameter
		 */

//		List<Item> items = new ArrayList<Item>();
//				
//		/*Sample data begins*/
//		for (int i = 0; i < 6; i++) {
//			Item item = new Item();
//			item.setItemID(123);
//			item.setDescription("sample description");
//			item.setType("BOOK");
//			item.setName("Sample Book");
//			item.setNumCopies(50);
//			items.add(item);
//		}
//		/*Sample data ends*/
//		
//		return items;
            return null;
	}

}
