package foodordersystem.Page;

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
}
