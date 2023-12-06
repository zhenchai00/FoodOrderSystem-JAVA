package foodordersystem.Model;

import java.time.LocalDateTime;
import foodordersystem.Enum.TransactionType;

public class Transaction extends User {
    public Transaction(int id, String username, LocalDateTime date, double debit, double credit, TransactionType transactionType) {
        super(id, username, date, debit, credit, transactionType);
    }
}
