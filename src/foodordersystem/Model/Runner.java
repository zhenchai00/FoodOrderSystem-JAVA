package foodordersystem.Model;

import foodordersystem.Enum.UserRole;

public class Runner extends User {
    public Runner (int number, String username, int password) {
        super(number, username, password, UserRole.RUNNER);
    }

    public boolean isAvailable () throws Exception {
        for (Object[] runner : DataIO.allRunners) {
            if ((int) runner[0] == this.getId()) {
                return (boolean) runner[1];
            } else {
                throw new Error("Runner not found");
            }
        }
        return false;
    }
}
