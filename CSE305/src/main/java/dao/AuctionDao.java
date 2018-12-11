package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Auction;

public class AuctionDao {
    public List<Auction> getAllActiveAuctions() {
        List<Auction> auctions = new ArrayList<>();
        
        String sql = "SELECT AuctionID, IF(CurrentHighestBidPrice IS NULL, MinBidPrice, CurrentHighestBidPrice) AS Price, ItemID, ItemName, NumCopies" +
                    " FROM Auction INNER JOIN Item USING (ItemID)" +
                    " WHERE NOW() < EndDate";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            try (
                    ResultSet rs = statement.executeQuery();
            ) {
                while (rs.next()) {
                    Auction auction = new Auction();
                    auction.setAuctionID(rs.getInt(1));
                    auction.setCurrentHighestBidPrice(rs.getBigDecimal(2));
                    auction.setItemID(rs.getInt(3));
                    auction.setItemName(rs.getString(4));
                    auction.setNumCopies(rs.getInt(5));
                    auctions.add(auction);
                }
            }
            
            
        } catch (SQLException e) {
        }
        return auctions;
    }

	public List<Auction> getAuctions(String customerID) {
		
//		List<Auction> auctions = new ArrayList<Auction>();
//		
//		/*
//		 * The students code to fetch data from the database will be written here
//		 * Each record is required to be encapsulated as a "Auction" class object and added to the "auctions" ArrayList
//		 * Query to get data about all the auctions in which a customer participated should be implemented
//		 * customerID is the customer's primary key, given as method parameter
//		 */
//		
//		/*Sample data begins*/
//		for (int i = 0; i < 5; i++) {
//			Auction auction = new Auction();
//			auction.setAuctionID(1);
//			auction.setBidIncrement(10);
//			auction.setMinimumBid(10);
//			auction.setCopiesSold(12);
//			auction.setItemID(1234);
//			auction.setClosingBid(120);
//			auction.setCurrentBid(120);
//			auction.setCurrentHighBid(120);
//			auction.setReserve(10);
//			auctions.add(auction);
//		}
//		/*Sample data ends*/
//		
//		return auctions;
            return null;
	}

	public List<Auction> getOpenAuctions(String employeeEmail) {
//		List<Auction> auctions = new ArrayList<Auction>();
//		
//		/*
//		 * The students code to fetch data from the database will be written here
//		 * Each record is required to be encapsulated as a "Auction" class object and added to the "auctions" ArrayList
//		 * Query to get data about all the open auctions monitored by a customer representative should be implemented
//		 * employeeEmail is the email ID of the customer representative, which is given as method parameter
//		 */
//		
//		/*Sample data begins*/
//		for (int i = 0; i < 5; i++) {
//			Auction auction = new Auction();
//			auction.setAuctionID(1);
//			auction.setBidIncrement(10);
//			auction.setMinimumBid(10);
//			auction.setCopiesSold(12);
//			auction.setItemID(1234);
//			auction.setClosingBid(120);
//			auction.setCurrentBid(120);
//			auction.setCurrentHighBid(120);
//			auction.setReserve(10);
//			auctions.add(auction);
//		}
//		/*Sample data ends*/
//		
//		return auctions;
            return null;
		
		
	}

	public String recordSale(String auctionID) {
		/*
		 * The students code to update data in the database will be written here
		 * Query to record a sale, indicated by the auction ID, should be implemented
		 * auctionID is the Auction's ID, given as method parameter
		 * The method should return a "success" string if the update is successful, else return "failure"
		 */
		/* Sample data begins */
		return "success";
		/* Sample data ends */
	}

	public List getAuctionData(String auctionID, String itemID) {
		
//		List output = new ArrayList();
//		Item item = new Item();
//		Bid bid = new Bid();
//		Auction auction = new Auction();
//		Customer customer = new Customer();
//		
//		/*
//		 * The students code to fetch data from the database will be written here
//		 * The item details are required to be encapsulated as a "Item" class object
//		 * The bid details are required to be encapsulated as a "Bid" class object
//		 * The auction details are required to be encapsulated as a "Auction" class object
//		 * The customer details are required to be encapsulated as a "Customer" class object
//		 * Query to get data about auction indicated by auctionID and itemID should be implemented
//		 * auctionID is the Auction's ID, given as method parameter
//		 * itemID is the Item's ID, given as method parameter
//		 * The customer details must include details about the current winner of the auction
//		 * The bid details must include details about the current highest bid
//		 * The item details must include details about the item, indicated by itemID
//		 * The auction details must include details about the item, indicated by auctionID
//		 * All the objects must be added in the "output" list and returned
//		 */
//		
//		/*Sample data begins*/
//		for (int i = 0; i < 4; i++) {
//			item.setItemID(123);
//			item.setDescription("sample description");
//			item.setType("BOOK");
//			item.setName("Sample Book");
//			
//			bid.setCustomerID("123-12-1234");
//			bid.setBidPrice(120);
//			
//			customer.setCustomerID("123-12-1234");
//			customer.setFirstName("Shiyong");
//			customer.setLastName("Lu");
//			
//			auction.setMinimumBid(100);
//			auction.setBidIncrement(10);
//			auction.setCurrentBid(110);
//			auction.setCurrentHighBid(115);
//			auction.setAuctionID(Integer.parseInt(auctionID));
//		}
//		/*Sample data ends*/
//		
//		output.add(item);
//		output.add(bid);
//		output.add(auction);
//		output.add(customer);
//		
//		return output;
            return null;
	}

	
}
