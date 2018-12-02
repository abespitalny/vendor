package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.Statement;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

import model.Customer;

public class CustomerDao {
	/*
	 * This class handles all the database operations related to the customer
	 * table
	 */

	/**
	 * @param String searchKeyword
	 * @return ArrayList<Customer> object
	 */
	//////////////////////////////////////////////////////////
	// TODO: Write the SQL. Address must be split up into city and state.
	// zipCode should be int. customerID should be String.
	public List<Customer> getCustomers(String searchKeyword) {
		/*
		 * This method fetches one or more customers based on the searchKeyword
		 * and returns it as an ArrayList
		 *
		 * The students code to fetch data from the database based on
		 * searchKeyword will be written here
		 * 
		 * Each record is required to be encapsulated as a "Customer" class
		 * object and added to the "customers" List
		 */

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "";

		ResultSet rs = statement.executeQuery(sql);

		List<Customer> customers = new ArrayList<Customer>();

		while (rs.next()) {
			String customerID = rs.getString(1);
			String address = rs.getString(2);
			String lastName = rs.getString(3);
			String firstName = rs.getString(4);
			String city = rs.getString(5);
			String state = rs.getString(6);
			String email = rs.getString(7);
			int zipCode = rs.getInt(8);
			String telephone = rs.getString(9);
			String creditCard = rs.getString(10);
			int rating = rs.getString(11);

			Customer customer = new Customer();
			customer.setCustomerID(customerID);
			customer.setAddress(address);
			customer.setLastName(lastName);
			customer.setFirstName(firstName);
			customer.setCity(city);
			customer.setState(state);
			customer.setEmail(email);
			customer.setZipCode(zipCode);
			customer.setTelephone(telephone);
			customer.setCreditCard(creditCard);
			customer.setRating(rating);
			customers.add(customer);
		}

		return customers;
	}

	/////////////////////////////////////////////
	// TODO: customerID should be String.
	public Customer getHighestRevenueCustomer() {
		/*
		 * This method fetches the customer who generated the highest total
		 * revenue and returns it
		 * 
		 * The students code to fetch data from the database will be written
		 * here
		 * 
		 * The customer record is required to be encapsulated as a "Customer"
		 * class object
		 */

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "SELECT \r\n" +
				"		C.CustomerID, \r\n" +
				"        C.LastName, \r\n" +
				"        C.FirstName, \r\n" +
				"        C.Email, \r\n" +
				"        SUM(A.CurrentHighestBidPrice) AS TotalRevenue\r\n" +
				"	FROM \r\n" +
				"		Customer C, \r\n" +
				"        Auction A, \r\n" +
				"        Post P\r\n" +
				"	WHERE \r\n" +
				"		C.CustomerID = P.CustomerID AND \r\n" +
				"        P.AuctionID = A.AuctionID AND \r\n" +
				"        P.EndDate < CURDATE() \r\n" +
				"	GROUP BY \r\n" +
				"		TotalRevenue\r\n" +
				"    ORDER BY \r\n" +
				"		TotalRevenue DESC";

		ResultSet rs = statement.executeQuery(sql);

		String customerID = rs.getString(1);
		String lastName = rs.getString(2);
		String firstName = rs.getString(3);
		String email = rs.getString(4);

		Customer customer = new Customer();
		customer.setCustomerID(customerID);
		customer.setLastName(lastName);
		customer.setFirstName(firstName);
		customer.setEmail(email);

		return customer;
	}

	////////////////////////////////////////////////
	// TODO: I don't know if this is right. It's pretty much getAllCustomers
	// minus some attributes. customerID should be String.
	public List<Customer> getCustomerMailingList() {
		/*
		 * This method fetches the all customer mailing details and returns it
		 * 
		 * The students code to fetch data from the database will be written
		 * here
		 * 
		 * Each customer record is required to be encapsulated as a "Customer"
		 * class object and added to the "customers" List
		 */

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "SELECT\r\n" +
				"        CustomerID,\r\n" +
				"        Address,\r\n" +
				"        LastName,\r\n" +
				"        FirstName,\r\n" +
				"        City,\r\n" +
				"        State,\r\n" +
				"        Email,\r\n" +
				"        ZipCode\r\n" +
				"    FROM\r\n" +
				"        Customer";

		ResultSet rs = statement.executeQuery(sql);

		List<Customer> customers = new ArrayList<Customer>();

		while (rs.next()) {
			String customerID = rs.getString(1);
			String address = rs.getString(2);
			String lastName = rs.getString(3);
			String firstName = rs.getString(4);
			String city = rs.getString(5);
			String state = rs.getString(6);
			String email = rs.getString(7);
			int zipCode = rs.getInt(8);

			Customer customer = new Customer();
			customer.setCustomerID(customerID);
			customer.setAddress(address);
			customer.setLastName(lastName);
			customer.setFirstName(firstName);
			customer.setCity(city);
			customer.setState(state);
			customer.setEmail(email);
			customer.setZipCode(zipCode);
			customers.add(customer);
		}

		return customers;
	}

