package foodordersystem.Page;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import foodordersystem.Enum.TransactionType;
import foodordersystem.FoodOrderSystem;
import foodordersystem.Manager.DwalletManager;
import foodordersystem.Model.Dwallet;
import foodordersystem.Model.Transaction;

public class CustomerDwalletPage implements ActionListener {
    public JFrame customerDwalletPage;
    private JPanel balancePanel, buttonPanel;
    private JLabel balanceLabel;
    private JButton backBtn;
    private JTable transactionTable;
    private DefaultTableModel transactionTableModel;
    private JScrollPane transactionScrollPane;

    public CustomerDwalletPage() {
        customerDwalletPage = new JFrame("Digital Wallet");
        customerDwalletPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerDwalletPage.setLayout(new BoxLayout(customerDwalletPage.getContentPane(), BoxLayout.Y_AXIS));

        transactionTableModel = new DefaultTableModel(new Object[]{"Date", "Type", "Amount"}, 0);
        transactionTable = new JTable(transactionTableModel);
        ArrayList<Transaction> allTransaction = DwalletManager.getAllTransaction();
        for (Transaction transaction : allTransaction) {
            if (transaction.getId() == FoodOrderSystem.currentUser.getId()) {
                String type;
                String amount;
                if (transaction.getTransactionType().equals(TransactionType.DEBIT)) {
                    type = String.valueOf(TransactionType.DEBIT);
                    amount = "- "+String.valueOf(transaction.getDebit());
                } else {
                    type = String.valueOf(TransactionType.CREDIT);
                    amount = "+ "+String.valueOf(transaction.getCredit());
                }
                transactionTableModel.addRow(new Object[] {transaction.getDate(), type, amount});
            }
        }
        //transactionScrollPane = new JScrollPane();
        transactionScrollPane = new JScrollPane(transactionTable);
        
        balanceLabel = new JLabel("Null");
        ArrayList<Dwallet> allDwallet = DwalletManager.getAllDwallet();
        for (Dwallet dwallet : allDwallet) {
            if (dwallet.getId() == FoodOrderSystem.currentUser.getId()) {
                balanceLabel = new JLabel("Balance: " + dwallet.getBalance());
            }
        }
        balanceLabel.setFont(new Font(null, Font.BOLD, 20));
        balancePanel = new JPanel();
        balancePanel.add(balanceLabel);
        
        backBtn = new JButton("Back");
        backBtn.addActionListener(this);
        buttonPanel = new JPanel();
        buttonPanel.add(backBtn);
        
        customerDwalletPage.add(transactionScrollPane);
        customerDwalletPage.add(balancePanel);
        customerDwalletPage.add(buttonPanel);

        customerDwalletPage.pack();
        customerDwalletPage.setLocationRelativeTo(null);
    }

    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == backBtn) {
                CustomerDashboardPage.getCustomerDashboardPageObj().getCustomerDashboardPage().setVisible(true);
                customerDwalletPage.setVisible(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(customerDwalletPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JFrame getCustomerDwalletPage() {
        return customerDwalletPage;
    }
}
