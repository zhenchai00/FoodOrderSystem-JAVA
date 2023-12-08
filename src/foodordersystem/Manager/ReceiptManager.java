package foodordersystem.Manager;

import foodordersystem.Enum.TransactionType;
import foodordersystem.Enum.ReceiptStatus;
import foodordersystem.FoodOrderSystem;
import foodordersystem.Model.DataIO;
import foodordersystem.Model.Receipt;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ReceiptManager {
    public static ArrayList<Receipt> getAllReceipts () {
        return DataIO.allReceipts;
    }
    
    public static void generateReceipt (int receiptId, int customerId, double debit, double credit, TransactionType transactionType) {
        for (Receipt receipt : getAllReceipts()) {
            if (receipt.getReceiptId() == receiptId) {
                receipt.setReceiptStatus(ReceiptStatus.GENERATED);
                DataIO.writeReceipt();
                if (transactionType.equals(TransactionType.DEBIT)) {
                    NotificationManager.sendNotification(customerId, "[ReceiptID - " + receipt.getReceiptId() + "] Your balance has been debited with amount RM" + debit + ".");
                } else if (transactionType.equals(TransactionType.CREDIT)) {
                    NotificationManager.sendNotification(customerId, "[ReceiptID - " + receipt.getReceiptId() + "] Your balance has been topped up with amount RM" + credit + ".");
                }
                JOptionPane.showMessageDialog(null, "Successfully generate receipt to " + customerId, "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
