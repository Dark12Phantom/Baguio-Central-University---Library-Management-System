package com.bcu.bculms;

// Bookshelf tab
import com.bcu.bculms.Book;
import com.bcu.bculms.DatabaseHelper;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.JTableHeader;

public class Bookshelf extends JPanel {

    private DatabaseHelper dbHelper;

    public Bookshelf() {
        initComponents();
        styleTable();
        loadBooks();
        
        if(!java.beans.Beans.isDesignTime()){
            initializeData();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        refreshBooks = new javax.swing.JButton();
        addBook = new javax.swing.JButton();
        setBorrow = new javax.swing.JButton();
        setBorrow1 = new javax.swing.JButton();
        removeBook = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setForeground(new java.awt.Color(102, 0, 0));
        setLayout(new java.awt.BorderLayout());

        jTable1.setBackground(new java.awt.Color(102, 0, 0));
        jTable1.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Book ID", "Title", "Author", "Publication Date", "Department", "Borrow Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setPreferredSize(new java.awt.Dimension(980, 688));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
        }

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setMinimumSize(new java.awt.Dimension(658, 40));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(980, 40));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 15, 5);
        flowLayout1.setAlignOnBaseline(true);
        jPanel1.setLayout(flowLayout1);

        refreshBooks.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        refreshBooks.setText("Refresh List");
        refreshBooks.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refreshBooks.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        refreshBooks.setPreferredSize(new java.awt.Dimension(140, 30));
        refreshBooks.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        refreshBooks.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        refreshBooks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBooksActionPerformed(evt);
            }
        });
        jPanel1.add(refreshBooks);

        addBook.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        addBook.setText("Add Book");
        addBook.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addBook.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addBook.setPreferredSize(new java.awt.Dimension(110, 30));
        addBook.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        addBook.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookActionPerformed(evt);
            }
        });
        jPanel1.add(addBook);

        setBorrow.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        setBorrow.setText("Set Borrowed Book");
        setBorrow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setBorrow.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        setBorrow.setPreferredSize(new java.awt.Dimension(175, 30));
        setBorrow.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        setBorrow.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        setBorrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setBorrowActionPerformed(evt);
            }
        });
        jPanel1.add(setBorrow);

        setBorrow1.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        setBorrow1.setText("Set Returned Book");
        setBorrow1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setBorrow1.setPreferredSize(new java.awt.Dimension(190, 30));
        setBorrow1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        setBorrow1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        setBorrow1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setBorrow1ActionPerformed(evt);
            }
        });
        jPanel1.add(setBorrow1);

        removeBook.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        removeBook.setText("Remove Book");
        removeBook.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        removeBook.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeBook.setPreferredSize(new java.awt.Dimension(125, 30));
        removeBook.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        removeBook.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        removeBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBookActionPerformed(evt);
            }
        });
        jPanel1.add(removeBook);

        add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(980, 70));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 980, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void setBorrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setBorrowActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_setBorrowActionPerformed

    private void addBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookActionPerformed
        AddBookDialog addBookDialog = new AddBookDialog((JFrame) SwingUtilities.getWindowAncestor(this));
        addBookDialog.setVisible(true);
        loadBooks();
    }//GEN-LAST:event_addBookActionPerformed

    private void setBorrow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setBorrow1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_setBorrow1ActionPerformed

    private void removeBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBookActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeBookActionPerformed

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered

    }//GEN-LAST:event_jTable1MouseEntered

    private void refreshBooksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBooksActionPerformed
        loadBooks();
    }//GEN-LAST:event_refreshBooksActionPerformed
    private boolean initialized = false;
    
    public void initializeData() {
        if (!initialized) {
            try {
                // Test connection first
                Connection testConn = DatabaseConnection.getConnection();
                if (testConn != null && !testConn.isClosed()) {
                    initialized = true;
                    loadBooks();
                    testConn.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Database initialization failed: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void styleTable() {
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(100);
        jTable1.setRowHeight(30);
        jTable1.setShowGrid(false);
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    comp.setBackground(new Color(170, 0, 0));
                } else {
                    if (row % 2 == 0) {
                        comp.setBackground(new Color(132, 3, 3));
                    } else {
                        comp.setBackground(new Color(132, 3, 3, 50));
                    }
                }
                comp.setForeground(Color.WHITE);
                return comp;
            }
        };
        JTableHeader header = jTable1.getTableHeader();
        header.setBackground(new Color(153,153,0));
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Monospaced", Font.BOLD, 14));
        header.setBorder(BorderFactory.createEmptyBorder());
        jTable1.setDefaultRenderer(Object.class, renderer);
        jTable1.setDefaultRenderer(Integer.class, renderer);
    }

    private void loadBooks() {
        if(!initialized) return;
        
        List<Book> books = DatabaseHelper.getAllBooks();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        for (Book book : books) {
            model.addRow(new Object[]{
                book.getBookID(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationDate(),
                book.getDepartment(),
                book.getBorrowStatus()
            });
        }
    }
    
    private void styleButtons(){
        
    }

    //Will add a table of detailed list for the books inside the library and who borrowed it

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBook;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton refreshBooks;
    private javax.swing.JButton removeBook;
    private javax.swing.JButton setBorrow;
    private javax.swing.JButton setBorrow1;
    // End of variables declaration//GEN-END:variables
}
