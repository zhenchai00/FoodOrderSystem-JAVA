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
    public JButton acceptBtn, rejectBtn, updateOrderBtn;
    public VendorHistoryOrderPage () {
        super("Vendor History Order Page");

        acceptBtn = new JButton("Accept");
        rejectBtn = new JButton("Reject");
        updateOrderBtn = new JButton("Update Order");

        acceptBtn.addActionListener(this);
        rejectBtn.addActionListener(this);
        updateOrderBtn.addActionListener(this);

        actionBtnPanel.add(acceptBtn);
        actionBtnPanel.add(rejectBtn);
        actionBtnPanel.add(updateOrderBtn);
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
                OrderManager.updateOrderStatus(orderId, OrderStatus.ACCEPT);
                historyTableModel.setValueAt(OrderStatus.ACCEPT.toString(), selectedRow, 3);
            } else if (event.getSource() == rejectBtn) {
                int selectedRow = historyOrderTable.getSelectedRow();
                int orderId = (int) historyTableModel.getValueAt(selectedRow, 0);
                OrderManager.updateOrderStatus(orderId, OrderStatus.REJECT);
                historyTableModel.setValueAt(OrderStatus.REJECT.toString(), selectedRow, 3);
            } else if (event.getSource() == updateOrderBtn) {
                int selectedRow = historyOrderTable.getSelectedRow();
                int orderId = (int) historyTableModel.getValueAt(selectedRow, 0);
                OrderManager.updateOrderStatus(orderId, OrderStatus.HANDOVER);
                historyTableModel.setValueAt(OrderStatus.HANDOVER.toString(), selectedRow, 3);
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
