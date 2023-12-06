package foodordersystem.Page;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import foodordersystem.FoodOrderSystem;
import foodordersystem.Manager.OrderManager;

public class RunnerRevenueReportPage implements ActionListener {
    private JFrame RunnerRevenueFrame;
    private JButton Backbutton;
    private JLabel dailyLabel, monthlyLabel, yearlyLabel;

    public RunnerRevenueReportPage() {
        RunnerRevenueFrame = new JFrame("Runner Revenue Report");
        RunnerRevenueFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RunnerRevenueFrame.setLayout(new BorderLayout());

        int runnerId = FoodOrderSystem.currentUser.getId();

        LocalDate dailyDate = LocalDate.now();
        double dailyRevenue = OrderManager.calculateDailyDeliveryCost(runnerId, dailyDate);
        dailyLabel = new JLabel("Daily Revenue: RM " + dailyRevenue);
        dailyLabel.setFont(new Font(null, Font.BOLD, 20));
        dailyLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        YearMonth currentMonth = YearMonth.now();
        double monthlyRevenue = OrderManager.calculateMonthlyDeliveryCost(runnerId, currentMonth);
        monthlyLabel = new JLabel("Monthly Revenue: RM " + monthlyRevenue);
        monthlyLabel.setFont(new Font(null, Font.BOLD, 20));
        monthlyLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

        Year currentYear = Year.now();
        double yearlyRevenue = OrderManager.calculateYearlyDeliveryCost(runnerId, currentYear);
        yearlyLabel = new JLabel("Yearly Revenue: RM " + yearlyRevenue);
        yearlyLabel.setFont(new Font(null, Font.BOLD, 20));
        yearlyLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

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