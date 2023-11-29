package foodordersystem.Page;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import foodordersystem.FoodOrderSystem;
import foodordersystem.Model.OrderItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class NewOrderPage implements ActionListener{
    private JFrame newOrderPage;

    private JButton addBtn, editBtn, deleteBtn, cancelBtn, continueBtn;

    private JRadioButton dineInRadio, takeAwayRadio, deliveryRadio;
    private ButtonGroup orderTypeGroup;

    private JLabel itemIdLabel, quantityLabel;
    private JTextField itemNameField;
    private JFormattedTextField quantityField;

    private JTable ordercontinueTable;
    private DefaultTableModel tableModel;

    /**
     * Constructor of NewOrderPage by creating the frame and its content
     */
    public NewOrderPage () {
        newOrderPage = new JFrame("New Order Page");
        newOrderPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newOrderPage.setLayout(new BoxLayout(newOrderPage.getContentPane(), BoxLayout.Y_AXIS));

        // add text field for menu item id
        itemIdLabel = new JLabel("Item ID:");
        itemNameField = new JTextField(10);

        // add text field for quantity
        quantityLabel = new JLabel("Quantity:");
        NumberFormat format = NumberFormat.getNumberInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        quantityField = new JFormattedTextField(formatter);
        quantityField.setColumns(10);

        // add table for order item listing
        tableModel = new DefaultTableModel(new Object[]{"Item Name", "Quantity", "Price"}, 0);
        ordercontinueTable = new JTable(tableModel);
        JScrollPane scrollPanel = new JScrollPane(ordercontinueTable);

        // add radio button for order type
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

        // add control button
        addBtn = new JButton("Add");
        editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete");
        cancelBtn = new JButton("Cancel");
        continueBtn = new JButton("Continue");
        addBtn.addActionListener(this);
        editBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        continueBtn.addActionListener(this);

        // prepare panel for form field
        JPanel formPanel = new JPanel();
        JPanel formActionPanel = new JPanel();
        JPanel orderTypePanel = new JPanel();
        JPanel footerPanel = new JPanel();

        // add relevant components to panel
        formPanel.add(itemIdLabel);
        formPanel.add(itemNameField);
        formPanel.add(quantityLabel);
        formPanel.add(quantityField);

        formActionPanel.add(addBtn);
        formActionPanel.add(editBtn);
        formActionPanel.add(deleteBtn);

        orderTypePanel.add(dineInRadio);
        orderTypePanel.add(takeAwayRadio);
        orderTypePanel.add(deliveryRadio);

        footerPanel.add(cancelBtn);
        footerPanel.add(continueBtn);

        newOrderPage.add(formPanel);
        newOrderPage.add(formActionPanel);
        newOrderPage.add(orderTypePanel);
        newOrderPage.add(scrollPanel);
        newOrderPage.add(footerPanel);

        newOrderPage.pack();
        newOrderPage.setLocationRelativeTo(null);
        newOrderPage.setVisible(false);
    }

    public JFrame getNewOrderPage() {
        return newOrderPage;
    }

    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == addBtn) {
                System.out.println("Add button clicked");
                String itemNameValue = itemNameField.getText();
                Integer quantityValue = (Integer) quantityField.getValue();
                if (quantityValue == null || quantityValue <= 0) {
                    throw new Exception("Quantity must be greater than 0");
                }

                // TODO: get item price from list of menu item
                addRowToTable(new OrderItem(Integer.parseInt(itemNameValue), quantityValue, 1.00));
                JOptionPane.showMessageDialog(addBtn, "Item added to cart" + "\n" + "Item ID: " + itemNameValue + "\n" + "Quantity: " + quantityValue, "Success", JOptionPane.INFORMATION_MESSAGE);

                itemNameField.setText("");
                quantityField.setText("");

            } else if (event.getSource() == cancelBtn) {
                FoodOrderSystem.customerOrderPage.getOrderPage().setVisible(true);
                newOrderPage.setVisible(false);

            } else if (event.getSource() == continueBtn) {
                // newOrderPage.setVisible(false);
                // OrderCartPage.getOrderCartPage().setVisible(true);
                System.out.println("Continue button clicked");

            }
        } catch (Exception e) {
            System.out.println("Error" + e);
            JOptionPane.showMessageDialog(newOrderPage, "Error: " + e.getMessage());
        }
    }

    public void addRowToTable (OrderItem orderItem) {
        tableModel.addRow(new Object[]{orderItem.getItemName(), orderItem.getQuantity(), orderItem.getPrice()});
    }

    // private OrderType getOrderType () {
    //     if (dineInRadio.isSelected()) {
    //         return OrderType.DINE_IN;
    //     } else if (takeAwayRadio.isSelected()) {
    //         return OrderType.TAKE_AWAY;
    //     } else if (deliveryRadio.isSelected()) {
    //         return OrderType.DELIVERY;
    //     } else {
    //         return null;
    //     }
    // }
}
