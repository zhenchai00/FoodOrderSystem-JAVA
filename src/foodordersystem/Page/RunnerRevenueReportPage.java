package foodordersystem.Page;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import foodordersystem.FoodOrderSystem;

public class RunnerRevenueReportPage implements ActionListener {
    private JFrame RunnerRevenueFrame;
    private JButton Backbutton;
    private JLabel dailyLabel, monthlyLabel, yearlyLabel;

    public RunnerRevenueReportPage() {
        RunnerRevenueFrame = new JFrame("Runner Revenue Report");
        RunnerRevenueFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RunnerRevenueFrame.setLayout(new BorderLayout());

        int runnerId = FoodOrderSystem.currentUser.getId();

        // get the revenue from order mananer by getting the runner id

        Backbutton = new JButton("Back");
        Backbutton.addActionListener(this);

        JPanel revenuePanel = new JPanel();
        revenuePanel.setLayout(new BoxLayout(revenuePanel, BoxLayout.Y_AXIS));
        revenuePanel.add(dailyLabel);
        revenuePanel.add(monthlyLabel);
        revenuePanel.add(yearlyLabel);

        RunnerRevenueFrame.add(revenuePanel, BorderLayout.CENTER);
        RunnerRevenueFrame.add(Backbutton, BorderLayout.SOUTH);

        RunnerRevenueFrame.pack();
        RunnerRevenueFrame.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent Event) {
        try {
            if (Event.getSource() == Backbutton) {
                RunnerDashboardPage.getRunnerDashboardPageObj().getRunnerDashboardPage().setVisible(true);
                RunnerRevenueFrame.setVisible(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(RunnerRevenueFrame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JFrame getRunnerRevenueReportPage() {
        return RunnerRevenueFrame;
    }
}