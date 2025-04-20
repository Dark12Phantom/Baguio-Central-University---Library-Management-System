package com.bcu.bculms.backend;

public class BookCopy {
    private String copyID;
    private String bookID;
    private String status; // 'Available', 'Borrowed', 'Lost', 'Damaged'
    private String purchaseDate;
    private String title; // Optional: for displaying book title without extra query

    public BookCopy(String copyID, String bookID, String status, String purchaseDate) {
        this.copyID = copyID;
        this.bookID = bookID;
        this.status = status;
        this.purchaseDate = purchaseDate;
    }

    // Overloaded constructor with title
    public BookCopy(String copyID, String bookID, String status, String purchaseDate, String title) {
        this(copyID, bookID, status, purchaseDate);
        this.title = title;
    }

    // Getters and Setters
    public String getCopyID() {
        return copyID;
    }

    public String getBookID() {
        return bookID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "BookCopy{" +
                "copyID='" + copyID + '\'' +
                ", bookID='" + bookID + '\'' +
                ", status='" + status + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                (title != null ? ", title='" + title + '\'' : "") +
                '}';
    }
}