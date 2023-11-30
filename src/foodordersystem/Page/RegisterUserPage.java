package foodordersystem.Page;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import foodordersystem.Enum.UserRole;
import foodordersystem.Manager.UserManager;
import foodordersystem.Model.User;

public class RegisterUserPage implements ActionListener {
    private static JFrame registerUserPage;
    private JButton newBtn, editBtn, deleteBtn, backBtn;
    private JTable userTable;
    private DefaultTableModel userTableModel;

    public RegisterUserPage () {
        registerUserPage = new JFrame("Register User Page");
        registerUserPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerUserPage.setLayout(new BoxLayout(registerUserPage.getContentPane(), BoxLayout.Y_AXIS));

        userTableModel = new DefaultTableModel(new Object[]{"Id", "Username", "Password", "Role"}, 0);
        userTable = new JTable(userTableModel);
        JScrollPane scrollPanel = new JScrollPane(userTable);
        for (User user : UserManager.getAllUsers()) {
            addRowToTable(user);
        }

        newBtn = new JButton("New");
        editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete");
        backBtn = new JButton("Back");
        newBtn.addActionListener(this);
        editBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        backBtn.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(backBtn);

        registerUserPage.add(scrollPanel);
        registerUserPage.add(buttonPanel);

        registerUserPage.pack();
        registerUserPage.setLocationRelativeTo(null);
        registerUserPage.setVisible(false);
    }

    public void actionPerformed (ActionEvent event) {
        try {
            if (event.getSource() == newBtn) {
                ArrayList<Object> credentials = UserManager.getUserCredentials();
                if (!credentials.isEmpty()) {
                    UserManager.registerUser(credentials.get(0).toString(), Integer.parseInt(credentials.get(1).toString()), UserRole.valueOf(credentials.get(2).toString()));
                }
            } else if (event.getSource() == editBtn) {
                ArrayList<Object> credentials = UserManager.getEditUserCredentials();
                if (!credentials.isEmpty()) {
                    UserManager.editUser(Integer.parseInt(credentials.get(0).toString()), credentials.get(1).toString(), Integer.parseInt(credentials.get(2).toString()), UserRole.valueOf(credentials.get(3).toString()));
                }
            } else if (event.getSource() == deleteBtn) {
                ArrayList<Object> credentials = UserManager.getDeleteUserCredentials();
                if (!credentials.isEmpty()) {
                    UserManager.deleteUser(Integer.parseInt(credentials.get(0).toString()));
                }
            } else if (event.getSource() == backBtn) {
                AdminDashboardPage.getAdminDashboardPageObj().getAdminDashboardPage().setVisible(true);
                // FoodOrderSystem.loginPage.getLoginPage().setVisible(true);
                registerUserPage.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
            JOptionPane.showMessageDialog(registerUserPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static JFrame getRegisterUserPage () {
        return registerUserPage;
    }

    public void addRowToTable (User user) {
        userTableModel.addRow(new Object[]{user.getId(), user.getUsername(), user.getPassword(), user.getRole()});
    }
    
    public void updateUserTable () {
        int rows = userTableModel.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            userTableModel.removeRow(i);
        }
        for (User user : UserManager.getAllUsers()) {
            addRowToTable(user);
        }
    }
}