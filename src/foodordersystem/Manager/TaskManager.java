package foodordersystem.Manager;

import javax.swing.JOptionPane;

import foodordersystem.Enum.OrderType;
import foodordersystem.Enum.TaskStatus;
import foodordersystem.Model.DataIO;
import foodordersystem.Model.Order;
import foodordersystem.Model.Task;

public class TaskManager {
    public static void createTask (int orderId) throws Exception {
        int number = DataIO.allTasks.size() + 1 + 600;
        int runnerId = 0;
        for (Object[] runner : DataIO.allRunners) {
            if ((boolean) runner[1]) {
                runnerId = (int) runner[0];
                runner[1] = false;
                DataIO.writeRunnerAvailable();
                break;
            } else {
                throw new Exception("No available runner");
            }
        }
        for (Order order : OrderManager.getAllOrders()) {
            if (order.getId() == orderId && order.getOrderType() == OrderType.DELIVERY) {
                Task task = new Task(
                    number,
                    order.getId(),
                    order.getCustomerId(),
                    order.getVendorId(),
                    runnerId,
                    order.getAddress(),
                    TaskStatus.PENDING,
                    ""
                );
                DataIO.allTasks.add(task);
                DataIO.writeTask();
                break;
            }
        }
    }
}
