package foodordersystem.Model;

import foodordersystem.Enum.TransactionType;

public class Transaction {
    private int transactionId;
    private int customerId;
    private String username;
    private String date;
    private double debit;
    private double credit;
    private TransactionType transactionType;
    
    public Transaction(int transactionId, int customerId, String username, String date, double debit, double credit, TransactionType transactionType) {
        this.transactionId = transactionId;
        this.customerId = customerId;
        this.username = username;
        this.date = date;
        this.debit = debit;
        this.credit = credit;
        this.transactionType = transactionType;
    }
    
    public int getTransactionId () {
        return transactionId;
    }
    
    public int getCustomerId () {
        return this.customerId;
    }
    
    public String getUsername () {
        return username;
    }
    
    public String getDate () {
        return date;
    }
    
    public double getDebit () {
        return this.debit;
    }

    public double getCredit () {
        return this.credit;
    }

    public TransactionType getTransactionType () {
        return this.transactionType;
    }
}
