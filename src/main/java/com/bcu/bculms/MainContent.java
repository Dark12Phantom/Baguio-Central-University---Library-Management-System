
package com.bcu.bculms;

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

        home1 = new com.bcu.bculms.Home();
        dashboard1 = new com.bcu.bculms.Dashboard();
        bookshelf1 = new com.bcu.bculms.Bookshelf();
        studentInformation1 = new com.bcu.bculms.StudentInformation();

        setOpaque(false);
        setLayout(new java.awt.CardLayout());
        add(home1, "home");
        add(dashboard1, "dashboard");
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
    private com.bcu.bculms.Bookshelf bookshelf1;
    private com.bcu.bculms.Dashboard dashboard1;
    private com.bcu.bculms.Home home1;
    private com.bcu.bculms.StudentInformation studentInformation1;
    // End of variables declaration//GEN-END:variables
}
