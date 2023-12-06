package foodordersystem.Model;

import foodordersystem.Enum.UserRole;

public class Customer extends User {
    public Customer (int number, String username, int password) {
        super(number, username, password, UserRole.CUSTOMER);
    }
}
