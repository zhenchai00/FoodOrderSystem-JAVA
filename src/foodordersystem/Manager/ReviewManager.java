package foodordersystem.Manager;

import javax.swing.JOptionPane;

import foodordersystem.FoodOrderSystem;
import foodordersystem.Model.DataIO;
import foodordersystem.Model.Menu;
import foodordersystem.Model.OrderItem;
import foodordersystem.Model.Task;

public class ReviewManager {
    public static void addMenuReview (int orderId) {
        boolean reviewed = false;
        for (OrderItem orderItem : OrderManager.getAllOrderItems()) {
            if (orderItem.getOrderId() == orderId) {
                String inputReview = JOptionPane.showInputDialog("Enter your review for \"" + orderItem.getItemName() + "\":");
                for (Menu menu : MenuManager.getAllMenus()) {
                    if (menu.getId() == orderItem.getMenuId() && menu.getReviewUserId() == FoodOrderSystem.currentUser.getId()) {
                        reviewed = true;
                        break;
                    }
                }

                if (!reviewed) {
                    for (Menu menu : MenuManager.getAllMenus()) {
                        if (menu.getId() == orderItem.getMenuId()) {
                            menu.setReview(inputReview);
                            menu.setReviewUserId(FoodOrderSystem.currentUser.getId());
                            DataIO.writeMenu();
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You have already reviewed this menu \"" + orderItem.getItemName() + "\"", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void addDeliveryReview (int orderId) {
        String inputReview = JOptionPane.showInputDialog("Enter your review for this delivery:");
        for (Task task : TaskManager.getAllTasks()) {
            if (task.getOrderId() != orderId) {
                JOptionPane.showMessageDialog(null, "No such delivery record", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!task.getReview().isEmpty()) {
                JOptionPane.showMessageDialog(null, "You have already reviewed this delivery", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            task.setReview(inputReview);
            task.setReviewUserId(FoodOrderSystem.currentUser.getId());
            DataIO.writeTask();
            break;
        }
    }
}
