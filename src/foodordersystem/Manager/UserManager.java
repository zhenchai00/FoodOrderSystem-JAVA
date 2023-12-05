package foodordersystem.Manager;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import foodordersystem.Enum.UserRole;
import foodordersystem.Model.DataIO;
import foodordersystem.Model.Dwallet;
import foodordersystem.Model.User;

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
        User newUser = new User(number, username, password, 0, role);

        if (role == UserRole.CUSTOMER) {
            DataIO.allDwallet.add(new Dwallet(
                newUser.getId(),
                newUser.getUsername(),
                0.0
            ));
            DataIO.writeDwallet();
        }

        if (role == UserRole.RUNNER) {
            DataIO.allRunners.add(new Object[] {
                newUser.getId(),
                true
            });
            DataIO.writeRunnerAvailable();
        }

        DataIO.allUsers.add(newUser);
        DataIO.writeUser();
    }

    public static void editUser(int id, String username, int password, UserRole role) throws Exception {
        User user = DataIO.checkUsername(username);
        if (user != null && user.getId() != id) {
            throw new Exception("Username already exists");
        }

        for (User u : DataIO.allUsers) {
            if (u.getId() == id) {
                u.setUsername(username);
                u.setPassword(password);
                u.setRole(role);
                break;
            }
        }
        DataIO.writeUser();

        for (Dwallet d : DataIO.allDwallet) {
            if (d.getId() == id) {
                d.setUsername(username);
                break;
            }
        }
        DataIO.writeDwallet();
    }

    public static void deleteUser(int id) throws Exception {
        for (User u : DataIO.allUsers) {
            if (u.getId() == id) {
                DataIO.allUsers.remove(u);
                break;
            }
        }
        DataIO.writeUser();

        for (Dwallet d : DataIO.allDwallet) {
            if (d.getId() == id) {
                DataIO.allDwallet.remove(d);
                break;
            }
        }
        DataIO.writeDwallet();

        for (Object[] r : DataIO.allRunners) {
            if ((int) r[0] == id) {
                DataIO.allRunners.remove(r);
                break;
            }
        }
        DataIO.writeRunnerAvailable();
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

    public static ArrayList<Object> getEditUserCredentials() {
        ArrayList<Object> userCredentials = new ArrayList<>();
        String inputIdString = JOptionPane.showInputDialog(null, "Enter user's id: ");
        String inputUsername = JOptionPane.showInputDialog(null, "Enter user's username: ");
        String inputUserPassString = JOptionPane.showInputDialog(null, "Enter user's password: ");

        UserRole[] values = {UserRole.USER, UserRole.ADMIN, UserRole.CUSTOMER, UserRole.RUNNER, UserRole.VENDOR};
        Object userRole = JOptionPane.showInputDialog(null, "Select user's role: ", "User Role", JOptionPane.QUESTION_MESSAGE, null, values, values[0]);

        if (
            inputIdString != null
            && !inputIdString.isEmpty()
            && inputUsername != null
            && !inputUsername.isEmpty()
            && inputUserPassString != null
            && !inputUserPassString.isEmpty()
            && userRole != null
            && !userRole.toString().isEmpty()
        ) {
            int inputId = Integer.parseInt(inputIdString);
            int inputUserPass = Integer.parseInt(inputUserPassString);
            userCredentials.add(inputId);
            userCredentials.add(inputUsername);
            userCredentials.add(inputUserPass);
            userCredentials.add(userRole);
        }
        return userCredentials;
    }

    public static ArrayList<Object> getDeleteUserCredentials() {
        ArrayList<Object> userCredentials = new ArrayList<>();
        String inputIdString = JOptionPane.showInputDialog(null, "Enter user's id: ");


        if (inputIdString != null && !inputIdString.isEmpty()) {
            int inputId = Integer.parseInt(inputIdString);
            userCredentials.add(inputId);
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
