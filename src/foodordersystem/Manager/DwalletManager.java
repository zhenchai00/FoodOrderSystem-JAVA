package foodordersystem.Manager;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import foodordersystem.Enum.OrderType;
import foodordersystem.FoodOrderSystem;
import foodordersystem.Model.DataIO;
import foodordersystem.Model.Dwallet;
import foodordersystem.Page.CustomerPaymentPage;

public class DwalletManager {
    public static void creditBalance (int id, double amount) {
        for (Dwallet u : DataIO.allDwallet) {
            if (u.getId() == id) {
                u.setCredit(u.getCredit() + amount);
                DataIO.writeDwallet();
                JOptionPane.showMessageDialog(null, "Successfully top up balance for user " + u.getId() + " with amount RM" + amount, "Success", JOptionPane.INFORMATION_MESSAGE);
                NotificationManager.sendNotification(id, "Your balance has been topped up with amount RM" + amount + ". Current total balance is RM" + u.getCredit() + ".");
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
                    NotificationManager.sendNotification(id, "Your balance has been debited with amount RM" + amount + ". Current total balance is RM" + u.getCredit() + ".");
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
    
    public static void paymentBalance (int id, double amount, ArrayList<Object[]> orderMenuList, String address, OrderType orderType, double deliveryCost) {
        for (Dwallet u : DataIO.allDwallet) {
            if (u.getId() == id) {
                if ((u.getCredit() - amount) >= 0.0) {
                    u.setCredit(u.getCredit() - amount);
                    DataIO.writeDwallet();
                    JOptionPane.showMessageDialog(null, "Payment Successfully\n You paid RM" + amount, "Success", JOptionPane.INFORMATION_MESSAGE);
                    NotificationManager.sendNotification(id, "Your balance has been detucted by payment RM" + amount + ". Current total balance is RM" + u.getCredit() + ".");
                    OrderManager orderManager = new OrderManager();
                    orderManager.storeOrderItems(orderMenuList);
                    try {
                        orderManager.addOrder(address, orderType, deliveryCost, amount);
                    } catch (Exception e) {
                        System.out.println("Error Occured: "+e);
                    }
                    FoodOrderSystem.customerOrderPage.getOrderPage().setVisible(true);
                    CustomerPaymentPage customerPaymentPage = new CustomerPaymentPage();
                    customerPaymentPage.getCustomerPaymentPage().setVisible(false);
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Not enough credit balance for payment!", "Failure", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error Occured!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static void refundBalance (int id, double amount) {
        for (Dwallet u : DataIO.allDwallet) {
            if (u.getId() == id) {
                u.setCredit(u.getCredit() + amount);
                DataIO.writeDwallet();
                JOptionPane.showMessageDialog(null, "Successfully refund balance to user " + u.getId() + " with amount RM" + amount, "Success", JOptionPane.INFORMATION_MESSAGE);
                NotificationManager.sendNotification(id, "Your balance has been refunded with amount RM" + amount + ". Current total balance is RM" + u.getCredit() + ".");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Error Occured!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static ArrayList<Dwallet> getAllCredits () {
        return DataIO.allDwallet;
    }
}
