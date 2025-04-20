package com.bcu.bculms.backend.bookmanagement;

import com.bcu.bculms.backend.BookCopy;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class CopiesTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Copy ID", "Book ID", "Title", "Status", "Purchase Date"};
    private List<BookCopy> copies;

    public CopiesTableModel(List<BookCopy> copies) {
        this.copies = copies;
    }

    @Override
    public int getRowCount() {
        return copies.size();
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
        BookCopy copy = copies.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return copy.getCopyID();
            case 1:
                return copy.getBookID();
            case 2:
                return copy.getTitle();
            case 3:
                return copy.getStatus();
            case 4:
                return copy.getPurchaseDate();
            default:
                return null;
        }
    }

    public BookCopy getCopyAt(int rowIndex) {
        return copies.get(rowIndex);
    }
}