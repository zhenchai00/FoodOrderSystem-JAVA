package foodordersystem.Manager;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import foodordersystem.Enum.OrderType;
import foodordersystem.Enum.TransactionType;
import foodordersystem.Model.DataIO;
import foodordersystem.Model.Dwallet;
import foodordersystem.Model.Transaction;

public class DwalletManager {
    public static void creditBalance (int id, double amount) {
        Boolean userFound = false;
        for (Dwallet u : DataIO.allDwallets) {
            if (u.getId() == id) {
                u.setBalance(u.getBalance() + amount);
                DataIO.writeDwallet();
                creditTransaction(id, u.getUsername(), amount);
                NotificationManager.sendNotification(id, "Your balance has been topped up with amount RM" + amount + ". Current total balance is RM" + u.getBalance() + ".");
                JOptionPane.showMessageDialog(null, "Successfully top up balance for user " + u.getId() + " with amount RM" + amount, "Success", JOptionPane.INFORMATION_MESSAGE);
                userFound = true;
                break;
            }
        }
        if (userFound == false) {
            JOptionPane.showMessageDialog(null, "User Not Found!", "Failure", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void debitBalance (int id, double amount) {
        Boolean userFound = false;
        for (Dwallet u : DataIO.allDwallets) {
            if (u.getId() == id) {
                if ((u.getBalance() - amount) >= 0.0) {
                    u.setBalance(u.getBalance() - amount);
                    DataIO.writeDwallet();
                    debitTransaction(id, u.getUsername(), amount);
                    NotificationManager.sendNotification(id, "Your balance has been debited with amount RM" + amount + ". Current total balance is RM" + u.getBalance() + ".");
                    JOptionPane.showMessageDialog(null, "Successfully debit balance for user " + u.getId() + " with amount RM" + amount, "Success", JOptionPane.INFORMATION_MESSAGE);
                    userFound = true;
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Not enough credit balance for debit!", "Failure", JOptionPane.WARNING_MESSAGE);
                    userFound = true;
                    break;
                }
            }
        }
        if (userFound == false) {
            JOptionPane.showMessageDialog(null, "User Not Found!", "Failure", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static ArrayList<Object> getCreditDetails () {
        ArrayList<Object> creditDetails = new ArrayList<>();
        String inputUserId = JOptionPane.showInputDialog(null, "Enter user ID");
        String inputAmountString = JOptionPane.showInputDialog(null, "Enter amount to credit");
        
        if (
            inputUserId != null 
            && !inputUserId.isEmpty() 
            && inputAmountString != null 
            && !inputAmountString.isEmpty()
        ) {
            double inputAmount = Double.parseDouble(inputAmountString);
            creditDetails.add(inputUserId);
            creditDetails.add(inputAmount);
        }

        return creditDetails;
    }

    public static ArrayList<Object> getDebitDetails () {
        ArrayList<Object> debitDetails = new ArrayList<>();
        String inputUserId = JOptionPane.showInputDialog(null, "Enter user ID");
        String inputAmountString = JOptionPane.showInputDialog(null, "Enter amount to debit");

        if (
            inputUserId != null 
            && !inputUserId.isEmpty() 
            && inputAmountString != null 
            && !inputAmountString.isEmpty()
        ) {
            double inputAmount = Double.parseDouble(inputAmountString);
            debitDetails.add(inputUserId);
            debitDetails.add(inputAmount);
        }

        return debitDetails;
    }
    
    public static void paymentBalance (
        int id,
        double amount,
        ArrayList<Object[]> orderMenuList,
        String address,
        OrderType orderType, 
        double deliveryCost
    ) throws Exception {
        for (Dwallet u : DataIO.allDwallets) {
            if (u.getId() == id) {
                if ((u.getBalance() - amount) >= 0.0) {
                    u.setBalance(u.getBalance() - amount);
                    DataIO.writeDwallet();
                    debitTransaction(id, u.getUsername(), amount);

                    OrderManager orderManager = new OrderManager();
                    orderManager.storeOrderItems(orderMenuList);
                    orderManager.addOrder(address, orderType, deliveryCost, amount);
                    NotificationManager.sendNotification(id, "Your balance has been detucted by payment RM" + amount + ". Current total balance is RM" + u.getBalance() + ".");
                    JOptionPane.showMessageDialog(null, "Payment Successfully\n You paid RM" + amount, "Success", JOptionPane.INFORMATION_MESSAGE);
                    break;
                } else {
                    throw new Exception("Not enough credit balance for payment!");
                }
            } else {
                throw new Exception("User Not Found!");
            }
        }
    }
    
    public static void refundBalance (int id, double amount) {
        for (Dwallet u : DataIO.allDwallets) {
            if (u.getId() == id) {
                u.setBalance(u.getBalance() + amount);
                DataIO.writeDwallet();
                creditTransaction(id, u.getUsername(), amount);
                NotificationManager.sendNotification(id, "Your balance has been refunded with amount RM" + amount + ". Current total balance is RM" + u.getBalance() + ".");
                JOptionPane.showMessageDialog(null, "Successfully refund balance to user " + u.getId() + " with amount RM" + amount, "Success", JOptionPane.INFORMATION_MESSAGE);
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Error Occured!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void debitTransaction (int customerId, String username, double debitAmount) {
        int newTransactionId = 700 + DataIO.allTransactions.size() + 1;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = LocalDateTime.now().format(formatter);
        TransactionType type = TransactionType.DEBIT;
        Transaction transaction = new Transaction(newTransactionId, customerId, username, date, debitAmount, 0, type);
        DataIO.allTransactions.add(transaction);
        DataIO.writeTransaction();
    }
    
    public static void creditTransaction (int customerId, String username, double creditAmount) {
        int newTransactionId = 700 + DataIO.allTransactions.size() + 1;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = LocalDateTime.now().format(formatter);
        TransactionType type = TransactionType.CREDIT;
        Transaction transaction = new Transaction(newTransactionId, customerId, username, date, 0, creditAmount, type);
        DataIO.allTransactions.add(transaction);
        DataIO.writeTransaction();
    }
    
    public static ArrayList<Dwallet> getAllDwallet () {
        return DataIO.allDwallets;
    }
    
    public static ArrayList<Transaction> getAllTransaction () {
        return DataIO.allTransactions;
    }
}
