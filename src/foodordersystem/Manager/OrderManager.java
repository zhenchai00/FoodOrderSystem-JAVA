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
    private ArrayList<OrderItem> orderItems = new ArrayList<>();
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

    public void storeOrderItems(ArrayList<Object[]> orderMenuList) {
        orderItems.clear();
        for (int i = 0; i < orderMenuList.size(); i++) {
            Object[] itemDetails = orderMenuList.get(i);
            Menu menu = (Menu) itemDetails[0];
            int menuId = menu.getId();
            int quantity = (int) itemDetails[1];
            double price = menu.getPrice() * quantity;

            OrderItem orderItem = new OrderItem(
                newOrderId,
                menuId,
                menu.getName(),
                quantity,
                price
            );
            orderItems.add(orderItem);
        }
    }

    public static ArrayList<Order> getAllOrders () {
        return DataIO.allOrders;
    }
    
    public static ArrayList<OrderItem> getAllOrderItems () {
        return DataIO.allOrderItems;
    }
}
