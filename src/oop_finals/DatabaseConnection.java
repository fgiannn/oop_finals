/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_finals;

/**
 *
 * @author Admin


/**
 * Database Connection Manager
 * Handles MySQL database connections for the Guidance Appointment System
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Database configuration
    private static final String URL = "jdbc:mysql://localhost:3306/guidance_appointment_system";
    private static final String USERNAME = "root"; 
    private static final String PASSWORD = "";
    
    /**
     * Establishes and returns a database connection
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found. Add mysql-connector-java.jar to your project.", e);
        }
    }
    
    /**
     * Tests if database connection is working
     * @return true if connection successful, false otherwise
     */
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            System.err.println("❌ Connection test failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Main method for testing database connection
     */
    public static void main(String[] args) {
        System.out.println("Testing database connection...");
        if (testConnection()) {
            System.out.println("✅ Database connection successful!");
        } else {
            System.out.println("❌ Database connection failed!");
            System.out.println("Please check:");
            System.out.println("1. MySQL server is running");
            System.out.println("2. Database 'guidance_appointment_system' exists");
            System.out.println("3. Username and password are correct");
            System.out.println("4. mysql-connector-java.jar is added to project");
        }
    }
}
