import javax.swing.*;
import java.awt.*;

public class AnswerPanel {
    JPanel AnsPanel;
    JLabel L1;
    JLabel L2;
    JLabel L3;
    JLabel L4;

    public AnswerPanel() {
        AnsPanel = new JPanel();
        AnsPanel.setBackground(new Color(235, 235, 235));
        AnsPanel.setBorder(BorderFactory.createLineBorder(new Color(110, 110, 110), 1));
        AnsPanel.setLayout(null);
        AnsPanel.setPreferredSize(new Dimension(220, 38));
        AnsPanel.setMinimumSize(new Dimension(220, 38));
        AnsPanel.setMaximumSize(new Dimension(220, 38));

        L1 = createSlot(18, 7);
        L2 = createSlot(69, 7);
        L3 = createSlot(120, 7);
        L4 = createSlot(171, 7);

        AnsPanel.add(L1);
        AnsPanel.add(L2);
        AnsPanel.add(L3);
        AnsPanel.add(L4);
    }

    private JLabel createSlot(int x, int y) {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setBackground(new Color(180, 180, 180));
        label.setBorder(BorderFactory.createLineBorder(new Color(125, 125, 125), 1));
        label.setBounds(x, y, 20, 20);
        return label;
    }

    public JLabel getSlot(int index) {
        JLabel[] slots = {L1, L2, L3, L4};
        return slots[index];
    }

    public void setActiveSlot(int index) {
        JLabel[] slots = {L1, L2, L3, L4};
        for (int i = 0; i < slots.length; i++) {
            slots[i].setBorder(BorderFactory.createLineBorder(new Color(125, 125, 125), 1));
            slots[i].setBounds(18 + i * 51, 7, 20, 20);
        }
        slots[index].setBorder(BorderFactory.createLineBorder(new Color(82, 102, 126), 3));
        slots[index].setBounds(15 + index * 51, 4, 26, 26);
        AnsPanel.repaint();
    }

    public void clearActiveSlot() {
        JLabel[] slots = {L1, L2, L3, L4};
        for (int i = 0; i < slots.length; i++) {
            slots[i].setBorder(BorderFactory.createLineBorder(new Color(125, 125, 125), 1));
            slots[i].setBounds(18 + i * 51, 7, 20, 20);
        }
        AnsPanel.repaint();
    }

    public void setPreviewColor(int index, String colorName) {
        JLabel slot = getSlot(index);
        slot.setBackground(getColor(colorName));
        slot.setOpaque(true);
    }

    public void setColors(String[] colors) {
        JLabel[] slots = {L1, L2, L3, L4};
        for (int i = 0; i < slots.length; i++) {
            slots[i].setBackground(getColor(colors[i]));
        }
        AnsPanel.repaint();
    }

    private Color getColor(String colorName) {
        switch (colorName) {
            case "Blue":
                return new Color(60, 120, 220);
            case "Green":
                return new Color(60, 180, 90);
            case "Red":
                return new Color(220, 70, 70);
            case "Yellow":
                return new Color(245, 210, 60);
            case "Pink":
                return new Color(230, 130, 170);
            case "Orange":
                return new Color(235, 145, 45);
            default:
                return new Color(180, 180, 180);
        }
    }
}
