package com.bcu.bculms.frontend.panels;

import com.bcu.bculms.backend.Book;
import com.bcu.bculms.backend.BookCopy;
import com.bcu.bculms.backend.bookmanagement.BooksTableModel;
import com.bcu.bculms.backend.bookmanagement.CopiesTableModel;
import com.bcu.bculms.backend.DatabaseHelper;
import com.bcu.bculms.backend.Loan;
import com.bcu.bculms.backend.bookmanagement.LoansTableModel;
import com.bcu.bculms.backend.bookmanagement.AddBookDialog;
import com.bcu.bculms.backend.bookmanagement.BorrowBookDialog;
import com.bcu.bculms.backend.bookmanagement.EditBookDialog;
import com.bcu.bculms.backend.bookmanagement.RemoveBookDialog;
import com.bcu.bculms.backend.bookmanagement.ReturnBookDialog;
import java.awt.Frame;
import java.util.List;
import javax.swing.*;

public class Bookshelf extends JPanel {
    private DatabaseHelper dbHelper;
    private BooksTableModel booksTableModel;
    private CopiesTableModel copiesTableModel;

    public Bookshelf() {
        initComponents();
        initializeDatabase();
        
        jTabbedPane2.addChangeListener(e -> {
        });
    }

    private void refreshAllTables() {
        // Initialize Books Table
        List<Book> books = dbHelper.getAllBooks();
        booksTableModel = new BooksTableModel(books);
        jTable1.setModel(booksTableModel);

        // Add selection listener to Books table
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = jTable1.getSelectedRow();
                if (selectedRow != -1) {
                    Book selectedBook = booksTableModel.getBookAt(selectedRow);
                    // Update Copies table with copies of selected book
                    List<BookCopy> copies = dbHelper.getBookCopies(selectedBook.getBookID());
                    copiesTableModel = new CopiesTableModel(copies);
                    jTable2.setModel(copiesTableModel);
                }
            }
        });

        // Initialize Copies Table (initially empty or with all copies)
        List<BookCopy> copies = dbHelper.getBookCopies(null);
        copiesTableModel = new CopiesTableModel(copies);
        jTable2.setModel(copiesTableModel);
    }





    private boolean confirmDelete(String itemType, String identifier) {
        return JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this " + itemType + ": " + identifier + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        booksPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        copiesPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        buttonPanel = new javax.swing.JPanel();
        refreshBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        removeBtn = new javax.swing.JButton();
        borrowBtn = new javax.swing.JButton();
        returnBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 0, 0));
        setForeground(new java.awt.Color(102, 0, 0));
        setPreferredSize(new java.awt.Dimension(980, 720));
        setLayout(new java.awt.BorderLayout());

        jTabbedPane2.setBackground(new java.awt.Color(102, 0, 0));
        jTabbedPane2.setForeground(new java.awt.Color(153, 153, 0));
        jTabbedPane2.setPreferredSize(new java.awt.Dimension(980, 680));

        booksPanel.setBackground(new java.awt.Color(102, 0, 0));
        booksPanel.setForeground(new java.awt.Color(102, 0, 0));
        booksPanel.setLayout(new java.awt.BorderLayout());

        jScrollPane4.setBackground(new java.awt.Color(102, 0, 0));
        jScrollPane4.setForeground(new java.awt.Color(102, 0, 0));
        jScrollPane4.setOpaque(false);

        jTable1.setBackground(new java.awt.Color(102, 0, 0));
        jTable1.setFont(new java.awt.Font("Open Sans", 1, 16)); // NOI18N
        jTable1.setForeground(new java.awt.Color(153, 153, 0));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Book ID", "Title", "Author", "Publication Date", "Department"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setOpaque(false);
        jTable1.setRowHeight(25);
        jTable1.setRowMargin(5);
        jScrollPane4.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        booksPanel.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jTabbedPane2.addTab("Books", booksPanel);

        copiesPanel.setBackground(new java.awt.Color(102, 0, 0));
        copiesPanel.setForeground(new java.awt.Color(102, 0, 0));
        copiesPanel.setLayout(new java.awt.BorderLayout());

        jScrollPane5.setBackground(new java.awt.Color(102, 0, 0));
        jScrollPane5.setForeground(new java.awt.Color(102, 0, 0));
        jScrollPane5.setOpaque(false);

        jTable2.setBackground(new java.awt.Color(102, 0, 0));
        jTable2.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        jTable2.setForeground(new java.awt.Color(153, 153, 0));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Copy ID", "Book ID", "Titile", "Status", "Publication Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setOpaque(false);
        jScrollPane5.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
        }

        copiesPanel.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        jTabbedPane2.addTab("Copies", copiesPanel);

        add(jTabbedPane2, java.awt.BorderLayout.PAGE_START);

        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new java.awt.Dimension(980, 50));
        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 6));

        refreshBtn.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        refreshBtn.setText("Refresh List");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });
        buttonPanel.add(refreshBtn);

        addBtn.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        addBtn.setText("Add Book");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        buttonPanel.add(addBtn);

        editBtn.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        editBtn.setText("Edit Book");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        buttonPanel.add(editBtn);

        removeBtn.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        removeBtn.setText("Remove Book");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });
        buttonPanel.add(removeBtn);

        borrowBtn.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        borrowBtn.setText("Set Borrowed");
        borrowBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowBtnActionPerformed(evt);
            }
        });
        buttonPanel.add(borrowBtn);

        returnBtn.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        returnBtn.setText("Set Returned");
        returnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnBtnActionPerformed(evt);
            }
        });
        buttonPanel.add(returnBtn);

        add(buttonPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {
        refreshAllTables();
    }
    
    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedTab = jTabbedPane2.getSelectedIndex();
        
        switch (selectedTab) {
            case 0: // Books tab
                Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
                AddBookDialog dialog = new AddBookDialog(parentFrame);
                dialog.setVisible(true);
                if (dialog.addBook()) {
                    refreshAllTables();
                }
                break;
            case 1: // Copies tab
                // TODO: Implement add copy functionality
                break;
            case 2: // Loans tab
                // TODO: Implement add loan functionality
                break;
        }
    }

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedTab = jTabbedPane2.getSelectedIndex();
        int selectedRow = -1;

        switch (selectedTab) {
            case 0: // Books tab
                selectedRow = jTable1.getSelectedRow();
                if (selectedRow != -1) {
                    Book selectedBook = booksTableModel.getBookAt(selectedRow);
                    Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
                    EditBookDialog dialog = new EditBookDialog(parentFrame, selectedBook);
                    dialog.setVisible(true);
                    if (dialog.updateBook()) {
                        refreshAllTables();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Please select a book to edit.", 
                        "No Selection", 
                        JOptionPane.WARNING_MESSAGE);
                }
                break;
            case 1: // Copies tab
                // TODO: Implement copy editing
                break;
            case 2: // Loans tab
                // TODO: Implement loan editing
                break;
        }
    }
    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedTab = jTabbedPane2.getSelectedIndex();
        int selectedRow = -1;

        switch (selectedTab) {
            case 0: // Books tab
                selectedRow = jTable1.getSelectedRow();
                if (selectedRow != -1) {
                    Book selectedBook = booksTableModel.getBookAt(selectedRow);
                    Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
                    RemoveBookDialog dialog = new RemoveBookDialog(parentFrame, selectedBook);
                    if (dialog.showDialog() && dialog.removeBook()) {
                        refreshAllTables();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Please select a book to remove.", 
                        "No Selection", 
                        JOptionPane.WARNING_MESSAGE);
                }
                break;
            case 1: // Copies tab
                // TODO: Implement copy removal
                break;
            case 2: // Loans tab
                // TODO: Implement loan removal
                break;
        }
    }

    private void borrowBtnActionPerformed(java.awt.event.ActionEvent evt) {
        if (jTabbedPane2.getSelectedIndex() == 1) { // Copies tab
            int selectedRow = jTable2.getSelectedRow();
            if (selectedRow != -1) {
                BookCopy selectedCopy = copiesTableModel.getCopyAt(selectedRow);
                if ("Available".equals(selectedCopy.getStatus())) {
                    Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
                    BorrowBookDialog dialog = new BorrowBookDialog(parentFrame, selectedCopy);
                    if (dialog.showDialog() && dialog.borrowBook()) {
                        refreshAllTables();
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                        "This copy is not available for borrowing.",
                        "Copy Unavailable",
                        JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                    "Please select a book copy to borrow.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // Add this method to handle return button actions
    private void returnBtnActionPerformed(java.awt.event.ActionEvent evt) {
        if (jTabbedPane2.getSelectedIndex() == 1) { // Copies tab
            int selectedRow = jTable2.getSelectedRow();
            if (selectedRow != -1) {
                BookCopy selectedCopy = copiesTableModel.getCopyAt(selectedRow);
                if ("Borrowed".equals(selectedCopy.getStatus())) {
                    if (dbHelper.returnBook(selectedCopy.getCopyID())) {
                        refreshAllTables();
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                        "This copy is not currently borrowed.",
                        "Return Not Needed",
                        JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                    "Please select a borrowed copy to return.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void initializeDatabase() {
        dbHelper = new DatabaseHelper();
        refreshAllTables();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JPanel booksPanel;
    private javax.swing.JButton borrowBtn;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel copiesPanel;
    private javax.swing.JButton editBtn;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JButton removeBtn;
    private javax.swing.JButton returnBtn;
    // End of variables declaration//GEN-END:variables
}

