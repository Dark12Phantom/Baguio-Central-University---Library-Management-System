package com.bcu.bculms;



import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainGUI extends javax.swing.JFrame {

    /*  Author
            Erick C. Gaceta
            Derrik Dwain V. Cabanilla
    For the System Analysis and Design Subject.
    
     */
    // The entry point for the application
    public MainGUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainContainer1 = new com.bcu.bculms.frontend.containers.MainContainer();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().add(mainContainer1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Execution
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.bcu.bculms.frontend.containers.MainContainer mainContainer1;
    // End of variables declaration//GEN-END:variables
}
