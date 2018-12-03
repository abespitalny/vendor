package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import model.Employee;

public class EmployeeDao {
	/*
	 * This class handles all the database operations related to the employee
	 * table
	 */

	//////////////////////////////////////////////
	// TODO: Decide if we reformat the Java or the DB
	public String addEmployee(Employee employee) throws SQLException {
		/*
		 * All the values of the add employee form are encapsulated in the
		 * employee object.
		 * 
		 * These can be accessed by getter methods (see Employee class in model
		 * package). e.g. firstName can be accessed by employee.getFirstName()
		 * method.
		 * 
		 * The sample code returns "success" by default.
		 * 
		 * You need to handle the database insertion of the employee details and
		 * return "success" or "failure" based on result of the database
		 * insertion.
		 */

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String employeeID = employee.getEmployeeID();
		String startDate = employee.getStartDate();
		float hourlyRate = employee.getHourlyRate();
		String level = employee.getLevel();
		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();
		String address = employee.getAddress();
		String city = employee.getCity();
		String state = employee.getState();
		int zipCode = employee.getZipCode();
		String email = employee.getEmail();
		String telephone = employee.getTelephone();
		String revenue = employee.getRevenue();

		String sql = "INSERT INTO \r\n" +
				"	Employee (\r\n" +
				"		SSN,\r\n" +
				"        LastName,\r\n" +
				"        FirstName,\r\n" +
				"        Address,\r\n" +
				"        ZipCode,\r\n" +
				"        Telephone,\r\n" +
				"        Email,\r\n" +
				"        StartDate,\r\n" +
				"        EmployeeLevel,\r\n" +
				"        HourlyRate) \r\n" +
				"	VALUES (\r\n" +
				"		123123123,\r\n" +
				"		'Doe',\r\n" +
				"        'John',\r\n" +
				"        '321 AppleCourt, Smithtown, NY',\r\n" +
				"        '11984',\r\n" +
				"        '6311234567',\r\n" +
				"        'john@doe.com',\r\n" +
				"        '2001-03-08',\r\n" +
				"        1,\r\n" +
				"        40.00)";

		int result = statement.executeUpdate(sql);
		if (result != 1) {
			return "failure";
		}

		return "success";
	}

	///////////////////////////////////////////////
	// TODO: Decide if we reformat the Java or the DB
	public String editEmployee(Employee employee) throws SQLException {
		/*
		 * All the values of the edit employee form are encapsulated in the
		 * employee object.
		 * 
		 * These can be accessed by getter methods (see Employee class in model
		 * package). e.g. firstName can be accessed by employee.getFirstName()
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

		String employeeID = employee.getEmployeeID();
		String startDate = employee.getStartDate();
		float hourlyRate = employee.getHourlyRate();
		String level = employee.getLevel();
		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();
		String address = employee.getAddress();
		String city = employee.getCity();
		String state = employee.getState();
		int zipCode = employee.getZipCode();
		String email = employee.getEmail();
		String telephone = employee.getTelephone();
		String revenue = employee.getRevenue();

		String sql = "UPDATE \r\n" +
				"		Employee \r\n" +
				"	SET \r\n" +
				"		StartDate = '2001-03-09', \r\n" +
				"        EmployeeLevel = 3, \r\n" +
				"        HourlyRate = 45\r\n" +
				"	WHERE \r\n" +
				"		SSN = 123123123";

		int result = statement.executeUpdate(sql);
		if (result != 1) {
			return "failure";
		}

		return "success";
	}

	public String deleteEmployee(String employeeID) throws SQLException {
		/*
		 * employeeID, which is the Employee's ID which has to be deleted, is
		 * given as method parameter
		 * 
		 * The sample code returns "success" by default.
		 * 
		 * You need to handle the database deletion and return "success" or
		 * "failure" based on result of the database deletion.
		 */

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "DELETE FROM \r\n" +
				"		Employee\r\n" +
				"	WHERE \r\n" +
				"		SSN = " + employeeID;

		int result = statement.executeUpdate(sql);
		if (result != 1) {
			return "failure";
		}

		return "success";
	}

