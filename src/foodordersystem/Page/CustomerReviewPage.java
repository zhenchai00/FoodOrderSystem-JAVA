package foodordersystem.Page;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import foodordersystem.Manager.OrderManager;
import foodordersystem.Model.DataIO;
import foodordersystem.Model.Order;
import foodordersystem.Model.User;

public class CustomerReviewPage extends ReviewPage {
    private JTable customerReviewTable;
    private DefaultTableModel customerReviewTableModel;
    private JScrollPane customerReviewScrollPane;
    private JButton backBtn;

    public CustomerReviewPage () {
        super("Customer Review Page");

        backBtn = new JButton("Back");
        backBtn.addActionListener(this);

        actionBtnPanel = new JPanel();
        actionBtnPanel.add(backBtn);
        reviewPage.add(customerReviewScrollPane);
        reviewPage.add(actionBtnPanel);

        reviewPage.pack();
        reviewPage.setLocationRelativeTo(null);
        reviewPage.setVisible(false);
    }

    @Override
    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == backBtn) {
                CustomerDashboardPage.getCustomerDashboardPageObj().getCustomerDashboardPage().setVisible(true);
                reviewPage.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
            JOptionPane.showMessageDialog(reviewPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showReviewTableData () {
        customerReviewTableModel = new DefaultTableModel(new Object[] {"Order ID", "Vendor ID", "Vendor Name", "Rating", "Review"}, 0);
        customerReviewTable = new JTable(customerReviewTableModel);
        customerReviewScrollPane = new JScrollPane(customerReviewTable);

        for (Order order : OrderManager.getAllOrders()) {
            String vendorName = "";
            for (User user : DataIO.allUsers) {
                if (user.getId() == order.getVendorId()) {
                    vendorName = user.getUsername();
                }
            }
            customerReviewTableModel.addRow(new Object[] {
                order.getId(),
                order.getVendorId(),
                vendorName,
                order.getRating() + "/ FIVE",
                order.getReview()
            });
        }
    }
}
