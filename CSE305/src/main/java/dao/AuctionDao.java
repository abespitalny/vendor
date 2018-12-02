package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.Statement;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

import model.Auction;
import model.Bid;
import model.Customer;
import model.Item;

public class AuctionDao {

	/*
	* The students code to fetch data from the database will be written
	* here
	* 
	* Each record is required to be encapsulated as a "Auction" class
	* object and added to the "auctions" ArrayList
	* 
	* Query to get data about all the auctions should be implemented
	*/
	public List<Auction> getAllAuctions() {
		// TODO: Apparently bidIncrement and reserve can only be integers, but
		// bidIncrement and minimumBid can be floats. ...Interesting.
		//
		// Also, the query or DB needs to be reformatted for closingBid.

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
	public List<Auction> getAuctions(String customerID) {
		// TODO: closingBid has to be refactored into either the DB or the query
	

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "SELECT " +
				"		CustomerID, " +
				"        AuctionID, " +
				"        MAX(BidTime) AS LastBidTime, " +
				"        MAX(BidPrice) AS LastBidPrice" +
				"	FROM " +
				"		Bid NATURAL JOIN Auction" +
				"	WHERE " +
				"		CustomerID = " + customerID + "" +
				"	GROUP BY " +
				"		AuctionID" +
				"	ORDER BY " +
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
	public List<Auction> getOpenAuctions(String employeeEmail) {
		// TODO: Reformat either the SQL or DB
		//
		// Also, why do we need a closingBid for open auctions?

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "SELECT" +
				"        A.AuctionID," +
				"        A.BidIncrement," +
				"        A.MinBidPrice," +
				"        A.NumCopies," +
				"        A.ItemID," +
				"        A.ClosingBid," +
				"        A.CurrentHighestBidPrice," +
				"        A.CurrentMaxBidPrice," +
				"        A.ReservePrice" +
				"    FROM" +
				"        Auction A," +
				"        Employee E," +
				"        Post P" +
				"    WHERE" +
				"        E.Email = 'tom@smolka.com' AND" +
				"        E.SSN = A.Monitor AND" +
				"        P.AuctionID = A.AuctionID AND" +
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
	
	/*
	 * The students code to update data in the database will be written here
	 * 
	 * Query to record a sale, indicated by the auction ID, should be
	 * implemented auctionID is the Auction's ID, given as method parameter
	 * 
	 * The method should return a "success" string if the update is
	 * successful, else return "failure"
	 */
	public String recordSale(String auctionID) {
		////////////////////////////////////////////
		// TODO: The query has to be rewritten

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
	public List getAuctionData(String auctionID1, String itemID1) {

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "SELECT" +
				"        I.ItemID," +
				"        I.Description," +
				"        I.ItemType," +
				"        I.ItemName," +
				"        B.CustomerID," +
				"        B.BidPrice," +
				"        C.CustomerID," +
				"        C.FirstName," +
				"        C.LastName," +
				"        A.MinBidPrice," +
				"        A.BidIncrement," +
				"        A.CurrentHighestBidPrice," +
				"        A.CurrentMaxBidPrice," +
				"        A.auctionID" +
				"    FROM" +
				"        Item I," +
				"        Bid B," +
				"        Customer C," +
				"        Auction A" +
				"    WHERE " +
				"        A.AuctionID = " + auctionID1 + " AND" +
				"        I.ItemID = " + itemID1 + " AND" +
				"        A.AuctionID = B.AuctionID AND" +
				"        B.CustomerID = C.CustomerID AND" +
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
