package foodordersystem.Page;

import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import foodordersystem.Manager.MenuManager;
import foodordersystem.Model.Menu;

public class CustomerMenuPage extends MenuPage {
    private JTable menuTable;
    private DefaultTableModel menuTableModel;
    public CustomerMenuPage () {
        super("Customer Menu Page");
        menuPage.setLayout(new BoxLayout(menuPage.getContentPane(), BoxLayout.Y_AXIS));

        menuTableModel = new DefaultTableModel(new Object[]{"Id", "Name", "Price", "Review"}, 0);
        menuTable = new JTable(menuTableModel);
        JScrollPane scrollPanel = new JScrollPane(menuTable);
        for (Menu menu : MenuManager.getAllMenus()) {
            addRowToTable(menu);
        }

        buttonPanel.add(backBtn);

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
    public JFrame getMenuPage() {
        return menuPage;
    }

    public void addRowToTable (Menu menu) {
        menuTableModel.addRow(new Object[]{
            menu.getId(),
            menu.getName(),
            menu.getPrice(), 
            menu.getReview()
        });
    }
}
