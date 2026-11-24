import javax.swing.*;
import java.awt.*;

public class ComboPanel extends JPanel {
    JComboBox<String>[] colorBoxes;

    @SuppressWarnings("unchecked")
    public ComboPanel() {
        super(new FlowLayout(FlowLayout.CENTER, 10, 0));
        setOpaque(false);
        String[] colors = {"?", "Blue", "Green", "Red", "Yellow", "Pink", "Orange"};
        colorBoxes = new JComboBox[4];
        int i;
        for (i = 0; i < 4; i++) {
            colorBoxes[i] = new JComboBox<>(colors);
            colorBoxes[i].setPreferredSize(new Dimension(56, 28));
            colorBoxes[i].setOpaque(true);
            colorBoxes[i].setBackground(new Color(245, 245, 245));
            add(colorBoxes[i]);
        }
    }

    public String[] getSelectedColors() {
        String[] selected = new String[colorBoxes.length];
        int i;
        for (i = 0; i < colorBoxes.length; i++) {
            selected[i] = (String) colorBoxes[i].getSelectedItem();
        }
        return selected;
    }

    public JComboBox<String> getColorBox(int index) {
        return colorBoxes[index];
    }
}
