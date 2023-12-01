package foodordersystem.Page;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class RunnerRevenueReportPage implements ActionListener {
    private JFrame RunnerRevenueFrame;
    private JButton Backbutton;
    private JTable RunnerRevenueTable;
    private static RunnerRevenueReportPage instance;

    public RunnerRevenueReportPage() {
        RunnerRevenueFrame = new JFrame();
        RunnerRevenueFrame.setSize(500, 300);
        RunnerRevenueFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RunnerRevenueFrame.setLayout(new FlowLayout());
        
        String[] Column = {"Month", "Service", "TotalRevenue"};
        String[][] data = {{"January", "Home cleaning", "RM1090"},
                          {"Februari","Home Cleaning","RM 1000"}};
        RunnerRevenueTable = new JTable(data, Column);
        RunnerRevenueTable.setSize(500, 200);
        RunnerRevenueTable.setVisible(true);
 

        Backbutton = new JButton("Back");
        Backbutton.setPreferredSize(new Dimension(50, 50));
        Backbutton.addActionListener(this);

        
        RunnerRevenueFrame.setVisible(true);
        RunnerRevenueFrame.setLayout(new BorderLayout());
        RunnerRevenueFrame.add(RunnerRevenueTable.getTableHeader(), BorderLayout.NORTH);
        RunnerRevenueFrame.add(RunnerRevenueTable, BorderLayout.CENTER);
        RunnerRevenueFrame.add(Backbutton, BorderLayout.SOUTH);
        RunnerRevenueFrame.setLocationRelativeTo(null);
    }

    public static RunnerRevenueReportPage getRunnerRevenueReportPageObj() {
        if (instance == null) {
            instance = new RunnerRevenueReportPage();
        }
        return instance;
    }

    @Override
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