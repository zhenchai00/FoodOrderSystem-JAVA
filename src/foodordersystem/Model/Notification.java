package foodordersystem.Model;

import java.time.LocalDateTime;

import foodordersystem.Enum.NotificationStatus;

public class Notification {
    private int id;
    private int userId;
    private String message;
    private LocalDateTime dateTime;
    private NotificationStatus status;

    public Notification(
        int id,
        int userId,
        String message,
        LocalDateTime dateTime,
        NotificationStatus status
    ) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.dateTime = dateTime;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }
}
