package foodordersystem.Page;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CustomerOrderPage extends OrderPage {
    private JButton newBtn, viewBtn;
    public CustomerOrderPage () {
        super("Customer Order Page");

        newBtn = new JButton("New Order");
        viewBtn = new JButton("View Order");
        newBtn.addActionListener(this);
        viewBtn.addActionListener(this);

        buttonPanel.add(newBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(backBtn);
        orderPage.add(buttonPanel);
        orderPage.pack();
        orderPage.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed (ActionEvent event) {
        // super.actionPerformed(event);
        try {
            if (event.getSource() == newBtn) {
                // FoodOrderSystem.newOrderPage.getNewOrderPage().setVisible(true);
                NewOrderPage newOrderPage = new NewOrderPage();
                newOrderPage.getNewOrderPage().setVisible(true);
                orderPage.setVisible(false);

            } else if (event.getSource() == viewBtn) {

            } else if (event.getSource() == backBtn) {
                CustomerDashboardPage.getCustomerDashboardPageObj().getCustomerDashboardPage().setVisible(true);
                orderPage.setVisible(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(orderPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public JFrame getOrderPage() {
        return orderPage;
    }
}