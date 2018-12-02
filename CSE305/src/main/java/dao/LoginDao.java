package dao;

import model.Employee;
import model.Login;

public class LoginDao {
	/*
	 * This class handles all the database operations related to login
	 * functionality
	 */

	//////////////////////////////////////////////////////
	// TODO: Add username and passwords to the DB. Then write SQL
	public Login login(String username, String password) {
		/*
		 * Return a Login object with role as "manager",
		 * "customerRepresentative" or "customer" if successful login
		 * 
		 * Else, return null
		 * 
		 * The role depends on the type of the user, which has to be handled in
		 * the database
		 * 
		 * username, which is the email address of the user, is given as method
		 * parameter
		 * 
		 * password, which is the password of the user, is given as method
		 * parameter
		 * 
		 * Query to verify the username and password and fetch the role of the
		 * user, must be implemented
		 */

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String sql = "";

		ResultSet rs = statement.executeQuery(sql);

		// Pull data from database
		String role = rs.getString(1);

		// Add data to login
		Login login = new Login();
		login.setRole(role);
		return login;
		/* Sample data ends */
	}

	////////////////////////////////////////////////////
	// TODO: Add username and passwords to the DB, then write SQL
	public String addUser(Login login) {
		/*
		 * Query to insert a new record for user login must be implemented
		 * 
		 * login, which is the "Login" Class object containing username and
		 * password for the new user, is given as method parameter
		 * 
		 * The username and password from login can get accessed using getter
		 * methods in the "Login" model
		 * 
		 * e.g. getUsername() method will return the username encapsulated in
		 * login object
		 * 
		 * Return "success" on successful insertion of a new user
		 * 
		 * Return "failure" for an unsuccessful database operation
		 */

		// Get connection
		Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
		Statement statement = connection.createStatement();

		String username = login.getUsername();
		String password = login.getPassword();
		String role = login.getRole();

		String sql = "";

		int result = statement.executeUpdate(sql);
		if (result != 1) {
			return "failure";
		}

		return "success";
	}

}
