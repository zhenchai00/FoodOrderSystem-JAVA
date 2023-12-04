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

import foodordersystem.Manager.TopUpManager;
import foodordersystem.Manager.UserManager;
import foodordersystem.Model.User;

public class TopUpPage implements ActionListener {
    private JFrame topUpPage;
    private JButton creditBtn, debitBtn, backBtn;
    private JPanel actionBtnPanel;
    private JTable userTable;
    private DefaultTableModel userTableModel;
    private JScrollPane userScrollPane;

    public TopUpPage () {
        topUpPage = new JFrame("Top Up Page");
        topUpPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        topUpPage.setLayout(new BoxLayout(topUpPage.getContentPane(), BoxLayout.Y_AXIS));

        userTableModel = new DefaultTableModel(new Object[] {"ID", "Username", "Balance"}, 0);
        userTable = new JTable(userTableModel);
        userScrollPane = new JScrollPane(userTable);

        ArrayList<User> users = UserManager.getAllUsers();
        for (User user : users) {
            if (user.getRole().toString().equals("CUSTOMER")) {
                userTableModel.addRow(new Object[] {
                    user.getId(),
                    user.getUsername(),
                    user.getBalance()
                });
            }
        }

        creditBtn = new JButton("Credit");
        creditBtn.addActionListener(this);
        debitBtn = new JButton("Debit");
        debitBtn.addActionListener(this);
        backBtn = new JButton("Back");
        backBtn.addActionListener(this);

        actionBtnPanel = new JPanel();
        actionBtnPanel.add(creditBtn);
        actionBtnPanel.add(debitBtn);
        actionBtnPanel.add(backBtn);

        topUpPage.add(userScrollPane);
        topUpPage.add(actionBtnPanel);

        topUpPage.pack();
        topUpPage.setLocationRelativeTo(null);
        topUpPage.setVisible(false);
    }

    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == creditBtn) {
                ArrayList<Object> creditDetails = TopUpManager.getCreditDetails();
                if (!creditDetails.isEmpty()) {
                    TopUpManager.creditBalance(Integer.parseInt(creditDetails.get(0).toString()), Double.parseDouble(creditDetails.get(1).toString()));
                    JOptionPane.showMessageDialog(topUpPage, "Successfully top up balance for user " + creditDetails.get(0).toString() + " with amount RM" + creditDetails.get(1).toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
                    updateUserTable();
                }
            } else if (event.getSource() == debitBtn) {
                ArrayList<Object> debitDetails = TopUpManager.getDebitDetails();
                if (!debitDetails.isEmpty()) {
                    TopUpManager.debitBalance(Integer.parseInt(debitDetails.get(0).toString()), Double.parseDouble(debitDetails.get(1).toString()));
                    JOptionPane.showMessageDialog(topUpPage, "Successfully debit balance for user " + debitDetails.get(0).toString() + " with amount RM" + debitDetails.get(1).toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
                    updateUserTable();
                }
            } else if (event.getSource() == backBtn) {
                AdminDashboardPage.getAdminDashboardPageObj().getAdminDashboardPage().setVisible(true);
                topUpPage.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(topUpPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JFrame getTopUpPage () {
        return topUpPage;
    }

    public void updateUserTable () {
        userTableModel.setRowCount(0);
        ArrayList<User> users = UserManager.getAllUsers();
        for (User user : users) {
            if (user.getRole().toString().equals("CUSTOMER")) {
                userTableModel.addRow(new Object[] {
                    user.getId(),
                    user.getUsername(),
                    user.getBalance()
                });
            }
        }
    }
}
