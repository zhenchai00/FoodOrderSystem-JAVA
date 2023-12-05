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

import foodordersystem.Manager.DwalletManager;
import foodordersystem.Model.DataIO;
import foodordersystem.Model.Dwallet;

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

        userTableModel = new DefaultTableModel(new Object[] {"ID", "Username", "Credit"}, 0);
        userTable = new JTable(userTableModel);
        userScrollPane = new JScrollPane(userTable);
        for (Dwallet dwallet : DwalletManager.getAllCredits()) {
            addRowToTable(dwallet);
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
                ArrayList<Object> creditDetails = DwalletManager.getCreditDetails();
                if (!creditDetails.isEmpty()) {
                    DwalletManager.creditBalance(Integer.parseInt(creditDetails.get(0).toString()), Double.parseDouble(creditDetails.get(1).toString()));
                    for (Dwallet dwallet : DataIO.allDwallet) {
                        updateUserTable(dwallet);
                    }
                }
            } else if (event.getSource() == debitBtn) {
                ArrayList<Object> debitDetails = DwalletManager.getDebitDetails();
                if (!debitDetails.isEmpty()) {
                    DwalletManager.debitBalance(Integer.parseInt(debitDetails.get(0).toString()), Double.parseDouble(debitDetails.get(1).toString()));
                    for (Dwallet dwallet : DataIO.allDwallet) {
                        updateUserTable(dwallet);
                    }
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
    
    public void addRowToTable (Dwallet dwallet) {
        userTableModel.addRow(new Object[] {
            dwallet.getId(),
            dwallet.getUsername(),
            dwallet.getCredit()
        });
    }

    public void updateUserTable (Dwallet dwallet) {
        userTableModel.setRowCount(0);
        userTableModel.addRow(new Object[] {
            dwallet.getId(),
            dwallet.getUsername(),
            dwallet.getCredit()
        });
    }
}
