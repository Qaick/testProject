package baka.to.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyPanel extends JPanel
{

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setStroke(new BasicStroke(10f, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_MITER));
        g2d.drawLine(0, 0, 10, 10);
        g2d.dispose();
    }

    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Calendar()
        Calendar cal = Calendar.getInstance();
        System.out.println("Current Date Time : " + dateFormat.format(cal.getTime()));
        cal.add(Calendar.DATE, -14);
        System.out.println("Add one day to current date : " + dateFormat.format(cal.getTime()));
        SwingUtilities.invokeLater(() ->
        {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(new Dimension(300, 300));
            frame.setLocation(300,300);
            JPanel panel = new MyPanel();
            panel.setSize(200,200);
            MyButton button = new MyButton();
            button.setMinimumSize(new Dimension(200, 200));
            button.setBackground(Color.black);
            button.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if (button.getBackground().equals(Color.black))
                        button.setBackground(Color.cyan);
                    else button.setBackground(Color.black);
                }
            });
//            panel.add(button);
            JPanel checkerboard = new TranslucentButton.Checkerboard();
            frame.add(panel);
            checkerboard.add(button);
            frame.add(checkerboard);
            frame.setVisible(true);
        });
    }
    
    static class MyButton extends JButton {
        {
            setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
//        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D)g;
//            super.paint(g2d);
            AlphaComposite newComposite =
                    AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f);
//            g2d.setComposite(newComposite);
            super.paintComponent(g2d);
            g2d.dispose();
            
        }
    }
}
