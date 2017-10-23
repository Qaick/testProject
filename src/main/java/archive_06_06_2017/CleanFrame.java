package archive_06_06_2017;

import javax.swing.*;
import javax.swing.text.PlainDocument;

public class CleanFrame {
    
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("archive_06_06_2017.CleanFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextField jTextField = new JTextField("text\n\nsecond");//while inserting this will be deleted EOL symbols
        jTextField.getDocument().putProperty("filterNewlines", Boolean.FALSE);
        PlainDocument document = new PlainDocument();
        frame.getContentPane().add(jTextField);
//        frame.getContentPane().add(new JLabel("Label in frame"));

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
