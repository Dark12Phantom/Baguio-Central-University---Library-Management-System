package com.bcu.bculms.backend.bookmanagement;

import com.bcu.bculms.backend.Book;
import com.bcu.bculms.backend.DatabaseHelper;
import javax.swing.*;
import java.awt.*;

public class RemoveBookDialog extends JDialog {
    private final Book book;
    private final DatabaseHelper dbHelper;
    private boolean confirmed = false;

    public RemoveBookDialog(Frame parent, Book book) {
        super(parent, "Remove Book", true);
        this.book = book;
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
        JLabel bookIdLabel = new JLabel("Book ID:");
        JLabel titleLabel = new JLabel("Title:");
        JLabel authorLabel = new JLabel("Author:");
        JLabel warningLabel = new JLabel("Are you sure you want to remove this book?");
        bookIdLabel.setForeground(Color.WHITE);
        titleLabel.setForeground(Color.WHITE);
        authorLabel.setForeground(Color.WHITE);
        warningLabel.setForeground(Color.WHITE);

        // Book ID (non-editable)
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(bookIdLabel, gbc);
        gbc.gridx = 1;
        JTextField bookIdField = new JTextField(book.getBookID());
        bookIdField.setEditable(false);
        bookIdField.setBackground(fieldColor);
        bookIdField.setForeground(Color.WHITE);
        panel.add(bookIdField, gbc);

        // Title (non-editable)
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(titleLabel, gbc);
        gbc.gridx = 1;
        JTextField titleField = new JTextField(book.getTitle());
        titleField.setEditable(false);
        titleField.setBackground(fieldColor);
        titleField.setForeground(Color.WHITE);
        panel.add(titleField, gbc);

        // Author (non-editable)
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(authorLabel, gbc);
        gbc.gridx = 1;
        JTextField authorField = new JTextField(book.getAuthor());
        authorField.setEditable(false);
        authorField.setBackground(fieldColor);
        authorField.setForeground(Color.WHITE);
        panel.add(authorField, gbc);

        // Warning message
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(warningLabel, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(bgColor);
        JButton removeButton = new JButton("Remove");
        JButton cancelButton = new JButton("Cancel");

        removeButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                this,
                "This action cannot be undone. Continue?",
                "Confirm Removal",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            
            if (choice == JOptionPane.YES_OPTION) {
                confirmed = true;
                dispose();
            }
        });

        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(removeButton);
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

    public boolean removeBook() {
        if (!confirmed) return false;
        return dbHelper.removeBook(book.getBookID());
    }
}