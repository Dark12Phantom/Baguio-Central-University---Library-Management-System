package com.bcu.bculms.backend.bookmanagement;

import com.bcu.bculms.backend.BookCopy;
import com.bcu.bculms.backend.DatabaseHelper;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Add import
    import com.toedter.calendar.JDateChooser;
    import java.util.Date;
    import java.text.SimpleDateFormat;

    public class BorrowBookDialog extends JDialog {
        private final JTextField borrowerIdField;
        private final JDateChooser dueDateChooser;  // Changed from JTextField
        private final DatabaseHelper dbHelper;
        private final BookCopy bookCopy;
        private boolean confirmed = false;

        public BorrowBookDialog(Frame parent, BookCopy bookCopy) {
            super(parent, "Borrow Book", true);
            this.bookCopy = bookCopy;
            dbHelper = new DatabaseHelper();

            // Set colors
            Color bgColor = new Color(102, 0, 0);
            Color fieldColor = new Color(153, 153, 0);
            getContentPane().setBackground(bgColor);

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(bgColor);
            panel.setForeground(Color.WHITE);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Labels with white text
            JLabel copyIdLabel = new JLabel("Copy ID:");
            JLabel titleLabel = new JLabel("Book Title:");
            JLabel borrowerLabel = new JLabel("Borrower ID:");
            JLabel dueDateLabel = new JLabel("Due Date:");
            copyIdLabel.setForeground(Color.WHITE);
            titleLabel.setForeground(Color.WHITE);
            borrowerLabel.setForeground(Color.WHITE);
            dueDateLabel.setForeground(Color.WHITE);

            // Copy details
            gbc.gridx = 0; gbc.gridy = 0;
            panel.add(copyIdLabel, gbc);
            gbc.gridx = 1;
            JTextField copyIdField = new JTextField(bookCopy.getCopyID());
            copyIdField.setEditable(false);
            copyIdField.setBackground(fieldColor);
            copyIdField.setForeground(Color.WHITE);
            panel.add(copyIdField, gbc);

            // Book Title
            gbc.gridx = 0; gbc.gridy = 1;
            panel.add(titleLabel, gbc);
            gbc.gridx = 1;
            JTextField titleField = new JTextField(bookCopy.getTitle());
            titleField.setEditable(false);
            titleField.setBackground(fieldColor);
            titleField.setForeground(Color.WHITE);
            panel.add(titleField, gbc);

            // Borrower ID
            gbc.gridx = 0; gbc.gridy = 2;
            panel.add(borrowerLabel, gbc);
            gbc.gridx = 1;
            borrowerIdField = new JTextField(20);
            borrowerIdField.setBackground(fieldColor);
            borrowerIdField.setForeground(Color.WHITE);
            panel.add(borrowerIdField, gbc);

            // Due Date
            gbc.gridx = 0; gbc.gridy = 3;
            panel.add(dueDateLabel, gbc);
            gbc.gridx = 1;
            dueDateChooser = new JDateChooser();
            dueDateChooser.setDateFormatString("yyyy-MM-dd");
            dueDateChooser.setDate(java.sql.Date.valueOf(LocalDate.now().plusDays(14)));
            dueDateChooser.setBackground(fieldColor);
            dueDateChooser.setForeground(Color.WHITE);
            panel.add(dueDateChooser, gbc);

            // Buttons
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(bgColor);
            JButton borrowButton = new JButton("Borrow");
            JButton cancelButton = new JButton("Cancel");

        borrowButton.addActionListener(e -> {
            if (validateInputs()) {
                confirmed = true;
                dispose();
            }
        });

        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(borrowButton);
        buttonPanel.add(cancelButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    private boolean validateInputs() {
        if (borrowerIdField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a borrower ID.");
            return false;
        }

        if (dueDateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please select a due date.");
            return false;
        }

        return true;
    }

    public boolean showDialog() {
        setVisible(true);
        return confirmed;
    }

    public boolean borrowBook() {
        if (!confirmed) return false;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dueDate = sdf.format(dueDateChooser.getDate());
        
        return dbHelper.createLoan(
            bookCopy.getCopyID(),
            borrowerIdField.getText().trim(),
            LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE),
            dueDate
        );
    }
}