package com.bcu.bculms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddBookDialog extends JDialog {
    private JTextField titleField;
    private JTextField authorField;
    private JTextField pubDateField;
    private JComboBox<String> deptBox;
    private JButton addButton;
    private JButton cancelButton;
    DatabaseHelper dbHelper;

    public AddBookDialog(JFrame parent) {
        super(parent, "Add Book", true);
        setLayout(new GridLayout(6, 2, 10, 10));
        setSize(400, 300);
        setLocationRelativeTo(parent);
        dbHelper = new DatabaseHelper();

        // Fields
        titleField = new JTextField();
        authorField = new JTextField();
        pubDateField = new JTextField();
        deptBox = new JComboBox<>(new String[] {
            "CBA", "CTELA", "CHTM", "CoE", "CCJE", "CNSM", "SHS", "JHS", "ES"
        });

        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");

        add(new JLabel("Title:"));
        add(titleField);
        add(new JLabel("Author:"));
        add(authorField);
        add(new JLabel("Publication Date:"));
        add(pubDateField);
        add(new JLabel("Department:"));
        add(deptBox);
        add(addButton);
        add(cancelButton);

        // Button actions
        addButton.addActionListener(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            String pubDate = pubDateField.getText();
            String dept = (String) deptBox.getSelectedItem();

            if (title.isEmpty() || author.isEmpty() || pubDate.isEmpty() || dept.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Call the DatabaseHelper method to add the book to the database
            boolean success = dbHelper.addBook(title, author, pubDate, dept);

            if (success) {
                JOptionPane.showMessageDialog(this, "Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();  // Close the dialog
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add the book. Please try again.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dispose());
    }
    
}
