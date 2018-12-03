package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import model.Bid;

public class BidDao {

	public List<Bid> getBidHistory(String auctionID1) throws SQLException {
		/*
		 * The students code to fetch data from the database 
		 * 
		 * Each record is required to be encapsulated as a "Bid" class object 
		 * and added to the "bids" ArrayList auctionID, which is the Auction's 
		 * ID, is given as method parameter 
		 * 
		 * Query to get the bid history of an auction, indicated by auctionID, 
		 * must be implemented
		 */

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "SELECT\r\n" +
				"        AuctionID,\r\n" +
				"        CustomerID,\r\n" +
				"        BidTime,\r\n" +
				"        BidPrice\r\n" +
				"    FROM\r\n" +
				"        Bid\r\n" +
				"    WHERE\r\n" +
				"        AuctionID = " + auctionID1 + "\r\n" +
				"    ORDER BY\r\n" +
				"        BidTime DESC";

		ResultSet rs = statement.executeQuery(sql);

		List<Bid> bids = new ArrayList<Bid>();

		while (rs.next()) {
			int auctionID2 = rs.getInt(1);
			String customerID = rs.getString(2);
			String bidTime = rs.getDate(3).toString();
			float bidPrice = rs.getFloat(4);

			Bid bid = new Bid();
			bid.setAuctionID(auctionID2);
			bid.setCustomerID(customerID);
			bid.setBidTime(bidTime);
			bid.setBidPrice(bidPrice);
			bids.add(bid);
		}

		return bids;
	}

	public List<Bid> getAuctionHistory(String customerID1) throws SQLException {
		/*
		 * The students code to fetch data from the database 
		 * 
		 * Each record is required to be encapsulated as a "Bid" class object 
		 * and added to the "bids" ArrayList customerID, which is the Customer's 
		 * ID, is given as method parameter 
		 * 
		 * Query to get the bid history of all the auctions in which a customer 
		 * participated, indicated by customerID, must be implemented
		 */	

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "SELECT\r\n" +
				"        AuctionID,\r\n" +
				"        CustomerID,\r\n" +
				"        BidTime,\r\n" +
				"        BidPrice\r\n" +
				"    FROM\r\n" +
				"        Bid\r\n" +
				"    WHERE\r\n" +
				"        CustomerID = " + customerID1 + "\r\n" +
				"    ORDER BY\r\n" +
				"        BidTime DESC";

		ResultSet rs = statement.executeQuery(sql);

		List<Bid> bids = new ArrayList<Bid>();

		while (rs.next()) {
			int auctionID = rs.getInt(1);
			String customerID2 = rs.getString(2);
			String bidTime = rs.getDate(3).toString();
			float bidPrice = rs.getFloat(4);

			Bid bid = new Bid();
			bid.setAuctionID(auctionID);
			bid.setCustomerID(customerID2);
			bid.setBidTime(bidTime);
			bid.setBidPrice(bidPrice);
			bids.add(bid);
		}

		return bids;
	}

	///////////////////////////////////////////////////////////////////////
	// TODO: This might need to be rewritten
	public Bid submitBid(String auctionID, String itemID, Float currentBid,
			Float maxBid, String customerID) throws SQLException {
		/*
		 * The students code to insert data in the database 
		 * 
		 * auctionID, which is the Auction's ID for which the bid is submitted, 
		 * is given as method parameter 
		 * 
		 * itemID, which is the Item's ID for which the bid is submitted, is 
		 * given as method parameter 
		 * 
		 * currentBid, which is the Customer's current bid, is given as method 
		 * parameter 
		 * 
		 * maxBid, which is the Customer's maximum bid for the item, is given 
		 * as method parameter
		 * 
		 * customerID, which is the Customer's ID, is given as method parameter
		 * 
		 * Query to submit a bid by a customer, indicated by customerID, must be
		 * implemented After inserting the bid data, return the bid details
		 * encapsulated in "bid" object
		 */

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		Date currentTime = new Date(); // So we don't have to run a third query

		String sql = "INSERT INTO \r\n" +
				"		Bid\r\n" +
				"	SELECT \r\n" +
				"		 " + customerID + " AS CustomerID, \r\n" +
				"        AuctionID, \r\n" +
				"        NOW() AS BidTime, \r\n" + // Might change this
				"        " + currentBid + " AS BidPrice\r\n" +
				"	FROM \r\n" +
				"		Auction\r\n" +
				"	WHERE \r\n" +
				"		AuctionID = " + auctionID + " AND \r\n" +
				"        IF(CurrentHighestBidPrice IS NULL, " + maxBid +
				" >= MinBidPrice, " + maxBid + " > CurrentHighestBidPrice)";

		statement.executeUpdate(sql); // Returns int, but not needed unless we
										// check for errors

		String sql_update = "UPDATE \r\n" +
				"		Auction\r\n" +
				"	SET \r\n" +
				"		CurrentHighestBidPrice = IF(CurrentHighestBidPrice IS NULL, MinBidPrice, IF(12.00 > CurrentMaxBidPrice, LEAST(12.00, CurrentMaxBidPrice + BidIncrement), LEAST(12.00 + BidIncrement, CurrentMaxBidPrice))),\r\n"
				+
				"		CurrentMaxBidPrice = IF(CurrentMaxBidPrice IS NULL, 12.00, IF(12.00 > CurrentMaxBidPrice, 12.00, CurrentMaxBidPrice)),\r\n"
				+
				"		BidIncrement = 1.00\r\n" +
				"	WHERE \r\n" +
				"		AuctionID = 1357";

		statement.executeUpdate(sql_update); // Returns int, but not needed
												// unless we check for errors

		// String sql_result = ""; // Get result bid
		// I'm not 100% certain yet as to how to get the bid in a result set
		// UPDATE: Created Data object so we have all the data already, this
		// next step might not be necessary
		//
		// ResultSet rs = statement.executeQuery(sql_result);
		//
		// String bidTime = rs.getDate(3).toString();
		// float bidPrice = rs.getFloat(4);

		Bid bid = new Bid();
		bid.setAuctionID(Integer.parseInt(auctionID));
		bid.setCustomerID(customerID);
		bid.setBidTime(currentTime.toString());
		bid.setBidPrice(maxBid);

		return bid;
	}

	////////////////////////////////////////////////////////
	// TODO: Not sure why we are returning a list of Bid objects. Auctions would 
	// make more sense. I need to ask the TA.
	public List<Bid> getSalesListing(String searchKeyword) throws SQLException {
		/*
		 * The students code to fetch data from the database 
		 * 
		 * Each record is required to be encapsulated as a "Bid" class object and 
		 * added to the "bids" ArrayList searchKeyword, which is the search 
		 * parameter, is given as method parameter 
		 * 
		 * Query to produce a list of sales by item name or by customer name must 
		 * be implemented The item name or the customer name can be searched with 
		 * the provided searchKeyword
		 */
		
		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "";

		ResultSet rs = statement.executeQuery(sql);

		List<Bid> bids = new ArrayList<Bid>();

		while (rs.next()) {
			int auctionID2 = rs.getInt(1);
			String customerID = rs.getString(2);
			String bidTime = rs.getDate(3).toString();
			float bidPrice = rs.getFloat(4);

			Bid bid = new Bid();
			bid.setAuctionID(auctionID2);
			bid.setCustomerID(customerID);
			bid.setBidTime(bidTime);
			bid.setBidPrice(bidPrice);
			bids.add(bid);
		}

		return bids;
	}

}
