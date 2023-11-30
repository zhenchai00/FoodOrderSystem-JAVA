package foodordersystem.Model;

public class Customer extends User {
    public Customer (int number, String username, int password) {
        super(number, username, password, UserRole.CUSTOMER);
    }
}
