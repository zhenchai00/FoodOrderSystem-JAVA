package foodordersystem.Model;

public class Vendor extends User {
    public Vendor (String username, int password) {
        super(username, password, UserRole.VENDOR);
    }
}
