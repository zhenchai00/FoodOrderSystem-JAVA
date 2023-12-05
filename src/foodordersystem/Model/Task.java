package foodordersystem.Model;

import java.time.LocalDateTime;

import foodordersystem.Enum.TaskStatus;

public class Task {
    private int id;
    private int orderId;
    private int customerId;
    private int vendorId;
    private int runnerId;
    private String address;
    private TaskStatus status;
    private String review;
    private int reviewUserId;
    private LocalDateTime dateTime;

    public Task (
        int id,
        int orderId,
        int customerId,
        int vendorId,
        int runnerId,
        String address,
        TaskStatus status,
        String review,
        int reviewUserId,
        LocalDateTime dateTime
    ) {
        this.id = id;
        this.orderId = orderId;
        this.customerId = customerId;
        this.vendorId = vendorId;
        this.runnerId = runnerId;
        this.address = address;
        this.status = status;
        this.review = review;
        this.reviewUserId = reviewUserId;
        this.dateTime = dateTime;
    }

    public int getId () {
        return id;
    }

    public int getOrderId () {
        return orderId;
    }

    public int getCustomerId () {
        return customerId;
    }

    public int getVendorId () {
        return vendorId;
    }

    public int getRunnerId () {
        return runnerId;
    }

    public String getAddress () {
        return address;
    }

    public TaskStatus getStatus () {
        return status;
    }

    public String getReview () {
        return review;
    }

    public LocalDateTime getDateTime () {
        return dateTime;
    }

    public void setId (int id) {
        this.id = id;
    }

    public void setOrderId (int orderId) {
        this.orderId = orderId;
    }

    public void setCustomerId (int customerId) {
        this.customerId = customerId;
    }

    public void setVendorId (int vendorId) {
        this.vendorId = vendorId;
    }

    public void setRunnerId (int runnerId) {
        this.runnerId = runnerId;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public void setStatus (TaskStatus status) {
        this.status = status;
    }

    public void setReview (String review) {
        this.review = review;
    }

    public void setDateTime (LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getReviewUserId () {
        return reviewUserId;
    }

    public void setReviewUserId (int reviewUserId) {
        this.reviewUserId = reviewUserId;
    }
}
