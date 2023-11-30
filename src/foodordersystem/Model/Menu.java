package foodordersystem.Model;

import foodordersystem.Enum.MenuCategory;

public class Menu {
    private int id;
    private String name;
    private double price;
    private MenuCategory category;

    public Menu (int id, String name, double price, MenuCategory category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
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
}
