import javax.swing.*;
import java.awt.*;

public class CleanFrame {
    
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("CleanFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(new JLabel("Label in frame"));

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
