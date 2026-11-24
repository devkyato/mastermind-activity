import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {
    private final JLabel[] results;

    public ResultPanel() {
        super(new GridLayout(1, 4));
        setBackground(new Color(235, 235, 235));
        setBorder(BorderFactory.createLineBorder(new Color(110, 110, 110), 1));
        setPreferredSize(new Dimension(220, 34));
        setMinimumSize(new Dimension(220, 34));
        setMaximumSize(new Dimension(220, 34));

        results = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            JLabel result = new JLabel("?", SwingConstants.CENTER);
            result.setForeground(new Color(70, 70, 70));
            result.setOpaque(true);
            result.setBackground(new Color(210, 210, 210));
            result.setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120), 1));
            results[i] = result;
            add(result);
        }
    }

    public void showColors(String[] colors) {
        int i;
        for (i = 0; i < 4; i++) {
            results[i].setText("");
            results[i].setBackground(getColor(colors[i]));
            results[i].setToolTipText(colors[i]);
        }
        repaint();
    }

    private Color getColor(String colorName) {
        if (colorName.equals("Blue")) {
            return new Color(60, 120, 220);
        }
        if (colorName.equals("Green")) {
            return new Color(60, 180, 90);
        }
        if (colorName.equals("Red")) {
            return new Color(220, 70, 70);
        }
        if (colorName.equals("Yellow")) {
            return new Color(245, 210, 60);
        }
        if (colorName.equals("Pink")) {
            return new Color(230, 130, 170);
        }
        if (colorName.equals("Orange")) {
            return new Color(235, 145, 45);
        }
        return new Color(210, 210, 210);
    }
}
