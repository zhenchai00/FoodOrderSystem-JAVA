package foodordersystem.Manager;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import foodordersystem.Model.DataIO;
import foodordersystem.Model.Dwallet;
import foodordersystem.Model.User;

public class TopUpManager {
    public static void creditBalance (int id, double amount) {
        for (Dwallet u : DataIO.allDwallet) {
            if (u.getId() == id) {
                u.setCredit(u.getCredit() + amount);
                DataIO.writeDwallet();
                JOptionPane.showMessageDialog(null, "Successfully top up balance for user " + u.getId() + " with amount RM" + amount, "Success", JOptionPane.INFORMATION_MESSAGE);
                break;
            } else {
                JOptionPane.showMessageDialog(null, "User Not Found!", "Failure", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static void debitBalance (int id, double amount) {
        for (Dwallet u : DataIO.allDwallet) {
            if (u.getId() == id) {
                if ((u.getCredit() - amount) >= 0.0) {
                    u.setCredit(u.getCredit() - amount);
                    DataIO.writeDwallet();
                    JOptionPane.showMessageDialog(null, "Successfully debit balance for user " + u.getId() + " with amount RM" + amount, "Success", JOptionPane.INFORMATION_MESSAGE);
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Not enough credit balance for debit!", "Failure", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "User Not Found!", "Failure", JOptionPane.WARNING_MESSAGE);
            }
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
    
    public static ArrayList<Dwallet> getAllCredits () {
        return DataIO.allDwallet;
    }
}
