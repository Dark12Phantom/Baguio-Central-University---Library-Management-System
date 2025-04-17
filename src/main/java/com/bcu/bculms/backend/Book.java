package com.bcu.bculms.backend;

public class Book {
    private String bookID;
    private String title;
    private String author;
    private String publicationDate;
    private String department;
    private String borrowStatus;
    private int bookCopies;
    
    public Book(String bookID, String title, String author, String publicationDate, String department, String borrowStatus, int bookCopies){
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.department = department;
        this.borrowStatus = borrowStatus;
        this.bookCopies = bookCopies;
    }

    public String getBookID() {
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

    public int getBookCopies() {
        return bookCopies;
    }

    public void setBookID(String bookID) {
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

    public void setBookCopies(int bookCopies) {
        this.bookCopies = bookCopies;
    }
}
