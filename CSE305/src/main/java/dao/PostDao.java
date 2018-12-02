package dao;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.Statement;

import model.Employee;
import model.Item;
import model.Post;

public class PostDao {

	/*
	 * The students code to fetch data from the database will be written
	 * here
	 * 
	 * Each record is required to be encapsulated as a "Item" class object
	 * and added to the "items" List
	 * 
	 * Query to get sales report for a particular month must be implemented
	 * 
	 * post, which has details about the month and year for which the sales
	 * report is to be generated, is given as method parameter
	 * 
	 * The month and year are in the format "month-year", e.g. "10-2018" and
	 * stored in the expireDate attribute of post object
	 * 
	 * The month and year can be accessed by getter method, i.e.,
	 * post.getExpireDate()
	 */
	public List<Item> getSalesReport(Post post) {
		// TODO: The query needs to be reformatted to properly pull month from the
		// post.

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "SELECT " +
				"        I.ItemName," +
				"        I.SoldPrice" +
				"	FROM" +
				"		Item I," +
				"		Auction A," +
				"		Post P" +
				"	WHERE" +
				"		I.ItemID = A.ItemID" +
				"        AND A.AuctionID = P.AuctionID" +
				"        AND MONTH(P.EndDate) = 12" +
				"        AND P.EndDate < CURDATE()";

		ResultSet rs = statement.executeQuery(sql);

		List<Item> items = new ArrayList<Item>();

		while (rs.next()) {
			// Pull data from database
			String name = rs.getString(4);
			int soldPrice = rs.getInt(7);

			// Add data to auction
			Item item = new Item();
			item.setName(name);
			item.setSoldPrice(soldPrice);
			items.add(item);
		}

		return items;
	}

}
