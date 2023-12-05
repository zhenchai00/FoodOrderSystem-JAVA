package foodordersystem.Page;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public abstract class NotificationPage implements ActionListener {
    protected JFrame notificationPage;
    protected JButton backBtn;
    protected JPanel actionBtnPanel;
    protected JTable notificationTable;
    protected DefaultTableModel notificationTableModel;
    protected JScrollPane notificationScrollPane;

    public NotificationPage(String title) {
        notificationPage = new JFrame(title);
        notificationPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        notificationPage.setLayout(new BoxLayout(notificationPage.getContentPane(), BoxLayout.Y_AXIS));

        notificationTableModel = new DefaultTableModel(new Object[] {"ID", "Message", "Date", "Status" }, 0);
        notificationTable = new JTable(notificationTableModel);
        notificationScrollPane = new JScrollPane(notificationTable);
        showNotificationTable();

        backBtn = new JButton("Back");
        backBtn.addActionListener(this);

        actionBtnPanel = new JPanel();

        notificationPage.add(notificationScrollPane);
        notificationPage.add(actionBtnPanel);

        notificationPage.pack();
        notificationPage.setLocationRelativeTo(null);
        notificationPage.setVisible(false);
    }

    public JFrame getNotificationPage() {
        return notificationPage;
    }

    public void actionPerformed(ActionEvent event) {
        try {
            // if (event.getSource() == backBtn) {
            //     FoodOrderSystem.customerOrderPage.getOrderPage().setVisible(true);
            //     notificationPage.setVisible(false);
            // }
        } catch (Exception e) {
            System.out.println("Error" + e);
            JOptionPane.showMessageDialog(notificationPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public abstract void showNotificationTable();

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > 300)
                width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
