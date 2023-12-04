package foodordersystem.Page;

import foodordersystem.FoodOrderSystem;
import foodordersystem.Page.CustomerDashboardPage;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;

public class CustomerDwalletPage implements ActionListener {
    public JFrame customerDwalletPage;
    private JPanel headerPanel, buttonPanel;
    private JLabel creditLabel;
    private JButton backBtn;

    public CustomerDwalletPage() {
        customerDwalletPage = new JFrame("Digital Wallet");
        customerDwalletPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerDwalletPage.setLayout(new BoxLayout(customerDwalletPage.getContentPane(), BoxLayout.Y_AXIS));

        headerPanel = new JPanel();
        creditLabel = new JLabel("Credit Balance: " + FoodOrderSystem.currentUser.getCredit());
        creditLabel.setFont(new Font(null, Font.BOLD, 20));
        headerPanel.add(creditLabel);
        
        buttonPanel = new JPanel();
        backBtn = new JButton("Back");
        backBtn.addActionListener(this);
        buttonPanel.add(backBtn);
        
        customerDwalletPage.add(headerPanel);
        customerDwalletPage.add(buttonPanel);

        customerDwalletPage.pack();
        customerDwalletPage.setLocationRelativeTo(null);
    }

    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == backBtn) {
                CustomerDashboardPage.getCustomerDashboardPageObj().getCustomerDashboardPage().setVisible(true);
                customerDwalletPage.setVisible(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(customerDwalletPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JFrame getCustomerDwalletPage() {
        return customerDwalletPage;
    }
}
