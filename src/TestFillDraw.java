import javax.swing.*;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static javafx.scene.input.KeyCode.R;

public class TestFillDraw
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(new Dimension(400, 400));
            frame.getContentPane().add(new JPanel(){
                @Override
                protected void paintComponent(Graphics g){
                    Graphics2D g2 = (Graphics2D) g;
                    super.paintComponent(g2);
                    g2.scale(10,10);
//                    g2.setTransform(AffineTransform.getScaleInstance(2,2));
                    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                    
                    g2.setColor(Color.LIGHT_GRAY);
                    g2.fillRect(0, 0, 2, 2);
                    g2.setColor(Color.BLACK);
                    g2.drawRect(0, 0, 2, 2);
                }
            });
            frame.setVisible(true);
        });
    }
}
