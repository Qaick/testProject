package baka.to.test;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TestClobber
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setUndecorated(true); // TODO no borders and title bar
            frame.setSize(new Dimension(400, 400));
            JPanel panel1 = new JPanel(new BorderLayout())
            {
                {

                    setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                }
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
                    Insets i = getInsets();// TODO with insets we can get borders
//                    g.fillRect(0, 50, 100, 100);
//                    TestClobber.fillRandPolygon(g);
                    TestClobber.fillCheckerboard(g);
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
//                    g.fillOval(0, 0, 100, 100);
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
//                    g.fillPolygon(new int[]{10+x,50+x,100+x},new int[]{10+y,100+y,50+y},3);
                    Graphics2D g2 = (Graphics2D) g;
                    RenderingHints rh = new RenderingHints(
                            RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                    g2.setFont(new Font("arial", 1, 100));
                    g2.drawString("wo",0,300);
                    g2.setRenderingHints(rh);
                    g2.drawString("wo",150,300);


                    // Paint a gradient for the sky
//                    GradientPaint background = new GradientPaint(
//                            0f, 0f, Color.blue,
//                            0f, (float)getHeight(), Color.red);
//                    g2.setPaint(background);
//                    g2.fillRect(0, 0, getWidth(), 4*getHeight()/5);
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
    
    public static void fillRandPolygon(Graphics g) {
        // TODO random polygon
        Random rand = new Random();
        int size = rand.nextInt(100);
        int x[] = new int[size], y[] = new int[size];
        for (int j = 0; j < size; j++)
        {
            x[j]=rand.nextInt(400);
            y[j]=rand.nextInt(400);
        }
        System.out.println(size);
        g.fillPolygon(x,y,size);
    }
    public static void fillCheckerboard(Graphics g) {
        int x[] = {200,200,100,100,200,200,300,300,000,000,200}, 
            y[] = {100,300,300,000,000,100,100,200,200,100,100};
        g.fillPolygon(x,y,11);
    }
}
