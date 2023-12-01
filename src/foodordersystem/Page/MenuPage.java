package foodordersystem.Page;

import javax.swing.*;
import java.awt.event.*;

public class MenuPage implements ActionListener {
    public JFrame menuPage;
    protected JPanel buttonPanel, headerPanel;
    protected JButton backBtn;

    public MenuPage (String title) {
        menuPage = new JFrame(title);
        menuPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuPage.setLayout(new BoxLayout(menuPage.getContentPane(), BoxLayout.Y_AXIS));

        buttonPanel = new JPanel();
        headerPanel = new JPanel();

        backBtn = new JButton("Back");
        backBtn.addActionListener(this);

        menuPage.setVisible(false);
    }

    public void actionPerformed (ActionEvent event) {
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(menuPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JFrame getMenuPage() {
        return menuPage;
    }
}
