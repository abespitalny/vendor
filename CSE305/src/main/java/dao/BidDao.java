package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Bid;
import model.Customer;
import model.Item;

public class BidDao {
    public List<Bid> getBidHistory(int auctionID) {
        List<Bid> bids = new ArrayList<>();
        
        String sql = "SELECT AuctionID, CustomerID, BidTime, BidPrice" +
                    " FROM Bid" +
                    " WHERE AuctionID = ?" +
                    " ORDER BY BidTime DESC";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setInt(1, auctionID);
            
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Bid bid = new Bid(rs.getString(2), rs.getInt(1), rs.getDate(3), rs.getBigDecimal(4));
                    bids.add(bid);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }
        
        return bids;
    }

    public List<Bid> getAuctionHistory(String customerID) {
        List<Bid> bids = new ArrayList<>();

        String sql = "SELECT AuctionID, CustomerID, MAX(BidTime) AS LastBidTime, MAX(BidPrice) AS LastBidPrice" +
                    " FROM Bid INNER JOIN Auction USING (AuctionID)" +
                    " WHERE CustomerID = ? GROUP BY AuctionID" +
                    " ORDER BY LastBidTime DESC";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, customerID);
            
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Bid bid = new Bid(rs.getString(2), rs.getInt(1), rs.getDate(3), rs.getBigDecimal(4));
                    bids.add(bid);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }
        
        return bids;
    }

	public Bid submitBid(String auctionID, String itemID, Float currentBid, Float maxBid, String customerID) {
		
//		Bid bid = new Bid();
//
//		/*
//		 * The students code to insert data in the database
//		 * auctionID, which is the Auction's ID for which the bid is submitted, is given as method parameter
//		 * itemID, which is the Item's ID for which the bid is submitted, is given as method parameter
//		 * currentBid, which is the Customer's current bid, is given as method parameter
//		 * maxBid, which is the Customer's maximum bid for the item, is given as method parameter
//		 * customerID, which is the Customer's ID, is given as method parameter
//		 * Query to submit a bid by a customer, indicated by customerID, must be implemented
//		 * After inserting the bid data, return the bid details encapsulated in "bid" object
//		 */
//
//		/*Sample data begins*/
//		bid.setAuctionID(123);
//		bid.setCustomerID("123-12-1234");
//		bid.setBidTime("2008-12-11");
//		bid.setBidPrice(currentBid);
//		/*Sample data ends*/
//		
//		return bid;
            return null;
	}

	public List<Bid> getSalesListing(String searchKeyword) {
		
//		List<Bid> bids = new ArrayList<Bid>();
//
//		/*
//		 * The students code to fetch data from the database
//		 * Each record is required to be encapsulated as a "Bid" class object and added to the "bids" ArrayList
//		 * searchKeyword, which is the search parameter, is given as method parameter
//		 * Query to  produce a list of sales by item name or by customer name must be implemented
//		 * The item name or the customer name can be searched with the provided searchKeyword
//		 */
//
//		/*Sample data begins*/
//		for (int i = 0; i < 10; i++) {
//			Bid bid = new Bid();
//			bid.setAuctionID(123);
//			bid.setCustomerID("123-12-1234");
//			bid.setBidTime("2008-12-11");
//			bid.setBidPrice(100);
//			bids.add(bid);			
//		}
//		/*Sample data ends*/
//		
//		return bids;
            return null;
	}

}
