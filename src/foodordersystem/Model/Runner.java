package foodordersystem.Model;

import foodordersystem.Enum.UserRole;

public class Runner extends User {
    public Runner (int number, String username, int password) {
        super(number, username, password, 0, UserRole.RUNNER);
    }
}
