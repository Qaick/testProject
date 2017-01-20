import javax.imageio.ImageIO;
import javax.swing.*;

import java.applet.Applet;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
                    testImage();
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
    
    static void testImage( )
    {
        Image image1 = Toolkit.getDefaultToolkit().getImage("url or path");
        Image image = null;
        try
        {
            image = new Applet().getImage(new URL("http://earaaanix.com"));
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        System.out.println(image.getClass());
    }
    
    static void trans(Graphics2D graphics){
        BufferedImage bImg = graphics.getDeviceConfiguration().
                createCompatibleImage(12, 12, Transparency.BITMASK);
    }

    static void a(Graphics2D g2d){
        
    }
}
