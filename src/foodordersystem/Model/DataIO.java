package foodordersystem.Model;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import foodordersystem.Enum.MenuCategory;
import foodordersystem.Enum.NotificationStatus;
import foodordersystem.Enum.OrderStatus;
import foodordersystem.Enum.OrderType;
import foodordersystem.Enum.Rating;
import foodordersystem.Enum.RefundStatus;
import foodordersystem.Enum.TaskStatus;
import foodordersystem.Enum.UserRole;

public class DataIO {
    private static final String USER_FILE_PATH = "database/user.txt";
    private static final String ORDER_FILE_PATH = "database/order.txt";
    private static final String ORDERITEM_FILE_PATH = "database/orderitem.txt";
    private static final String MENU_FILE_PATH = "database/menu.txt";
    private static final String TASK_FILE_PATH = "database/task.txt";
    private static final String RUNNER_AVAILABLE_FILE_PATH = "database/runneravailable.txt";
    //private static final String INVOICE_FILE_PATH = "database/invoice.txt";
    private static final String DWALLET_FILE_PATH = "database/dwallet.txt";
    private static final String NOTIFICATION_FILE_PATH = "database/notification.txt";

    public static ArrayList<Order> allOrders = new ArrayList<Order>();
    public static ArrayList<OrderItem> allOrderItems = new ArrayList<OrderItem>();
    public static ArrayList<User> allUsers = new ArrayList<User>();
    public static ArrayList<Menu> allMenus = new ArrayList<Menu>();
    // public static ArrayList<Invoice> allInvoices = new ArrayList<Invoice>();
    public static ArrayList<Task> allTasks = new ArrayList<Task>();
    public static ArrayList<Object[]> allRunners = new ArrayList<>();
    public static ArrayList<Dwallet> allDwallet = new ArrayList<Dwallet>();
    public static ArrayList<Notification> allNotifications = new ArrayList<Notification>();

