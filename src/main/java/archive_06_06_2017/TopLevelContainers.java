package archive_06_06_2017;

import javax.swing.*;
import java.awt.*;

public class TopLevelContainers {

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("archive_06_06_2017.TopLevelContainers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println(frame.getLayout());

        /*
        frame
        root pane
        layered pane
        content pane
        glass pane
         */
        frame.getRootPane();
        frame.getLayeredPane();
        frame.getContentPane();
        frame.getGlassPane();
        
        
        frame.getContentPane().setLayout(new FlowLayout());
        JLabel comp1 = new JLabel("Label in content panel");
        
        Menu menu = new Menu("Menu");
        menu.add("menulabel");
        frame.setMenuBar(new MenuBar());
        frame.getMenuBar().add(menu);
        frame.add(new JLabel("Label in frame"));
        JLabel comp = new JLabel("Label in frame");
        
        // in both variants parent is same JPanel
        frame.getContentPane().add(comp1);
        frame.add(comp);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
