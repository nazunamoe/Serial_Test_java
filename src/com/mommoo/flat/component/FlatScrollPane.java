package com.mommoo.flat.component;

import com.mommoo.flat.frame.FlatFrame;
import com.mommoo.flat.text.textarea.FlatTextArea;
import com.mommoo.util.ColorManager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * Created by mommoo on 2017-03-11.
 */
public class FlatScrollPane extends JScrollPane {
    private Color themeColor = ColorManager.getColorAccent();

    public FlatScrollPane(Component view, int vsbPolicy, int hsbPolicy) {
        super(view, vsbPolicy, hsbPolicy);
        init();
    }

    public FlatScrollPane(Component view) {
        super(view);
        init();
    }
    
    public FlatScrollPane(Component view, Color input) {
        super(view);
        setThemeColor(input);
        init();
    }


    public FlatScrollPane(int vsbPolicy, int hsbPolicy) {
        super(vsbPolicy, hsbPolicy);
        init();
    }

    public FlatScrollPane() {
        super();
        init();
    }

    public static void main(String[] ags) {
        FlatScrollPane flatScrollPane = new FlatScrollPane();
        FlatFrame flatFrame = new FlatFrame();
        flatFrame.setTitle("FlatScrollPane Test");
        flatFrame.setSize(500,300);
        flatFrame.setLocationOnScreenCenter();
        flatFrame.getContainer().add(flatScrollPane);
        flatFrame.show();
        flatScrollPane.setThemeColor(Color.RED);
    }

    public Color getThemeColor() {
        return this.themeColor;
    }

    public void setThemeColor(Color themeColor) {
        this.themeColor = themeColor;
        repaint();
    }

    public Color getVerticalScrollTrackColor() {
        return getVerticalScrollBar().getBackground();
    }

    public void setVerticalScrollTrackColor(Color trackColor) {
        getVerticalScrollBar().setOpaque(true);
        getVerticalScrollBar().setBackground(trackColor);
        repaint();
    }

    public Color getHorizontalScrollTrackColor() {
        return getHorizontalScrollBar().getBackground();
    }

    public void setHorizontalScrollTrackColor(Color trackColor) {
        getHorizontalScrollBar().setOpaque(true);
        getHorizontalScrollBar().setBackground(trackColor);
        repaint();
    }

    private void init() {
        setBorder(BorderFactory.createEmptyBorder());
        setVerticalScrollBar(new FlatScrollBar());
        setHorizontalScrollBar(new FlatScrollBar());
    }

    private class FlatScrollBar extends JScrollBar {

        FlatScrollBar() {
            setUI(new FlatScrollBarUI());
            setPreferredSize(new Dimension(10, 0));
            setOpaque(false);
        }
    }

    private class FlatScrollBarUI extends BasicScrollBarUI {

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Color color;
            if (!c.isEnabled() || thumbBounds.width > thumbBounds.height) {
                return;
            } else if (isDragging) {
                color = themeColor.darker();
            } else if (isThumbRollover()) {
                color = themeColor.brighter();
            } else {
                color = themeColor;
            }

            g2.setPaint(color);
            g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 5, 5);
            g2.dispose();
        }

        @Override
        protected void setThumbBounds(int x, int y, int width, int height) {
            super.setThumbBounds(x, y, width, height);
            scrollbar.repaint();
        }

        private JButton createZeroButton() {
            JButton button = new JButton();
            Dimension zeroDim = new Dimension(0, 0);
            button.setPreferredSize(zeroDim);
            button.setMinimumSize(zeroDim);
            button.setMaximumSize(zeroDim);
            return button;
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }
}}