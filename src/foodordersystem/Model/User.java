package foodordersystem.Model;

import foodordersystem.Enum.UserRole;

public class User {
    private int id;
    private String username;
    private int password;
    private UserRole role = UserRole.USER;
    private double balance;
    private double credit;

    public User (int id, String username, int password, double balance, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.role = role;
    }
    
    public User (int id, String username, double credit) {
        this.id = id;
        this.username = username;
        this.credit = credit;
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
    
    public double getCredit () {
        return credit;
    }
    
    public void setCredit (double credit) {
        this.credit = credit;
    }
}