package com.bcu.bculms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DATABASE_URL = "jdbc:sqlite:src/main/resources/com/bcu/database/library.db";
    private static Connection conn = null;
    
    public static Connection connect(){
        if(conn == null){
            try{
                conn = DriverManager.getConnection(DATABASE_URL);
                System.out.println("Database Connection established.");
            } catch (SQLException e){
                System.err.println("Error: " + e.getMessage());
            }
        }
        return conn;
    }
    
    public static void closeConnection(){
        if(conn != null){
            try {
                conn.close();
            }catch (SQLException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
