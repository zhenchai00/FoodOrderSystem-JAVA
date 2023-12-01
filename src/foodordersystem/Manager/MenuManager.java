package foodordersystem.Manager;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import foodordersystem.FoodOrderSystem;
import foodordersystem.Enum.MenuCategory;
import foodordersystem.Model.DataIO;
import foodordersystem.Model.Menu;

public class MenuManager {
    public static void addMenu (String name, double price, MenuCategory category) throws Exception {
        if (DataIO.checkMenuName(name) != null) {
            throw new Exception("Menu name already exists");
        }

        int number = DataIO.allMenus.size() + 1;
        Menu newMenu = new Menu(number, name, price, category, "-");
        DataIO.allMenus.add(newMenu);
        DataIO.writeMenu();
        FoodOrderSystem.vendorMenuPage.addRowToTable(newMenu);
    }

    public static void editMenu (int id, String name, double price) throws Exception {
        Menu menu = DataIO.checkMenuName(name);
        if (menu != null && menu.getId() != id) {
            throw new Exception("Menu name already exists");
        }

        for (Menu m : DataIO.allMenus) {
            if (m.getId() == id) {
                m.setName(name);
                m.setPrice(price);
                break;
            }
        }
        DataIO.writeMenu();
        FoodOrderSystem.vendorMenuPage.updateMenuTable();
    }

    public static void deleteMenu (int id) throws Exception {
        for (Menu m : DataIO.allMenus) {
            if (m.getId() == id) {
                DataIO.allMenus.remove(m);
                break;
            }
        }
        DataIO.writeMenu();
        FoodOrderSystem.vendorMenuPage.updateMenuTable();
    }

    public static ArrayList<Menu> getAllMenus () {
        return DataIO.allMenus;
    }

    public static ArrayList<Object> getMenuDetails () {
        ArrayList<Object> menuDetails = new ArrayList<Object>();
        String inputName = JOptionPane.showInputDialog("Enter menu name");
        String inputPrice = JOptionPane.showInputDialog("Enter menu price");

        MenuCategory[] categories = {MenuCategory.CHINESE, MenuCategory.INDIAN, MenuCategory.WESTERN, MenuCategory.JAPANESE, MenuCategory.MALAY, MenuCategory.THAI, MenuCategory.WESTERN};
        Object inputCategory = JOptionPane.showInputDialog(null, "Choose menu category", "Menu Category", JOptionPane.QUESTION_MESSAGE, null, categories, categories[0]);

        if (
            inputName != null
            && !inputName.isEmpty()
            && inputPrice != null
            && !inputPrice.isEmpty()
            && inputCategory != null
            && !inputCategory.toString().isEmpty()
        ) {
            menuDetails.add(inputName);
            menuDetails.add(Double.parseDouble(inputPrice));
            menuDetails.add(inputCategory);
        }

        return menuDetails;
    }

    public static ArrayList<Object> getEditMenuDetails () {
        ArrayList<Object> menuDetails = new ArrayList<Object>();
        String inputId = JOptionPane.showInputDialog("Enter menu id");
        String inputName = JOptionPane.showInputDialog("Enter menu name");
        String inputPrice = JOptionPane.showInputDialog("Enter menu price");

        if (
            inputId != null
            && !inputId.isEmpty()
            && inputName != null
            && !inputName.isEmpty()
            && inputPrice != null
            && !inputPrice.isEmpty()
        ) {
            menuDetails.add(Integer.parseInt(inputId));
            menuDetails.add(inputName);
            menuDetails.add(Double.parseDouble(inputPrice));
        }

        return menuDetails;
    }

    public static ArrayList<Object> getDeleteMenuDetails () {
        ArrayList<Object> menuDetails = new ArrayList<Object>();
        String inputId = JOptionPane.showInputDialog("Enter menu id");

        if (
            inputId != null
            && !inputId.isEmpty()
        ) {
            menuDetails.add(Integer.parseInt(inputId));
        }

        return menuDetails;
    }
}
