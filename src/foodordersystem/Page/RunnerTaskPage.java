package foodordersystem.Page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import foodordersystem.FoodOrderSystem;
import foodordersystem.Enum.TaskStatus;
import foodordersystem.Manager.TaskManager;
import foodordersystem.Model.DataIO;
import foodordersystem.Model.Task;
import foodordersystem.Model.User;

public class RunnerTaskPage implements ActionListener {
    private JFrame RunnerTaskFrame;
    private JButton AcceptButton,RejectButton,UpdateStatButton, BackButton;
    private JTable RunnerTaskTable; 
    private DefaultTableModel RunnerTaskTableModel;
    private JScrollPane RunnerTaskScrollPane;
    private JPanel actionBtnPanel;

    public RunnerTaskPage(){
        RunnerTaskFrame = new JFrame();
        RunnerTaskFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RunnerTaskFrame.setLayout(new BoxLayout(RunnerTaskFrame.getContentPane(), BoxLayout.Y_AXIS));

        RunnerTaskTableModel = new DefaultTableModel(new Object[]{"ID", "Order ID", "Customer Name", "Status"}, 0);
        RunnerTaskTable = new JTable(RunnerTaskTableModel);
        RunnerTaskScrollPane = new JScrollPane(RunnerTaskTable);
        showTaskPage();
    
        AcceptButton = new JButton("Accept");
        AcceptButton.addActionListener(this);
        RejectButton = new JButton("Reject");
        RejectButton.addActionListener(this);
        UpdateStatButton = new JButton("Update");
        UpdateStatButton.addActionListener(this);
        BackButton = new JButton("Back");
        BackButton.addActionListener(this);

        actionBtnPanel = new JPanel();
        actionBtnPanel.add(AcceptButton);
        actionBtnPanel.add(RejectButton);
        actionBtnPanel.add(UpdateStatButton);
        actionBtnPanel.add(BackButton);
    
        RunnerTaskFrame.add(RunnerTaskScrollPane);
        RunnerTaskFrame.add(actionBtnPanel);

        RunnerTaskFrame.pack();
        RunnerTaskFrame.setLocationRelativeTo(null);
        RunnerTaskFrame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event){
        try{ 
            if (event.getSource() == AcceptButton) {
                int selectedRow = RunnerTaskTable.getSelectedRow();
                int orderId = (int) RunnerTaskTableModel.getValueAt(selectedRow, 1);
                TaskManager.updateTaskStatus(orderId, TaskStatus.ACCEPT);
                RunnerTaskTableModel.setValueAt(TaskStatus.ACCEPT.toString(), selectedRow, 3);
            } else if (event.getSource() == RejectButton) {
                int selectedRow = RunnerTaskTable.getSelectedRow();
                int orderId = (int) RunnerTaskTableModel.getValueAt(selectedRow, 1);
                TaskManager.updateTaskStatus(orderId, TaskStatus.REJECT);
                RunnerTaskTableModel.setValueAt(TaskStatus.REJECT.toString(), selectedRow, 3);
            } else if (event.getSource() == UpdateStatButton) {
                int selectedRow = RunnerTaskTable.getSelectedRow();
                int orderId = (int) RunnerTaskTableModel.getValueAt(selectedRow, 1);
                TaskManager.updateTaskStatus(orderId, TaskStatus.COMPLETED);
                RunnerTaskTableModel.setValueAt(TaskStatus.COMPLETED.toString(), selectedRow, 3);
            } else if (event.getSource() == BackButton) {
                RunnerDashboardPage.getRunnerDashboardPageObj().getRunnerDashboardPage().setVisible(true);
                RunnerTaskFrame.setVisible(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(RunnerTaskFrame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JFrame getRunnerTaskPage() {
        return RunnerTaskFrame;
    }

    public void showTaskPage() {
        RunnerTaskTableModel = new DefaultTableModel(new Object[]{"ID", "Order ID", "Customer Name", "Status"}, 0);
        RunnerTaskTable = new JTable(RunnerTaskTableModel);
        RunnerTaskScrollPane = new JScrollPane(RunnerTaskTable);
        ArrayList<Task> allTasks = TaskManager.getAllTasks();
        for (Task task : allTasks) {
            if (task.getRunnerId() == FoodOrderSystem.currentUser.getId()) {
                String customerName = "";
                for (User user : DataIO.allUsers) {
                    if (user.getId() == task.getCustomerId()) {
                        customerName = user.getUsername();
                    }
                }
                RunnerTaskTableModel.addRow(new Object[]{task.getId(), task.getOrderId(), customerName, task.getStatus()});
            }
        }
    }
}
