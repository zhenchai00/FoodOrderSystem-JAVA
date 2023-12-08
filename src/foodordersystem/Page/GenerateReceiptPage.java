package foodordersystem.Page;

import foodordersystem.Enum.ReceiptStatus;
import foodordersystem.Enum.TransactionType;
import foodordersystem.FoodOrderSystem;
import foodordersystem.Manager.ReceiptManager;
import foodordersystem.Model.Receipt;
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


public class GenerateReceiptPage implements ActionListener {
    protected JFrame generateReceiptPage;
    protected JButton backBtn, generateBtn;
    protected JPanel actionBtnPanel;
    protected JTable receiptTable;
    protected DefaultTableModel receiptTableModel;
    protected JScrollPane receiptScrollPane;
    
    public GenerateReceiptPage () {
        generateReceiptPage = new JFrame("Generate Receipt");
        generateReceiptPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generateReceiptPage.setLayout(new BoxLayout(generateReceiptPage.getContentPane(), BoxLayout.Y_AXIS));
        
        receiptTableModel = new DefaultTableModel(new Object[] {"ID", "Customer ID", "Date", "Debit", "Credit", "Type", "Status" }, 0);
        receiptTable = new JTable(receiptTableModel);
        receiptScrollPane = new JScrollPane(receiptTable);
        showReceiptTable();
        
        backBtn = new JButton("Back");
        generateBtn = new JButton("Generate Receipt");

        backBtn.addActionListener(this);
        generateBtn.addActionListener(this);

        actionBtnPanel = new JPanel();
        
        actionBtnPanel.add(backBtn);
        actionBtnPanel.add(generateBtn);
        
        generateReceiptPage.add(receiptScrollPane);
        generateReceiptPage.add(actionBtnPanel);

        generateReceiptPage.pack();
        generateReceiptPage.setLocationRelativeTo(null);
        generateReceiptPage.setVisible(false);
    }
    
    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == generateBtn) {
                int selectedRow = receiptTable.getSelectedRow();
                int receiptId = (int) receiptTableModel.getValueAt(selectedRow, 0);
                int customerId = (int) receiptTableModel.getValueAt(selectedRow, 1);
                double debit = (double) receiptTableModel.getValueAt(selectedRow, 3);
                double credit = (double) receiptTableModel.getValueAt(selectedRow, 4);
                TransactionType transactionType = (TransactionType) receiptTableModel.getValueAt(selectedRow, 5);
                ReceiptManager.generateReceipt(receiptId, customerId, debit, credit, transactionType);
                receiptTableModel.setValueAt(ReceiptStatus.GENERATED.toString(), selectedRow, 6);
            } else if (event.getSource() == backBtn) {
                AdminDashboardPage adminDashboardPage = new AdminDashboardPage();
                adminDashboardPage.getAdminDashboardPage().setVisible(true);
                generateReceiptPage.setVisible(false);
            }
            
        } catch (Exception e) {
            System.out.println("Error" + e);
            JOptionPane.showMessageDialog(generateReceiptPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JFrame getGenerateReceiptPage() {
        return generateReceiptPage;
    }
    
    public void showReceiptTable () {
        ArrayList<Receipt> receipts = ReceiptManager.getAllReceipts();
        for (Receipt receipt : receipts) {
            if (receipt.getTransactionType().equals(TransactionType.DEBIT) || receipt.getTransactionType().equals(TransactionType.CREDIT)) {
                receiptTableModel.addRow(new Object[] {
                    receipt.getReceiptId(),
                    receipt.getCustomerId(),
                    receipt.getDate(),
                    receipt.getDebit(),
                    receipt.getCredit(),
                    receipt.getTransactionType(),
                    receipt.getReceiptStatus()
                });
            }
        }
    }
}
