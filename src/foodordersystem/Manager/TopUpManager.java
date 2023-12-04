package foodordersystem.Manager;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import foodordersystem.Model.DataIO;
import foodordersystem.Model.User;

public class TopUpManager {
    public static void creditBalance (int id, double amount) {
        for (User u : DataIO.allUsers) {
            if (u.getId() == id) {
                u.setBalance(u.getBalance() + amount);
                break;
            }
        }
        DataIO.writeUser();
    }

    public static void debitBalance (int id, double amount) {
        for (User u : DataIO.allUsers) {
            if (u.getId() == id) {
                u.setBalance(u.getBalance() - amount);
                break;
            }
        }
        DataIO.writeUser();
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
}
