package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Auction;
import model.Bid;
import model.Customer;
import model.Item;

public class AuctionDao {

	///////////////////////////////////////
	// TODO: Apparently bidIncrement and reserve can only be integers, but
	// bidIncrement and minimumBid can be floats. ...Interesting.
	//
	// Also, the query or DB needs to be reformatted for closingBid.
	public List<Auction> getAllAuctions() throws SQLException {
		/*
		 * The students code to fetch data from the database will be written
		 * here
		 * 
		 * Each record is required to be encapsulated as a "Auction" class
		 * object and added to the "auctions" ArrayList
		 * 
		 * Query to get data about all the auctions should be implemented
		 */

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
			float bidIncrement = rs.getFloat(2);
			float minimumBid = rs.getFloat(3);
			int copiesSold = rs.getInt(7);
			int itemID = rs.getInt(9);
			int closingBid = 0; // Needs to be implemented in query
			int currentBid = rs.getInt(5);
			int currentHighBid = rs.getInt(6);
			int reserve = rs.getInt(4);

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

	/////////////////////////////////////////////////////
	// TODO: closingBid has to be refactored into either the DB or the query
	public List<Auction> getAuctions(String customerID) throws SQLException {
		/*
		 * The students code to fetch data from the database will be written
		 * here
		 * 
		 * Each record is required to be encapsulated as a "Auction" class
		 * object and added to the "auctions" ArrayList
		 * 
		 * Query to get data about all the auctions in which a customer
		 * participated should be implemented customerID is the customer's
		 * primary key, given as method parameter
		 */

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
				"		LastBidTime DESC";

		ResultSet rs = statement.executeQuery(sql);

		List<Auction> auctions = new ArrayList<Auction>();

		while (rs.next()) {
			// Pull data from database
			int auctionID = rs.getInt(1);
			float bidIncrement = rs.getFloat(2);
			float minimumBid = rs.getFloat(3);
			int copiesSold = rs.getInt(7);
			int itemID = rs.getInt(9);
			int closingBid = 0; // Needs to be implemented in query
			int currentBid = rs.getInt(5);
			int currentHighBid = rs.getInt(6);
			int reserve = rs.getInt(4);

			// Add data to auction
			Auction auction = new Auction();
			auction.setAuctionID(auctionID);
			auction.setBidIncrement(bidIncrement);
			auction.setMinimumBid(minimumBid);
			auction.setCopiesSold(copiesSold);
			auction.setItemID(itemID);
			auction.setClosingBid(closingBid);
			auction.setCurrentBid(currentBid);
			auction.setCurrentHighBid(currentHighBid);
			auction.setReserve(reserve);
			auctions.add(auction);
		}

		return auctions;
	}

	////////////////////////////////////////////////////////////
	// TODO: Reformat either the SQL or DB
	//
	// Also, why do we need a closingBid for open auctions?
	public List<Auction> getOpenAuctions(String employeeEmail) throws SQLException {
		/*
		 * The students code to fetch data from the database will be written
		 * here
		 * 
		 * Each record is required to be encapsulated as a "Auction" class
		 * object and added to the "auctions" ArrayList
		 * 
		 * Query to get data about all the open auctions monitored by a customer
		 * representative should be implemented employeeEmail is the email ID of
		 * the customer representative, which is given as method parameter
		 */

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "SELECT\r\n" +
				"        A.AuctionID,\r\n" +
				"        A.BidIncrement,\r\n" +
				"        A.MinBidPrice,\r\n" +
				"        A.NumCopies,\r\n" +
				"        A.ItemID,\r\n" +
				"        A.ClosingBid,\r\n" +
				"        A.CurrentHighestBidPrice,\r\n" +
				"        A.CurrentMaxBidPrice,\r\n" +
				"        A.ReservePrice\r\n" +
				"    FROM\r\n" +
				"        Auction A,\r\n" +
				"        Employee E,\r\n" +
				"        Post P\r\n" +
				"    WHERE\r\n" +
				"        E.Email = 'tom@smolka.com' AND\r\n" +
				"        E.SSN = A.Monitor AND\r\n" +
				"        P.AuctionID = A.AuctionID AND\r\n" +
				"        P.EndDate > NOW()";

		ResultSet rs = statement.executeQuery(sql);

		List<Auction> auctions = new ArrayList<Auction>();

		while (rs.next()) {
			// Pull data from database
			int auctionID = rs.getInt(1);
			float bidIncrement = rs.getFloat(2);
			float minimumBid = rs.getFloat(3);
			int copiesSold = rs.getInt(4);
			int itemID = rs.getInt(5);
			int closingBid = rs.getInt(6); // Needs to be implemented in DB
			int currentBid = rs.getInt(7);
			int currentHighBid = rs.getInt(8);
			int reserve = rs.getInt(9);

			// Add data to auction
			Auction auction = new Auction();
			auction.setAuctionID(auctionID);
			auction.setBidIncrement(bidIncrement);
			auction.setMinimumBid(minimumBid);
			auction.setCopiesSold(copiesSold);
			auction.setItemID(itemID);
			auction.setClosingBid(closingBid);
			auction.setCurrentBid(currentBid);
			auction.setCurrentHighBid(currentHighBid);
			auction.setReserve(reserve);
			auctions.add(auction);
		}

		return auctions;
	}

	////////////////////////////////////////////
	// TODO: The query has to be rewritten
	public String recordSale(String auctionID) throws SQLException {
		/*
		 * The students code to update data in the database will be written here
		 * 
		 * Query to record a sale, indicated by the auction ID, should be
		 * implemented auctionID is the Auction's ID, given as method parameter
		 * 
		 * The method should return a "success" string if the update is
		 * successful, else return "failure"
		 */

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "";

		int result = statement.executeUpdate(sql);
		if (result != 1) {
			return "failure";
		}

		return "success";
	}

	public List getAuctionData(String auctionID1, String itemID1) throws SQLException {
		/*
		 * The students code to fetch data from the database will be written
		 * here
		 * 
		 * The item details are required to be encapsulated as a "Item" class
		 * object
		 * 
		 * The bid details are required to be encapsulated as a "Bid" class
		 * object
		 * 
		 * The auction details are required to be encapsulated as a "Auction"
		 * class object
		 * 
		 * The customer details are required to be encapsulated as a "Customer"
		 * class object
		 * 
		 * Query to get data about auction indicated by auctionID and itemID
		 * should be implemented
		 * 
		 * auctionID is the Auction's ID, given as method parameter
		 * 
		 * itemID is the Item's ID, given as method parameter
		 * 
		 * The customer details must include details about the current winner of
		 * the auction
		 * 
		 * The bid details must include details about the current highest bid
		 * 
		 * The item details must include details about the item, indicated by
		 * itemID
		 * 
		 * The auction details must include details about the item, indicated by
		 * auctionID
		 * 
		 * All the objects must be added in the "output" list and returned
		 */

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "SELECT\r\n" +
				"        I.ItemID,\r\n" +
				"        I.Description,\r\n" +
				"        I.ItemType,\r\n" +
				"        I.ItemName,\r\n" +
				"        B.CustomerID,\r\n" +
				"        B.BidPrice,\r\n" +
				"        C.CustomerID,\r\n" +
				"        C.FirstName,\r\n" +
				"        C.LastName,\r\n" +
				"        A.MinBidPrice,\r\n" +
				"        A.BidIncrement,\r\n" +
				"        A.CurrentHighestBidPrice,\r\n" +
				"        A.CurrentMaxBidPrice,\r\n" +
				"        A.auctionID\r\n" +
				"    FROM\r\n" +
				"        Item I,\r\n" +
				"        Bid B,\r\n" +
				"        Customer C,\r\n" +
				"        Auction A\r\n" +
				"    WHERE \r\n" +
				"        A.AuctionID = " + auctionID1 + " AND\r\n" +
				"        I.ItemID = " + itemID1 + " AND\r\n" +
				"        A.AuctionID = B.AuctionID AND\r\n" +
				"        B.CustomerID = C.CustomerID AND\r\n" +
				"        I.ItemID = A.ItemID";

		ResultSet rs = statement.executeQuery(sql);

		List output = new ArrayList();
		Item item = new Item();
		Bid bid = new Bid();
		Auction auction = new Auction();
		Customer customer = new Customer();

		while (rs.next()) {
			// Pull data from database
			int itemID2 = rs.getInt(1);
			String description = rs.getString(2);
			String type = rs.getString(3);
			String name = rs.getString(4);

			int customerID1 = rs.getInt(5);
			float bidPrice = rs.getFloat(6);

			int customerID2 = rs.getInt(7);
			String firstName = rs.getString(8);
			String lastName = rs.getString(9);

			float minimumBid = rs.getFloat(10);
			float bidIncrement = rs.getFloat(11);
			int currentBid = rs.getInt(12);
			int currentHighBid = rs.getInt(13);
			int auctionID2 = rs.getInt(14);

			// Store result data
			item.setItemID(itemID2);
			item.setDescription(description);
			item.setType(type);
			item.setName(name);

			bid.setCustomerID(Integer.toString(customerID1));
			bid.setBidPrice(bidPrice);

			customer.setCustomerID(Integer.toString(customerID2));
			customer.setFirstName(firstName);
			customer.setLastName(lastName);

			auction.setMinimumBid(minimumBid);
			auction.setBidIncrement(bidIncrement);
			auction.setCurrentBid(currentBid);
			auction.setCurrentHighBid(currentHighBid);
			auction.setAuctionID(auctionID2);

			output.add(item);
			output.add(bid);
			output.add(auction);
			output.add(customer);
		}

		return output;
	}

}
