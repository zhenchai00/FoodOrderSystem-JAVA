package foodordersystem.Page;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class VendorOrderPage extends OrderPage {
    private JButton viewBtn;
    public VendorOrderPage () {
        super("Vendor Order Page");

        viewBtn = new JButton("View Order");
        viewBtn.addActionListener(this);

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
            if (event.getSource() == viewBtn) {
                VendorHistoryOrderPage historyOrderPage = new VendorHistoryOrderPage();
                historyOrderPage.getHistoryOrderPage().setVisible(true);
                orderPage.setVisible(false);

            } else if (event.getSource() == backBtn) {
                VendorDashboardPage.getVendorDashboardPageObj().getVendorDashboardPage().setVisible(true);
                orderPage.setVisible(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(orderPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
