package com.bcu.bculms.backend.bookmanagement;

import com.bcu.bculms.backend.DatabaseHelper;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Add this import at the top
    import com.toedter.calendar.JDateChooser;
    import java.util.Date;
    import java.text.SimpleDateFormat;

    public class AddBookDialog extends JDialog {
        private final JTextField titleField;
        private final JTextField authorField;
        private final JDateChooser pubDateChooser;  // Changed from JTextField
        private final JComboBox<String> deptComboBox;
        private final DatabaseHelper dbHelper;
        private boolean confirmed = false;

        public AddBookDialog(Frame parent) {
            super(parent, "Add New Book", true);
            dbHelper = new DatabaseHelper();
    
            // Set background colors
            Color bgColor = new Color(102, 0, 0);
            getContentPane().setBackground(bgColor);

            // Create components
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(bgColor);
            panel.setForeground(Color.WHITE);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;
    
            // Set label colors
            JLabel titleLabel = new JLabel("Title:");
            titleLabel.setForeground(Color.WHITE);
            JLabel authorLabel = new JLabel("Author:");
            authorLabel.setForeground(Color.WHITE);
            JLabel dateLabel = new JLabel("Publication Date:");
            dateLabel.setForeground(Color.WHITE);
            JLabel deptLabel = new JLabel("Department:");
            deptLabel.setForeground(Color.WHITE);
    
            // Title
            gbc.gridx = 0; gbc.gridy = 0;
            panel.add(titleLabel, gbc);
            gbc.gridx = 1;
            titleField = new JTextField(30);
            titleField.setBackground(new Color(153, 153, 0));
            titleField.setForeground(Color.WHITE);
            panel.add(titleField, gbc);
    
            // Author
            gbc.gridx = 0; gbc.gridy = 1;
            panel.add(authorLabel, gbc);
            gbc.gridx = 1;
            authorField = new JTextField(30);
            authorField.setBackground(new Color(153, 153, 0));
            authorField.setForeground(Color.WHITE);
            panel.add(authorField, gbc);
    
            // Publication Date
            gbc.gridx = 0; gbc.gridy = 2;
            panel.add(dateLabel, gbc);
            gbc.gridx = 1;
            pubDateChooser = new JDateChooser();
            pubDateChooser.setDateFormatString("yyyy-MM-dd");
            pubDateChooser.setBackground(new Color(153, 153, 0));
            pubDateChooser.setForeground(Color.WHITE);
            panel.add(pubDateChooser, gbc);
    
            // Department
            gbc.gridx = 0; gbc.gridy = 3;
            panel.add(deptLabel, gbc);
            gbc.gridx = 1;
            String[] departments = {
                "CBA", "CTELA", "CHTM", "CoE", "CCJE", 
                "CNSM", "SHS", "JHS", "GS", "ES"
            };
            deptComboBox = new JComboBox<>(departments);
            deptComboBox.setBackground(new Color(153, 153, 0));
            deptComboBox.setForeground(Color.WHITE);
            panel.add(deptComboBox, gbc);
    
            // Buttons
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(bgColor);
            JButton confirmButton = new JButton("Add Book");
            JButton cancelButton = new JButton("Cancel");
    
            confirmButton.addActionListener(e -> {
                if (validateInputs()) {
                    confirmed = true;
                    dispose();
                }
            });
    
            cancelButton.addActionListener(e -> dispose());
    
            buttonPanel.add(confirmButton);
            buttonPanel.add(cancelButton);
    
            // Add panels to dialog
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
    
            // Validate department
            String selectedDept = deptComboBox.getSelectedItem().toString();
            if (selectedDept == null || selectedDept.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a valid department code.");
                return false;
            }
    
            return true;
        }
    
        public boolean addBook() {
            if (!confirmed) return false;
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String pubDate = sdf.format(pubDateChooser.getDate());
            
            return dbHelper.addBook(
                titleField.getText().trim(),
                authorField.getText().trim(),
                pubDate,
                deptComboBox.getSelectedItem().toString()
            );
        }
    }