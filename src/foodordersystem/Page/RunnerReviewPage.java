package foodordersystem.Page;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import foodordersystem.FoodOrderSystem;
import foodordersystem.Manager.TaskManager;
import foodordersystem.Model.DataIO;
import foodordersystem.Model.Task;
import foodordersystem.Model.User;

public class RunnerReviewPage extends ReviewPage {
    private JTable runnerReviewTable;
    private DefaultTableModel runnerReviewTableModel;
    private JScrollPane runnerReviewScrollPane;
    private JButton backBtn;

    public RunnerReviewPage () {
        super("Runner Review Page");

        backBtn = new JButton("Back");
        backBtn.addActionListener(this);

        actionBtnPanel = new JPanel();
        actionBtnPanel.add(backBtn);
        reviewPage.add(runnerReviewScrollPane);
        reviewPage.add(actionBtnPanel);

        reviewPage.pack();
        reviewPage.setLocationRelativeTo(null);
        reviewPage.setVisible(false);
    }

    @Override
    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == backBtn) {
                RunnerDashboardPage.getRunnerDashboardPageObj().getRunnerDashboardPage().setVisible(true);
                reviewPage.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
            JOptionPane.showMessageDialog(reviewPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showReviewTableData () {
        runnerReviewTableModel = new DefaultTableModel(new Object[] {"Order ID", "Customer ID", "Customer Name", "Rating", "Review"}, 0);
        runnerReviewTable = new JTable(runnerReviewTableModel);
        runnerReviewScrollPane = new JScrollPane(runnerReviewTable);

        for (Task task : TaskManager.getAllTasks()) {
            if (task.getRunnerId() == FoodOrderSystem.currentUser.getId()) {
                String customerName = "";
                for (User user : DataIO.allUsers) {
                    if (user.getId() == task.getCustomerId()) {
                        customerName = user.getUsername();
                    }
                }
                runnerReviewTableModel.addRow(new Object[] {
                    task.getId(),
                    task.getCustomerId(),
                    customerName,
                    task.getRating() + "/ FIVE",
                    task.getReview()
                });
            }
        }
    }
}
