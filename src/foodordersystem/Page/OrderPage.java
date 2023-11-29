package foodordersystem.Page;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.*;

public class OrderPage implements ActionListener{
    public JFrame orderPage;
    protected JPanel buttonPanel, headerPanel;
    protected JButton backBtn;

    public OrderPage (String title) {
        orderPage = new JFrame(title);
        orderPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        orderPage.setLayout(new BoxLayout(orderPage.getContentPane(), BoxLayout.Y_AXIS));

        buttonPanel = new JPanel();
        headerPanel = new JPanel();

        backBtn = new JButton("Back");
        backBtn.addActionListener(this);

        orderPage.setVisible(false);
    }

    public void actionPerformed (ActionEvent event) {
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(orderPage, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JFrame getOrderPage() {
        return orderPage;
    }
}