	////////////////////////////////////////////////
	// TODO: customerID in DB should be String. Qury needs to be rewritten
	public Customer getCustomer(String customerID) {
		/*
		 * This method fetches the customer details and returns it customerID,
		 * which is the Customer's ID who's details have to be fetched, is given
		 * as method parameter
		 * 
		 * The students code to fetch data from the database will be written
		 * here
		 * 
		 * The customer record is required to be encapsulated as a "Customer"
		 * class object
		 */

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "SELECT\r\n" +
				"        *\r\n" +
				"    FROM\r\n" +
				"        Customer\r\n" +
				"    WHERE\r\n" +
				"        CustomerID = " + customerID;

		ResultSet rs = statement.executeQuery(sql);

		String customerID = rs.getString(1);
		String address = rs.getString(2);
		String lastName = rs.getString(3);
		String firstName = rs.getString(4);
		String city = rs.getString(5);
		String state = rs.getString(6);
		String email = rs.getString(7);
		int zipCode = rs.getInt(8);
		String telephone = rs.getString(9);
		String creditCard = rs.getString(10);
		int rating = rs.getString(11);

		Customer customer = new Customer();
		customer.setCustomerID(customerID);
		customer.setAddress(address);
		customer.setLastName(lastName);
		customer.setFirstName(firstName);
		customer.setCity(city);
		customer.setState(state);
		customer.setEmail(email);
		customer.setZipCode(zipCode);
		customer.setTelephone(telephone);
		customer.setCreditCard(creditCard);
		customer.setRating(rating);

		return customer;
	}

	public String deleteCustomer(String customerID) {
		/*
		 * This method deletes a customer returns "success" string on success,
		 * else returns "failure"
		 * 
		 * The students code to delete the data from the database will be
		 * written here customerID, which is the Customer's ID who's details
		 * have to be deleted, is given as method parameter
		 */

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "UPDATE \r\n" +
				"		Employee \r\n" +
				"	SET \r\n" +
				"		StartDate = '2001-03-09', \r\n" +
				"        EmployeeLevel = 3, \r\n" +
				"        HourlyRate = 45\r\n" +
				"	WHERE \r\n" +
				"		SSN = " + customerID;

		int result = statement.executeUpdate(sql);
		if (result != 1) {
			return "failure";
		}

		return "success";
	}

	//////////////////////////////////////////////
	// TODO: Need to add a username property to Customer database, unless this
	// means customerID
	public String getCustomerID(String username) {
		/*
		 * This method returns the Customer's ID based on the provided email
		 * address
		 * 
		 * The students code to fetch data from the database will be written
		 * here username, which is the email address of the customer, who's ID
		 * has to be returned, is given as method parameter
		 * 
		 * The Customer's ID is required to be returned as a String
		 */

		return "111-11-1111";
	}

	////////////////////////////////////
	// TODO: Need to write a new query as far as I know
	public List<Customer> getSellers() {

		/*
		 * This method fetches the all seller details and returns it
		 * 
		 * The students code to fetch data from the database will be written
		 * here
		 * 
		 * The seller (which is a customer) record is required to be
		 * encapsulated as a "Customer" class object and added to the
		 * "customers" List
		 */

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "";

		ResultSet rs = statement.executeQuery(sql);

		List<Customer> customers = new ArrayList<Customer>();

		while (rs.next()) {
			String customerID = rs.getString(1);
			String address = rs.getString(2);
			String lastName = rs.getString(3);
			String firstName = rs.getString(4);
			String city = rs.getString(5);
			String state = rs.getString(6);
			String email = rs.getString(7);
			int zipCode = rs.getInt(8);

			Customer customer = new Customer();
			customer.setCustomerID(customerID);
			customer.setAddress(address);
			customer.setLastName(lastName);
			customer.setFirstName(firstName);
			customer.setCity(city);
			customer.setState(state);
			customer.setEmail(email);
			customer.setZipCode(zipCode);
			customers.add(customer);
		}

		return customers;
	}

