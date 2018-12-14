package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomerDao {
    public List<Customer> getCustomers(String searchKeyword) {
        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT CustomerID, FirstName, LastName, Address, City, State, ZipCode, Telephone, Email, CreditCardNum, Rating" +
                    " FROM Customer INNER JOIN VendorUser ON Username = CustomerID" +
                    " WHERE FirstName LIKE CONCAT('%', ?, '%') OR LastName LIKE CONCAT('%', ?, '%')";
        
        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, searchKeyword);
            statement.setString(2, searchKeyword);
            
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Customer customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9));
                    customer.setTelephone(rs.getString(8));
                    customer.setCreditCardNum(rs.getString(10));
                    customer.setRating(rs.getBigDecimal(11));
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }
        
        return customers;
    }

    /**
     * Customer is taken to be the seller 
     */
    public Customer getHighestRevenueCustomer() {
        Customer customer = null;
        
        String sql = "SELECT Seller, FirstName, LastName, Email, SUM(CurrentHighestBidPrice) AS HighestRevenue" +
                    " FROM Auction INNER JOIN VendorUser ON Seller = Username" +
                    " WHERE SaleStatus = 'Paid' GROUP BY Seller" +
                    " HAVING HighestRevenue = (SELECT SUM(CurrentHighestBidPrice) AS TotalRevenue" +
                    "                          FROM Auction" +
                    "                          WHERE SaleStatus = 'Paid' GROUP BY Seller" +
                    "                          ORDER BY TotalRevenue DESC LIMIT 1)";
        
        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {            
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer();
                    customer.setUsername(rs.getString(1));
                    customer.setFirstName(rs.getString(2));
                    customer.setLastName(rs.getString(3));
                    customer.setEmail(rs.getString(4));
                    customer.setRevenue(rs.getBigDecimal(5));
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }
        
        return customer;
    }

    public List<Customer> getCustomerMailingList() {
        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT CustomerID, FirstName, LastName, Address, City, State, ZipCode, Email" +
                    " FROM VendorUser INNER JOIN Customer ON Username = CustomerID";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Customer customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }
        
        return customers;
    }

    public Customer getCustomer(String customerID) {
        Customer customer = null;
        
        String sql = "SELECT CustomerID, UserPassword, FirstName, LastName, Address, City, State, ZipCode, Telephone, Email, CreditCardNum, Rating" +
                    " FROM Customer INNER JOIN VendorUser ON Username = CustomerID" +
                    " WHERE CustomerID = ?";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, customerID);
            
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), 
                        rs.getString(10), rs.getString(11), rs.getBigDecimal(12));
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }
        
        return customer;
    }
    
    public String deleteCustomer(String customerID) {
        String sql = "DELETE FROM VendorUser WHERE Username = ?";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, customerID);
            statement.executeUpdate();
            return "success";
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
            return "failure";
        }
    }

    public List<Customer> getSellers() {
        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT DISTINCT Seller, FirstName, LastName, Email, Rating, ItemsSold" +
                    " FROM Auction INNER JOIN (VendorUser INNER JOIN Customer ON Username = CustomerID) ON Username = Seller";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Customer customer = new Customer();
                    customer.setUsername(rs.getString(1));
                    customer.setFirstName(rs.getString(2));
                    customer.setLastName(rs.getString(3));
                    customer.setEmail(rs.getString(4));
                    customer.setRating(rs.getBigDecimal(5));
                    customer.setItemsSold(rs.getInt(6));
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
        }

        return customers;
    }
    
    public String addCustomer(Customer customer) {
        String sqlInsertUser = "INSERT INTO VendorUser VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlInsertCustomer = "INSERT INTO Customer (CustomerID, Rating, CreditCardNum) VALUES (?, ?, ?)";

        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statementInsertUser = conn.prepareStatement(sqlInsertUser);
                PreparedStatement statementInsertCustomer = conn.prepareStatement(sqlInsertCustomer);
        ) {
            statementInsertUser.setString(1, customer.getUsername());
            statementInsertUser.setString(2, customer.getPassword());
            statementInsertUser.setString(3, customer.getFirstName());
            statementInsertUser.setString(4, customer.getLastName());
            statementInsertUser.setString(5, customer.getAddress());
            statementInsertUser.setString(6, customer.getCity());
            statementInsertUser.setString(7, customer.getState());
            statementInsertUser.setString(8, customer.getZipCode());
            statementInsertUser.setString(9, customer.getTelephone());
            statementInsertUser.setString(10, customer.getEmail());
            
            statementInsertCustomer.setString(1, customer.getUsername());
            statementInsertCustomer.setBigDecimal(2, customer.getRating());
            statementInsertCustomer.setString(3, customer.getCreditCardNum());
            
            // start transaction
            conn.setAutoCommit(false);
            try {
                int affected = statementInsertUser.executeUpdate();
                if (affected != 1)
                    throw new Exception("Unable to insert into the user table.");
                
                affected = statementInsertCustomer.executeUpdate();
                if (affected != 1)
                    throw new Exception("Unable to insert into the customer table.");
                
                conn.commit();
                // transaction committed
                return "success";
            } catch (Exception e) {
                conn.rollback();
                System.out.println("Add customer transaction was rolled back");
                System.err.println(e);
                return "failure";
            }
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
            return "failure";
        }
    }

    public String editCustomer(String oldCustomerID, Customer customer) {
        String sqlUpateUser = "UPDATE VendorUser" +
                             " SET Username = ?, UserPassword = ?, FirstName = ?, LastName = ?, Address = ?, City = ?, State = ?, ZipCode = ?, Telephone = ?, Email = ?" +
                             " WHERE Username = ?";
        String sqlUpdateCustomer = "UPDATE Customer SET CreditCardNum = ?, Rating = ?" +
                                  " WHERE CustomerID = ?";
        
        try (
                Connection conn = ConnectionUtils.getMyConnection();
                PreparedStatement statementUpdateUser = conn.prepareStatement(sqlUpateUser);
                PreparedStatement statementUpdateCustomer = conn.prepareStatement(sqlUpdateCustomer);
        ) {
            statementUpdateUser.setString(1, customer.getUsername());
            statementUpdateUser.setString(2, customer.getPassword());
            statementUpdateUser.setString(3, customer.getFirstName());
            statementUpdateUser.setString(4, customer.getLastName());
            statementUpdateUser.setString(5, customer.getAddress());
            statementUpdateUser.setString(6, customer.getCity());
            statementUpdateUser.setString(7, customer.getState());
            statementUpdateUser.setString(8, customer.getZipCode());
            statementUpdateUser.setString(9, customer.getTelephone());
            statementUpdateUser.setString(10, customer.getEmail());
            statementUpdateUser.setString(11, oldCustomerID);
            
            statementUpdateCustomer.setString(1, customer.getCreditCardNum());
            statementUpdateCustomer.setBigDecimal(2, customer.getRating());
            statementUpdateCustomer.setString(3, customer.getUsername());            

            // start transaction
            conn.setAutoCommit(false);
            try {
                statementUpdateUser.executeUpdate();
                statementUpdateCustomer.executeUpdate();
                
                conn.commit();
                // transaction committed
                return "success";
            } catch (Exception e) {
                conn.rollback();
                System.out.println("Update customer transaction was rolled back");
                System.err.println(e);
                return "failure";
            }            
        } catch (SQLException e) {
            System.out.println("There was an unexpected error");
            System.err.println(e);
            return "failure";
        }
    }
}
