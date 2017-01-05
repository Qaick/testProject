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
            JPanel panel = new MyPanel();
            frame.add(panel);
            JButton button = new JButton();
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
//            frame.add(button);
            frame.setVisible(true);
            
        });
    }
}
