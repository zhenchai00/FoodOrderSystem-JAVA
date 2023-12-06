package foodordersystem.Page;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import foodordersystem.Enum.MenuCategory;
import foodordersystem.Manager.MenuManager;
import foodordersystem.Model.DataIO;
import foodordersystem.Model.Menu;
import foodordersystem.Model.User;

public class CustomerMenuPage extends MenuPage implements ItemListener {
    private JTable menuTable;
    private JComboBox<String> categoryComboBox;
    private DefaultTableModel menuTableModel;
    public CustomerMenuPage () {
        super("Customer Menu Page");
        menuPage.setLayout(new BoxLayout(menuPage.getContentPane(), BoxLayout.Y_AXIS));

        categoryComboBox = new JComboBox<>(new String[]{
            "ALL",
            MenuCategory.CHINESE.toString(),
            MenuCategory.WESTERN.toString(),
            MenuCategory.INDIAN.toString(),
            MenuCategory.JAPANESE.toString(),
            MenuCategory.THAI.toString()
        });
        categoryComboBox.addItemListener(this);

        menuTableModel = new DefaultTableModel(new Object[]{"Id", "Name","Category", "Vendor Name", "Price"}, 0);
        menuTable = new JTable(menuTableModel);
        JScrollPane scrollPanel = new JScrollPane(menuTable);
        for (Menu menu : MenuManager.getAllMenus()) {
            addRowToTable(menu);
        }

        buttonPanel.add(backBtn);

        menuPage.add(categoryComboBox);
        menuPage.add(scrollPanel);
        menuPage.add(buttonPanel);

        menuPage.pack();
        menuPage.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == backBtn) {
                CustomerDashboardPage.getCustomerDashboardPageObj().getCustomerDashboardPage().setVisible(true);
                menuPage.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void itemStateChanged (ItemEvent event) {
        if (event.getSource() == categoryComboBox) {
            String selectedCategory = (String) event.getItem();
            menuTableModel.setRowCount(0);
            for (Menu menu : MenuManager.getAllMenus()) {
                if (menu.getCategory().toString().equals(selectedCategory)) {
                    addRowToTable(menu);
                } else if (selectedCategory == "ALL") {
                    addRowToTable(menu);
                }
            }
        }
    }

    @Override
    public JFrame getMenuPage() {
        return menuPage;
    }

    public void addRowToTable (Menu menu) {
        String vendorName = "";
        for (User user : DataIO.allUsers) {
            if (user.getId() == menu.getVendorId()) {
                vendorName = user.getUsername();
            }
        }
        menuTableModel.addRow(new Object[]{
            menu.getId(),
            menu.getName(),
            menu.getCategory().toString(),
            vendorName,
            menu.getPrice() 
        });
    }
}
