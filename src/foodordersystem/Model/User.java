package foodordersystem.Model;

import foodordersystem.Enum.UserRole;
import foodordersystem.Enum.TransactionType;

public class User {
    private int id;
    private String username;
    private int password;
    private UserRole role = UserRole.USER;
    private double balance;
    private String date;
    private double debit;
    private double credit;
    private TransactionType transactionType;

    public User (int id, String username, int password, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    public User (int id, String username, double balance) {
        this.id = id;
        this.username = username;
        this.balance = balance;
    }
    
    public User (int id, String username, String date, double debit, double credit, TransactionType transactionType) {
        this.id = id;
        this.username = username;
        this.date = date;
        this.debit = debit;
        this.credit = credit;
        this.transactionType = transactionType;
    }

    public String getUsername () {
        return username;
    }

    public int getPassword () {
        return password;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public void setPassword (int password) {
        this.password = password;
    }

    public void setId (int id) {
        this.id = id;
    }

    public int getId () {
        return id;
    }

    public UserRole getRole () {
        return role;
    }

    public UserRole setRole (UserRole role) {
        return this.role = role;
    }
    
    public double getBalance () {
        return balance;
    }
    
    public void setBalance (double balance) {
        this.balance = balance;
    }
    
    public String getDate () {
        return date;
    }
    
    public void setDate (String date) {
        this.date = date;
    }
    
    public double getDebit () {
        return debit;
    }
    
    public void setDebit (double debit) {
        this.debit = debit;
    }
    
    public double getCredit () {
        return credit;
    }
    
    public void setCredit (double credit) {
        this.credit = credit;
    }
    
    public TransactionType getTransactionType () {
        return transactionType;
    }
    
    public void setTransactionType (TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}