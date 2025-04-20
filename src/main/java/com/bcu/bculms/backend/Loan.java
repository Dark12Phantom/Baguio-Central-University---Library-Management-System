package com.bcu.bculms.backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Loan {
    private String loanID;
    private String copyID;
    private String bookTitle;
    private String borrowerID;
    private String borrowerName;
    private String borrowDate;
    private String dueDate;
    private String returnDate;

    public Loan(String loanID, String copyID, String bookTitle, String borrowerID, 
                String borrowerName, String borrowDate, String dueDate, String returnDate) {
        this.loanID = loanID;
        this.copyID = copyID;
        this.bookTitle = bookTitle;
        this.borrowerID = borrowerID;
        this.borrowerName = borrowerName;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    // Getters
    public String getLoanID() { return loanID; }
    public String getCopyID() { return copyID; }
    public String getBookTitle() { return bookTitle; }
    public String getBorrowerID() { return borrowerID; }
    public String getBorrowerName() { return borrowerName; }
    public String getBorrowDate() { return borrowDate; }
    public String getDueDate() { return dueDate; }
    public String getReturnDate() { return returnDate; }
    
    public String getStatus() {
        return returnDate == null ? "Borrowed" : "Returned";
    }
}