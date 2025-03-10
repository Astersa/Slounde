/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Astersa
 */
package util;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DBUtil {

    private static BasicDataSource dataSource;
    
    static {
        try {
            // Initialize the data source with hard-coded values
            dataSource = new BasicDataSource();
            
            // Database connection settings
            dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            dataSource.setUrl("jdbc:sqlserver://ND2P\\PHUONG:1433;databaseName=SloundeDB;encrypt=true;trustServerCertificate=true");
            dataSource.setUrl("jdbc:sqlserver://ND2P\\PHUONG:1433;databaseName=SloundeDB;encrypt=true;trustServerCertificate=true");
            dataSource.setUsername("sa");
            dataSource.setPassword("123");
            
            // Connection pool settings
            dataSource.setInitialSize(5);
            dataSource.setMaxTotal(10);
            dataSource.setMaxIdle(5);
            dataSource.setMinIdle(2);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
    try {
        // Try to get a connection
        Connection conn = DBUtil.getConnection();
        
        if (conn != null) {
            System.out.println("Database connection successful!");
            
            // Print connection details
            System.out.println("Connection details:");
            System.out.println("- Auto-commit: " + conn.getAutoCommit());
            System.out.println("- Catalog: " + conn.getCatalog());
            System.out.println("- Connection valid: " + conn.isValid(5));
            
            // Close the connection
            DBUtil.closeConnection(conn);
            System.out.println("\nConnection closed.");
        } else {
            System.out.println("Failed to get database connection!");
        }
    } catch (Exception e) {
        System.out.println("Error testing database connection:");
        e.printStackTrace();
    }
}
}
