package foodordersystem.Page;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class ReviewPage implements ActionListener {
    protected JFrame reviewPage;
    protected JPanel actionBtnPanel;

    public ReviewPage (String title) {
        reviewPage = new JFrame(title);
        reviewPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reviewPage.setLayout(new BoxLayout(reviewPage.getContentPane(), BoxLayout.Y_AXIS));

        showVendorReviewPage();

        reviewPage.pack();
        reviewPage.setLocationRelativeTo(null);
        reviewPage.setVisible(false);
    }

    public JFrame getReviewPage () {
        return reviewPage;
    }
    
    public abstract void showVendorReviewPage ();

}
