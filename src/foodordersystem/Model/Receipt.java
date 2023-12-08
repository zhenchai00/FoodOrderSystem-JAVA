package foodordersystem.Model;

import foodordersystem.Enum.TransactionType;
import foodordersystem.Enum.ReceiptStatus;

public class Receipt extends Transaction {
    private int receiptId;
    private ReceiptStatus receiptStatus;
    
    public Receipt(int receiptId, int customerId, String username, String date, double debit, double credit, TransactionType transactionType, ReceiptStatus receiptStatus) {
        super(customerId, username, date, debit, credit, transactionType);
        this.receiptId = receiptId;
        this.receiptStatus = receiptStatus;
    }
    
    public int getReceiptId () {
        return receiptId;
    }
    
    public ReceiptStatus getReceiptStatus () {
        return receiptStatus;
    }
    
    public void setReceiptStatus (ReceiptStatus receiptStatus) {
        this.receiptStatus = receiptStatus;
    }
}
