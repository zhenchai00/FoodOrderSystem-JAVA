package foodordersystem.Page;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import foodordersystem.FoodOrderSystem;
import foodordersystem.Enum.NotificationStatus;
import foodordersystem.Manager.NotificationManager;
import foodordersystem.Model.Notification;

public class CustomerNotificationPage extends NotificationPage {
    protected JButton readBtn, unReadBtn, allReadBtn, allUnReadBtn;
    public CustomerNotificationPage () {
        super("Customer Notification");

        readBtn = new JButton("Read");
        unReadBtn = new JButton("Unread");
        allReadBtn = new JButton("Mark All Read");
        allUnReadBtn = new JButton("Mark All Unread");

        readBtn.addActionListener(this);
        unReadBtn.addActionListener(this);
        allReadBtn.addActionListener(this);
        allUnReadBtn.addActionListener(this);

        actionBtnPanel.add(readBtn);
        actionBtnPanel.add(unReadBtn);
        actionBtnPanel.add(allReadBtn);
        actionBtnPanel.add(allUnReadBtn);
        actionBtnPanel.add(backBtn);

        notificationPage.pack();
        notificationPage.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == readBtn) {
                int selectedRow = notificationTable.getSelectedRow();
                int notificationId = (int) notificationTableModel.getValueAt(selectedRow, 0);
                NotificationManager.readNotification(notificationId);
                notificationTableModel.setValueAt(NotificationStatus.READ.toString(), selectedRow, 3);
            } else if (event.getSource() == unReadBtn) {
                int selectedRow = notificationTable.getSelectedRow();
                int notificationId = (int) notificationTableModel.getValueAt(selectedRow, 0);
                NotificationManager.unReadNotification(notificationId);
                notificationTableModel.setValueAt(NotificationStatus.UNREAD.toString(), selectedRow, 3);
            } else if (event.getSource() == allReadBtn) {
                NotificationManager.readAllNotification();
                updateNotificationTable(NotificationStatus.READ);
            } else if (event.getSource() == allUnReadBtn) {
                NotificationManager.unReadAllNotification();
                updateNotificationTable(NotificationStatus.UNREAD);
            } else if (event.getSource() == backBtn) {
                CustomerDashboardPage.getCustomerDashboardPageObj().getCustomerDashboardPage().setVisible(true);
                notificationPage.setVisible(false);
            }
            
        } catch (Exception e) {
            System.out.println("Error" + e);
            JOptionPane.showMessageDialog(notificationPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showNotificationTable () {
        ArrayList<Notification> notifications = NotificationManager.getAllNotifications();
        for (Notification notification : notifications) {
            if (notification.getUserId() == FoodOrderSystem.currentUser.getId()) {
                notificationTableModel.addRow(new Object[] {
                    notification.getId(),
                    notification.getMessage(),
                    notification.getDateTime(),
                    notification.getStatus()
                });
            }
        }
        resizeColumnWidth(notificationTable);
    }

    public void updateNotificationTable (NotificationStatus status) {
        int rowCount = notificationTableModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            notificationTableModel.setValueAt(status.toString(), i, 3);
        }
    }
}
