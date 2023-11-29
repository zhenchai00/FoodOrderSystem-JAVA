package foodordersystem.Model;

public class OrderItem {
    private int itemName;
    private int quantity;
    private double price;

    public OrderItem (int itemName, int quantity, double price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getItemName () {
        return itemName;
    }

    public int getQuantity () {
        return quantity;
    }

    public double getPrice () {
        return price;
    }
}
