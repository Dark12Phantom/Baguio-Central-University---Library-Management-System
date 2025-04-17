package com.bcu.bculms.backend;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class BooksTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Book ID", "Title", "Author", "Publication Date", "Department", "Borrow Status", "Date Borrowed", "Book Copies"};
    private List<Book> books;

    public BooksTableModel(List<Book> books) {
        this.books = books;
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = books.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return book.getBookID();
            case 1:
                return book.getTitle();
            case 2:
                return book.getAuthor();
            case 3:
                return book.getPublicationDate();
            case 4:
                return book.getDepartment();
            case 5:
                return book.getBorrowStatus();
            case 6:
                return book.getBookCopies();
            default:
                return null;
        }
    }
}
