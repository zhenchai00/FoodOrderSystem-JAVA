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

public class VendorHistoryOrderPage extends HistoryOrderPage {
    public JButton acceptBtn, rejectBtn, handOverBtn, processBtn, completeBtn;
    public VendorHistoryOrderPage () {
        super("Vendor History Order Page");

        acceptBtn = new JButton("Accept");
        rejectBtn = new JButton("Reject");
        handOverBtn = new JButton("Hand Over");
        processBtn = new JButton("Process");
        completeBtn = new JButton("Complete");

        acceptBtn.addActionListener(this);
        rejectBtn.addActionListener(this);
        handOverBtn.addActionListener(this);
        processBtn.addActionListener(this);
        completeBtn.addActionListener(this);

        actionBtnPanel.add(acceptBtn);
        actionBtnPanel.add(rejectBtn);
        actionBtnPanel.add(handOverBtn);
        actionBtnPanel.add(processBtn);
        actionBtnPanel.add(completeBtn);
        actionBtnPanel.add(backBtn);

        historyOrderPage.pack();
        historyOrderPage.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed (ActionEvent event) {
        // super.actionPerformed(event);
        try {
            if (event.getSource() == acceptBtn) {
                int selectedRow = historyOrderTable.getSelectedRow();
                int orderId = (int) historyTableModel.getValueAt(selectedRow, 0);
                Order order = OrderManager.getOrderById(orderId);
                OrderStatus status = order.getOrderStatus();
                if (status == OrderStatus.PENDING) {
                    OrderManager.updateOrderStatus(orderId, OrderStatus.ACCEPT);
                    historyTableModel.setValueAt(OrderStatus.ACCEPT.toString(), selectedRow, 3);
                } else {
                    throw new Exception("Order status is not pending");
                }
            } else if (event.getSource() == rejectBtn) {
                int selectedRow = historyOrderTable.getSelectedRow();
                int orderId = (int) historyTableModel.getValueAt(selectedRow, 0);
                Order order = OrderManager.getOrderById(orderId);
                OrderStatus status = order.getOrderStatus();
                if (status == OrderStatus.PENDING) {
                    OrderManager.updateOrderStatus(orderId, OrderStatus.REJECT);
                    historyTableModel.setValueAt(OrderStatus.REJECT.toString(), selectedRow, 3);
                } else {
                    throw new Exception("Order status is not pending");
                }
            } else if (event.getSource() == handOverBtn) {
                int selectedRow = historyOrderTable.getSelectedRow();
                int orderId = (int) historyTableModel.getValueAt(selectedRow, 0);
                Order order = OrderManager.getOrderById(orderId);
                OrderStatus status = order.getOrderStatus();
                if (status == OrderStatus.ACCEPT) {
                    OrderManager.updateOrderStatus(orderId, OrderStatus.HANDOVER);
                    historyTableModel.setValueAt(OrderStatus.HANDOVER.toString(), selectedRow, 3);
                } else {
                    throw new Exception("Order status is not accept");
                }
            } else if (event.getSource() == processBtn) {
                int selectedRow = historyOrderTable.getSelectedRow();
                int orderId = (int) historyTableModel.getValueAt(selectedRow, 0);
                Order order = OrderManager.getOrderById(orderId);
                OrderStatus status = order.getOrderStatus();
                if (status == OrderStatus.ACCEPT) {
                    OrderManager.updateOrderStatus(orderId, OrderStatus.PROCESSING);
                    historyTableModel.setValueAt(OrderStatus.PROCESSING.toString(), selectedRow, 3);
                } else {
                    throw new Exception("Order status is not accept");
                }
            } else if (event.getSource() == completeBtn) {
                int selectedRow = historyOrderTable.getSelectedRow();
                int orderId = (int) historyTableModel.getValueAt(selectedRow, 0);
                Order order = OrderManager.getOrderById(orderId);
                OrderStatus status = order.getOrderStatus();
                if (status == OrderStatus.PROCESSING) {
                    OrderManager.updateOrderStatus(orderId, OrderStatus.COMPLETED);
                    historyTableModel.setValueAt(OrderStatus.COMPLETED.toString(), selectedRow, 3);
                } else {
                    throw new Exception("Order status is not processing");
                }
            } else if (event.getSource() == backBtn) {
                VendorDashboardPage.getVendorDashboardPageObj().getVendorDashboardPage().setVisible(true);
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
            if (order.getVendorId() == FoodOrderSystem.currentUser.getId()) {
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
