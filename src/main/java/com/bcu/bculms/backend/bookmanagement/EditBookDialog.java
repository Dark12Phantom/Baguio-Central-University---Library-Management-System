package com.bcu.bculms.backend.bookmanagement;

import com.bcu.bculms.backend.Book;
import com.bcu.bculms.backend.DatabaseHelper;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

// Add import
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EditBookDialog extends JDialog {

    private final JTextField titleField;
    private final JTextField authorField;
    private final JDateChooser pubDateChooser;
    private final JComboBox<String> deptComboBox;
    private final JSpinner copiesSpinner;  // Add this field
    private final DatabaseHelper dbHelper;
    private final Book book;
    private boolean confirmed = false;

    public EditBookDialog(Frame parent, Book book) {
        super(parent, "Edit Book", true);
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
        JLabel dateLabel = new JLabel("Publication Date:");
        JLabel deptLabel = new JLabel("Department:");
        bookIdLabel.setForeground(Color.WHITE);
        titleLabel.setForeground(Color.WHITE);
        authorLabel.setForeground(Color.WHITE);
        dateLabel.setForeground(Color.WHITE);
        deptLabel.setForeground(Color.WHITE);

        // Book ID (non-editable)
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(bookIdLabel, gbc);
        gbc.gridx = 1;
        JTextField bookIdField = new JTextField(book.getBookID());
        bookIdField.setEditable(false);
        bookIdField.setBackground(fieldColor);
        bookIdField.setForeground(Color.WHITE);
        panel.add(bookIdField, gbc);

        // Title
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(titleLabel, gbc);
        gbc.gridx = 1;
        titleField = new JTextField(book.getTitle(), 30);
        titleField.setBackground(fieldColor);
        titleField.setForeground(Color.WHITE);
        panel.add(titleField, gbc);

        // Author
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(authorLabel, gbc);
        gbc.gridx = 1;
        authorField = new JTextField(book.getAuthor(), 30);
        authorField.setBackground(fieldColor);
        authorField.setForeground(Color.WHITE);
        panel.add(authorField, gbc);

        // Publication Date
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(dateLabel, gbc);
        gbc.gridx = 1;
        pubDateChooser = new JDateChooser();
        pubDateChooser.setDateFormatString("yyyy-MM-dd");
        try {
            pubDateChooser.setDate(java.sql.Date.valueOf(book.getPublicationDate()));
        } catch (Exception e) {
            pubDateChooser.setDate(new Date());
        }
        pubDateChooser.setBackground(fieldColor);
        pubDateChooser.setForeground(Color.WHITE);
        panel.add(pubDateChooser, gbc);

        // Department
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(deptLabel, gbc);
        gbc.gridx = 1;
        String[] departments = {
            "CBA", "CTELA", "CHTM", "CoE", "CCJE",
            "CNSM", "SHS", "JHS", "GS", "ES"
        };
        deptComboBox = new JComboBox<>(departments);
        deptComboBox.setSelectedItem(book.getDepartment());
        deptComboBox.setBackground(fieldColor);
        deptComboBox.setForeground(Color.WHITE);
        panel.add(deptComboBox, gbc);

        // Add number of copies spinner
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel copiesLabel = new JLabel("Number of Copies:");
        copiesLabel.setForeground(Color.WHITE);
        panel.add(copiesLabel, gbc);

        gbc.gridx = 1;
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(
                dbHelper.getBookCopies(book.getBookID()).size(), // current value
                0, // minimum
                100, // maximum
                1 // step
        );
        copiesSpinner = new JSpinner(spinnerModel);  // Initialize the field instead of local variable
        JComponent editor = copiesSpinner.getEditor();
        JFormattedTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
        tf.setBackground(fieldColor);
        tf.setForeground(Color.WHITE);
        panel.add(copiesSpinner, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(bgColor);
        JButton saveButton = new JButton("Save Changes");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> {
            if (validateInputs()) {
                confirmed = true;
                updateBook();
                updateBookCopies();  // Add this line to update copies when saving
                dispose();
            }
        });

        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    private boolean validateInputs() {
        if (titleField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a title.");
            return false;
        }

        if (authorField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an author.");
            return false;
        }

        if (pubDateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please select a publication date.");
            return false;
        }

        return true;
    }
 
    // Change from private to public
    public boolean updateBook() {
        try {
            // Convert java.util.Date to formatted string
            java.util.Date pubDateUtil = pubDateChooser.getDate();
            String pubDate = new SimpleDateFormat("yyyy-MM-dd").format(pubDateUtil);
            
            return dbHelper.updateBook(
                book.getBookID(),
                titleField.getText(),
                authorField.getText(),
                pubDate,
                (String) deptComboBox.getSelectedItem()  // Use combo box instead of text field
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating book: " + e.getMessage(), 
                "Update Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Fix 2: Add proper error handling for copies update
    public boolean updateBookCopies() {
        if (!confirmed) {
            return false;
        }
        try {
            int targetCount = (Integer) copiesSpinner.getValue();
            // Get fresh count from database after any updates
            int currentCount = dbHelper.getBookCopies(book.getBookID()).size();
            return dbHelper.updateBookCopies(book.getBookID(), currentCount, targetCount);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Failed to update copies: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    private boolean isValidDepartment(String dept) {
        String[] validDepts = {
            "CBA", "CTELA", "CHTM", "CoE", "CCJE",
            "CNSM", "SHS", "JHS", "GS", "ES"
        };
        for (String validDept : validDepts) {
            if (validDept.equals(dept)) {
                return true;
            }
        }
        return false;
    }
}
