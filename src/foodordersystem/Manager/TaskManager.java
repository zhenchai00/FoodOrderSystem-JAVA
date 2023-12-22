package foodordersystem.Manager;

import java.time.LocalDateTime;
import java.util.ArrayList;

import foodordersystem.Enum.OrderStatus;
import foodordersystem.Enum.OrderType;
import foodordersystem.Enum.Rating;
import foodordersystem.Enum.TaskStatus;
import foodordersystem.Model.DataIO;
import foodordersystem.Model.Order;
import foodordersystem.Model.Task;

public class TaskManager {
    public static ArrayList<Task> getAllTasks () {
        return DataIO.allTasks;
    }
    public static boolean createTask (int orderId) throws Exception {
        int number = DataIO.allTasks.size() + 1 + 600;
        ArrayList<Object> runnerAvailable = getRunnerAvailable(0);
        if (!(boolean) runnerAvailable.get(1)) {
            throw new Exception("No runner available");
        }

        for (Order order : OrderManager.getAllOrders()) {
            if (
                order.getId() == orderId
                && order.getOrderType() == OrderType.DELIVERY
                && runnerAvailable.get(1).equals(true)
            ) {
                Task task = new Task(
                    number,
                    order.getId(),
                    order.getCustomerId(),
                    order.getVendorId(),
                    (int) runnerAvailable.get(0),
                    order.getAddress(),
                    TaskStatus.PENDING,
                    "",
                    Rating.NONE,
                    0,
                    LocalDateTime.now()
                );
                DataIO.allTasks.add(task);
                DataIO.writeTask();
                NotificationManager.sendNotification((int) runnerAvailable.get(0), "[TaskID - " + task.getId() + "] You have a new task from Vendor");
                NotificationManager.sendNotification(order.getCustomerId(), "[OrderID - " + task.getOrderId() + "] You have been processed by Vendor");
                return true;
            }
        }
        return false;
    }

    public static void updateTaskStatus (int orderId, TaskStatus status) throws Exception {
        for (Task task : getAllTasks()) {
            if (task.getOrderId() == orderId) {
                if (task.getStatus() == TaskStatus.PENDING && status == TaskStatus.ACCEPT) {
                    updateRunnerAvailable(task.getRunnerId(), false);
                    task.setStatus(status);
                    NotificationManager.sendNotification(task.getCustomerId(), "[OrderID - " + task.getOrderId() + "] Your order has been accepted by Runner.");
                    NotificationManager.sendNotification(task.getVendorId(), "[OrderID - " + task.getOrderId() + "] Your order has been accepted by Runner.");
                }
                if (task.getStatus() == TaskStatus.PENDING && status == TaskStatus.REJECT) {
                    ArrayList<Object> runnerAvailable = getRunnerAvailable(task.getRunnerId());
                    if (runnerAvailable.get(1).equals(true)) {
                        updateRunnerAvailable(task.getRunnerId(), false);
                        task.setRunnerId((int) runnerAvailable.get(0));
                        task.setStatus(TaskStatus.PENDING);
                        NotificationManager.sendNotification(task.getVendorId(), "[OrderID - " + task.getOrderId() + "] Your order has been to a Runner.");
                    } else {
                        updateRunnerAvailable(task.getRunnerId(), true);
                        task.setStatus(status);
                        NotificationManager.sendNotification(task.getCustomerId(), "[OrderID - " + task.getOrderId() + "] Your order has been rejected by Runner. System trying to find another runner.");
                    }
                }
                if (task.getStatus() == TaskStatus.ACCEPT && status == TaskStatus.COMPLETED) {
                    updateRunnerAvailable(task.getRunnerId(), true);
                    task.setStatus(status);
                    OrderManager.updateOrderStatus(orderId, OrderStatus.COMPLETED);
                        NotificationManager.sendNotification(task.getCustomerId(), "[OrderID - " + task.getOrderId() + "] Your order has been delivered by Runner.");
                        NotificationManager.sendNotification(task.getVendorId(), "[OrderID - " + task.getOrderId() + "] Your order has been delivered by Runner.");
                }
                DataIO.writeTask();
                break;
            }
        }
    }

    public static ArrayList<Object> getRunnerAvailable (int excludeRunnerId) {
        ArrayList<Object> runnerAvailable = new ArrayList<>();
        boolean runnerFound = false;
        int runnerId = 0;

        for (Object[] runner : DataIO.allRunners) {
            if ((boolean) runner[1] && (int) runner[0] != excludeRunnerId) {
                runnerId = (int) runner[0];
                runnerFound = true;
                break;
            }
        }

        runnerAvailable.add(runnerId);
        runnerAvailable.add(runnerFound);
        return runnerAvailable;
    }

    private static void updateRunnerAvailable (int runnerId, boolean available) throws Exception {
        for (Object[] runner : DataIO.allRunners) {
            if ((int) runner[0] == runnerId) {
                runner[1] = available;
                DataIO.writeRunnerAvailable();
                break;
            }
        }
    }
}
