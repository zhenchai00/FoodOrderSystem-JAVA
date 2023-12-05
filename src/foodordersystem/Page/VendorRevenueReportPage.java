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

import foodordersystem.FoodOrderSystem;
import foodordersystem.Manager.OrderManager;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VendorRevenueReportPage implements ActionListener {
    private JFrame VendorRevenueFrame;
    private JButton Backbutton;
    private JLabel dailyLabel, monthlyLabel, yearlyLabel;

    public VendorRevenueReportPage() {

        VendorRevenueFrame = new JFrame("Vendor Revenue Report");
        VendorRevenueFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        VendorRevenueFrame.setLayout(new BorderLayout());

        int vendorId = FoodOrderSystem.currentUser.getId();

        LocalDate currentDate = LocalDate.now();
        double dailyRevenue = OrderManager.calculateDailyRevenue(vendorId, currentDate);

        YearMonth currentMonth = YearMonth.now();
        double monthlyRevenue = OrderManager.calculateMonthlyRevenue(vendorId, currentMonth);

        Year currentYear = Year.now();
        double yearlyRevenue = OrderManager.calculateYearlyRevenue(vendorId, currentYear);

        dailyLabel = new JLabel("Daily Revenue: RM " + dailyRevenue);
        dailyLabel.setFont(new Font(null, Font.BOLD, 20));
        dailyLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

        monthlyLabel = new JLabel("Monthly Revenue: RM " + monthlyRevenue);
        monthlyLabel.setFont(new Font(null, Font.BOLD, 20));
        monthlyLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

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

        VendorRevenueFrame.add(revenuePanel, BorderLayout.CENTER);
        VendorRevenueFrame.add(Backbutton, BorderLayout.SOUTH);

        VendorRevenueFrame.pack();
        VendorRevenueFrame.setLocationRelativeTo(null);
        VendorRevenueFrame.setVisible(false);
    }

    public void actionPerformed(ActionEvent Event) {
        try {
            if (Event.getSource() == Backbutton) {
                VendorDashboardPage.getVendorDashboardPageObj().getVendorDashboardPage().setVisible(true);
                VendorRevenueFrame.setVisible(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(VendorRevenueFrame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JFrame getVendorRevenueReportPage() {
        return VendorRevenueFrame;
    }
}
