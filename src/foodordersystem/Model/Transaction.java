package foodordersystem.Model;

import foodordersystem.Enum.TransactionType;

public class Transaction extends User {
    public Transaction(int id, int customerId, String username, String date, double debit, double credit, TransactionType transactionType) {
        super(id, customerId, username, date, debit, credit, transactionType);
    }
}
