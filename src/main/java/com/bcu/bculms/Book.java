package com.bcu.bculms;

public class Book {
    private int bookID;
    private String title;
    private String author;
    private String publicationDate;
    private String department;
    private String borrowStatus;
    private String dateBorrowed;
    
    public Book(int bookID, String title, String author, String publicationDate, String department, String borrowStatus, String dateBorrowed){
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.department = department;
        this.borrowStatus = borrowStatus;
        this.dateBorrowed = dateBorrowed;
    }

    public int getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getDepartment() {
        return department;
    }

    public String getBorrowStatus() {
        return borrowStatus;
    }

    public String getDateBorrowed() {
        return dateBorrowed;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setBorrowStatus(String borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }
}
