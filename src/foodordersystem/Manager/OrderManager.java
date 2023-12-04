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
    public int newOrderId = 900 + DataIO.allOrders.size() + 1;
    public int firstVendorId;

    public static ArrayList<Order> getAllOrders () {
        return DataIO.allOrders;
    }
    
    public static ArrayList<OrderItem> getAllOrderItems () {
        return DataIO.allOrderItems;
    }

    public static Order getOrderById (int orderId) {
        for (Order order : getAllOrders()) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public void addOrder (String address, OrderType orderType) throws Exception {
        Customer customer = (Customer) FoodOrderSystem.currentUser;
        OrderType type = orderType;
        RefundStatus refund = RefundStatus.NO;
        OrderStatus status = OrderStatus.PENDING;

        Order order = new Order(
            newOrderId,
            800 + DataIO.allOrders.size() + 1,
            customer.getId(),
            firstVendorId,
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
        firstVendorId = -1;

        for (int i = 0; i < orderMenuList.size(); i++) {
            Object[] itemDetails = orderMenuList.get(i);
            Menu menu = (Menu) itemDetails[0];
            int menuId = menu.getId();
            int vendorId = menu.getVendorId();
            int quantity = (int) itemDetails[1];
            double price = menu.getPrice() * quantity;

            if (i == 0) {
                firstVendorId = vendorId;
            } else if (vendorId != firstVendorId) {
                throw new IllegalArgumentException("You can only order from one vendor at a time.");
            }

            OrderItem orderItem = new OrderItem(
                newOrderId,
                menuId,
                vendorId,
                menu.getName(),
                quantity,
                price
            );
            orderItems.add(orderItem);
        }
    }

    public static void updateOrderStatus (int orderId, OrderStatus orderStatus) throws Exception {
        for (Order order : DataIO.allOrders) {
            if (order.getId() == orderId) {
                // need to call the invoice manager to update the invoice status
                if (orderStatus == OrderStatus.ACCEPT && order.getOrderStatus() == OrderStatus.PENDING) {
                    order.setOrderStatus(orderStatus);
                }

                if (
                    (orderStatus == OrderStatus.REJECT || orderStatus == OrderStatus.CANCELLED)
                    && order.getOrderStatus() == OrderStatus.PENDING
                ) {
                    order.setRefundStatus(RefundStatus.YES);
                    order.setOrderStatus(orderStatus);
                }

                if (
                    orderStatus == OrderStatus.HANDOVER
                    && order.getOrderType() == OrderType.DELIVERY
                    && order.getOrderStatus() == OrderStatus.ACCEPT
                ) {
                    System.out.println("Order id " + order.getId());
                    if (TaskManager.createTask(order.getId())) {
                        order.setOrderStatus(orderStatus);
                    } else {
                        // need to update the customer to change the order type to dine in or takeaway
                        // by notify the customer
                        throw new Exception("Runner not available.");
                    }
                }

                if (orderStatus == OrderStatus.COMPLETED && order.getOrderStatus() == OrderStatus.HANDOVER) {
                    order.setOrderStatus(orderStatus);
                }
            }
        }
        DataIO.writeOrder();
    }

    public void reOrder (Order existingOrder) throws Exception {
        String address = existingOrder.getAddress();
        OrderType orderType = existingOrder.getOrderType();

        storeOrderItems(existingOrder.getOrderItemsWithMenuList());
        addOrder(address, orderType);
    }
}
