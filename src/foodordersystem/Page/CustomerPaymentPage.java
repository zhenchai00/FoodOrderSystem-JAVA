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

public class CustomerPaymentPage implements ActionListener {
    private JFrame customerPaymentPage;
    private JLabel orderTypeTxLabel, addressTxLabel,  totalTxLabel, orderTypeLabel, addressLabel, deliveryCostLabel, totalLabel;
    private JButton cancelBtn, payBtn;
    private JTable paymentTable;
    private DefaultTableModel paymentTableModel;
    private JPanel orderTypePanel, addressPanel, deliveryCostPanel, totalPanel, actionPanel;
    
    private double totalPayment;
    
    public CustomerPaymentPage () {
        customerPaymentPage = new JFrame("Payment Page");
        customerPaymentPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerPaymentPage.setLayout(new BoxLayout(customerPaymentPage.getContentPane(), BoxLayout.Y_AXIS));
        
        // add table for order item listing
        paymentTableModel = new DefaultTableModel(new Object[]{"ID", "Item Name", "Quantity", "Price"}, 0);
        paymentTable = new JTable(paymentTableModel);
        JScrollPane scrollPanel = new JScrollPane(paymentTable);
        
        // add label and button
        orderTypeTxLabel = new JLabel("Order Type: ");
        addressTxLabel = new JLabel("Address: ");
        totalTxLabel = new JLabel("Total Payment: ");
        cancelBtn = new JButton("Cancel");
        payBtn = new JButton("Pay");
        cancelBtn.addActionListener(this);
        payBtn.addActionListener(this);
        orderTypePanel = new JPanel();
        addressPanel = new JPanel();
        totalPanel = new JPanel();
        actionPanel = new JPanel();
        
        // add components and panels
        orderTypePanel.add(orderTypeTxLabel);
        addressPanel.add(addressTxLabel);
        totalPanel.add(totalTxLabel);
        actionPanel.add(cancelBtn);
        actionPanel.add(payBtn);
        customerPaymentPage.add(scrollPanel);
        customerPaymentPage.add(orderTypePanel);
        customerPaymentPage.add(addressPanel);
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
                customerPaymentPage.setVisible(false);
            } else if (event.getSource() == payBtn) {
                DwalletManager.paymentBalance(FoodOrderSystem.currentUser.getId(), this.totalPayment);
            }
        } catch (Exception e) {
            
        }
    }
    
    public void addRowToTable (ArrayList<Object[]> orderMenuList, String address, OrderType orderType) {
        double orderItemSum = 0;
        double deliveryCost = 0;
        for (int i = 0; i < orderMenuList.size(); i++) {
            Object[] itemDetails = orderMenuList.get(i);
            Menu menu = (Menu) itemDetails[0];
            int menuId = menu.getId();
            int vendorId = menu.getVendorId();
            int quantity = (int) itemDetails[1];
            double price = menu.getPrice() * quantity;
            orderItemSum += price;
            paymentTableModel.addRow(new Object[]{menuId, menu.getName(), vendorId, quantity, price});
        }
        
        orderTypeLabel = new JLabel(String.valueOf(orderType));
        orderTypePanel.add(orderTypeLabel);
        customerPaymentPage.add(orderTypePanel);
        if (orderType.equals(OrderType.DELIVERY)) {
            deliveryCost = 5;
            addressLabel = new JLabel(address);
            deliveryCostLabel = new JLabel(String.valueOf(deliveryCost));
            addressPanel.add(addressLabel);
            deliveryCostPanel.add(deliveryCostLabel);
            customerPaymentPage.add(addressPanel);
            customerPaymentPage.add(deliveryCostPanel);
        } else {
            customerPaymentPage.remove(addressPanel);
        }
        
        this.totalPayment = orderItemSum + deliveryCost;
        totalLabel = new JLabel(String.valueOf(totalPayment));
        totalPanel.add(totalLabel);
        customerPaymentPage.add(totalPanel);
    }
}