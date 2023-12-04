package foodordersystem.Manager;

import java.time.LocalDateTime;
import java.util.ArrayList;

import foodordersystem.FoodOrderSystem;
import foodordersystem.Enum.NotificationStatus;
import foodordersystem.Model.DataIO;
import foodordersystem.Model.Notification;

public class NotificationManager {
    public static ArrayList<Notification> getAllNotifications () {
        return DataIO.allNotifications;
    }

    public static void sendNotification (int receiverUserId, String message) {
        Notification notification = new Notification(
            1000 + DataIO.allNotifications.size() + 1,
            receiverUserId,
            message,
            LocalDateTime.now(),
            NotificationStatus.UNREAD
        );
        DataIO.allNotifications.add(notification);
        DataIO.writeNotification();
    }

    public static void readNotification (int notificationId) {
        for (Notification notification : getAllNotifications()) {
            if (notification.getId() == notificationId) {
                notification.setStatus(NotificationStatus.READ);
                DataIO.writeNotification();
            }
        }
    }

    public static void unReadNotification (int notificationId) {
        for (Notification notification : getAllNotifications()) {
            if (notification.getId() == notificationId) {
                notification.setStatus(NotificationStatus.UNREAD);
                DataIO.writeNotification();
            }
        }
    }

    public static void readAllNotification () {
        for (Notification notification : getAllNotifications()) {
            if (notification.getUserId() == FoodOrderSystem.currentUser.getId()) {
                notification.setStatus(NotificationStatus.READ);
            }
        }
        DataIO.writeNotification();
    }

    public static void unReadAllNotification () {
        for (Notification notification : getAllNotifications()) {
            if (notification.getUserId() == FoodOrderSystem.currentUser.getId()) {
                notification.setStatus(NotificationStatus.UNREAD);
            }
        }
        DataIO.writeNotification();
    }
}
