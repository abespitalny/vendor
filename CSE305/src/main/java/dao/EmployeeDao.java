package dao;

import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
import com.mysql.cj.xdevapi.Statement;

import model.Employee;

public class EmployeeDao {
	/*
	 * This class handles all the database operations related to the employee
	 * table
	 */

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
	public String addEmployee(Employee employee) {
		// TODO: Decide if we reformat the Java or the DB

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

		String sql = "INSERT INTO " +
				"	Employee (" +
				"		SSN," +
				"        LastName," +
				"        FirstName," +
				"        Address," +
				"        ZipCode," +
				"        Telephone," +
				"        Email," +
				"        StartDate," +
				"        EmployeeLevel," +
				"        HourlyRate) " +
				"	VALUES (" +
				"		123123123," +
				"		'Doe'," +
				"        'John'," +
				"        '321 AppleCourt, Smithtown, NY'," +
				"        '11984'," +
				"        '6311234567'," +
				"        'john@doe.com'," +
				"        '2001-03-08'," +
				"        1," +
				"        40.00)";

		int result = statement.executeUpdate(sql);
		if (result != 1) {
			return "failure";
		}

		return "success";
	}

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
	public String editEmployee(Employee employee) {
		// TODO: Decide if we reformat the Java or the DB

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

		String sql = "UPDATE " +
				"		Employee " +
				"	SET " +
				"		StartDate = '2001-03-09', " +
				"        EmployeeLevel = 3, " +
				"        HourlyRate = 45" +
				"	WHERE " +
				"		SSN = 123123123";

		int result = statement.executeUpdate(sql);
		if (result != 1) {
			return "failure";
		}

		return "success";
	}

	/*
	 * employeeID, which is the Employee's ID which has to be deleted, is
	 * given as method parameter
	 * 
	 * The sample code returns "success" by default.
	 * 
	 * You need to handle the database deletion and return "success" or
	 * "failure" based on result of the database deletion.
	 */
	public String deleteEmployee(String employeeID) {

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "DELETE FROM " +
				"		Employee" +
				"	WHERE " +
				"		SSN = " + employeeID;

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
	 * Query to return details about all the employees must be implemented
	 * 
	 * Each record is required to be encapsulated as a "Employee" class
	 * object and added to the "employees" List
	 */
	public List<Employee> getEmployees() {
		// TODO: Data base might have to be refactored

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
	public Employee getEmployee(String employeeID) {

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "SELECT" +
				"        *" +
				"    FROM" +
				"        Employee" +
				"    WHERE" +
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

		return employee;
	}

	/*
	 * The students code to fetch employee data who generated the highest
	 * revenue will be written here
	 * 
	 * The record is required to be encapsulated as a "Employee" class
	 * object
	 */
	public Employee getHighestRevenueEmployee() {
		// TODO: The DB might have to be reformatted

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "SELECT " +
				"        E.Email," +
				"        E.FirstName," +
				"        E.LastName," +
				"		 E.EmployeeID," +
				"        SUM(A.CurrentHighestBidPrice) AS TotalRevenue" +
				"	FROM " +
				"		Employee E, " +
				"        Auction A, " +
				"        Post P" +
				"	WHERE " +
				"		A.Monitor = E.EmployeeID AND " +
				"        P.AuctionID = A.AuctionID AND " +
				"        P.EndDate < CURDATE() " +
				"	GROUP BY 1 " +
				"    ORDER BY TotalRevenue DESC";

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

	/*
	 * The students code to fetch data from the database based on "username"
	 * will be written here
	 * 
	 * username, which is the Employee's email address who's Employee ID has
	 * to be fetched, is given as method parameter
	 * 
	 * The Employee ID is required to be returned as a String
	 */
	public String getEmployeeID(String username) {
		// TODO: Employees don't have usernames

		return "111-11-1111";
	}

}
