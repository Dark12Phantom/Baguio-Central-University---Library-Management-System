package com.bcu.bculms.frontend.containers;

import com.bcu.bculms.frontend.containers.MainContent;
import java.awt.Color;
import javax.swing.JLabel;

// The container for the left side of theapplication.
// This houses the navigation bar and application title

public class MenuContainer extends javax.swing.JPanel {

    private MainContent mC;
    
    public MenuContainer() {
        initComponents();
        setOpaque(false);
        init();
        selectedButton(home);
    }

    public void init() {
        appTitle.setOpaque(false);
        blank.setOpaque(false);
        btnSet1.setOpaque(false);
        btnSet2.setOpaque(false);
        // Initialize the Panel components containing the Navigation and app title
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        appTitle = new javax.swing.JPanel();
        appName = new javax.swing.JLabel();
        appSubname = new javax.swing.JLabel();
        btnSet1 = new javax.swing.JPanel();
        home = new javax.swing.JLabel();
        dashboard = new javax.swing.JLabel();
        btnSet2 = new javax.swing.JPanel();
        bookshelf = new javax.swing.JLabel();
        studentInformation = new javax.swing.JLabel();
        blank = new javax.swing.JPanel();

        setLayout(new java.awt.GridLayout(4, 1));

        appTitle.setOpaque(false);
        appTitle.setLayout(new java.awt.GridLayout(2, 1));

        appName.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        appName.setForeground(new java.awt.Color(153, 153, 0));
        appName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        appName.setText("BAGUIO CENTRAL UNIVERSITY");
        appName.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        appName.setAlignmentY(0.0F);
        appName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        appName.setMaximumSize(new java.awt.Dimension(300, 25));
        appName.setMinimumSize(new java.awt.Dimension(300, 25));
        appName.setPreferredSize(new java.awt.Dimension(300, 25));
        appTitle.add(appName);

        appSubname.setFont(new java.awt.Font("MV Boli", 1, 14)); // NOI18N
        appSubname.setForeground(new java.awt.Color(153, 153, 0));
        appSubname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        appSubname.setText("LIBRARY MANAGEMENT SYSTEM");
        appSubname.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        appSubname.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        appTitle.add(appSubname);

        add(appTitle);

        btnSet1.setOpaque(false);
        btnSet1.setLayout(new java.awt.GridLayout(2, 1, 3, 0));

        home.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 18)); // NOI18N
        home.setForeground(new java.awt.Color(153, 153, 0));
        home.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        home.setText("Home    ");
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                homeMousePressed(evt);
            }
        });
        btnSet1.add(home);

        dashboard.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 18)); // NOI18N
        dashboard.setForeground(new java.awt.Color(153, 153, 0));
        dashboard.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dashboard.setText("Dashboard    ");
        dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dashboardMousePressed(evt);
            }
        });
        btnSet1.add(dashboard);

        add(btnSet1);

        btnSet2.setOpaque(false);
        btnSet2.setLayout(new java.awt.GridLayout(2, 1));

        bookshelf.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 18)); // NOI18N
        bookshelf.setForeground(new java.awt.Color(153, 153, 0));
        bookshelf.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        bookshelf.setText("Bookshelf    ");
        bookshelf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bookshelfMousePressed(evt);
            }
        });
        btnSet2.add(bookshelf);

        studentInformation.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 18)); // NOI18N
        studentInformation.setForeground(new java.awt.Color(153, 153, 0));
        studentInformation.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        studentInformation.setText("Student Information    ");
        studentInformation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                studentInformationMousePressed(evt);
            }
        });
        btnSet2.add(studentInformation);

        add(btnSet2);

        blank.setOpaque(false);

        javax.swing.GroupLayout blankLayout = new javax.swing.GroupLayout(blank);
        blank.setLayout(blankLayout);
        blankLayout.setHorizontalGroup(
            blankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        blankLayout.setVerticalGroup(
            blankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        add(blank);
    }// </editor-fold>//GEN-END:initComponents

    JLabel activeLb = null;
    
    private void selectedButton(JLabel lb){
        if(activeLb != null){
            activeLb.setOpaque(false);
            activeLb.setBackground(null);
            activeLb.setForeground(new Color(153, 153, 0));
            activeLb.repaint();
        }
        
        activeLb = lb;
        activeLb.setOpaque(true);
        activeLb.setBackground(new Color(122, 31, 31));
        activeLb.setForeground(new Color(255, 255,0));
        activeLb.repaint();
        // A function for interactive navigation/ It highlights the selected component on the navigation bar.
    }
    
    
    public void setMainContent(MainContent content){
        this.mC = content;
    }
    
    // Calling the functions for the navigation bar
    
    private void homeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMousePressed
        selectedButton(home);
        if(mC != null){
            mC.showPanel("home");
        }
    }//GEN-LAST:event_homeMousePressed

    private void dashboardMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMousePressed
        selectedButton(dashboard);
        if(mC != null){
            mC.showPanel("dashboard");
        }
    }//GEN-LAST:event_dashboardMousePressed

    private void bookshelfMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookshelfMousePressed
        selectedButton(bookshelf);
        if(mC != null){
            mC.showPanel("bookshelf");
        }
    }//GEN-LAST:event_bookshelfMousePressed

    private void studentInformationMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentInformationMousePressed
        selectedButton(studentInformation);
        if(mC != null){
            mC.showPanel("student");
        }
    }//GEN-LAST:event_studentInformationMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel appName;
    private javax.swing.JLabel appSubname;
    private javax.swing.JPanel appTitle;
    private javax.swing.JPanel blank;
    private javax.swing.JLabel bookshelf;
    private javax.swing.JPanel btnSet1;
    private javax.swing.JPanel btnSet2;
    private javax.swing.JLabel dashboard;
    private javax.swing.JLabel home;
    private javax.swing.JLabel studentInformation;
    // End of variables declaration//GEN-END:variables
}
