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

public class CustomerDashboardPage implements DashboardPage, ActionListener {
    public CustomerOrderPage customerOrderPage;
    public static JFrame customerDashboardPage;
    private JButton notifyBtn, orderBtn, menuBtn, dwalletBtn, logoutBtn;
    private JLabel welcomeLabel;

    private static CustomerDashboardPage instance;

    private CustomerDashboardPage() {
        if (FoodOrderSystem.currentUser == null) {
            throw new NullPointerException("User is null at CustomerDashboardPage");
        }
        customerDashboardPage = new JFrame("Customer Dashboard");
        customerDashboardPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerDashboardPage.setLayout(new BoxLayout(customerDashboardPage.getContentPane(), BoxLayout.Y_AXIS));
        System.out.println("cusomter dashboard page: " + FoodOrderSystem.currentUser.getUsername());

        JPanel headerPanel = new JPanel();
        welcomeLabel = new JLabel("Welcome, " + FoodOrderSystem.currentUser.getUsername().toUpperCase() + "!");
        welcomeLabel.setFont(new Font(null, Font.BOLD, 20));
        headerPanel.add(welcomeLabel);

        notifyBtn = new JButton("Notification");
        orderBtn = new JButton("Order");
        menuBtn = new JButton("Menu");
        dwalletBtn = new JButton("Digital Wallet");
        logoutBtn = new JButton("Logout");

        notifyBtn.addActionListener(this);
        orderBtn.addActionListener(this);
        menuBtn.addActionListener(this);
        dwalletBtn.addActionListener(this);
        logoutBtn.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(notifyBtn);
        buttonPanel.add(orderBtn);
        buttonPanel.add(menuBtn);
        buttonPanel.add(dwalletBtn);
        buttonPanel.add(logoutBtn);

        
        customerDashboardPage.add(headerPanel);
        customerDashboardPage.add(buttonPanel);

        customerDashboardPage.pack();
        customerDashboardPage.setLocationRelativeTo(null);
        customerDashboardPage.setVisible(false);
    }

    public static CustomerDashboardPage getCustomerDashboardPageObj() {
        if (instance == null) {
            instance = new CustomerDashboardPage();
        }
        return instance;
    }

    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == notifyBtn) {
                CustomerNotificationPage customerNotificationPage = new CustomerNotificationPage();
                customerNotificationPage.getNotificationPage().setVisible(true);
                customerDashboardPage.setVisible(false);
            } else if (event.getSource() == orderBtn) {
                CustomerOrderPage customerOrderPage = new CustomerOrderPage();
                customerOrderPage.getOrderPage().setVisible(true);
                customerDashboardPage.setVisible(false);
            } else if (event.getSource() == menuBtn) {
                CustomerMenuPage customerMenuPage = new CustomerMenuPage();
                customerMenuPage.getMenuPage().setVisible(true);
                customerDashboardPage.setVisible(false);
            } else if (event.getSource() == dwalletBtn) {
                CustomerDwalletPage customerDwalletPage = new CustomerDwalletPage();
                customerDwalletPage.getCustomerDwalletPage().setVisible(true);
                customerDashboardPage.setVisible(false);
            } else if (event.getSource() == logoutBtn) {
                logout();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            JOptionPane.showMessageDialog(customerDashboardPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JFrame getCustomerDashboardPage() {
        return customerDashboardPage;
    }

    public void logout() {
        FoodOrderSystem.loginPage.getLoginPage().setVisible(true);
        customerDashboardPage.setVisible(false);
    }
}
