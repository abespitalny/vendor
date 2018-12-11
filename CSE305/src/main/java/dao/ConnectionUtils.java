package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "smolka305";
    private static final String CONN_STRING = "jdbc:mysql://35.237.254.109:3306/vendor";
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Fatal error!");
        }
    }
    
    public static Connection getMyConnection() throws SQLException {
        return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
    }
}
