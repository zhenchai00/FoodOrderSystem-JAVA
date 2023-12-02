package foodordersystem.Page;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import foodordersystem.FoodOrderSystem;
import foodordersystem.Interface.DashboardPage;

public class RunnerDashboardPage implements DashboardPage, ActionListener {
    private JFrame runnerDashboardPage;
    private JButton taskBtn, revenueBtn, reviewBtn, logoutBtn;
    private JLabel welcomeLabel;

    private static RunnerDashboardPage instance;

    private RunnerDashboardPage () {
        runnerDashboardPage = new JFrame("Runner Dashboard");
        runnerDashboardPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        runnerDashboardPage.setLayout(new BoxLayout(runnerDashboardPage.getContentPane(), BoxLayout.Y_AXIS));

        JPanel headerPanel = new JPanel();
        welcomeLabel = new JLabel("Welcome, " + FoodOrderSystem.currentUser.getUsername().toUpperCase() + "!");
        welcomeLabel.setFont(new Font(null, Font.BOLD, 20));
        headerPanel.add(welcomeLabel);

        JPanel buttonPanel = new JPanel();
        taskBtn = new JButton("Task");
        taskBtn.addActionListener(this);
        buttonPanel.add(taskBtn);

        revenueBtn = new JButton("Revenue");
        revenueBtn.addActionListener(this);
        buttonPanel.add(revenueBtn);

        reviewBtn = new JButton("Review");
        reviewBtn.addActionListener(this);
        buttonPanel.add(reviewBtn);

        logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(this);
        buttonPanel.add(logoutBtn);

        runnerDashboardPage.add(headerPanel);
        runnerDashboardPage.add(buttonPanel);

        runnerDashboardPage.pack();
        runnerDashboardPage.setLocationRelativeTo(null);
        runnerDashboardPage.setVisible(false);
    }

    public static RunnerDashboardPage getRunnerDashboardPageObj() {
        if (instance == null) {
            instance = new RunnerDashboardPage();
        }
        return instance;
    }

    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == taskBtn) {
              RunnerTaskPage.getRunnerTaskPageObj().getRunnerTaskPage().setVisible(true);
            } else if (event.getSource() == revenueBtn) {
              RunnerRevenueReportPage.getRunnerRevenueReportPageObj().getRunnerRevenueReportPage().setVisible(true);
            } else if (event.getSource() == reviewBtn) {

            } else if (event.getSource() == logoutBtn) {
                logout();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            JOptionPane.showMessageDialog(runnerDashboardPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JFrame getRunnerDashboardPage() {
        return runnerDashboardPage;
    }

    public void logout() {
        FoodOrderSystem.loginPage.getLoginPage().setVisible(true);
        runnerDashboardPage.setVisible(false);
    }
}
