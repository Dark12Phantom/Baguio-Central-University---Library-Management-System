package com.bcu.bculms.backend;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());
    private static final String DATABASE_URL = "jdbc:sqlite:library.db";
    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {
        if (conn == null || isClosed()) {
            File dbFile = new File("library.db");
            LOGGER.info("Looking for DB at: " + dbFile.getAbsolutePath());

            if (!dbFile.exists()) {
                LOGGER.severe("Database file not found at: " + dbFile.getAbsolutePath());
                throw new SQLException("Database file not found");
            }

            try {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection(DATABASE_URL);
                LOGGER.info("Database connection established");
            } catch (ClassNotFoundException e) {
                LOGGER.severe("SQLite JDBC Driver not found: " + e.getMessage());
                throw new SQLException("SQLite JDBC Driver not found", e);
            }
        }
        return conn;
    }

    private static boolean isClosed() {
        try {
            return conn == null || conn.isClosed();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error checking connection state", e);
            return true;
        }
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                    LOGGER.info("Database connection closed");
                }
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error closing database connection", e);
            } finally {
                conn = null;
            }
        }
    }
}
