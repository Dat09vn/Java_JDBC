package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Database connection utility for Oracle DB
 */
public class DatabaseConnection {
    
    private static final String PROPERTIES_FILE = "config.properties";
    private static final Properties properties = loadProperties();
    private static final String DB_URL = properties.getProperty("DB_URL");
    private static final String DB_USER = properties.getProperty("DB_USER");
    private static final String DB_PASSWORD = properties.getProperty("DB_PASSWORD");
    
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("Oracle JDBC Driver not found");
            e.printStackTrace();
        }
    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (FileInputStream input = new FileInputStream(PROPERTIES_FILE)) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database configuration from " + PROPERTIES_FILE, e);
        }
        return props;
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
