package foodordersystem.Page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import foodordersystem.Model.Order;

public class HistoryOrderPage implements ActionListener {
    private JFrame historyOrderPage;
    private JButton cancelOrderBtn, reOrderBtn, backBtn;
    private JPanel actionBtnPanel;
    private JTable historyOrderTable;
    private DefaultTableModel historyTableModel;

    public HistoryOrderPage () {
        historyOrderPage = new JFrame("History Order Page");
        historyOrderPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        historyOrderPage.setLayout(new BoxLayout(historyOrderPage.getContentPane(), BoxLayout.Y_AXIS));

        historyTableModel = new DefaultTableModel(new Object[]{"ID", "Date", "Type", "Status", "Refund"}, 0);
        historyOrderTable = new JTable(historyTableModel);
        JScrollPane historyOrderScrollPane = new JScrollPane(historyOrderTable);
        showHistoryOrderPage();

        cancelOrderBtn = new JButton("Cancel Order");
        reOrderBtn = new JButton("Re-Order");
        backBtn = new JButton("Back");
        cancelOrderBtn.addActionListener(this);
        reOrderBtn.addActionListener(this);
        backBtn.addActionListener(this);

        actionBtnPanel = new JPanel();
        actionBtnPanel.add(cancelOrderBtn);
        actionBtnPanel.add(reOrderBtn);
        actionBtnPanel.add(backBtn);

        historyOrderPage.add(historyOrderScrollPane);
        historyOrderPage.add(actionBtnPanel);

        historyOrderPage.pack();
        historyOrderPage.setLocationRelativeTo(null);
        historyOrderPage.setVisible(false);
    }

    public JFrame getHistoryOrderPage () {
        return historyOrderPage;
    }

    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == cancelOrderBtn) {

            } else if (event.getSource() == reOrderBtn) {

            } else if (event.getSource() == backBtn) {
                FoodOrderSystem.customerOrderPage.getOrderPage().setVisible(true);
                historyOrderPage.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
            JOptionPane.showMessageDialog(historyOrderPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showHistoryOrderPage () {
        ArrayList<Order> allOrders = OrderManager.getAllOrders();
        for (Order order : allOrders) {
            if (order.getCustomerId() == FoodOrderSystem.currentUser.getId()) {
                historyTableModel.addRow(new Object[]{
                    order.getId(),
                    order.getDate(),
                    order.getOrderType(),
                    order.getOrderStatus(),
                    order.getRefundStatus()
                });
            }
        }
    }
}
