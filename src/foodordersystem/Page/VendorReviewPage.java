package foodordersystem.Page;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import foodordersystem.FoodOrderSystem;
import foodordersystem.Manager.OrderManager;
import foodordersystem.Model.DataIO;
import foodordersystem.Model.Order;
import foodordersystem.Model.User;

public class VendorReviewPage extends ReviewPage {
    private JTable vendorReviewTable;
    private DefaultTableModel vendorReviewTableModel;
    private JScrollPane vendorReviewScrollPane;
    private JButton backBtn;

    public VendorReviewPage () {
        super("Vendor Review Page");

        backBtn = new JButton("Back");
        backBtn.addActionListener(this);

        actionBtnPanel = new JPanel();
        actionBtnPanel.add(backBtn);
        reviewPage.add(vendorReviewScrollPane);
        reviewPage.add(actionBtnPanel);

        reviewPage.pack();
        reviewPage.setLocationRelativeTo(null);
        reviewPage.setVisible(false);
    }

    @Override
    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == backBtn) {
                VendorDashboardPage.getVendorDashboardPageObj().getVendorDashboardPage().setVisible(true);
                reviewPage.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
            JOptionPane.showMessageDialog(reviewPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showReviewTableData () {
        vendorReviewTableModel = new DefaultTableModel(new Object[] {"Order ID", "Customer ID", "Customer Name", "Rating", "Review"}, 0);
        vendorReviewTable = new JTable(vendorReviewTableModel);
        vendorReviewScrollPane = new JScrollPane(vendorReviewTable);

        for (Order order : OrderManager.getAllOrders()) {
            if (order.getVendorId() == FoodOrderSystem.currentUser.getId()) {
                String customerName = "";
                for (User user : DataIO.allUsers) {
                    if (user.getId() == order.getCustomerId()) {
                        customerName = user.getUsername();
                    }
                }
                vendorReviewTableModel.addRow(new Object[] {
                    order.getId(),
                    order.getCustomerId(),
                    customerName,
                    order.getRating() + "/ FIVE",
                    order.getReview()
                });
            }
        }
    }
}
