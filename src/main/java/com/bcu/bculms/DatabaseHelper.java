package com.bcu.bculms;

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
                        rs.getString("date_borrowed")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public boolean addBook(String title, String author, String pubDate, String dept) {
        String borrowID = getNextBorrowID(dept);
        String query = "INSERT INTO Books (bookID, title, author, publication_date, department, borrow_status, date_borrowed) "
                + "VALUES (?, ?, ?, ?, ?, 'In Library', NULL)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, borrowID);
            pst.setString(2, title);
            pst.setString(3, author);
            pst.setString(4, pubDate);
            pst.setString(5, dept);

            int rowsAffected = pst.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getNextBorrowID(String dept) {
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
