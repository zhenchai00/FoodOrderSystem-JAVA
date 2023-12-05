package foodordersystem.Model;

import foodordersystem.Enum.MenuCategory;

public class Menu {
    private int id;
    private String name;
    private double price;
    private MenuCategory category;
    private String review;
    private int vendorId;
    private int reviewUserId;

    public Menu (
        int id,
        String name,
        double price,
        MenuCategory category,
        String review,
        int vendorId,
        int reviewUserId
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.review = review;
        this.vendorId = vendorId;
        this.reviewUserId = reviewUserId;
    }

    public int getId () {
        return id;
    }
    
    public String getName () {
        return name;
    }

    public double getPrice () {
        return price;
    }

    public MenuCategory getCategory () {
        return category;
    }

    public void setId (int id) {
        this.id = id;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setPrice (double price) {
        this.price = price;
    }

    public void setCategory (MenuCategory category) {
        this.category = category;
    }

    public void setReview (String review) {
        this.review = review;
    }

    public String getReview () {
        return review;
    }

    public void setVendorId (int vendorId) {
        this.vendorId = vendorId;
    }

    public int getVendorId () {
        return vendorId;
    }

    public void setReviewUserId (int reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    public int getReviewUserId () {
        return reviewUserId;
    }
}
