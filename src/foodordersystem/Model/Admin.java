package foodordersystem.Model;

public class Admin extends User {
    public Admin (String username, int password) {
        super(username, password, UserRole.ADMIN);
    }
}
