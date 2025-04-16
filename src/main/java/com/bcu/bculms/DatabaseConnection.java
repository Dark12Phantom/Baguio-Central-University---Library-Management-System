package com.bcu.bculms;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DATABASE_URL = "jdbc:sqlite:library.db";
    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null || isClosed()) {

            try {
                System.out.println("Working Directory: " + System.getProperty("user.dir"));

                File dbFile = new File("library.db");
                System.out.println("Looking for DB at: " + dbFile.getAbsolutePath());
                System.out.println("Database file exists? " + dbFile.exists());

                if (!dbFile.exists()) {
                    System.err.println("Database file NOT FOUND!");
                    return null;
                }

                conn = DriverManager.getConnection(DATABASE_URL);
                System.out.println("Database Connection established.");
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        return conn;
    }

    private static boolean isClosed() {
        try {
            return conn != null && conn.isClosed();
        } catch (SQLException e) {
            return true;
        }
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                    System.out.println("Database Closed.");
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
