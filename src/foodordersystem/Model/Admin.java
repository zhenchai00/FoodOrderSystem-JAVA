package foodordersystem.Model;

import foodordersystem.Enum.UserRole;

public class Admin extends User {
    public Admin (int number, String username, int password) {
        super(number, username, password, UserRole.ADMIN);
    }
}
