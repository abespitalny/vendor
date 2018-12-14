package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Employee;
import model.EmployeeLevel;

public class EmployeeDao {
    public String addEmployee(Employee employee) {
        String sqlInsertUser = "INSERT INTO VendorUser VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlInsertEmployee = "INSERT INTO Employee VALUES (?, ?, CURDATE(), ?, ?);";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statementInsertUser = conn.prepareStatement(sqlInsertUser);
                PreparedStatement statementInsertEmployee = conn.prepareStatement(sqlInsertEmployee);
        ) {
            statementInsertUser.setString(1, employee.getUsername());
            statementInsertUser.setString(2, employee.getPassword());
            statementInsertUser.setString(3, employee.getFirstName());
            statementInsertUser.setString(4, employee.getLastName());
            statementInsertUser.setString(5, employee.getAddress());
            statementInsertUser.setString(6, employee.getCity());
            statementInsertUser.setString(7, employee.getState());
            statementInsertUser.setString(8, employee.getZipCode());
            statementInsertUser.setString(9, employee.getTelephone());
            statementInsertUser.setString(10, employee.getEmail());
            
            statementInsertEmployee.setString(1, employee.getUsername());
            statementInsertEmployee.setString(2, employee.getSsn());
            statementInsertEmployee.setString(3, EmployeeLevel.CustomerRep.name());
            statementInsertEmployee.setBigDecimal(4, employee.getHourlyRate());
            
            // start transaction
            conn.setAutoCommit(false);
            try {
                int affected = statementInsertUser.executeUpdate();
                if (affected != 1)
                    throw new Exception("Unable to insert into the user table.");
                
                affected = statementInsertEmployee.executeUpdate();
                if (affected != 1)
                    throw new Exception("Unable to insert into the employee table.");
                
                conn.commit();
                // transaction committed
                return "success";
            } catch (Exception e) {
                conn.rollback();
                System.out.println("Add employee transaction was rolled back");
                System.err.println(e);
                return "failure";
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
            return "failure";
        }
    }

    public String editEmployee(String oldEmployeeID, Employee employee) {
        String sqlUpateUser = "UPDATE VendorUser" +
                             " SET Username = ?, UserPassword = ?, FirstName = ?, LastName = ?, Address = ?, City = ?, State = ?, ZipCode = ?, Telephone = ?, Email = ?" +
                             " WHERE Username = ?";
        String sqlUpdateEmployee = "UPDATE Employee SET SSN = ?, HourlyRate = ?" +
                                  " WHERE EmployeeID = ?";
        
        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statementUpdateUser = conn.prepareStatement(sqlUpateUser);
                PreparedStatement statementUpdateEmployee = conn.prepareStatement(sqlUpdateEmployee);
        ) {
            statementUpdateUser.setString(1, employee.getUsername());
            statementUpdateUser.setString(2, employee.getPassword());
            statementUpdateUser.setString(3, employee.getFirstName());
            statementUpdateUser.setString(4, employee.getLastName());
            statementUpdateUser.setString(5, employee.getAddress());
            statementUpdateUser.setString(6, employee.getCity());
            statementUpdateUser.setString(7, employee.getState());
            statementUpdateUser.setString(8, employee.getZipCode());
            statementUpdateUser.setString(9, employee.getTelephone());
            statementUpdateUser.setString(10, employee.getEmail());
            statementUpdateUser.setString(11, oldEmployeeID);
            
            statementUpdateEmployee.setString(1, employee.getSsn());
            statementUpdateEmployee.setBigDecimal(2, employee.getHourlyRate());
            statementUpdateEmployee.setString(3, employee.getUsername());            

            // start transaction
            conn.setAutoCommit(false);
            try {
                statementUpdateUser.executeUpdate();
                statementUpdateEmployee.executeUpdate();
                
                conn.commit();
                // transaction committed
                return "success";
            } catch (Exception e) {
                conn.rollback();
                System.out.println("Update employee transaction was rolled back");
                System.err.println(e);
                return "failure";
            }            
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
            return "failure";
        }
    }

    public String deleteEmployee(String employeeID) {
        String sql = "DELETE V FROM VendorUser AS V INNER JOIN Employee ON Username = EmployeeID" +
                    " WHERE Username = ? AND EmployeeLevel = 'CustomerRep'";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, employeeID);
            statement.executeUpdate();
            return "success";
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
            return "failure";
        }
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();

        String sql = "SELECT EmployeeID, FirstName, LastName, Address, City, State, ZipCode, Telephone, Email, StartDate, SSN, HourlyRate" +
                    " FROM VendorUser INNER JOIN Employee ON Username = EmployeeID" +
                    " WHERE EmployeeLevel = 'CustomerRep'";
        
        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) { 
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Employee employee = new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getDate(10),
                        rs.getString(11), rs.getBigDecimal(12));
                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }
        
        return employees;
    }

    public Employee getEmployee(String employeeID) {
        Employee employee = null;

        String sql = "SELECT EmployeeID, UserPassword, FirstName, LastName, Address, City, State, ZipCode, Telephone, Email, SSN, HourlyRate" +
                    " FROM Employee INNER JOIN VendorUser ON Username = EmployeeID" +
                    " WHERE EmployeeID = ?";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, employeeID);
            
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    employee = new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
                        rs.getString(11), rs.getBigDecimal(12));
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }
        
        return employee;
    }
    
    public Employee getHighestRevenueEmployee() {
        Employee employee = null;

        String sql = "SELECT Monitor, FirstName, LastName, Email, SUM(CurrentHighestBidPrice) AS HighestRevenue" +
                    " FROM Auction INNER JOIN VendorUser ON Monitor = Username" +
                    " WHERE SaleStatus = 'Paid' GROUP BY Monitor" +
                    " HAVING HighestRevenue = (SELECT SUM(CurrentHighestBidPrice) AS TotalRevenue" +
                    "                          FROM Auction" +
                    "                          WHERE SaleStatus = 'Paid' GROUP BY Monitor" +
                    "                          ORDER BY TotalRevenue DESC LIMIT 1)";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {    
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    employee = new Employee();
                    employee.setUsername(rs.getString(1));
                    employee.setFirstName(rs.getString(2));
                    employee.setLastName(rs.getString(3));
                    employee.setEmail(rs.getString(4));
                    employee.setRevenue(rs.getBigDecimal(5));
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }
        
        return employee;
    }
}
