package foodordersystem.Page;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import foodordersystem.FoodOrderSystem;
import foodordersystem.Enum.MenuCategory;
import foodordersystem.Manager.MenuManager;
import foodordersystem.Model.Menu;
import java.awt.event.ActionEvent;

public class VendorMenuPage extends MenuPage{
    private JButton addBtn, editBtn, deleteBtn;
    private JTable menuTable;
    private DefaultTableModel menuTableModel;

    public VendorMenuPage () {
        super("Vendor Menu Page");
        menuPage.setLayout(new BoxLayout(menuPage.getContentPane(), BoxLayout.Y_AXIS));

        menuTableModel = new DefaultTableModel(new Object[]{"Id", "Name", "Price", "Category"}, 0);
        menuTable = new JTable(menuTableModel);
        JScrollPane scrollPanel = new JScrollPane(menuTable);
        for (Menu menu : MenuManager.getAllMenus()) {
            addRowToTable(menu);
        }

        addBtn = new JButton("Add Menu");
        editBtn = new JButton("Edit Menu");
        deleteBtn = new JButton("Delete Menu");
        addBtn.addActionListener(this);
        editBtn.addActionListener(this);
        deleteBtn.addActionListener(this);

        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(backBtn);

        menuPage.add(scrollPanel);
        menuPage.add(buttonPanel);

        menuPage.pack();
        menuPage.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == addBtn) {
                System.out.println("add menu");
                ArrayList<Object> menuDetails = MenuManager.getMenuDetails();
                if (menuDetails != null && !menuDetails.isEmpty()) {
                    MenuManager.addMenu(menuDetails.get(0).toString(), (double) menuDetails.get(1), MenuCategory.valueOf(menuDetails.get(2).toString()));
                }
                updateMenuTable();
            } else if (event.getSource() == editBtn) {
                System.out.println("edit menu");
                ArrayList<Object> menuDetails = MenuManager.getEditMenuDetails();
                if (menuDetails != null && !menuDetails.isEmpty()) {
                    MenuManager.editMenu((int) menuDetails.get(0), menuDetails.get(1).toString(), (double) menuDetails.get(2));
                }
                updateMenuTable();
            } else if (event.getSource() == deleteBtn) {
                System.out.println("delete menu");
                ArrayList<Object> menuDetails = MenuManager.getDeleteMenuDetails();
                if (menuDetails != null && !menuDetails.isEmpty()) {
                    MenuManager.deleteMenu((int) menuDetails.get(0));
                }
                updateMenuTable();
            } else if (event.getSource() == backBtn) {
                updateMenuTable();
                VendorDashboardPage.getVendorDashboardPageObj().getVendorDashboardPage().setVisible(true);
                menuPage.setVisible(false);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(menuPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public JFrame getMenuPage () {
        return menuPage;
    }

    public void addRowToTable (Menu menu) {
        if (FoodOrderSystem.currentUser.getId() == menu.getVendorId()) {
            menuTableModel.addRow(new Object[]{menu.getId(), menu.getName(), menu.getPrice(), menu.getCategory().toString()});
        }
    }

    public void updateMenuTable () {
        int rows = menuTableModel.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            menuTableModel.removeRow(i);
        }
        for (Menu menu : MenuManager.getAllMenus()) {
            addRowToTable(menu);
        }
    }
}