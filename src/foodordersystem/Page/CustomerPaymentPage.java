package foodordersystem.Page;

import foodordersystem.FoodOrderSystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import foodordersystem.Enum.OrderType;
import foodordersystem.Manager.DwalletManager;
import foodordersystem.Model.Menu;
import foodordersystem.Model.OrderItem;

public class CustomerPaymentPage implements ActionListener {
    private JFrame customerPaymentPage;
    private JLabel orderTypeTxLabel, addressTxLabel,  totalTxLabel, orderTypeLabel, addressLabel, deliveryCostLabel, totalLabel;
    private JButton cancelBtn, payBtn;
    private JTable paymentTable;
    private DefaultTableModel paymentTableModel;
    private JPanel orderTypePanel, addressPanel, deliveryCostPanel, totalPanel, actionPanel;
    
    private ArrayList<Object[]> orderItemList = new ArrayList<>();
    private ArrayList<Object[]> itemList = new ArrayList<>();
    //private OrderItem reorderItem;
    private String address;
    private OrderType orderType;
    private double deliveryCost;
    private double totalPayment;
    
    public CustomerPaymentPage (ArrayList<Object[]> orderItemList, String address, OrderType orderType) {
        this.orderItemList = orderItemList;
        //this.reorderItem = reorderItem;
        this.address = address;
        this.orderType = orderType;
        
        customerPaymentPage = new JFrame("Payment Page");
        customerPaymentPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerPaymentPage.setLayout(new BoxLayout(customerPaymentPage.getContentPane(), BoxLayout.Y_AXIS));
        
        // add table for order item listing
        paymentTableModel = new DefaultTableModel(new Object[]{"ID", "Item Name", "Quantity", "Price"}, 0);
        paymentTable = new JTable(paymentTableModel);
        JScrollPane scrollPanel = new JScrollPane(paymentTable);
        if (orderItemList.size() > 0) {
            addRowToTable();
        } else {
            //addReorderRowToTable();
        }
        
        // add label and button
        orderTypeTxLabel = new JLabel("Order Type: ");
        orderTypeLabel = new JLabel(String.valueOf(orderType));
        addressTxLabel = new JLabel("Address: ");
        totalTxLabel = new JLabel("Total Payment: ");

        cancelBtn = new JButton("Cancel");
        payBtn = new JButton("Pay");
        cancelBtn.addActionListener(this);
        payBtn.addActionListener(this);

        orderTypePanel = new JPanel();
        addressPanel = new JPanel();
        deliveryCostPanel = new JPanel();
        totalPanel = new JPanel();
        actionPanel = new JPanel();
        
        // add components and panels
        orderTypePanel.add(orderTypeTxLabel);
        orderTypePanel.add(orderTypeLabel);

        addressPanel.add(addressTxLabel);
        totalPanel.add(totalTxLabel);
        actionPanel.add(cancelBtn);
        actionPanel.add(payBtn);

        customerPaymentPage.add(scrollPanel);
        customerPaymentPage.add(orderTypePanel);
        customerPaymentPage.add(addressPanel);
        customerPaymentPage.add(deliveryCostPanel);
        if (orderType.equals(OrderType.DELIVERY)) {
            deliveryCost = 5;
            addressLabel = new JLabel(address);
            deliveryCostLabel = new JLabel("Delivery Cost: "+deliveryCost);
            addressPanel.add(addressLabel);
            deliveryCostPanel.add(deliveryCostLabel);
        } else {
            this.address = "";
            deliveryCost = 0;
            customerPaymentPage.remove(deliveryCostPanel);
            customerPaymentPage.remove(addressPanel);
        }
        customerPaymentPage.add(totalPanel);
        customerPaymentPage.add(actionPanel);
        
        customerPaymentPage.pack();
        customerPaymentPage.setLocationRelativeTo(null);
        customerPaymentPage.setVisible(false);
    }
    
    public JFrame getCustomerPaymentPage() {
        return customerPaymentPage;
    }
    
    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == cancelBtn) {
                NewOrderPage newOrderPage = new NewOrderPage();
                newOrderPage.getNewOrderPage().setVisible(true);
                getCustomerPaymentPage().setVisible(false);
            } else if (event.getSource() == payBtn) {
                DwalletManager.paymentBalance(FoodOrderSystem.currentUser.getId(), totalPayment, itemList, address, orderType, deliveryCost);
                CustomerDashboardPage.getCustomerDashboardPageObj().getCustomerDashboardPage().setVisible(true);
                customerPaymentPage.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
            JOptionPane.showMessageDialog(customerPaymentPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void addRowToTable () {
        double orderItemSum = 0;
        for (int i = 0; i < orderItemList.size(); i++) {
            Object[] itemDetails = orderItemList.get(i);
            Menu menu = (Menu) itemDetails[0];
            int menuId = menu.getId();
            int quantity = (int) itemDetails[1];
            double price = menu.getPrice() * quantity;
            orderItemSum += price;
            paymentTableModel.addRow(new Object[]{menuId, menu.getName(), quantity, price});
            Object[] row = {menu, quantity, price};
            this.itemList.add(row);
        }
        totalPayment = orderItemSum + deliveryCost;
        totalLabel = new JLabel(String.valueOf(totalPayment));
        totalPanel.add(totalLabel);
    }
    
//    public void addReorderRowToTable () {
//        ArrayList<Object[]> reorderItemList = new ArrayList<>();
//        for (OrderItem reoderItem : ) {
//            reorderItem.
//        }
//        
//        double orderItemSum = 0;
//        for (int i = 0; i < reorderItemList.size(); i++) {
//            OrderItem itemDetails = reorderItemList.get(i);
//            int menuId = itemDetails[1];
//            OrderItem reorderItem = (OrderItem) itemDetails[0];
//            int menuId = menu.getId();
//            int quantity = (int) itemDetails[1];
//            double price = menu.getPrice() * quantity;
//            orderItemSum += price;
//            paymentTableModel.addRow(new Object[]{menuId, menu.getName(), quantity, price});
//            Object[] row = {menu, quantity, price};
//            this.itemList.add(row);
//        }
//        totalPayment = orderItemSum + deliveryCost;
//        totalLabel = new JLabel(String.valueOf(totalPayment));
//        totalPanel.add(totalLabel);
//    }
}