    public static void readData () {
        try {
            readUser();
            readOrder();
            readMenu();
            readOrderItem();
            readTask();
            readRunnerAvailable();
            readDwallet();
            readNotification();
        } catch (Exception e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
    }

    public static void readUser () {
        try {
            Scanner sc = new Scanner(new File(USER_FILE_PATH));
            while (sc.hasNext()) {
                int id  = Integer.parseInt(sc.nextLine());
                String username  = sc.nextLine();
                int password  = Integer.parseInt(sc.nextLine());
                double balance  = Double.parseDouble(sc.nextLine());
                UserRole role  = UserRole.valueOf(sc.nextLine().toUpperCase());
                allUsers.add(new User(
                    id,
                    username,
                    password,
                    balance,
                    role
                ));
                sc.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Error reading " + USER_FILE_PATH + ": " + e);
        }
    }

    public static void writeUser () {
        try {
            PrintWriter pw = new PrintWriter(USER_FILE_PATH);
            for (User user : allUsers) {
                pw.println(user.getId());
                pw.println(user.getUsername());
                pw.println(user.getPassword());
                pw.println(user.getBalance());
                pw.println(user.getRole());
                pw.println();
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Error writing " + USER_FILE_PATH + ": " + e);
        }
    }

    public static User checkUsername (String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void readMenu () {
        try {
            Scanner sc = new Scanner(new File(MENU_FILE_PATH));
            while (sc.hasNext()) {
                int id  = Integer.parseInt(sc.nextLine());
                String name  = sc.nextLine();
                double price  = Double.parseDouble(sc.nextLine());
                MenuCategory category  = MenuCategory.valueOf(sc.nextLine().toUpperCase());
                String review  = sc.nextLine();
                int vendor = Integer.parseInt(sc.nextLine());
                int reviewUserId = Integer.parseInt(sc.nextLine());
                allMenus.add(new Menu(
                    id,
                    name,
                    price,
                    category,
                    review,
                    vendor,
                    reviewUserId
                ));
                sc.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Error reading " + MENU_FILE_PATH + ": " + e);
        }
    }

    public static void writeMenu () {
        try {
            PrintWriter pw = new PrintWriter(MENU_FILE_PATH);
            for (Menu menu : allMenus) {
                pw.println(menu.getId());
                pw.println(menu.getName());
                pw.println(menu.getPrice());
                pw.println(menu.getCategory());
                pw.println(menu.getReview());
                pw.println(menu.getVendorId());
                pw.println(menu.getReviewUserId());
                pw.println();
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Error writing " + MENU_FILE_PATH + ": " + e);
        }
    }

    public static Menu checkMenuName (String name) {
        for (Menu menu : allMenus) {
            if (menu.getName().equals(name)) {
                return menu;
            }
        }
        return null;
    }

    public static void readOrder () {
        try {
            Scanner sc = new Scanner(new File(ORDER_FILE_PATH));
            while (sc.hasNext()) {
                int orderId = Integer.parseInt(sc.nextLine());
		        int invoiceId  = Integer.parseInt(sc.nextLine());
		        int customerId  = Integer.parseInt(sc.nextLine());
		        int vendorId  = Integer.parseInt(sc.nextLine());
		        String address  = sc.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime localDateTime = LocalDateTime.parse(sc.nextLine(), formatter);
		        OrderType type  = OrderType.valueOf(sc.nextLine());
                double deliveryCost = Double.parseDouble(sc.nextLine());
                double totalCost = Double.parseDouble(sc.nextLine());
                String review  = sc.nextLine();
		        Rating rating  = Rating.valueOf(sc.nextLine());
		        RefundStatus refund  = RefundStatus.valueOf(sc.nextLine());
		        OrderStatus status  = OrderStatus.valueOf(sc.nextLine());
                sc.nextLine();
                allOrders.add(new Order(
                    orderId,
                    invoiceId,
                    customerId,
                    vendorId,
                    address,
                    localDateTime,
                    type,
                    deliveryCost,
                    totalCost,
                    review,
                    rating,
                    refund,
                    status
                ));
            }
        } catch (Exception e) {
            System.out.println("Error reading " + ORDER_FILE_PATH + ": " + e);
        }
    }

    public static void writeOrder () {
        try {
            PrintWriter pw = new PrintWriter(ORDER_FILE_PATH);
            for (Order order : allOrders) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = order.getDate().format(formatter);
                pw.println(order.getId());
                pw.println(order.getInvoiceId());
                pw.println(order.getCustomerId());
                pw.println(order.getVendorId());
                pw.println(order.getAddress());
                pw.println(formattedDateTime);
                pw.println(order.getOrderType());
                pw.println(order.getDeliveryCost());
                pw.println(order.getReview());
                pw.println(order.getRating());
                pw.println(order.getRefundStatus());
                pw.println(order.getOrderStatus());
                pw.println();
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Error writing " + ORDER_FILE_PATH + ": " + e);
        }
    }

    public static void readOrderItem () {
        try {
            Scanner sc = new Scanner(new File(ORDERITEM_FILE_PATH));
            while (sc.hasNext()) {
                int orderId = Integer.parseInt(sc.nextLine());
                int menuId  = Integer.parseInt(sc.nextLine());
                int vendorId  = Integer.parseInt(sc.nextLine());
                String itemName  = sc.nextLine();
                int quantity  = Integer.parseInt(sc.nextLine());
                double price  = Double.parseDouble(sc.nextLine());
                sc.nextLine();
                allOrderItems.add(new OrderItem(
                    orderId,
                    menuId,
                    vendorId,
                    itemName,
                    quantity,
                    price
                ));
            }
        } catch (Exception e) {
            System.out.println("Error reading " + ORDERITEM_FILE_PATH + ": " + e);
        }
    }

    public static void writeOrderItem () {
        try {
            PrintWriter pw = new PrintWriter(ORDERITEM_FILE_PATH);
            for (OrderItem orderItem : allOrderItems) {
                pw.println(orderItem.getOrderId());
                pw.println(orderItem.getMenuId());
                pw.println(orderItem.getVendorId());
                pw.println(orderItem.getItemName());
                pw.println(orderItem.getQuantity());
                pw.println(orderItem.getPrice());
                pw.println();
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Error writing " + ORDERITEM_FILE_PATH + ": " + e);
        }
    }

    public static void readTask () {
        try {
            Scanner sc = new Scanner(new File(TASK_FILE_PATH));
            while (sc.hasNext()) {
                int id  = Integer.parseInt(sc.nextLine());
                int orderId  = Integer.parseInt(sc.nextLine());
                int customerId  = Integer.parseInt(sc.nextLine());
                int vendorId  = Integer.parseInt(sc.nextLine());
                int runnerId  = Integer.parseInt(sc.nextLine());
                String address = sc.nextLine();
                TaskStatus status  = TaskStatus.valueOf(sc.nextLine().toUpperCase());
                String review  = sc.nextLine();
                int reviewUserId  = Integer.parseInt(sc.nextLine());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime localDateTime = LocalDateTime.parse(sc.nextLine(), formatter);
                sc.nextLine();
                allTasks.add(new Task(
                    id,
                    orderId,
                    customerId,
                    vendorId,
                    runnerId,
                    address,
                    status,
                    review,
                    reviewUserId,
                    localDateTime
                ));
            }
        } catch (Exception e) {
            System.out.println("Error reading " + TASK_FILE_PATH + ": " + e);
        }
    }

    public static void writeTask () {
        try {
            PrintWriter pw = new PrintWriter(TASK_FILE_PATH);
            for (Task task : allTasks) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = task.getDateTime().format(formatter);

                pw.println(task.getId());
                pw.println(task.getOrderId());
                pw.println(task.getCustomerId());
                pw.println(task.getVendorId());
                pw.println(task.getRunnerId());
                pw.println(task.getAddress());
                pw.println(task.getStatus());
                pw.println(task.getReview());
                pw.println(task.getReviewUserId());
                pw.println(formattedDateTime);
                pw.println();
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Error writing " + TASK_FILE_PATH + ": " + e);
        }
    }

    public static void readRunnerAvailable () {
        try {
            Scanner sc = new Scanner(new File(RUNNER_AVAILABLE_FILE_PATH));
            while (sc.hasNext()) {
                int runnerId  = Integer.parseInt(sc.nextLine());
                boolean available = Boolean.parseBoolean(sc.nextLine().toString());
                sc.nextLine();
                allRunners.add(new Object[]{
                    runnerId,
                    available
                });
            }
        } catch (Exception e) {
            System.out.println("Error reading " + RUNNER_AVAILABLE_FILE_PATH + ": " + e);
        }
    }

    public static void writeRunnerAvailable () {
        try {
            PrintWriter pw = new PrintWriter(RUNNER_AVAILABLE_FILE_PATH);
            for (Object[] runner : allRunners) {
                System.out.println("Runner " + runner[0] + " is available: " + runner[1]);
                pw.println(runner[0]);
                pw.println(runner[1].toString());
                pw.println();
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Error writing " + RUNNER_AVAILABLE_FILE_PATH + ": " + e);
        }
    }
    
    public static void readDwallet () {
        try {
            Scanner sc = new Scanner(new File(DWALLET_FILE_PATH));
            while (sc.hasNext()) {
                int id  = Integer.parseInt(sc.nextLine());
                String username  = sc.nextLine();
                double credit  = Double.parseDouble(sc.nextLine());
                allDwallet.add(new Dwallet(
                    id,
                    username,
                    credit
                ));
                sc.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Error reading " + DWALLET_FILE_PATH + ": " + e);
        }
    }
    
    public static void writeDwallet () {
        try {
            PrintWriter pw = new PrintWriter(DWALLET_FILE_PATH);
            for (Dwallet dwallet : allDwallet) {
                pw.println(dwallet.getId());
                pw.println(dwallet.getUsername());
                pw.println(dwallet.getBalance());
                pw.println();
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Error writing " + DWALLET_FILE_PATH + ": " + e);
        }
    }

    public static void readNotification () {
        try {
            Scanner sc = new Scanner(new File(NOTIFICATION_FILE_PATH));
            while (sc.hasNext()) {
                int id  = Integer.parseInt(sc.nextLine());
                int userId  = Integer.parseInt(sc.nextLine());
                String message  = sc.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime localDateTime = LocalDateTime.parse(sc.nextLine(), formatter);
                NotificationStatus status  = NotificationStatus.valueOf(sc.nextLine());
                allNotifications.add(new Notification(
                    id,
                    userId,
                    message,
                    localDateTime,
                    status
                ));
                sc.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Error reading " + NOTIFICATION_FILE_PATH + ": " + e);
        }
    }

    public static void writeNotification () {
        try {
            PrintWriter pw = new PrintWriter(NOTIFICATION_FILE_PATH);
            for (Notification notification : allNotifications) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = notification.getDateTime().format(formatter);
                pw.println(notification.getId());
                pw.println(notification.getUserId());
                pw.println(notification.getMessage());
                pw.println(formattedDateTime);
                pw.println(notification.getStatus());
                pw.println();
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Error writing " + NOTIFICATION_FILE_PATH + ": " + e);
        }
    }
}
