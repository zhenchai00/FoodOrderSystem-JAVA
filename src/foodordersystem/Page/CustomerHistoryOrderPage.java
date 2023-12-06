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
import foodordersystem.Enum.OrderType;
import foodordersystem.Manager.OrderManager;
import foodordersystem.Manager.ReviewManager;
import foodordersystem.Model.Order;

public class CustomerHistoryOrderPage extends HistoryOrderPage {
    private JButton cancelOrderBtn, reOrderBtn, foodReviewBtn, deliveryReviewBtn;

    public CustomerHistoryOrderPage () {
        super("Customer History Order Page");

        cancelOrderBtn = new JButton("Cancel Order");
        reOrderBtn = new JButton("Re-Order");
        foodReviewBtn = new JButton("Review Food");
        deliveryReviewBtn = new JButton("Review Delivery");
        cancelOrderBtn.addActionListener(this);
        reOrderBtn.addActionListener(this);
        foodReviewBtn.addActionListener(this);
        deliveryReviewBtn.addActionListener(this);
        actionBtnPanel.add(cancelOrderBtn);
        actionBtnPanel.add(reOrderBtn);
        actionBtnPanel.add(foodReviewBtn);
        actionBtnPanel.add(deliveryReviewBtn);
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
            } else if (event.getSource() == foodReviewBtn) {
                int selectedRow = historyOrderTable.getSelectedRow();
                int orderId = (int) historyTableModel.getValueAt(selectedRow, 0);

                OrderStatus orderStatus = OrderManager.getOrderById(orderId).getOrderStatus();
                if (orderStatus == OrderStatus.COMPLETED) {
                    ReviewManager.addMenuReview(orderId);
                } else {
                    throw new Exception("Order is not completed");
                }
            } else if (event.getSource() == deliveryReviewBtn) {
                int selectedRow = historyOrderTable.getSelectedRow();
                int orderId = (int) historyTableModel.getValueAt(selectedRow, 0);

                Order order = OrderManager.getOrderById(orderId);
                if (order.getOrderStatus() == OrderStatus.COMPLETED && order.getOrderType() == OrderType.DELIVERY) {
                    ReviewManager.addDeliveryReview(orderId);
                } else {
                    throw new Exception("Order is not completed or is not delivery");
                }
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
        historyTableModel = new DefaultTableModel(new Object[]{"ID", "Date", "Type", "Status", "Refund", "Delivery Fee"}, 0);
        historyOrderTable = new JTable(historyTableModel);
        historyOrderScrollPane = new JScrollPane(historyOrderTable);
        ArrayList<Order> allOrders = OrderManager.getAllOrders();
        for (Order order : allOrders) {
            if (order.getCustomerId() == FoodOrderSystem.currentUser.getId()) {
                String deliveryCost = order.getOrderType() == OrderType.DELIVERY ? String.valueOf(order.getDeliveryCost()) : "-";
                historyTableModel.addRow(new Object[]{
                    order.getId(),
                    order.getDate(),
                    order.getOrderType(),
                    order.getOrderStatus(),
                    order.getRefundStatus(),
                    deliveryCost
                });
            }
        }
    }

    public void updateHistoryOrderPage () {
        historyTableModel.setRowCount(0);
        ArrayList<Order> allOrders = OrderManager.getAllOrders();
        for (Order order : allOrders) {
            if (order.getCustomerId() == FoodOrderSystem.currentUser.getId()) {
                String deliveryCost = order.getOrderType() == OrderType.DELIVERY ? String.valueOf(order.getDeliveryCost()) : "-";
                historyTableModel.addRow(new Object[]{
                    order.getId(),
                    order.getDate(),
                    order.getOrderType(),
                    order.getOrderStatus(),
                    order.getRefundStatus(),
                    deliveryCost
                });
            }
        }
    }
}
