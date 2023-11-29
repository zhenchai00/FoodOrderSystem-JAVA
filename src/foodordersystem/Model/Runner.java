package foodordersystem.Model;

public class Runner extends User {
    public Runner (String username, int password) {
        super(username, password, UserRole.RUNNER);
    }
}
