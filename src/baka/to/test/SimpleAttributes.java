package baka.to.test;

import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Chet
 */
public class SimpleAttributes extends JComponent {

    protected void paintComponent(Graphics g) {
        // We will need a Graphics2D object to set the RenderingHint
        Graphics2D g2d = (Graphics2D)g;
        // Erase to white
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, getWidth(), getHeight());
        // Draw line with default (aliased) setting
        g2d.drawLine(0, 0, 50, 50);
        // Enable antialiasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        // Draw line with new (anti-aliased) setting
        g2d.drawLine(50, 0, 100, 50);
    }

    private static void createAndShowGUI() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(150, 100);
        JComponent test = new SimpleAttributes();
        f.add(test);
        f.setVisible(true);
    }

    public static void main(String args[]) {
        Runnable doCreateAndShowGUI = new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        };
        SwingUtilities.invokeLater(doCreateAndShowGUI);
    }
}