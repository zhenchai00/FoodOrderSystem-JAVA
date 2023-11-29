package foodordersystem.Page;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import foodordersystem.FoodOrderSystem;
import foodordersystem.Model.DataIO;
import foodordersystem.Model.OrderItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderCartPage implements ActionListener{
    private JFrame orderCartPage;
    private JButton backBtn, confirmBtn;
    // private JTable orderCartTable;
    private DefaultTableModel tableModel;
    private JPanel headerPanel, footerPanel;
    private JRadioButton dineInRadio, takeAwayRadio, deliveryRadio;
    private ButtonGroup orderTypeGroup;

    public OrderCartPage () {
        orderCartPage = new JFrame("Order Cart Page");
        orderCartPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        orderCartPage.setLayout(new BoxLayout(orderCartPage.getContentPane(), BoxLayout.Y_AXIS));


        //// Need to redo the order page 




        dineInRadio = new JRadioButton("Dine In");
        takeAwayRadio = new JRadioButton("Take Away");
        deliveryRadio = new JRadioButton("Delivery");
        dineInRadio.addActionListener(this);
        takeAwayRadio.addActionListener(this);
        deliveryRadio.addActionListener(this);

        orderTypeGroup = new ButtonGroup();
        orderTypeGroup.add(dineInRadio);
        orderTypeGroup.add(takeAwayRadio);
        orderTypeGroup.add(deliveryRadio);


        backBtn = new JButton("Back");
        confirmBtn = new JButton("Confirm Payment");
        backBtn.addActionListener(this);
        confirmBtn.addActionListener(this);

        // prepare panel for form field
        headerPanel = new JPanel();
        footerPanel = new JPanel();

        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        headerPanel.add(dineInRadio);
        headerPanel.add(takeAwayRadio);
        headerPanel.add(deliveryRadio);

        // add relevant components to panel
        footerPanel.add(backBtn);
        footerPanel.add(confirmBtn);

        orderCartPage.add(headerPanel);
        orderCartPage.add(footerPanel);

        orderCartPage.pack();
        orderCartPage.setLocationRelativeTo(null);
        orderCartPage.setVisible(false);
    }

    public JFrame getOrderCartPage () {
        return orderCartPage;
    }

    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == backBtn) {
                orderCartPage.setVisible(false);
                FoodOrderSystem.newOrderPage.getNewOrderPage().setVisible(true);

            } else if (event.getSource() == confirmBtn) {
                // OrderType selectedOrderType = getOrderType();
                // DataIO.allOrders.add(new Order(
                //     1,
                //     1,
                //     1,
                //     "address",
                //     "date",
                //     selectedOrderType,
                //     Order.Refund.NO,
                //     Order.Status.PENDING
                // ));
                DataIO.writeOrder();
                
                System.out.println("selected group: " + orderTypeGroup.getSelection());

            }
            // System.out.println("Clicked: " + event.getSource());
        } catch (Exception e) {
            System.out.println("Error" + e);
            JOptionPane.showMessageDialog(orderCartPage, "Error: " + e.getMessage());
        }
    }

    public void addRowToTable (OrderItem orderItem) {
        tableModel.addRow(new Object[]{orderItem.getItemName(), orderItem.getQuantity(), orderItem.getPrice()});
    }

    // private Order.Type getOrderType () {
    //     if (dineInRadio.isSelected()) {
    //         return Order.Type.DINEIN;
    //     } else if (takeAwayRadio.isSelected()) {
    //         return Order.Type.TAKEAWAY;
    //     } else if (deliveryRadio.isSelected()) {
    //         return Order.Type.DELIVERY;
    //     } else {
    //         return null;
    //     }
    // }
}
