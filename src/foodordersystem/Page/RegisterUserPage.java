package foodordersystem.Page;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import foodordersystem.Manager.UserManager;
import foodordersystem.Model.UserRole;

public class RegisterUserPage implements ActionListener {
    private static JFrame registerUserPage;
    private JButton newCustBtn, newVendBtn, newAdminBtn, newRunnerBtn, backBtn;

    public RegisterUserPage () {
        registerUserPage = new JFrame("Register User Page");
        registerUserPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        newCustBtn = new JButton("New Customer");
        newVendBtn = new JButton("New Vendor");
        newAdminBtn = new JButton("New Admin");
        newRunnerBtn = new JButton("New Runner");
        backBtn = new JButton("Back");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newCustBtn);
        buttonPanel.add(newVendBtn);
        buttonPanel.add(newAdminBtn);
        buttonPanel.add(newRunnerBtn);
        buttonPanel.add(backBtn);

        newAdminBtn.addActionListener(this);
        newVendBtn.addActionListener(this);
        newCustBtn.addActionListener(this);
        newRunnerBtn.addActionListener(this);
        backBtn.addActionListener(this);

        registerUserPage.add(buttonPanel);

        registerUserPage.pack();
        registerUserPage.setLocationRelativeTo(null);
        registerUserPage.setVisible(false);
    }

    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == newCustBtn) {
                ArrayList<Object> credentials = UserManager.getUserCredentials();
                if (!credentials.isEmpty()) {
                    UserManager.registerUser(credentials.get(0).toString(), Integer.parseInt(credentials.get(1).toString()), UserRole.CUSTOMER);
                }
            } else if (event.getSource() == newVendBtn) {
                ArrayList<Object> credentials = UserManager.getUserCredentials();
                if (!credentials.isEmpty()) {
                    UserManager.registerUser(credentials.get(0).toString(), Integer.parseInt(credentials.get(1).toString()), UserRole.VENDOR);
                }
            } else if (event.getSource() == newAdminBtn) {
                ArrayList<Object> credentials = UserManager.getUserCredentials();
                if (!credentials.isEmpty()) {
                    UserManager.registerUser(credentials.get(0).toString(), Integer.parseInt(credentials.get(1).toString()), UserRole.ADMIN);
                }
            } else if (event.getSource() == newRunnerBtn) {
                ArrayList<Object> credentials = UserManager.getUserCredentials();
                if (!credentials.isEmpty()) {
                    UserManager.registerUser(credentials.get(0).toString(), Integer.parseInt(credentials.get(1).toString()), UserRole.RUNNER);
                }
            } else if (event.getSource() == backBtn) {
                AdminDashboardPage.getAdminDashboardPageObj().getAdminDashboardPage().setVisible(true);
                // FoodOrderSystem.loginPage.getLoginPage().setVisible(true);
                registerUserPage.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
            JOptionPane.showMessageDialog(registerUserPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static JFrame getRegisterUserPage () {
        return registerUserPage;
    }
}