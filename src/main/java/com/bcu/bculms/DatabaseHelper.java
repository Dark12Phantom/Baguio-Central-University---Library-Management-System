package com.bcu.bculms;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    
    public static List<Book> getAllBooks(){
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM Books";
        
        try(Connection conn = DatabaseConnection.getConnection();
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(query)){
            while(rs.next()){
                Book book = new Book(
                        rs.getInt("bookID"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publication_date"),
                        rs.getString("department"),
                        rs.getString("borrow_status"),
                        rs.getString("dat_borrowed")
                );
                books.add(book);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return books;
    }
    
}
