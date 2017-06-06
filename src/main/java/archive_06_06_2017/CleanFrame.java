package archive_06_06_2017;

import javax.swing.*;
import java.awt.*;

public class CleanFrame {
    
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("archive_06_06_2017.CleanFrame");
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
