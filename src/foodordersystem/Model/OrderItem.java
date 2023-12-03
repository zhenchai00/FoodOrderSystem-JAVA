package foodordersystem.Model;

public class OrderItem {
    private int orderId;
    private int menuId;
    private int vendorId;
    private String itemName;
    private int quantity;
    private double price;

    public OrderItem (
        int orderId,
        int menuId,
        int vendorId,
        String itemName,
        int quantity,
        double price
    ) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.vendorId = vendorId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderId () {
        return orderId;
    }

    public int getMenuId () {
        return menuId;
    }

    public int getVendorId () {
        return vendorId;
    }

    public String getItemName () {
        return itemName;
    }

    public int getQuantity () {
        return quantity;
    }

    public double getPrice () {
        return price;
    }
}
