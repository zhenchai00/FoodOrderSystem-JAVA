package foodordersystem.Model;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataIO {
    private static final String USER_FILE_PATH = "user.txt";
    private static final String ORDER_FILE_PATH = "order.txt";

    public static ArrayList<Order> allOrders = new ArrayList<Order>();
    public static ArrayList<User> allUsers = new ArrayList<User>();

    public static void readData () {
        try {
            readUser();
            readOrder();
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
                UserRole role  = UserRole.valueOf(sc.nextLine().toUpperCase());
                allUsers.add(new User(
                    id,
                    username,
                    password,
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

    public static void readOrder () {
        try {
            Scanner sc = new Scanner(new File(ORDER_FILE_PATH));
            while (sc.hasNext()) {
		        int orderDetailId  = Integer.parseInt(sc.nextLine());
		        int invoiceId  = Integer.parseInt(sc.nextLine());
		        int customerId  = Integer.parseInt(sc.nextLine());
		        String address  = sc.nextLine();
		        String date  = sc.nextLine();
		        OrderType type  = OrderType.valueOf(sc.nextLine());
		        RefundStatus refund  = RefundStatus.valueOf(sc.nextLine());
		        OrderStatus status  = OrderStatus.valueOf(sc.nextLine());
                sc.nextLine();
                allOrders.add(new Order(
                    orderDetailId,
                    invoiceId,
                    customerId,
                    address,
                    date,
                    type,
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
                pw.println(order.getOrderDetailId());
                pw.println(order.getInvoiceId());
                pw.println(order.getCustomerId());
                pw.println(order.getAddress());
                pw.println(order.getDate());
                pw.println(order.getOrderType());
                pw.println(order.getRefundStatus());
                pw.println(order.getOrderStatus());
                pw.println();
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Error writing " + ORDER_FILE_PATH + ": " + e);
        }
    }
}
