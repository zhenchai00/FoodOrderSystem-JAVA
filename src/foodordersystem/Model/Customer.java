package foodordersystem.Model;

public class Customer extends User {
    public Customer (String username, int password) {
        super(username, password, UserRole.CUSTOMER);
    }
}