	//////////////////////////////////////////////
	// TODO: We need to reformat the database to fit these data. zipCode needs
	// to be an int. customerID needs to be a String.
	public String addCustomer(Customer customer) {
		/*
		 * All the values of the add customer form are encapsulated in the
		 * customer object.
		 * 
		 * These can be accessed by getter methods (see Customer class in model
		 * package). e.g. firstName can be accessed by customer.getFirstName()
		 * method.
		 * 
		 * The sample code returns "success" by default.
		 * 
		 * You need to handle the database insertion of the customer details and
		 * return "success" or "failure" based on result of the database
		 * insertion.
		 */

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		int customerID = Integer.parseInt(customer.getCustomerID());
		String firstName = customer.getFirstName();
		String lastName = customer.getLastName();
		String address = customer.getAddress();
		String city = customer.getCity();
		String state = customer.getState();
		int zipCode = customer.getZipCode();
		String telephone = customer.getTelephone();
		String email = customer.getEmail();
		int rating = customer.getRating();
		String creditCard = customer.getCreditCard();

		String sql = "INSERT INTO \r\n" +
				"	Customer (\r\n" +
				"		CustomerID,\r\n" +
				"        FirstName,\r\n" +
				"        LastName,\r\n" +
				"        Address,\r\n" +
				"        City,\r\n" +
				"        State,\r\n" +
				"        ZipCode,\r\n" +
				"        Telephone,\r\n" +
				"        Email,\r\n" +
				"        Rating,\r\n" +
				"        CreditCardNum,\r\n" +
				"        ItemsSold,\r\n" +
				"        ItemsPurchased) \r\n" +
				"	VALUES (\r\n" +
				"		 " + customerID + ",\r\n" +
				"		 '" + firstName + "',\r\n" +
				"        '" + lastName + "',\r\n" +
				"        '" + address + "', \r\n" +
				"        '" + city + "', \r\n" +
				"        '" + state + "',\r\n" +
				"        " + zipCode + ",\r\n" +
				"        '" + telephone + "',\r\n" +
				"        '" + email + "',\r\n" +
				"        " + rating + ",\r\n" +
				"        '" + creditCard + "',\r\n" +
				"        0,\r\n" +
				"        0)";

		int result = statement.executeUpdate(sql);
		if (result != 1) {
			return "failure";
		}

		return "success";
	}

	///////////////////////////////////////////////
	// TODO: zipCode needs to be an int
	public String editCustomer(Customer customer) {
		/*
		 * All the values of the edit customer form are encapsulated in the
		 * customer object.
		 * 
		 * These can be accessed by getter methods (see Customer class in model
		 * package). e.g. firstName can be accessed by customer.getFirstName()
		 * method.
		 * 
		 * The sample code returns "success" by default.
		 * 
		 * You need to handle the database update and return "success" or
		 * "failure" based on result of the database update.
		 */

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		int customerID = Integer.parseInt(customer.getCustomerID());
		String firstName = customer.getFirstName();
		String lastName = customer.getLastName();
		String address = customer.getAddress();
		String city = customer.getCity();
		String state = customer.getState();
		int zipCode = customer.getZipCode();
		String telephone = customer.getTelephone();
		String email = customer.getEmail();
		int rating = customer.getRating();
		String creditCard = customer.getCreditCard();

		String sql = "UPDATE \r\n" +
				"		Customer\r\n" +
				"	SET \r\n" +
				"		FirstName = '" + firstName + "',\r\n" +
				"        LastName = '" + lastName + "',\r\n" +
				"		Address = '" + address + "',\r\n" +
				"        City = '" + city + "',\r\n" +
				"        State = '" + state + "',\r\n" +
				"        ZipCode = " + zipCode + ",\r\n" +
				"        Telephone = '" + telephone + "',\r\n" +
				"        Email = '" + email + "',\r\n" +
				"        Rating = " + rating + ",\r\n" +
				"        CreditCardNum = '" + creditCard + "'\r\n" +
				"	WHERE CustomerID = " + customerID;

		int result = statement.executeUpdate(sql);
		if (result != 1) {
			return "failure";
		}

		return "success";
	}

}
