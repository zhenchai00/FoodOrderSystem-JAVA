package foodordersystem.Model;

import foodordersystem.Enum.TransactionType;

public class Transaction extends User {
    private int customerId;
    private TransactionType transactionType;
    private double debit;
    private double credit;
    public Transaction(int id, int customerId, String username, String date, double debit, double credit, TransactionType transactionType) {
        super(id, customerId, username, date, debit, credit, transactionType);
        this.customerId = customerId;
        this.transactionType = transactionType;
        this.debit = debit;
        this.credit = credit;
    }
    
    public int getCustomerId () {
        return this.customerId;
    }

    public TransactionType getTransactionType () {
        return this.transactionType;
    }

    public double getDebit () {
        return this.debit;
    }

    public double getCredit () {
        return this.credit;
    }
}
