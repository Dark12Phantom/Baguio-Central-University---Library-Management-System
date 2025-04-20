package com.bcu.bculms.backend.bookmanagement;

import com.bcu.bculms.backend.Book;
import com.bcu.bculms.backend.BookCopy;
import com.bcu.bculms.backend.DatabaseHelper;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class BooksTableModel extends AbstractTableModel {

    private final String[] columnNames = {
        "Book ID", "Title", "Author", "Publication Date", "Department", "Available Copies"
    };
    private List<Book> books;
    private DatabaseHelper dbHelper;

    public BooksTableModel(List<Book> books) {
        this.books = books;
        this.dbHelper = new DatabaseHelper();
    }

    public void updateBooks(List<Book> newBooks) {
        this.books = newBooks;
        fireTableDataChanged();
    }

    public Book getBookAt(int rowIndex) {
        return books.get(rowIndex);
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
                List<BookCopy> copies = dbHelper.getCopiesByBookId(book.getBookID());
                return copies.stream()
                    .filter(copy -> DatabaseHelper.STATUS_AVAILABLE.equals(copy.getStatus()))
                    .count();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 5:  // Available Copies column
                return Long.class;
            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;  // Make all cells non-editable
    }
}
