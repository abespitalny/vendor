package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;
import model.Employee;
import model.EmployeeLevel;
import model.VendorUser;

/*
 * This class handles all the database operations related to login functionality
 */
public class LoginDao {
    /**
     * Return a VendorUser object with permissions of Manager, Customer rep., or Customer if successful login
     * 
     * @param username Username of VendorUser
     * @param password Plain text password for VendorUser (in the future we hope to hash it with a salt)
     * @return a VendorUser which is either an Employee or Customer if the username and password are valid else it returns null
     * @throws java.lang.ClassNotFoundException
     */
    public VendorUser login(String username, String password) {
        String sql = "SELECT IF(UserPassword = ?, TRUE, FALSE) AS ValidPassword, Email, CustomerID, EmployeeID, EmployeeLevel" + 
                    " FROM (VendorUser LEFT JOIN Employee ON Username = EmployeeID) LEFT JOIN Customer ON Username = CustomerID" + 
                    " WHERE Username = ?";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            // set the password
            statement.setString(1, password);
            // set the username
            statement.setString(2, username);

            ResultSet rs = statement.executeQuery();
            // get the info. seeing if this username belongs to an employee or customer
            if (rs.next()) {
                Boolean isPasswordValid = rs.getBoolean("ValidPassword");
                if (isPasswordValid) {
                    String email = rs.getString("Email");
                    String employeeID = rs.getString("EmployeeID");
                    if (employeeID == null) {
                        Customer customer = new Customer(username, email);
                        return customer;
                    } else {
                        EmployeeLevel level = EmployeeLevel.valueOf(rs.getString("EmployeeLevel"));
                        Employee employee = new Employee(username, email, level);
                        return employee;
                    }
                } else {
                    System.out.println("Password was invalid");
                    return null;
                }
            }
            // if there were no results than username wasn't valid
            else {
                System.out.println("No rows were found because of invalid username");
                return null;
            }	
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
            return null;
        }
    }
}
