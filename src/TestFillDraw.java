import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javafx.scene.input.KeyCode.R;

public class TestFillDraw
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(new Dimension(500, 400));
            frame.getContentPane().add(new JPanel(){
                @Override
                protected void paintComponent(Graphics g){
                    Graphics2D g2 = (Graphics2D) g.create();
                    super.paintComponent(g2);
//                    scaleImage(g2);
                    scaleFigure(g2);
                    g2.dispose();
                }
            });
            frame.setLocation(1000,500);
            frame.setVisible(true);
        });
    }
    
    static void scaleImage(Graphics2D g2) {
        g2.scale(30,30);
        BufferedImage tmp = null;
        try {
            tmp = ImageIO.read(new File("gitLab_earnix.png"));
        } catch (IOException e) {
        }
        g2.drawImage(tmp,0,0,null);
        //load miage
    }
    
    static void scaleFigure(Graphics2D g2) {
//        g2.scale(20,20);
        //                    g2.setTransform(AffineTransform.getScaleInstance(2,2));
//        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(1, 1, 2, 2);
        g2.setColor(Color.BLACK);
        g2.drawRect(0, 0, 2, 2);
    }
}
