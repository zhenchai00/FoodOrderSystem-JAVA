package foodordersystem.Model;

import foodordersystem.Enum.UserRole;

public class Vendor extends User {
    public Vendor (int number, String username, int password) {
        super(number, username, password, UserRole.VENDOR);
    }
}
