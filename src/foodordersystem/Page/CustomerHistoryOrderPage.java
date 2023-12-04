package foodordersystem.Page;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import foodordersystem.FoodOrderSystem;
import foodordersystem.Enum.OrderStatus;
import foodordersystem.Manager.OrderManager;
import foodordersystem.Model.Order;

public class CustomerHistoryOrderPage extends HistoryOrderPage {
    private JButton cancelOrderBtn, reOrderBtn;

    public CustomerHistoryOrderPage () {
        super("Customer History Order Page");

        cancelOrderBtn = new JButton("Cancel Order");
        reOrderBtn = new JButton("Re-Order");
        cancelOrderBtn.addActionListener(this);
        reOrderBtn.addActionListener(this);
        actionBtnPanel.add(cancelOrderBtn);
        actionBtnPanel.add(reOrderBtn);
        actionBtnPanel.add(backBtn);

        historyOrderPage.pack();
        historyOrderPage.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed (ActionEvent event) {
        // super.actionPerformed(event);
        try {
            if (event.getSource() == cancelOrderBtn) {
                int selectedRow = historyOrderTable.getSelectedRow();
                int orderId = (int) historyTableModel.getValueAt(selectedRow, 0);
                OrderManager.updateOrderStatus(orderId, OrderStatus.CANCELLED);
                historyTableModel.setValueAt(OrderStatus.CANCELLED.toString(), selectedRow, 3);
            } else if (event.getSource() == reOrderBtn) {
                int selectedRow = historyOrderTable.getSelectedRow();
                int orderId = (int) historyTableModel.getValueAt(selectedRow, 0);
                Order existingOrder = OrderManager.getOrderById(orderId);

                if (existingOrder == null) {
                    throw new Exception("Order not found");
                }

                OrderManager orderManager = new OrderManager();
                orderManager.reOrder(existingOrder);
                JOptionPane.showMessageDialog(historyOrderPage, "Order has been re-ordered", "Success", JOptionPane.INFORMATION_MESSAGE);
                updateHistoryOrderPage();
            } else if (event.getSource() == backBtn) {
                CustomerDashboardPage.getCustomerDashboardPageObj().getCustomerDashboardPage().setVisible(true);
                historyOrderPage.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
            JOptionPane.showMessageDialog(historyOrderPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showHistoryOrderPage () {
        historyTableModel = new DefaultTableModel(new Object[]{"ID", "Date", "Type", "Status", "Refund"}, 0);
        historyOrderTable = new JTable(historyTableModel);
        historyOrderScrollPane = new JScrollPane(historyOrderTable);
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

    public void updateHistoryOrderPage () {
        historyTableModel.setRowCount(0);
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
