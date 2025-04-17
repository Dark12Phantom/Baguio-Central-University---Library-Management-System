package com.bcu.bculms.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM Books";

        try (Connection conn = DatabaseConnection.getConnection(); Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery(query)) {
            while (rs.next()) {
                Book book = new Book(
                        rs.getString("bookID"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publication_date"),
                        rs.getString("department"),
                        rs.getString("borrow_status"),
                        rs.getInt("book_copies")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public boolean addBook(String title, String author, String pubDate, String dept, int bookCopies) {
        String bookID = getNextBookID(dept);
        String query = "INSERT INTO Books (bookID, title, author, publication_date, department, borrow_status, book_copies) "
                + "VALUES (?, ?, ?, ?, ?, 'In Library', ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, bookID);
            pst.setString(2, title);
            pst.setString(3, author);
            pst.setString(4, pubDate);
            pst.setString(5, dept);
            pst.setInt(6, bookCopies);

            int rowsAffected = pst.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean removeBook(String bookID){
        String query = "DELETE FROM Books WHERE bookID = ?";
        
        try(Connection conn = DatabaseConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(query)){
            pst.setString(1, bookID);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return false;
    }

    public String getNextBookID(String dept) {
        // Format the prefix based on the department (e.g., "CBA", "CTELA")
        String prefix = dept;
        String query = "SELECT MAX(bookID) FROM Books WHERE department = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, dept);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String maxBorrowID = rs.getString(1);

                if (maxBorrowID != null && maxBorrowID.startsWith(prefix)) {
                    // Extract the numeric part of the borrowID and increment it
                    String numericPart = maxBorrowID.substring(prefix.length());
                    int nextID = Integer.parseInt(numericPart) + 1;
                    return String.format("%s%06d", prefix, nextID); // Format with leading zeros (e.g., CBA000002)
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // If no books exist for the department, return the first ID in the format "CBA000001"
        return String.format("%s000001", prefix);
    }

}
