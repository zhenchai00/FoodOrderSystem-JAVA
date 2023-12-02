package foodordersystem.Page;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import foodordersystem.FoodOrderSystem;
import foodordersystem.Enum.OrderType;
import foodordersystem.Manager.MenuManager;
import foodordersystem.Manager.OrderManager;
import foodordersystem.Model.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

public class NewOrderPage implements ActionListener{
    private JFrame newOrderPage;

    private JButton addBtn, editBtn, deleteBtn, cancelBtn, continueBtn;

    private JRadioButton dineInRadio, takeAwayRadio, deliveryRadio;
    private ButtonGroup orderTypeGroup;

    private JLabel itemIdLabel, quantityLabel;
    private JTextField itemIdField;
    private JFormattedTextField quantityField;
    private Integer quantityValue;

    private JTable orderTable;
    private DefaultTableModel orderTableModel;

    private ArrayList<Object[]> orderMenuList = new ArrayList<>();

    /**
     * Constructor of NewOrderPage by creating the frame and its content
     */
    public NewOrderPage () {
        newOrderPage = new JFrame("New Order Page");
        newOrderPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newOrderPage.setLayout(new BoxLayout(newOrderPage.getContentPane(), BoxLayout.Y_AXIS));

        // add text field for menu item id
        itemIdLabel = new JLabel("Item ID:");
        itemIdField = new JTextField(10);

        // add text field for quantity
        quantityLabel = new JLabel("Quantity:");
        NumberFormat format = NumberFormat.getNumberInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        quantityField = new JFormattedTextField(formatter);
        quantityField.setColumns(10);

        // add table for order item listing
        orderTableModel = new DefaultTableModel(new Object[]{"ID", "Item Name", "Quantity", "Price"}, 0);
        orderTable = new JTable(orderTableModel);
        JScrollPane scrollPanel = new JScrollPane(orderTable);

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
        formPanel.add(itemIdField);
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
                String itemIdValue = itemIdField.getText();
                quantityValue = (Integer) quantityField.getValue();
                if (quantityValue == null || quantityValue <= 0) {
                    throw new Exception("Quantity must be greater than 0");
                }

                Menu menu = MenuManager.getMenuById(Integer.parseInt(itemIdValue));
                if (menu == null) {
                    throw new Exception("Menu not found");
                }

                // orderMenuList.add(menu);
                addRowToTable(menu, quantityValue);

                itemIdField.setText("");
                quantityField.setText("");
            } else if (event.getSource() == editBtn) {
                System.out.println("Edit button clicked");

            } else if (event.getSource() == deleteBtn) {
                System.out.println("Delete button clicked");

            } else if (event.getSource() == cancelBtn) {
                FoodOrderSystem.customerOrderPage.getOrderPage().setVisible(true);
                newOrderPage.setVisible(false);

            } else if (event.getSource() == continueBtn) {
                // newOrderPage.setVisible(false);
                // OrderCartPage.getOrderCartPage().setVisible(true);
                System.out.println("Continue button clicked");
                OrderManager orderManager = new OrderManager();
                orderManager.storeOrderItems(orderMenuList);
                orderManager.addOrder("test", getOrderType());
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
            JOptionPane.showMessageDialog(newOrderPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addRowToTable (Menu menu, int quantity) {
        int menuId = menu.getId();

        boolean menuItemExists = false;
        for (int i = 0; i < orderMenuList.size(); i++) {
            Object[] existingMenu = orderMenuList.get(i);
            Menu existingMenuObject = (Menu) existingMenu[0];
            if (existingMenuObject.getId() == menuId) {
                int currentQuantity = (int) orderTableModel.getValueAt(i, 2);
                double price = menu.getPrice() * (quantity + currentQuantity);

                orderTableModel.setValueAt(quantity + currentQuantity, i, 2);
                orderTableModel.setValueAt(price, i, 3);

                orderMenuList.get(i)[1] = quantity + currentQuantity;

                menuItemExists = true;
                break;
            }
        }

        if (!menuItemExists) {
            double price = menu.getPrice() * quantity;
            orderTableModel.addRow(new Object[]{menuId, menu.getName(), quantity, price});
            Object[] row = {menu, quantity, price};
            orderMenuList.add(row);
        }
    }
    
    public OrderType getOrderType () {
        if (dineInRadio.isSelected()) {
            return OrderType.DINE_IN;
        } else if (takeAwayRadio.isSelected()) {
            return OrderType.TAKE_AWAY;
        } else if (deliveryRadio.isSelected()) {
            return OrderType.DELIVERY;
        } else {
            return null;
        }
    }
}
