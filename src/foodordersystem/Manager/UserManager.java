package foodordersystem.Manager;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import foodordersystem.Model.DataIO;
import foodordersystem.Model.User;
import foodordersystem.Model.UserRole;

public class UserManager {
    public static User loginUser (String username, int password) throws Exception {
        User user = DataIO.checkUsername(username);

        if (user == null || user.getPassword() != password) {
            throw new Exception("Invalid username or password");
        }
        System.out.println("User: " + user.getUsername() + " " + user.getPassword() + " " + user.getRole());
        return user;
    }

    public static void registerUser(String username, int password, UserRole role) throws Exception {
        if (DataIO.checkUsername(username) != null) {
            throw new Exception("Username already exists");
        }

        int number = DataIO.allUsers.size() + 1;
        User newUser = new User(number, username, password, role);
        DataIO.allUsers.add(newUser);
        DataIO.writeUser();
    }

    public static ArrayList<Object> getUserCredentials() {
        ArrayList<Object> userCredentials = new ArrayList<>();
        String inputUsername = JOptionPane.showInputDialog(null, "Enter user's username: ");
        String inputUserPassString = JOptionPane.showInputDialog(null, "Enter user's password: ");

        UserRole[] values = {UserRole.USER, UserRole.ADMIN, UserRole.CUSTOMER, UserRole.RUNNER, UserRole.VENDOR};
        Object userRole = JOptionPane.showInputDialog(null, "Select user's role: ", "User Role", JOptionPane.QUESTION_MESSAGE, null, values, values[0]);

        if (
            inputUsername != null
            && !inputUsername.isEmpty()
            && inputUserPassString != null
            && !inputUserPassString.isEmpty()
            && userRole != null
            && !userRole.toString().isEmpty()
        ) {
            int inputUserPass = Integer.parseInt(inputUserPassString);
            userCredentials.add(inputUsername);
            userCredentials.add(inputUserPass);
            userCredentials.add(userRole);
        }
        return userCredentials;
    }

    public static void showErrorMessage(String message) {
        System.out.println("Error: " + message);
        JOptionPane.showMessageDialog(null, "Error: " + message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static ArrayList<User> getAllUsers () {
        return DataIO.allUsers;
    }
}
