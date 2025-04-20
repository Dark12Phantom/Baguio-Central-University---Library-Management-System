package com.bcu.bculms.backend.bookmanagement;

import com.bcu.bculms.backend.Loan;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LoansTableModel extends AbstractTableModel {
    private final List<Loan> loans;
    // Match column count with BooksTableModel pattern
    private final String[] columnNames = {
        "Loan ID", "Copy ID", "Book Title", "Borrower", 
        "Borrowed", "Due", "Returned", "Status"
    };

    public LoansTableModel(List<Loan> loans) {
        this.loans = loans != null ? loans : new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return loans.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Loan loan = loans.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> loan.getLoanID();
            case 1 -> loan.getCopyID();
            case 2 -> loan.getBookTitle();
            case 3 -> String.format("%s - %s", loan.getBorrowerID(), loan.getBorrowerName());
            case 4 -> loan.getBorrowDate();
            case 5 -> loan.getDueDate();
            case 6 -> loan.getReturnDate();
            case 7 -> loan.getReturnDate() == null ? "Active" : "Completed";
            default -> null;
        };
    }
    
    public Loan getLoanAt(int row) {
        return loans.get(row);
    }
}