	//////////////////////////////////////
	// TODO: Data base might have to be refactored
	public List<Employee> getEmployees() throws SQLException {
		/*
		 * The students code to fetch data from the database will be written
		 * here
		 * 
		 * Query to return details about all the employees must be implemented
		 * 
		 * Each record is required to be encapsulated as a "Employee" class
		 * object and added to the "employees" List
		 */

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "SELECT * FROM Employee";

		ResultSet rs = statement.executeQuery(sql);

		List<Employee> employees = new ArrayList<Employee>();

		while (rs.next()) {
			// Pull data from database
			String email = rs.getString(11);
			String firstName = rs.getString(5);
			String lastName = rs.getString(6);
			String address = rs.getString(7);
			String city = rs.getString(8);
			String startDate = rs.getDate(2).toString();
			String state = rs.getString(9);
			int zipCode = rs.getInt(10);
			String telephone = rs.getString(12);
			String employeeID = rs.getString(1);
			float hourlyRate = rs.getFloat(3);

			// Add data to employee
			Employee employee = new Employee();
			employee.setEmail(email);
			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setAddress(address);
			employee.setCity(city);
			employee.setStartDate(startDate);
			employee.setState(state);
			employee.setZipCode(zipCode);
			employee.setTelephone(telephone);
			employee.setEmployeeID(employeeID);
			employee.setHourlyRate(hourlyRate);
			employees.add(employee);
		}

		return employees;
	}

	public Employee getEmployee(String employeeID) throws SQLException {
		/*
		 * The students code to fetch data from the database based on
		 * "employeeID" will be written here
		 * 
		 * employeeID, which is the Employee's ID who's details have to be
		 * fetched, is given as method parameter
		 * 
		 * The record is required to be encapsulated as a "Employee" class
		 * object
		 */

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "SELECT\r\n" +
				"        *\r\n" +
				"    FROM\r\n" +
				"        Employee\r\n" +
				"    WHERE\r\n" +
				"        EmployeeID = " + employeeID;

		ResultSet rs = statement.executeQuery(sql);

		// Pull data from database
		String email = rs.getString(11);
		String firstName = rs.getString(5);
		String lastName = rs.getString(6);
		String address = rs.getString(7);
		String city = rs.getString(8);
		String startDate = rs.getDate(2).toString();
		String state = rs.getString(9);
		int zipCode = rs.getInt(10);
		String telephone = rs.getString(12);
		float hourlyRate = rs.getFloat(3);

		// Add data to employee
		Employee employee = new Employee();
		employee.setEmail(email);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setAddress(address);
		employee.setCity(city);
		employee.setStartDate(startDate);
		employee.setState(state);
		employee.setZipCode(zipCode);
		employee.setTelephone(telephone);
		employee.setEmployeeID(employeeID);
		employee.setHourlyRate(hourlyRate);

		return employee;
	}

	/////////////////////////////////////////////
	// TODO: The DB might have to be reformatted
	public Employee getHighestRevenueEmployee() throws SQLException {
		/*
		 * The students code to fetch employee data who generated the highest
		 * revenue will be written here
		 * 
		 * The record is required to be encapsulated as a "Employee" class
		 * object
		 */

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "SELECT \r\n" +
				"        E.Email,\r\n" +
				"        E.FirstName,\r\n" +
				"        E.LastName,\r\n" +
				"		 E.EmployeeID,\r\n" +
				"        SUM(A.CurrentHighestBidPrice) AS TotalRevenue\r\n" +
				"	FROM \r\n" +
				"		Employee E, \r\n" +
				"        Auction A, \r\n" +
				"        Post P\r\n" +
				"	WHERE \r\n" +
				"		A.Monitor = E.EmployeeID AND \r\n" +
				"        P.AuctionID = A.AuctionID AND \r\n" +
				"        P.EndDate < CURDATE() \r\n" +
				"	GROUP BY 1 \r\n" +
				"    ORDER BY TotalRevenue DESC;";

		ResultSet rs = statement.executeQuery(sql);

		// Pull data from database
		String email = rs.getString(11);
		String firstName = rs.getString(5);
		String lastName = rs.getString(6);
		String employeeID = rs.getString(1);

		// Add data to employee
		Employee employee = new Employee();
		employee.setEmail(email);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setEmployeeID(employeeID);

		return employee;
	}

	//////////////////////////////////////////////
	// TODO: Employees don't have usernames
	public String getEmployeeID(String username) {
		/*
		 * The students code to fetch data from the database based on "username"
		 * will be written here
		 * 
		 * username, which is the Employee's email address who's Employee ID has
		 * to be fetched, is given as method parameter
		 * 
		 * The Employee ID is required to be returned as a String
		 */

		return "111-11-1111";
	}

}
