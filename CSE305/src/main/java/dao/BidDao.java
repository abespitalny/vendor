package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.Statement;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

import model.Bid;

public class BidDao {

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
	public List<Bid> getBidHistory(String auctionID1) {

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "SELECT" +
				"        AuctionID," +
				"        CustomerID," +
				"        BidTime," +
				"        BidPrice" +
				"    FROM" +
				"        Bid" +
				"    WHERE" +
				"        AuctionID = " + auctionID1 + "" +
				"    ORDER BY" +
				"        BidTime DESC";

		ResultSet rs = statement.executeQuery(sql);

		List<Bid> bids = new ArrayList<Bid>();

		while (rs.next()) {
			int auctionID2 = rs.getInt(1)
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
	public List<Bid> getAuctionHistory(String customerID1) {

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "SELECT" +
				"        AuctionID," +
				"        CustomerID," +
				"        BidTime," +
				"        BidPrice" +
				"    FROM" +
				"        Bid" +
				"    WHERE" +
				"        CustomerID = " + customerID1 + "" +
				"    ORDER BY" +
				"        BidTime DESC";

		ResultSet rs = statement.executeQuery(sql);

		List<Bid> bids = new ArrayList<Bid>();

		while (rs.next()) {
			int auctionID = rs.getInt(1)
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
	public Bid submitBid(String auctionID, String itemID, Float currentBid,
	Float maxBid, String customerID) {
		// TODO: This might need to be rewritten
		
		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		Date currentTime = new Date(); // So we don't have to run a third query

		String sql = "INSERT INTO " +
				"		Bid" +
				"	SELECT " +
				"		 " + customerID + " AS CustomerID, " +
				"        AuctionID, " +
				"        NOW() AS BidTime, " + // Might change this
				"        " + currentBid + " AS BidPrice" +
				"	FROM " +
				"		Auction" +
				"	WHERE " +
				"		AuctionID = " + auctionID + " AND " +
				"        IF(CurrentHighestBidPrice IS NULL, " + maxBid +
				" >= MinBidPrice, " + maxBid + " > CurrentHighestBidPUPDATE " +
				"		Auction" +
				"	SET " +
				"		CurrentHighestBidPrice = IF(CurrentHighestBidPrice IS NULL, MinBidPrice, IF(12.00 > CurrentMaxBidPrice, LEAST(12.00, CurrentMaxBidPrice + BidIncrement), LEAST(12.00 + BidIncrement, CurrentMaxBidPrice))),"
				+
				"		CurrentMaxBidPrice = IF(CurrentMaxBidPrice IS NULL, 12.00, IF(12.00 > CurrentMaxBidPrice, 12.00, CurrentMaxBidPrice)),"
				+
				"		BidIncrement = 1.00" +
				"	WHERE " +
				"		AuctionID = 1357rice)";

		statement.executeUpdate(sql); // Returns int, but not needed unless we
										// check for errors

		String sql_update = "UPDATE " +
				"		Auction" +
				"	SET " +
				"		CurrentHighestBidPrice = IF(CurrentHighestBidPrice IS NULL, MinBidPrice, IF(12.00 > CurrentMaxBidPrice, LEAST(12.00, CurrentMaxBidPrice + BidIncrement), LEAST(12.00 + BidIncrement, CurrentMaxBidPrice))),"
				+
				"		CurrentMaxBidPrice = IF(CurrentMaxBidPrice IS NULL, 12.00, IF(12.00 > CurrentMaxBidPrice, 12.00, CurrentMaxBidPrice)),"
				+
				"		BidIncrement = 1.00" +
				"	WHERE " +
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
	public List<Bid> getSalesListing(String searchKeyword) {
		// TODO: Not sure why we are returning a list of Bid objects. Auctions would 
		// make more sense. I need to ask the TA.
		
		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "";

		ResultSet rs = statement.executeQuery(sql);

		List<Bid> bids = new ArrayList<Bid>();

		while (rs.next()) {
			int auctionID2 = rs.getInt(1)
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
