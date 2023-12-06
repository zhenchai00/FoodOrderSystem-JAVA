package foodordersystem.Manager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;

import foodordersystem.FoodOrderSystem;
import foodordersystem.Enum.OrderStatus;
import foodordersystem.Enum.OrderType;
import foodordersystem.Enum.RefundStatus;
import foodordersystem.Enum.TaskStatus;
import foodordersystem.Model.Customer;
import foodordersystem.Model.DataIO;
import foodordersystem.Model.Menu;
import foodordersystem.Model.Order;
import foodordersystem.Model.OrderItem;
import foodordersystem.Model.Task;

public class OrderManager {
    private ArrayList<OrderItem> orderItems = new ArrayList<>();
    private double deliveryCost;
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

    public void addOrder (String address, OrderType orderType, double deliveryCost) throws Exception {
        this.deliveryCost = deliveryCost;
        
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
            deliveryCost,
            refund,
            status
        );
        DataIO.allOrders.add(order);
        DataIO.allOrderItems.addAll(orderItems);
        DataIO.writeOrder();
        DataIO.writeOrderItem();
        NotificationManager.sendNotification(firstVendorId, "[OrderID - " + order.getId() + " ] You have a new order from " + customer.getUsername() + ".");
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
                    NotificationManager.sendNotification(order.getCustomerId(), "[OrderID - " + order.getId() + "] Your order has been accepted by Vendor.");
                    order.setOrderStatus(orderStatus);
                }

                if (
                    (orderStatus == OrderStatus.REJECT || orderStatus == OrderStatus.CANCELLED)
                    && order.getOrderStatus() == OrderStatus.PENDING
                ) {
                    if (orderStatus == OrderStatus.REJECT) {
                        NotificationManager.sendNotification(order.getCustomerId(), "[OrderID - " + order.getId() + "] Your order has been rejected by Vendor.");
                    }
                    if (orderStatus == OrderStatus.CANCELLED) {
                        NotificationManager.sendNotification(order.getVendorId(), "[OrderID - " + order.getId() + "] Your order has been cancelled.");
                    }
                    // TODO: wenqing - debit balance account
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
                        NotificationManager.sendNotification(order.getCustomerId(), "[OrderID - " + order.getId() + "] Your order is finding Runner.");
                    } else {
                        NotificationManager.sendNotification(orderId, "[OrderID - " + order.getId() + "] Runner not available. Please change to dine in or takeaway.");
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
        addOrder(address, orderType, deliveryCost);
    }

    public static double calculateDailyRevenue (int vendorId, LocalDate date) {
        double dailyRevenue = 0.0;
        for (Order order : getAllOrders()) {
            if (
                order.getVendorId() == vendorId
                && order.getDate().toLocalDate().equals(date)
            ) {
                dailyRevenue += calculateOrderTotal(order);
            }
        }
        return dailyRevenue;
    }

    public static double calculateMonthlyRevenue (int vendorId, YearMonth month) {
        double monthlyRevenue = 0.0;
        for (Order order : getAllOrders()) {
            if (
                order.getVendorId() == vendorId
                && order.getDate().toLocalDate().getYear() == month.getYear()
                && order.getDate().toLocalDate().getMonth() == month.getMonth()
            ) {
                monthlyRevenue += calculateOrderTotal(order);
            }
        }
        return monthlyRevenue;
    }

    public static double calculateYearlyRevenue (int vendorId, Year year) {
        double yearlyRevenue = 0.0;
        for (Order order : getAllOrders()) {
            if (
                order.getVendorId() == vendorId
                && order.getDate().toLocalDate().getYear() == year.getValue()
            ) {
                yearlyRevenue += calculateOrderTotal(order);
            }
        }
        return yearlyRevenue;
    }

    public static double calculateOrderTotal (Order order) {
        double total = 0.0;
        for (OrderItem orderItem : getAllOrderItems()) {
            if (orderItem.getOrderId() == order.getId()) {
                total += orderItem.getPrice();
            }
        }
        return total;
    }

    public static double calculateDailyDeliveryCost (int runnerId, LocalDate date) {
        double dailyDeliveryCost = 0.0;
        for (Task task : TaskManager.getAllTasks()) {
            if (
                task.getRunnerId() == runnerId
                && task.getStatus() == TaskStatus.COMPLETED
                && task.getDateTime().toLocalDate().equals(date)
            ) {
                Order order = getOrderById(task.getOrderId());
                dailyDeliveryCost += order.getDeliveryCost();
            }
        }
        return dailyDeliveryCost;
    }

    public static double calculateMonthlyDeliveryCost (int runnerId, YearMonth month) {
        double monthlyDeliveryCost = 0.0;
        for (Task task : TaskManager.getAllTasks()) {
            if (
                task.getRunnerId() == runnerId
                && task.getStatus() == TaskStatus.COMPLETED
                && task.getDateTime().toLocalDate().getYear() == month.getYear()
                && task.getDateTime().toLocalDate().getMonth() == month.getMonth()
            ) {
                Order order = getOrderById(task.getOrderId());
                monthlyDeliveryCost += order.getDeliveryCost();
            }
        }
        return monthlyDeliveryCost;
    }

    public static double calculateYearlyDeliveryCost (int runnerId, Year year) {
        double yearlyDeliveryCost = 0.0;
        for (Task task : TaskManager.getAllTasks()) {
            if (
                task.getRunnerId() == runnerId
                && task.getStatus() == TaskStatus.COMPLETED
                && task.getDateTime().toLocalDate().getYear() == year.getValue()
            ) {
                Order order = getOrderById(task.getOrderId());
                yearlyDeliveryCost += order.getDeliveryCost();
            }
        }
        return yearlyDeliveryCost;
    }
}
