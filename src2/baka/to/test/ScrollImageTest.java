import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class ScrollImageTest extends JPanel {
    private static final long serialVersionUID = 1L;
    private BufferedImage image;
    private BufferedImage image2;
    private JPanel panel;

    public ScrollImageTest() {
        try {
            this.image = ImageIO.read(new URL("http://interviewpenguin.com/wp-content/uploads/2011/06/java-programmers-brain.jpg"));
            this.image2 = ImageIO.read(new URL("http://echo.msk.ru/files/1549298.jpg"));
        }catch(IOException ex) {
            Logger.getLogger(ScrollImageTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.panel = new JPanel() {
            private static final long serialVersionUID = 1L;
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
                g.drawImage(image2, 0, image.getHeight(), null);
            }
        };
        panel.add(new JButton("Currently I do nothing"));
        panel.setPreferredSize(
                new Dimension(image.getWidth() > image2.getWidth() ? image.getWidth() : image2.getWidth(),
                        image.getHeight() + image2.getHeight()));

        JScrollPane sp = new JScrollPane(panel);
        setLayout(new BorderLayout());
        add(sp, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JPanel p = new ScrollImageTest();
                JFrame f = new JFrame();
                f.setContentPane(p);
                f.setSize(400, 300);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setVisible(true);
            }
        });
    }
}