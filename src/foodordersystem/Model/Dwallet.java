package foodordersystem.Model;

public class Dwallet extends User {
    private double balance;
    
    public Dwallet (int id, String username, double balance) {
        super(id, username);
        this.balance = balance;
    }
    
    public double getBalance () {
        return balance;
    }
    
    public void setBalance (double balance) {
        this.balance = balance;
    }
}
