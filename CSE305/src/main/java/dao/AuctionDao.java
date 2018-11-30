package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Auction;
import model.Bid;
import model.Customer;
import model.Item;

public class AuctionDao {

	public List<Auction> getAllAuctions() {

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "SELECT * FROM Auction";

		ResultSet rs = statement.executeQuery(sql);

		List<Auction> auctions = new ArrayList<Auction>();

		while (rs.next()) {
			// Pull data from database
			int auctionID = rs.getInt(1);
			double bidIncrement = rs.getDouble(2);
			double minimumBid = rs.getDouble(3);
			double reserve = rs.getDouble(4);
			double currentBid = rs.getDouble(5);
			double currentHighBid = rs.getDouble(6);
			int copiesSold = rs.getInt(7);
			String monitor = rs.getString(8); // Is not currently used
			int itemID = rs.getInt(9);
			int winnerID = rs.getInt(10); // Is not currently used

			// Add data to auction
			Auction auction = new Auction();
			auction.setAuctionID(auctionID);
			auction.setBidIncrement(bidIncrement);
			auction.setMinimumBid(minimumBid);
			auction.setCopiesSold(copiesSold);
			auction.setItemID(itemID);
			auction.setClosingBid(120); // Sample data,
			auction.setCurrentBid(currentBid);
			auction.setCurrentHighBid(currentHighBid);
			auction.setReserve(reserve);
			auctions.add(auction);
		}

		return auctions;

	}

	public List<Auction> getAuctions(String customerID) {

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "SELECT \r\n" +
				"		CustomerID, \r\n" +
				"        AuctionID, \r\n" +
				"        MAX(BidTime) AS LastBidTime, \r\n" +
				"        MAX(BidPrice) AS LastBidPrice\r\n" +
				"	FROM \r\n" +
				"		Bid NATURAL JOIN Auction\r\n" +
				"	WHERE \r\n" +
				"		CustomerID = " + customerID + "\r\n" +
				"	GROUP BY \r\n" +
				"		AuctionID\r\n" +
				"	ORDER BY \r\n" +
				"		LastBidTime DESC;";

		ResultSet rs = statement.executeQuery(sql);

		List<Auction> auctions = new ArrayList<Auction>();

		while (rs.next()) {
			// Pull data from database
			int auctionID = rs.getInt(1);
			double bidIncrement = rs.getDouble(2);
			double minimumBid = rs.getDouble(3);
			double reserve = rs.getDouble(4);
			double currentBid = rs.getDouble(5);
			double currentHighBid = rs.getDouble(6);
			int copiesSold = rs.getInt(7);
			String monitor = rs.getString(8); // Is not currently used
			int itemID = rs.getInt(9);
			int winnerID = rs.getInt(10); // Is not currently used

			// Add data to auction
			Auction auction = new Auction();
			auction.setAuctionID(auctionID);
			auction.setBidIncrement(bidIncrement);
			auction.setMinimumBid(minimumBid);
			auction.setCopiesSold(copiesSold);
			auction.setItemID(itemID);
			auction.setClosingBid(120); // Sample data
			auction.setCurrentBid(currentBid);
			auction.setCurrentHighBid(currentHighBid);
			auction.setReserve(reserve);
			auctions.add(auction);
		}

		return auctions;

	}

	////////////////////////////////////////////////////////////
	// TODO
	public List<Auction> getOpenAuctions(String employeeEmail) {

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "";

		ResultSet rs = statement.executeQuery(sql);

		List<Auction> auctions = new ArrayList<Auction>();

		while (rs.next()) {
			// Pull data from database
			int auctionID = rs.getInt(1);
			double bidIncrement = rs.getDouble(2);
			double minimumBid = rs.getDouble(3);
			double reserve = rs.getDouble(4);
			double currentBid = rs.getDouble(5);
			double currentHighBid = rs.getDouble(6);
			int copiesSold = rs.getInt(7);
			String monitor = rs.getString(8); // Is not currently used
			int itemID = rs.getInt(9);
			int winnerID = rs.getInt(10); // Is not currently used

			// Add data to auction
			Auction auction = new Auction();
			auction.setAuctionID(auctionID);
			auction.setBidIncrement(bidIncrement);
			auction.setMinimumBid(minimumBid);
			auction.setCopiesSold(copiesSold);
			auction.setItemID(itemID);
			auction.setClosingBid(120); // Sample data
			auction.setCurrentBid(currentBid);
			auction.setCurrentHighBid(currentHighBid);
			auction.setReserve(reserve);
			auctions.add(auction);
		}

		return auctions;

	}

	////////////////////////////////////////////
	// TODO
	public String recordSale(String auctionID) {
		/*
		 * The students code to update data in the database will be written here
		 * Query to record a sale, indicated by the auction ID, should be
		 * implemented auctionID is the Auction's ID, given as method parameter
		 * The method should return a "success" string if the update is
		 * successful, else return "failure"
		 */
		/* Sample data begins */
		return "success";
		/* Sample data ends */
	}

	/////////////////////////////////////////////////////////////
	// TODO
	public List getAuctionData(String auctionID, String itemID) {

		List output = new ArrayList();
		Item item = new Item();
		Bid bid = new Bid();
		Auction auction = new Auction();
		Customer customer = new Customer();

		/*
		 * The students code to fetch data from the database will be written
		 * here The item details are required to be encapsulated as a "Item"
		 * class object The bid details are required to be encapsulated as a
		 * "Bid" class object The auction details are required to be
		 * encapsulated as a "Auction" class object The customer details are
		 * required to be encapsulated as a "Customer" class object Query to get
		 * data about auction indicated by auctionID and itemID should be
		 * implemented auctionID is the Auction's ID, given as method parameter
		 * itemID is the Item's ID, given as method parameter The customer
		 * details must include details about the current winner of the auction
		 * The bid details must include details about the current highest bid
		 * The item details must include details about the item, indicated by
		 * itemID The auction details must include details about the item,
		 * indicated by auctionID All the objects must be added in the "output"
		 * list and returned
		 */

		/* Sample data begins */
		for (int i = 0; i < 4; i++) {
			item.setItemID(123);
			item.setDescription("sample description");
			item.setType("BOOK");
			item.setName("Sample Book");

			bid.setCustomerID("123-12-1234");
			bid.setBidPrice(120);

			customer.setCustomerID("123-12-1234");
			customer.setFirstName("Shiyong");
			customer.setLastName("Lu");

			auction.setMinimumBid(100);
			auction.setBidIncrement(10);
			auction.setCurrentBid(110);
			auction.setCurrentHighBid(115);
			auction.setAuctionID(Integer.parseInt(auctionID));
		}
		/* Sample data ends */

		output.add(item);
		output.add(bid);
		output.add(auction);
		output.add(customer);

		return output;

	}

}
