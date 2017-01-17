package baka.to.test;

import javax.swing.*;
import java.awt.*;

public class TestClobber
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(new Dimension(400, 400));
            JPanel panel1 = new JPanel(new BorderLayout())
            {
                @Override
                public void paint(Graphics g){
                    g.setColor(Color.blue);
                    Graphics2D g2d  = (Graphics2D)g;
                    AlphaComposite newComposite =
                            AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f);
                    g2d.setComposite(newComposite);
                    System.out.println(this+" "+ g);
                    super.paint(g);
                    System.out.println(this+" "+ g);
                }
                @Override
                protected void paintComponent(Graphics g)
                {
                    System.out.println(this+" "+ g);
                    super.paintComponent(g);
                    System.out.println(this+" "+ g);
                    //                    g.fillOval(0,0,100,100);
                    g.fillRect(50, 50, 100, 100);
                }
            };
            JPanel panel2 = new JPanel(new BorderLayout())
            {
                @Override
                public void paint(Graphics g){
                    g.setColor(Color.blue);
                    System.out.println(this+" "+ g);
                    super.paint(g);
                    System.out.println(this+" "+ g);
                }
                @Override
                protected void paintComponent(Graphics g)
                {
                    System.out.println(this+" "+ g);
                    super.paintComponent(g);
                    System.out.println(this+" "+ g);
//                    g.fillRect(50, 50, 100, 100);
                    g.fillOval(0, 0, 100, 100);
                    //                    g.fill3DRect(50,50,100,100,true);
                }
            };
            panel2.setOpaque(false);
            JPanel panel3 = new JPanel()
            {
                @Override
                protected void paintComponent(Graphics g)
                {
                    System.out.println(this+" "+ g);
                    super.paintComponent(g);
                    System.out.println(this+" "+ g);
                    int x=100,y=100;
                    g.fillPolygon(new int[]{10+x,50+x,100+x},new int[]{10+y,100+y,50+y},3);
                }
            };
            panel3.setOpaque(false);
            panel2.add(panel3);
            panel1.add(panel2);
            panel1.add(new JButton("adsf"), BorderLayout.NORTH);
            frame.getContentPane().add(panel1);
            frame.setVisible(true);
        });
    }
}
