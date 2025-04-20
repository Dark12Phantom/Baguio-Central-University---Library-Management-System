package com.bcu.bculms.backend.bookmanagement;

import com.bcu.bculms.backend.Loan;
import com.bcu.bculms.backend.DatabaseHelper;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReturnBookDialog extends JDialog {
    private final Loan loan;
    private final DatabaseHelper dbHelper;
    private boolean confirmed = false;

    public ReturnBookDialog(Frame parent, Loan loan) {
        super(parent, "Return Book", true);
        this.loan = loan;
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

        // Labels
        JLabel copyIdLabel = new JLabel("Copy ID:");
        JLabel borrowerLabel = new JLabel("Borrower ID:");
        JLabel dueDateLabel = new JLabel("Due Date:");
        JLabel returnDateLabel = new JLabel("Return Date:");
        
        copyIdLabel.setForeground(Color.WHITE);
        borrowerLabel.setForeground(Color.WHITE);
        dueDateLabel.setForeground(Color.WHITE);
        returnDateLabel.setForeground(Color.WHITE);

        // Fields
        JTextField copyIdField = new JTextField(loan.getCopyID());
        JTextField borrowerField = new JTextField(loan.getBorrowerID());
        JTextField dueDateField = new JTextField(loan.getDueDate());
        JTextField returnDateField = new JTextField(
            LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));

        // Set field properties
        copyIdField.setEditable(false);
        borrowerField.setEditable(false);
        dueDateField.setEditable(false);
        returnDateField.setEditable(false);

        // Set colors for fields
        copyIdField.setBackground(fieldColor);
        borrowerField.setBackground(fieldColor);
        dueDateField.setBackground(fieldColor);
        returnDateField.setBackground(fieldColor);
        
        copyIdField.setForeground(Color.WHITE);
        borrowerField.setForeground(Color.WHITE);
        dueDateField.setForeground(Color.WHITE);
        returnDateField.setForeground(Color.WHITE);

        // Add components to panel
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(copyIdLabel, gbc);
        gbc.gridx = 1;
        panel.add(copyIdField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(borrowerLabel, gbc);
        gbc.gridx = 1;
        panel.add(borrowerField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(dueDateLabel, gbc);
        gbc.gridx = 1;
        panel.add(dueDateField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(returnDateLabel, gbc);
        gbc.gridx = 1;
        panel.add(returnDateField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(bgColor);
        JButton confirmButton = new JButton("Confirm Return");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(e -> {
            confirmed = true;
            dispose();
        });

        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    public boolean showDialog() {
        setVisible(true);
        return confirmed;
    }

    public boolean returnBook() {
        if (!confirmed) return false;
        return dbHelper.returnBook(loan.getCopyID());
    }
}
