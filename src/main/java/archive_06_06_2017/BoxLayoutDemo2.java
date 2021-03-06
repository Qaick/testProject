package archive_06_06_2017;/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


/*
 * archive_06_06_2017.BoxLayoutDemo2.java requires one other file:  BLDComponent.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BoxLayoutDemo2 implements ItemListener {
    

    /** A rectangle that has a fixed size. */
    class BLDComponent extends JComponent
            implements MouseListener {
        private Color normalHue;
        private final Dimension preferredSize;
        private String name;
        private boolean restrictMaximumSize;
        private boolean printSize;

        public BLDComponent(float alignmentX, float hue,
                            int shortSideSize,
                            boolean restrictSize,
                            boolean printSize,
                            String name) {
            this.name = name;
            this.restrictMaximumSize = restrictSize;
            this.printSize = printSize;
            setAlignmentX(alignmentX);
            normalHue = Color.getHSBColor(hue, 0.4f, 0.85f);
            preferredSize = new Dimension(shortSideSize*2,
                    shortSideSize);

            addMouseListener(this);
        }

        //The MouseListener interface requires that we define
        //mousePressed, mouseReleased, mouseEntered, mouseExited,
        //and mouseClicked.
        public void mousePressed(MouseEvent e) {
            int width = getWidth();
            float alignment = (float)(e.getX())
                    / (float)width;

            // Round to the nearest 1/10th.
            int tmp = Math.round(alignment * 10.0f);
            alignment = (float)tmp / 10.0f;

            setAlignmentX(alignment);
            revalidate(); // this GUI needs relayout
            repaint();
        }
        public void mouseReleased(MouseEvent e) { }
        public void mouseEntered(MouseEvent e) { }
        public void mouseExited(MouseEvent e) { }
        public void mouseClicked(MouseEvent e) { }

        /**
         * Our BLDComponents are completely opaque, so we override
         * this method to return true.  This lets the painting
         * system know that it doesn't need to paint any covered
         * part of the components underneath this component.  The
         * end result is possibly improved painting performance.
         */
        public boolean isOpaque() {
            return true;
        }

        public void paint(Graphics g) {
            int width = getWidth();
            int height = getHeight();
            float alignmentX = getAlignmentX();

            g.setColor(normalHue);
            g.fill3DRect(0, 0, width, height, true);

        /* Draw a vertical white line at the alignment point.*/
            // XXX: This code is probably not the best.
            g.setColor(Color.white);
            int x = (int)(alignmentX * (float)width) - 1;
            g.drawLine(x, 0, x, height - 1);

        /* Say what the alignment point is. */
            g.setColor(Color.black);
            g.drawString(Float.toString(alignmentX), 3, height - 3);

            if (printSize) {
                System.out.println("BLDComponent " + name + ": size is "
                        + width + "x" + height
                        + "; preferred size is "
                        + getPreferredSize().width + "x"
                        + getPreferredSize().height);
            }
        }

        public Dimension getPreferredSize() {
            return preferredSize;
        }

        public Dimension getMinimumSize() {
            return preferredSize;
        }

        public Dimension getMaximumSize() {
            if (restrictMaximumSize) {
                return preferredSize;
            } else {
                return super.getMaximumSize();
            }
        }

        public void setSizeRestriction(boolean restrictSize) {
            restrictMaximumSize = restrictSize;
        }
    }
    
    protected static int NUM_COMPONENTS = 3;
    protected static float[] xAlignment = {
            Component.LEFT_ALIGNMENT,
            Component.CENTER_ALIGNMENT,
            Component.RIGHT_ALIGNMENT};
    protected static float[] hue = {0.0f, 0.33f, 0.67f};
    protected static boolean restrictSize = true;
    protected static boolean sizeIsRandom = false;
    protected static BLDComponent[] bldComponent =
            new BLDComponent[NUM_COMPONENTS];

    public void populateContentPane(Container contentPane) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        //Create the rectangles.
        int shortSideSize = 15;
        for (int i = 0; i < NUM_COMPONENTS; i++) {
            if (sizeIsRandom) {
                shortSideSize = (int)(30.0 * Math.random()) + 30;
            } else {
                shortSideSize += 10;
            }
            bldComponent[i] = new BLDComponent(xAlignment[i], hue[i],
                    shortSideSize,
                    restrictSize,
                    sizeIsRandom,
                    String.valueOf(i));
            panel.add(bldComponent[i]);
        }

        //Create the instructions.
        JLabel label = new JLabel("Click a rectangle to "
                + "change its X alignment.");
        JCheckBox cb = new JCheckBox("Restrict maximum rectangle size.");
        cb.setSelected(restrictSize);
        cb.addItemListener(this);

        panel.setBorder(BorderFactory.createLineBorder(Color.red));

        Box box = Box.createVerticalBox();
        box.add(label);
        box.add(cb);

    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            restrictSize = true;
        } else {
            restrictSize = false;
        }
        notifyBLDComponents();
    }

    static public void notifyBLDComponents() {
        for (int i = 0; i < NUM_COMPONENTS; i++) {
            bldComponent[i].setSizeRestriction(restrictSize);
        }
        bldComponent[0].revalidate();
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("archive_06_06_2017.BoxLayoutDemo2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        BoxLayoutDemo2 demo = new BoxLayoutDemo2();
        demo.populateContentPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}