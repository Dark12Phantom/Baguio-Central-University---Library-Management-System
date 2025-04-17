package com.bcu.bculms.backend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AddBookDialog extends JDialog {
    private JTextField titleField;
    private JTextField authorField;
    private JTextField pubDateField;
    private JComboBox<String> deptBox;
    private JTextField bookCopiesField;
    private JButton addButton;
    private JButton cancelButton;
    
    public AddBookDialog(JFrame parent) {
        super(parent, "Add Book", true);
        initializeUI(parent);
        setupEventHandlers();
    }

    private void initializeUI(JFrame parent) {
        
        getContentPane().setBackground(new Color(153,153,0));
        
        setLayout(new GridLayout(7, 2, 10, 10));
        setSize(400, 400);
        setLocationRelativeTo(parent);
        
        // Initialize components
        titleField = createStyledTextField();
        authorField = createStyledTextField();
        pubDateField = createStyledTextField();
        
        deptBox = new JComboBox<>(new String[]{
            "CBA", "CTELA", "CHTM", "CoE", "CCJE", "CNSM", "SHS", "JHS", "ES"
        });
        styleComboBox(deptBox);
        bookCopiesField = createStyledTextField();
        addButton = createStyledButton("Add");
        cancelButton = createStyledButton("Cancel");
        
        JLabel title = new JLabel("Title:");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        JLabel author = new JLabel("Author:");
        author.setForeground(Color.WHITE);
        author.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        JLabel pubDate = new JLabel("Publication Date:");
        pubDate.setForeground(Color.WHITE);
        pubDate.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        JLabel dept = new JLabel("Department:");
        dept.setForeground(Color.WHITE);
        dept.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        JLabel bookCopies = new JLabel("Book Copies");
        bookCopies.setForeground(Color.WHITE);
        bookCopies.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        // Add components to dialog
        add(title);
        add(titleField);
        add(author);
        add(authorField);
        add(pubDate);
        add(pubDateField);
        add(dept);
        add(deptBox);
        add(bookCopies);
        add(bookCopiesField);
        add(addButton);
        add(cancelButton);
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setBackground(new Color(133, 7, 7));
        field.setForeground(Color.WHITE);
        field.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        field.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        return field;
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setBackground(new Color(133, 7, 7));
        comboBox.setForeground(Color.WHITE);
        comboBox.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBackground(new Color(133, 7, 7));
                setForeground(Color.WHITE);
                return this;
            }
        });
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(133, 7, 7));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.setFocusPainted(false);
        return button;
    }

    private void setupEventHandlers() {
        addButton.addActionListener(this::handleAddBook);
        cancelButton.addActionListener(e -> dispose());
    }

    private void handleAddBook(ActionEvent e) {
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        String pubDate = pubDateField.getText().trim();
        String dept = (String) deptBox.getSelectedItem();
        int bookCopies;

        if (title.isEmpty() || author.isEmpty() || pubDate.isEmpty()) {
            showError("All fields must be filled!");
            return;
        }
        
        try{
            bookCopies = Integer.parseInt(bookCopiesField.getText().trim());
            if(bookCopies <= 0){
                showError("Number of Copies must be a positive integer/ must not be zero");
                return;
            }
        }catch(NumberFormatException ex){
            showError("Invalid number format for copies");
            return;
        }

        boolean success = new DatabaseHelper().addBook(title, author, pubDate, dept, bookCopies);
        if (success) {
            showSuccess("Book added successfully!");
            dispose();
        } else {
            showError("Failed to add the book. Please try again.");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}