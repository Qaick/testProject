import javax.swing.*;
import java.awt.*;

public class BorderLayoutDemo {
    
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("CleanFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());
        frame.add(new JLabel("center"), BorderLayout.CENTER);

        // first priority
        frame.add(new JLabel("first"), BorderLayout.BEFORE_FIRST_LINE);
        frame.add(new JLabel("last"), BorderLayout.AFTER_LAST_LINE);
        frame.add(new JLabel("after"), BorderLayout.AFTER_LINE_ENDS);
        frame.add(new JLabel("before"), BorderLayout.BEFORE_LINE_BEGINS);
        
        // second priority
        frame.add(new JLabel("north"), BorderLayout.NORTH);
        frame.add(new JLabel("south"), BorderLayout.SOUTH);
        frame.add(new JLabel("west"), BorderLayout.WEST);
        frame.add(new JLabel("east"), BorderLayout.EAST);
        

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
