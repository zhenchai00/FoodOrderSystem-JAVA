package foodordersystem.Manager;

import java.time.LocalDateTime;
import java.util.ArrayList;

import foodordersystem.FoodOrderSystem;
import foodordersystem.Enum.OrderStatus;
import foodordersystem.Enum.OrderType;
import foodordersystem.Enum.RefundStatus;
import foodordersystem.Model.Customer;
import foodordersystem.Model.DataIO;
import foodordersystem.Model.Menu;
import foodordersystem.Model.Order;
import foodordersystem.Model.OrderItem;

public class OrderManager {
    public static ArrayList<OrderItem> orderItems = new ArrayList<>();
    public static int newOrderId = 900 + DataIO.allOrders.size() + 1;

    public void addOrder (String address, OrderType orderType) throws Exception {
        Customer customer = (Customer) FoodOrderSystem.currentUser;
        OrderType type = orderType;
        RefundStatus refund = RefundStatus.NO;
        OrderStatus status = OrderStatus.PENDING;

        Order order = new Order(
            newOrderId,
            800 + DataIO.allOrders.size() + 1,
            customer.getId(),
            address,
            LocalDateTime.now(),
            type,
            refund,
            status
        );
        DataIO.allOrders.add(order);
        DataIO.allOrderItems.addAll(orderItems);
        DataIO.writeOrder();
        DataIO.writeOrderItem();
    }

    public void storeOrderItems(ArrayList<Menu> orderMenuList) {
        for (Menu menu : orderMenuList) {
            int menuId = menu.getId();
            String itemName = menu.getName();
            int quantity = getExistingOrderItemMenuId(menuId);
            double price = menu.getPrice() * quantity;

            OrderItem orderItem = new OrderItem(
                newOrderId,
                menuId,
                itemName,
                quantity,
                price
            );
            orderItems.add(orderItem);
        }
    }

    private int getExistingOrderItemMenuId(int menuId) {
        int totalQuantity = 0;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getMenuId() == menuId) {
                totalQuantity += orderItem.getQuantity();
            }
        }
        return totalQuantity;
    }

    public static ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }
}
