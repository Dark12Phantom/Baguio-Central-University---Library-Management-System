
package com.bcu.bculms.frontend.containers;

import java.awt.CardLayout;

// The panel which contains the Home, Dashboard, Bookshelf, and Student Information tabs

public class MainContent extends javax.swing.JPanel {

    public MainContent() {
        initComponents();
        setCardName();
        showPanel("home");
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        home1 = new com.bcu.bculms.frontend.panels.Home();
        dashboard1 = new com.bcu.bculms.frontend.panels.Dashboard();
        bookshelf1 = new com.bcu.bculms.frontend.panels.Bookshelf();
        studentInformation1 = new com.bcu.bculms.frontend.panels.StudentInformation();

        setOpaque(false);
        setLayout(new java.awt.CardLayout());
        add(home1, "home");
        add(dashboard1, "dashboard");

        bookshelf1.setBackground(new java.awt.Color(102, 102, 102));
        bookshelf1.setForeground(new java.awt.Color(255, 255, 255));
        bookshelf1.setOpaque(false);
        add(bookshelf1, "bookshelf");
        add(studentInformation1, "student");
    }// </editor-fold>//GEN-END:initComponents

    public void setCardName(){
        home1.setName("home");
        dashboard1.setName("dashboard");
        bookshelf1.setName("bookshelf");
        studentInformation1.setName("student");
    }
    
    public void init(){
        home1.setOpaque(false);
        dashboard1.setOpaque(false);
        bookshelf1.setOpaque(false);
        studentInformation1.setOpaque(false);
    }
    
    public void showPanel(String name){
        CardLayout cl = (CardLayout) this.getLayout();
        cl.show(this, name);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bcu.bculms.frontend.panels.Bookshelf bookshelf1;
    private com.bcu.bculms.frontend.panels.Dashboard dashboard1;
    private com.bcu.bculms.frontend.panels.Home home1;
    private com.bcu.bculms.frontend.panels.StudentInformation studentInformation1;
    // End of variables declaration//GEN-END:variables
}
