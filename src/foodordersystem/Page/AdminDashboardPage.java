package foodordersystem.Page;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import foodordersystem.FoodOrderSystem;
import foodordersystem.Interface.DashboardPage;

public class AdminDashboardPage implements DashboardPage, ActionListener {
    private JFrame adminDashboardPage;
    private JButton registerUserBtn, topUpBtn, generateReceiptBtn, logoutBtn;
    private JLabel welcomeLabel;

    private static AdminDashboardPage instance;

    public AdminDashboardPage () {
        adminDashboardPage = new JFrame("Admin Dashboard");
        adminDashboardPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminDashboardPage.setLayout(new BoxLayout(adminDashboardPage.getContentPane(), BoxLayout.Y_AXIS));

        JPanel headerPanel = new JPanel();
        welcomeLabel = new JLabel("Welcome, " + FoodOrderSystem.currentUser.getUsername().toUpperCase() + "!");
        welcomeLabel.setFont(new Font(null, Font.BOLD, 20));
        headerPanel.add(welcomeLabel);

        JPanel buttonPanel = new JPanel();
        registerUserBtn = new JButton("Register User");
        topUpBtn = new JButton("Top Up");
        generateReceiptBtn = new JButton("Generate Receipt");
        logoutBtn = new JButton("Logout");
        registerUserBtn.addActionListener(this);
        topUpBtn.addActionListener(this);
        generateReceiptBtn.addActionListener(this);
        logoutBtn.addActionListener(this);
        buttonPanel.add(registerUserBtn);
        buttonPanel.add(topUpBtn);
        buttonPanel.add(generateReceiptBtn);
        buttonPanel.add(logoutBtn);

        adminDashboardPage.add(headerPanel);
        adminDashboardPage.add(buttonPanel);

        adminDashboardPage.pack();
        adminDashboardPage.setLocationRelativeTo(null);
        adminDashboardPage.setVisible(false);
    }

    public static AdminDashboardPage getAdminDashboardPageObj() {
        if (instance == null) {
            instance = new AdminDashboardPage();
        }
        return instance;
    }

    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == registerUserBtn) {
                RegisterUserPage.getRegisterUserPage().setVisible(true);
                adminDashboardPage.setVisible(false);
            } else if (event.getSource() == topUpBtn) {
                TopUpPage topUpPage = new TopUpPage();
                topUpPage.getTopUpPage().setVisible(true);
                adminDashboardPage.setVisible(false);
            } else if (event.getSource() == generateReceiptBtn) {
                // GenerateReceiptPage.getGenerateReceiptPage().setVisible(true);
                // adminDashboardPage.setVisible(false);
            } else if (event.getSource() == logoutBtn) {
                logout();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            JOptionPane.showMessageDialog(adminDashboardPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JFrame getAdminDashboardPage() {
        return adminDashboardPage;
    }

    public void logout() {
        FoodOrderSystem.loginPage.getLoginPage().setVisible(true);
        adminDashboardPage.setVisible(false);
    }
}
