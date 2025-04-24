package com.bcu.bculms.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHelper {
    private static final Logger LOGGER = Logger.getLogger(DatabaseHelper.class.getName());
    
    public static final String STATUS_AVAILABLE = "Available";
    public static final String STATUS_BORROWED = "Borrowed";  // Ensure case matches database enum
    public static final String STATUS_MAINTENANCE = "Damaged";  // Fixed placement inside class
    public static final String STATUS_LOST = "Lost";
    
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT bookID, title, author, publication_date, department FROM Books";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                books.add(createBookFromResultSet(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all books: " + e.getMessage(), e);
        }
        return books;
    }
    
    public List<Book> searchBooks(String searchTerm, String department) {
        List<Book> books = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder(
            "SELECT bookID, title, author, publication_date, department FROM Books WHERE ");
        
        boolean hasSearchTerm = searchTerm != null && !searchTerm.trim().isEmpty();
        boolean hasDepartment = department != null && !department.trim().isEmpty();
        
        if (hasSearchTerm) {
            queryBuilder.append("(title LIKE ? OR author LIKE ?) ");
        }
        
        if (hasSearchTerm && hasDepartment) {
            queryBuilder.append("AND ");
        }
        
        if (hasDepartment) {
            queryBuilder.append("department = ? ");
        }
        
        if (!hasSearchTerm && !hasDepartment) {
            // If no search criteria, return all books
            return getAllBooks();
        }
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(queryBuilder.toString())) {
            
            int paramIndex = 1;
            if (hasSearchTerm) {
                String searchPattern = "%" + searchTerm + "%";
                pst.setString(paramIndex++, searchPattern);
                pst.setString(paramIndex++, searchPattern);
            }
            
            if (hasDepartment) {
                pst.setString(paramIndex, department);
            }
            
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    books.add(createBookFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error searching books", e);
        }
        return books;
    }
    
    public Book getBookById(String bookID) {
        String query = "SELECT bookID, title, author, publication_date, department FROM Books WHERE bookID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            
            pst.setString(1, bookID);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return createBookFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving book by ID: " + bookID + ". Cause: " + e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve book with ID: " + bookID, e);
        }
        return null;
    }
    
    private Book createBookFromResultSet(ResultSet rs) throws SQLException {
        return new Book(
            rs.getString("bookID"),
            rs.getString("title"),
            rs.getString("author"),
            rs.getString("publication_date"),
            rs.getString("department")
        );
    }
    
    public List<BookCopy> getBookCopies(String status) {
        List<BookCopy> copies = new ArrayList<>();
        
        StringBuilder queryBuilder = new StringBuilder(
            "SELECT bc.copyID, bc.bookID, b.title, bc.status, bc.purchase_date " +
            "FROM BookCopies bc JOIN Books b ON bc.bookID = b.bookID");
        
        if (status != null && !status.isEmpty()) {
            queryBuilder.append(" WHERE bc.status = ?");
        }
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(queryBuilder.toString())) {
            
            if (status != null && !status.isEmpty()) {
                pst.setString(1, status);
            }
            
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    BookCopy copy = createBookCopyFromResultSet(rs);
                    copies.add(copy);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving book copies", e);
        }
        
        return copies;
    }
    
    public List<BookCopy> getCopiesByBookId(String bookID) {
        List<BookCopy> copies = new ArrayList<>();
        String query = "SELECT bc.copyID, bc.bookID, b.title, bc.status, bc.purchase_date " +
                       "FROM BookCopies bc JOIN Books b ON bc.bookID = b.bookID " +
                       "WHERE bc.bookID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            
            pst.setString(1, bookID);
            
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    copies.add(createBookCopyFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving copies by book ID: " + bookID, e);
        }
        return copies;
    }
    
    public BookCopy getCopyById(String copyID) {
        String query = "SELECT bc.copyID, bc.bookID, b.title, bc.status, bc.purchase_date " +
                       "FROM BookCopies bc JOIN Books b ON bc.bookID = b.bookID " +
                       "WHERE bc.copyID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            
            pst.setString(1, copyID);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return createBookCopyFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving copy by ID: " + copyID, e);
        }
        return null;
    }
    
    private BookCopy createBookCopyFromResultSet(ResultSet rs) throws SQLException {
        BookCopy copy = new BookCopy(
            rs.getString("copyID"),
            rs.getString("bookID"),
            rs.getString("status"),
            rs.getString("purchase_date")
        );
        copy.setTitle(rs.getString("title"));
        return copy;
    }
    
    public List<Loan> getLoans(boolean includeActive, boolean includeReturned, String borrowerID, boolean includeBookTitle) {
        List<Loan> loans = new ArrayList<>();
        LOGGER.info("[DEBUG] Building loans query. Active: " + includeActive + 
                  " Returned: " + includeReturned + " Borrower: " + borrowerID);
        
        String query = """
            SELECT l.loanID, l.copyID, b.title, 
                   l.borrowerID, 
                   br.name as borrower_name,
                   l.borrow_date, l.due_date, l.return_date
            FROM Loans l
            JOIN BookCopies bc ON l.copyID = bc.copyID
            JOIN Books b ON bc.bookID = b.bookID
            JOIN Borrowers br ON l.borrowerID = br.borrowerID
            WHERE 1=1
        """;
        
        if (!includeActive && !includeReturned) return loans;
        if (!includeActive) query += " AND l.return_date IS NOT NULL";
        if (!includeReturned) query += " AND l.return_date IS NULL";
        if (borrowerID != null) query += " AND l.borrowerID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            
            if (borrowerID != null) {
                pst.setString(1, borrowerID);
            }
            
            LOGGER.info("[DEBUG] Executing loans query: " + query);
            
            try (ResultSet rs = pst.executeQuery()) {
                int count = 0;
                while (rs.next()) {
                    count++;
                    loans.add(new Loan(
                        rs.getString("loanID"),
                        rs.getString("copyID"),
                        rs.getString("title"),
                        rs.getString("borrowerID"),
                        rs.getString("borrower_name"),  // Make sure this matches SQL alias
                        rs.getString("borrow_date"),
                        rs.getString("due_date"),
                        rs.getString("return_date")
                    ));
                }
                LOGGER.info("[DEBUG] Retrieved " + count + " loan records");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "[ERROR] Loan query failed: " + e.getMessage(), e);
        }
        return loans;
    }

    public boolean createLoan(String copyID, String borrowerID, String borrowDate, String dueDate) {
        String insertLoanSQL = "INSERT INTO Loans (copyID, borrowerID, borrow_date, due_date) VALUES (?, ?, ?, ?)";
        String updateCopySQL = "UPDATE BookCopies SET status = ? WHERE copyID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement loanStmt = conn.prepareStatement(insertLoanSQL);
             PreparedStatement copyStmt = conn.prepareStatement(updateCopySQL)) {
            
            conn.setAutoCommit(false);
            
            // Set parameters for loan insertion
            loanStmt.setString(1, copyID);
            loanStmt.setString(2, borrowerID);
            loanStmt.setString(3, borrowDate);
            loanStmt.setString(4, dueDate);
            int loansCreated = loanStmt.executeUpdate();
            
            // Set parameters for copy status update
            copyStmt.setString(1, STATUS_BORROWED);  // Use constant with correct case
            copyStmt.setString(2, copyID);
            int copiesUpdated = copyStmt.executeUpdate();
            
            if (loansCreated == 1 && copiesUpdated == 1) {
                conn.commit();
                return true;
            }
            conn.rollback();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Loan creation failed for copy: " + copyID, e);
        }
        return false;
    }

    public boolean returnBook(String copyID) {
        String updateLoanSQL = "UPDATE Loans SET return_date = DATE('now') WHERE copyID = ? AND return_date IS NULL";
        String updateCopySQL = "UPDATE BookCopies SET status = ? WHERE copyID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement loanStmt = conn.prepareStatement(updateLoanSQL);
             PreparedStatement copyStmt = conn.prepareStatement(updateCopySQL)) {
            
            conn.setAutoCommit(false);
            
            // Update loan return date
            loanStmt.setString(1, copyID);
            int loansUpdated = loanStmt.executeUpdate();
            
            // Update copy status
            copyStmt.setString(1, STATUS_AVAILABLE);
            copyStmt.setString(2, copyID);
            int copiesUpdated = copyStmt.executeUpdate();
            
            if (loansUpdated >= 1 && copiesUpdated == 1) {
                conn.commit();
                return true;
            }
            conn.rollback();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Book return failed for copy: " + copyID, e);
        }
        return false;
    }

    // Add these core methods back
    public boolean addBook(String title, String author, String pubDate, String department) {
        String sql = "INSERT INTO Books (bookID, title, author, publication_date, department) VALUES (?, ?, ?, ?, ?)";
        String bookID = generateBookID(department);  // Pass department to ID generator
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, bookID);
            pst.setString(2, title);
            pst.setString(3, author);
            pst.setString(4, pubDate);
            pst.setString(5, department);
            
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Add book failed: " + title, e);
        }
        return false;
    }

    private String generateBookID(String department) {
        String maxIdQuery = "SELECT MAX(bookID) FROM Books WHERE bookID LIKE ?";
        String prefix = department.toUpperCase();  // Use full department name
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(maxIdQuery)) {
            
            pst.setString(1, prefix + "%");
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String maxId = rs.getString(1);
                    if (maxId != null && maxId.startsWith(prefix)) {
                        String numberPart = maxId.substring(prefix.length());
                        int lastNumber = Integer.parseInt(numberPart);
                        return prefix + String.format("%05d", lastNumber + 1);
                    }
                }
            }
        } catch (SQLException | NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Error generating book ID", e);
        }
        return prefix + "00001";  // Fallback to first in sequence
    }

    public boolean updateBook(String bookID, String title, String author, String publicationDate, String department) {
        String sql = "UPDATE Books SET title = ?, author = ?, publication_date = ?, department = ? WHERE bookID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            // Convert String date to SQL Date if needed, or keep as string if using TEXT format
            pst.setString(1, title);
            pst.setString(2, author);
            pst.setString(3, publicationDate);  // Should be in 'YYYY-MM-DD' format
            pst.setString(4, department);
            pst.setString(5, bookID);
            
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Update failed for book: " + bookID, e);
        }
        return false;
    }

    public boolean removeBook(String bookID) {
        String sql = "DELETE FROM Books WHERE bookID = ? AND NOT EXISTS (SELECT 1 FROM BookCopies WHERE bookID = ? AND status = ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, bookID);
            pst.setString(2, bookID);
            pst.setString(3, STATUS_BORROWED);
            
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Delete failed for book: " + bookID, e);
        }
        return false;
    }

    private String generateLoanID() {
        return "LN" + System.currentTimeMillis();
    }

    private String generateBookID() {
        return "BK" + System.currentTimeMillis();
    }
    
    public boolean updateBookCopies(String bookID, int currentCount, int targetCount) {
        // First delete all existing copies for this book
        String deleteSQL = "DELETE FROM BookCopies WHERE bookID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement deleteStmt = conn.prepareStatement(deleteSQL)) {
            
            deleteStmt.setString(1, bookID);
            deleteStmt.executeUpdate();
            
            // Now insert fresh copies with sequential numbering
            String insertSQL = "INSERT INTO BookCopies (copyID, bookID, status, purchase_date) VALUES (?, ?, ?, DATE('now'))";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
                for (int i = 1; i <= targetCount; i++) {
                    String copyID = generateSequentialCopyID(bookID, i);
                    insertStmt.setString(1, copyID);
                    insertStmt.setString(2, bookID);
                    insertStmt.setString(3, STATUS_AVAILABLE);
                    insertStmt.addBatch();
                }
                int[] results = insertStmt.executeBatch();
                return Arrays.stream(results).allMatch(r -> r == 1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to update copies for book: " + bookID, e);
        }
        return false;
    }

    private String generateSequentialCopyID(String bookID, int index) {
        return String.format("%s-C%03d", bookID, index);
    }

    private String generateCopyID(String bookID) {
        return bookID + "-C" + String.format("%03d", (int) (Math.random() * 1000));
    }
    
    private String generateCopyID() {
        return "CP" + System.currentTimeMillis();
    }

    public boolean updateCopyStatus(String copyId, String newStatus) {
        String sql = "UPDATE BookCopies SET status = ? WHERE copyID = ?";
        try (Connection conn = DatabaseConnection.getConnection();  // Use centralized connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, newStatus);
            pstmt.setString(2, copyId);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating copy status: " + e.getMessage(), e);
            return false;
        }
    }
}