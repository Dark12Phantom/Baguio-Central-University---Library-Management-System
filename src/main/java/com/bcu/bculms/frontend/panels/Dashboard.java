package com.bcu.bculms.frontend.panels;

// Dashboard tab

import com.bcu.bculms.backend.DatabaseHelper;


public class Dashboard extends javax.swing.JPanel {

    // Add these outside the generated code block
    private void loadStatistics() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        try {
            totalBooksValue.setText(String.valueOf(dbHelper.getBookCopies(null).size()));
            borrowedBooksValue.setText(String.valueOf(dbHelper.getBookCopies(DatabaseHelper.STATUS_BORROWED).size()));
            availableBooksValue.setText(String.valueOf(dbHelper.getBookCopies(DatabaseHelper.STATUS_AVAILABLE).size()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Add to constructor:
    public Dashboard() {
        initComponents();
        loadStatistics();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        totalBooksPanel = new javax.swing.JPanel();
        totalBooksValue = new javax.swing.JLabel();
        borrowedBooksPanel = new javax.swing.JPanel();
        borrowedBooksValue = new javax.swing.JLabel();
        availableBooksPanel = new javax.swing.JPanel();
        availableBooksValue = new javax.swing.JLabel();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        totalBooksPanel.setBackground(new java.awt.Color(102, 0, 0));
        totalBooksPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Books", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(204, 204, 0))); // NOI18N
        totalBooksPanel.setLayout(new java.awt.GridBagLayout());

        totalBooksValue.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        totalBooksValue.setForeground(new java.awt.Color(204, 204, 0));
        totalBooksValue.setText("jLabel1");
        totalBooksPanel.add(totalBooksValue, new java.awt.GridBagConstraints());

        add(totalBooksPanel);

        borrowedBooksPanel.setBackground(new java.awt.Color(105, 0, 0));
        borrowedBooksPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Borrowed Books", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(204, 204, 0))); // NOI18N
        borrowedBooksPanel.setLayout(new java.awt.GridBagLayout());

        borrowedBooksValue.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        borrowedBooksValue.setForeground(new java.awt.Color(204, 204, 0));
        borrowedBooksValue.setText("jLabel2");
        borrowedBooksPanel.add(borrowedBooksValue, new java.awt.GridBagConstraints());

        add(borrowedBooksPanel);

        availableBooksPanel.setBackground(new java.awt.Color(102, 0, 0));
        availableBooksPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Available Books", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 36), new java.awt.Color(204, 204, 0))); // NOI18N
        availableBooksPanel.setLayout(new java.awt.GridBagLayout());

        availableBooksValue.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        availableBooksValue.setForeground(new java.awt.Color(204, 204, 0));
        availableBooksValue.setText("jLabel3");
        availableBooksPanel.add(availableBooksValue, new java.awt.GridBagConstraints());

        add(availableBooksPanel);
    }// </editor-fold>//GEN-END:initComponents

    
    // Will add summaries for book borrow/return and book total.

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel availableBooksPanel;
    private javax.swing.JLabel availableBooksValue;
    private javax.swing.JPanel borrowedBooksPanel;
    private javax.swing.JLabel borrowedBooksValue;
    private javax.swing.JPanel totalBooksPanel;
    private javax.swing.JLabel totalBooksValue;
    // End of variables declaration//GEN-END:variables
}
