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

public class VendorDashboardPage implements DashboardPage, ActionListener {
    private JFrame vendorDashboardPage;
    private JButton notifyBtn, menuBtn, orderBtn, revenueBtn, reviewBtn, logoutBtn;
    private JLabel welcomeLabel;

    private static VendorDashboardPage instance;

    private VendorDashboardPage () {
        vendorDashboardPage = new JFrame("Vendor Dashboard");
        vendorDashboardPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vendorDashboardPage.setLayout(new BoxLayout(vendorDashboardPage.getContentPane(), BoxLayout.Y_AXIS));

        JPanel headerPanel = new JPanel();
        welcomeLabel = new JLabel("Welcome, " + FoodOrderSystem.currentUser.getUsername().toUpperCase() + "!");
        welcomeLabel.setFont(new Font(null, Font.BOLD, 20));
        headerPanel.add(welcomeLabel);

        JPanel buttonPanel = new JPanel();
        notifyBtn = new JButton("Notification");
        menuBtn = new JButton("Menu");
        orderBtn = new JButton("Order");
        revenueBtn = new JButton("Revenue");
        reviewBtn = new JButton("Review");
        logoutBtn = new JButton("Logout");
        notifyBtn.addActionListener(this);
        menuBtn.addActionListener(this);
        orderBtn.addActionListener(this);
        revenueBtn.addActionListener(this);
        revenueBtn.addActionListener(this);
        logoutBtn.addActionListener(this);
        buttonPanel.add(notifyBtn);
        buttonPanel.add(menuBtn);
        buttonPanel.add(orderBtn);
        buttonPanel.add(revenueBtn);
        buttonPanel.add(reviewBtn);
        buttonPanel.add(logoutBtn);

        vendorDashboardPage.add(headerPanel);
        vendorDashboardPage.add(buttonPanel);

        vendorDashboardPage.pack();
        vendorDashboardPage.setLocationRelativeTo(null);
        vendorDashboardPage.setVisible(false);
    }

    public static VendorDashboardPage getVendorDashboardPageObj() {
        if (instance == null) {
            instance = new VendorDashboardPage();
        }
        return instance;
    }

    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == notifyBtn) {
                VendorNotificationPage vendorNotificationPage = new VendorNotificationPage();
                vendorNotificationPage.getNotificationPage().setVisible(true);
                vendorDashboardPage.setVisible(false);
            } else if (event.getSource() == menuBtn) {
                VendorMenuPage vendorMenuPage = new VendorMenuPage();
                vendorMenuPage.getMenuPage().setVisible(true);
                vendorDashboardPage.setVisible(false);
            } else if (event.getSource() == orderBtn) {
                VendorOrderPage vendorOrderPage = new VendorOrderPage();
                vendorOrderPage.getOrderPage().setVisible(true);
                vendorDashboardPage.setVisible(false);
            } else if (event.getSource() == revenueBtn) {
                VendorRevenueReportPage.getVendorRevenueReportPageObj().getVendorRevenueReportPage().setVisible(true);
            } else if (event.getSource() == reviewBtn) {

            } else if (event.getSource() == logoutBtn) {
                logout();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            JOptionPane.showMessageDialog(vendorDashboardPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JFrame getVendorDashboardPage() {
        return vendorDashboardPage;
    }

    public void logout() {
        FoodOrderSystem.loginPage.getLoginPage().setVisible(true);
        vendorDashboardPage.setVisible(false);
    }
}
