package foodordersystem.Page;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JOptionPane;

public class VendorRevenueReportPage  implements ActionListener { 
      private JFrame VendorRevenueFrame;
      private JButton Backbutton;
      private JTable VendorRevenueTable;
      private static VendorRevenueReportPage instance;
    public VendorRevenueReportPage(){ 
        
       VendorRevenueFrame= new JFrame();
       VendorRevenueFrame.setSize(500,300);
       VendorRevenueFrame.setVisible(true);
       VendorRevenueFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       
       String[] Column=
           {"Date","Month","Service","TotalRevenue"};
       String[][] data={
           {"19/10/2023","January","Home cleaning","RM50"}
       };
       VendorRevenueTable= new JTable(data,Column);
       VendorRevenueTable.setSize(500,200);
       VendorRevenueTable.setVisible(true);
       
       
       Backbutton= new JButton("Back");
       Backbutton.setPreferredSize (new Dimension(50,50));
       Backbutton.addActionListener(this);
       
       VendorRevenueFrame.add(VendorRevenueTable.getTableHeader(), BorderLayout.NORTH);
       VendorRevenueFrame.add(VendorRevenueTable,BorderLayout.CENTER);
       VendorRevenueFrame.add(Backbutton,BorderLayout.SOUTH);
       VendorRevenueFrame.setLocationRelativeTo(null);
 }
    @Override
    public void actionPerformed(ActionEvent Event){
        try{
             if(Event.getSource() == Backbutton){
                VendorDashboardPage.getVendorDashboardPageObj().getVendorDashboardPage().setVisible(true);
                VendorRevenueFrame.setVisible(false);
                   }
              } 
        catch(Exception e){
                JOptionPane.showMessageDialog(VendorRevenueFrame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
       }
    }
   public static VendorRevenueReportPage getVendorRevenueReportPageObj() {
        if (instance == null) {
            instance = new VendorRevenueReportPage();
        }
        return instance;
   }
     public JFrame getVendorRevenueReportPage() {
        return VendorRevenueFrame;
     }
}
