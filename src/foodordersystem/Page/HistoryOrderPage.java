package foodordersystem.Page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import foodordersystem.FoodOrderSystem;

public abstract class HistoryOrderPage implements ActionListener {
    protected JFrame historyOrderPage;
    protected JButton backBtn;
    protected JPanel actionBtnPanel;
    protected JTable historyOrderTable;
    protected DefaultTableModel historyTableModel;
    protected JScrollPane historyOrderScrollPane;

    public HistoryOrderPage (String title) {
        historyOrderPage = new JFrame(title);
        historyOrderPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        historyOrderPage.setLayout(new BoxLayout(historyOrderPage.getContentPane(), BoxLayout.Y_AXIS));

        showHistoryOrderPage();

        backBtn = new JButton("Back");
        backBtn.addActionListener(this);

        actionBtnPanel = new JPanel();

        historyOrderPage.add(historyOrderScrollPane);
        historyOrderPage.add(actionBtnPanel);

        historyOrderPage.pack();
        historyOrderPage.setLocationRelativeTo(null);
        historyOrderPage.setVisible(false);
    }

    public JFrame getHistoryOrderPage () {
        return historyOrderPage;
    }

    public void actionPerformed (ActionEvent event) {
        try {
            // if (event.getSource() == backBtn) {
            //     FoodOrderSystem.customerOrderPage.getOrderPage().setVisible(true);
            //     historyOrderPage.setVisible(false);
            // }
        } catch (Exception e) {
            System.out.println("Error" + e);
            JOptionPane.showMessageDialog(historyOrderPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public abstract void showHistoryOrderPage ();
}
