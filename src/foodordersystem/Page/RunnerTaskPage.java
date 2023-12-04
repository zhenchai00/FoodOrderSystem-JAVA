package foodordersystem.Page;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RunnerTaskPage implements ActionListener{
    private JTable RunnerTaskTable;//OrderID,CustomerName,Address,Salary 
    private JButton AcceptButton,RejectButton,UpdateStatButton, BackButton;
    private JFrame RunnerTaskFrame;
    private JPanel RunnerTaskPanel;
    private static RunnerTaskPage instance;
    public RunnerTaskPage(){
    String[] Column={"OrderID","CustomerName","Address","Salary"};
    String[][] Data={{"01","H","APU","RM5"}};
    RunnerTaskTable= new JTable(Data,Column);
    RunnerTaskTable.setPreferredSize(new Dimension(600,300));
    
    
    AcceptButton=new JButton("Accept");
    AcceptButton.setPreferredSize(new Dimension (100,40));
    AcceptButton.addActionListener(this);
    
    RejectButton=new JButton("Reject");
    RejectButton.setPreferredSize(new Dimension(100,40));
    RejectButton.addActionListener(this);
    
    UpdateStatButton=new JButton("Update");
    UpdateStatButton.setPreferredSize(new Dimension(100,40));
    UpdateStatButton.addActionListener(this);
    
    BackButton=new JButton("Back");
    BackButton.setPreferredSize(new Dimension(100,40));
    BackButton.addActionListener(this);
    
    RunnerTaskPanel=new JPanel();
    RunnerTaskPanel.setSize(new Dimension(100,300));
    RunnerTaskPanel.add(AcceptButton, BorderLayout.EAST); 
    RunnerTaskPanel.add(RejectButton, BorderLayout.CENTER); 
    RunnerTaskPanel.add(UpdateStatButton, BorderLayout.CENTER); 
    RunnerTaskPanel.add(BackButton, BorderLayout.WEST); 
    
    RunnerTaskFrame=new JFrame();
    RunnerTaskFrame.setSize(600,400);
    RunnerTaskFrame.add(new JScrollPane(RunnerTaskTable),BorderLayout.CENTER);
    RunnerTaskFrame.add(RunnerTaskPanel, BorderLayout.SOUTH);
    RunnerTaskFrame.setLocationRelativeTo(null);
    RunnerTaskFrame.setVisible(true);
    RunnerTaskFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    }
    
    public static RunnerTaskPage getRunnerTaskPageObj() {
         instance= new RunnerTaskPage();
         return instance;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent event){
       try{ 
          if (event.getSource() == AcceptButton) {
              
        } else if (event.getSource() == RejectButton) {
            
        
        } else if (event.getSource() == UpdateStatButton) {
          
        
        } else if (event.getSource() == BackButton) {
           RunnerDashboardPage.getRunnerDashboardPageObj().getRunnerDashboardPage().setVisible(true);
           
        }
           
       }catch(Exception e){
           JOptionPane.showMessageDialog(RunnerTaskFrame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
       }
    }
    public JFrame getRunnerTaskPage() {
        return RunnerTaskFrame;
    }
}
