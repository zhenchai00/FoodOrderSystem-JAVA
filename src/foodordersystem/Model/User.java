package foodordersystem.Model;

import foodordersystem.Enum.UserRole;

public class User {
    private int id;
    private String username;
    private int password;
    private UserRole role = UserRole.USER;

    public User (int id, String username, int password, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
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
}