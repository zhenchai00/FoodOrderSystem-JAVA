package foodordersystem.Model;

public class User {
    private String username;
    private int password;
    private UserRole role = UserRole.USER;

    public User (String username, int password, UserRole role) {
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

    public UserRole getRole () {
        return role;
    }
}