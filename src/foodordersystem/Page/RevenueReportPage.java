package foodordersystem.Page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RevenueReportPage implements ActionListener{
    public JFrame Revenueframe;
    private JButton Revenuebutton, Backbutton;

    public RevenueReportPage(){
      Revenueframe= new JFrame();
      Revenueframe.setTitle("Revenue Report Page");
      Revenueframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Revenueframe.setResizable(false);
      Revenueframe.setSize(400,400);
      Revenueframe.setLayout(null);
      Revenueframe.setVisible(true);
      
      Revenuebutton=new JButton ();
      Revenuebutton.addActionListener(this);
      Revenuebutton.setText("View Report");
      
      Backbutton=new JButton(); 
      Backbutton.addActionListener(this);
      Backbutton.setText("Back");
     
    }

    @Override
    public void actionPerformed(ActionEvent event) {
      if (event.getSource() == Backbutton){
           RunnerDashboardPage.getRunnerDashboardPageObj().getRunnerDashboardPage().setVisible(true);
        }
      else if (event.getSource()== Revenuebutton){
           
      }
    }
 
}
