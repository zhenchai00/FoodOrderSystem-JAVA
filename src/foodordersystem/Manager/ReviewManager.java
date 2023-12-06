package foodordersystem.Manager;

import javax.swing.JOptionPane;

import foodordersystem.FoodOrderSystem;
import foodordersystem.Enum.Rating;
import foodordersystem.Model.DataIO;
import foodordersystem.Model.Menu;
import foodordersystem.Model.Order;
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
        
        Rating[] values = {Rating.NONE, Rating.ONE, Rating.TWO, Rating.THREE, Rating.FOUR, Rating.FIVE};
        Object ratingObject = JOptionPane.showInputDialog(null, "Select rating: ", "Rating", JOptionPane.QUESTION_MESSAGE, null, values, values[0]);
        
        if (
            inputReview != null
            && !inputReview.isEmpty()
            && ratingObject != null
        ) {
            Rating rating = (Rating) ratingObject;
            for (Task task : TaskManager.getAllTasks()) {
                if (task.getOrderId() == orderId) {
                    task.setReview(inputReview);
                    task.setReviewUserId(FoodOrderSystem.currentUser.getId());
                    task.setRating(rating);
                    DataIO.writeTask();
                    break;
                }
            }
        }
    }

    public static void addOrderReview (int orderId) {
        String inputReview = JOptionPane.showInputDialog("Enter your review for this order:");

        Rating[] values = {Rating.NONE, Rating.ONE, Rating.TWO, Rating.THREE, Rating.FOUR, Rating.FIVE};
        Object ratingObject = JOptionPane.showInputDialog(null, "Select rating: ", "Rating", JOptionPane.QUESTION_MESSAGE, null, values, values[0]);

        if (
            inputReview != null
            && !inputReview.isEmpty()
            && ratingObject != null
        ) {
            Rating rating = (Rating) ratingObject;
            for (Order order : OrderManager.getAllOrders()) {
                if (order.getId() == orderId) {
                    order.setReview(inputReview);
                    order.setRating(rating);
                    DataIO.writeOrder();
                    break;
                }
            }
        }
    }
}
