package foodordersystem.Page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
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

public class VendorReviewPage implements ActionListener {
    private JFrame vendorReviewPage;
    private JButton backBtn;
    private JPanel actionBtnPanel;
    private JTable vendorReviewTable;
    private DefaultTableModel vendorReviewTableModel;
    private JScrollPane vendorReviewScrollPane;

    public VendorReviewPage () {
        vendorReviewPage = new JFrame("Vendor Review Page");
        vendorReviewPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vendorReviewPage.setLayout(new BoxLayout(vendorReviewPage.getContentPane(), BoxLayout.Y_AXIS));

        showVendorReviewPage();

        backBtn = new JButton("Back");
        backBtn.addActionListener(this);

        actionBtnPanel = new JPanel();

        vendorReviewPage.add(vendorReviewScrollPane);
        vendorReviewPage.add(actionBtnPanel);

        vendorReviewPage.pack();
        vendorReviewPage.setLocationRelativeTo(null);
        vendorReviewPage.setVisible(false);
    }

    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == backBtn) {
                VendorDashboardPage.getVendorDashboardPageObj().getVendorDashboardPage().setVisible(true);
                vendorReviewPage.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
            JOptionPane.showMessageDialog(vendorReviewPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showVendorReviewPage () {
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
                    order.getRating(),
                    order.getReview()
                });
            }
        }
    }
}
