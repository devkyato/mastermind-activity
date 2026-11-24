import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MasterMind {
    JFrame mainFrame;
    AnswerPanel[] MAP;
    CluePanel[] clues;
    String[] secret;
    int currentRow;
    int activeSlot;
    boolean gameOver;

    public MasterMind() {
        mainFrame = new JFrame("MasterMind");
        mainFrame.setSize(350, 650);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        currentRow = 0;
        activeSlot = 0;
        gameOver = false;

        JPanel content = new JPanel();
        content.setBackground(new Color(230, 232, 235));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        MAP = new AnswerPanel[10];
        clues = new CluePanel[10];
        secret = new String[]{"Blue", "Yellow", "Orange", "Pink"};
        int j;
        for (j = 0; j < 10; j++) {
            MAP[j] = new AnswerPanel();
            clues[j] = new CluePanel();

            JPanel row = new JPanel();
            row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
            row.setOpaque(false);
            row.setAlignmentX(Component.CENTER_ALIGNMENT);
            row.add(MAP[j].AnsPanel);
            row.add(Box.createHorizontalStrut(10));
            row.add(clues[j]);
            content.add(row);

            if (j < MAP.length - 1) {
                content.add(Box.createVerticalStrut(4));
            }
        }

        content.add(Box.createVerticalStrut(28));
        ComboPanel comboPanel = new ComboPanel();
        comboPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(comboPanel);

        content.add(Box.createVerticalStrut(8));
        ButtonPanel buttonPanel = new ButtonPanel();
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.exitButton.addActionListener(event -> mainFrame.dispose());
        content.add(buttonPanel);

        content.add(Box.createVerticalStrut(16));
        ResultPanel resultPanel = new ResultPanel();
        resultPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(resultPanel);
        content.add(Box.createVerticalStrut(20));

        int rowIndex;
        for (rowIndex = 0; rowIndex < 10; rowIndex++) {
            final int thisRow = rowIndex;
            int slotIndex;
            for (slotIndex = 0; slotIndex < 4; slotIndex++) {
                final int selectedSlot = slotIndex;
                MAP[rowIndex].getSlot(slotIndex).addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent event) {
                        if (thisRow == currentRow) {
                            activeSlot = selectedSlot;
                            MAP[currentRow].setActiveSlot(activeSlot);
                            comboPanel.getColorBox(activeSlot).requestFocusInWindow();
                        }
                    }
                });
            }
        }

        int slotIndex;
        for (slotIndex = 0; slotIndex < 4; slotIndex++) {
            final int selectedSlot = slotIndex;
            comboPanel.getColorBox(slotIndex).addActionListener(event -> {
                activeSlot = selectedSlot;
                String selectedColor = (String) comboPanel.getColorBox(selectedSlot).getSelectedItem();
                MAP[currentRow].setPreviewColor(selectedSlot, selectedColor);
                MAP[currentRow].setActiveSlot(activeSlot);
                comboPanel.getColorBox(selectedSlot).setBackground(getColor(selectedColor));
            });
        }

        MAP[currentRow].setActiveSlot(activeSlot);

        buttonPanel.submitButton.addActionListener(event -> {
            if (gameOver) {
                return;
            }

            String[] selectedColors = comboPanel.getSelectedColors();
            int i;
            for (i = 0; i < 4; i++) {
                if ("?".equals(selectedColors[i])) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Please select all four colors.",
                            "Incomplete Guess",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            MAP[currentRow].setColors(selectedColors);
            MAP[currentRow].clearActiveSlot();

            int blackCount = countBlack(selectedColors);
            int whiteCount = countWhite(selectedColors);
            clues[currentRow].setClues(selectedColors, secret);

            if (blackCount == 4) {
                resultPanel.showColors(selectedColors);
                JOptionPane.showMessageDialog(mainFrame,
                        "You're a winner",
                        "MasterMind",
                        JOptionPane.INFORMATION_MESSAGE);
                buttonPanel.submitButton.setEnabled(false);
                gameOver = true;
                return;
            }

            if (currentRow < 9) {
                currentRow++;
                activeSlot = 0;
                MAP[currentRow].setActiveSlot(activeSlot);
                int resetIndex;
                for (resetIndex = 0; resetIndex < 4; resetIndex++) {
                    comboPanel.getColorBox(resetIndex).setSelectedItem("?");
                    comboPanel.getColorBox(resetIndex).setBackground(getColor("?"));
                }
                activeSlot = 0;
                MAP[currentRow].setPreviewColor(0, "?");
                MAP[currentRow].setActiveSlot(activeSlot);
            } else if (blackCount != 4) {
                buttonPanel.submitButton.setEnabled(false);
                gameOver = true;
                JOptionPane.showMessageDialog(mainFrame,
                        "You lose",
                        "MasterMind",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        mainFrame.setContentPane(content);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public int countBlack(String[] guess) {
        int blackCount = 0;
        int i;
        for (i = 0; i < 4; i++) {
            if (guess[i].equals(secret[i])) {
                blackCount++;
            }
        }
        return blackCount;
    }

    public int countWhite(String[] guess) {
        boolean[] secretUsed = new boolean[4];
        boolean[] guessUsed = new boolean[4];
        int i;
        int j;

        for (i = 0; i < 4; i++) {
            if (guess[i].equals(secret[i])) {
                secretUsed[i] = true;
                guessUsed[i] = true;
            }
        }

        int whiteCount = 0;
        for (i = 0; i < 4; i++) {
            if (!guessUsed[i]) {
                for (j = 0; j < 4; j++) {
                    if (!secretUsed[j] && guess[i].equals(secret[j])) {
                        secretUsed[j] = true;
                        guessUsed[i] = true;
                        whiteCount++;
                        break;
                    }
                }
            }
        }
        return whiteCount;
    }

    public Color getColor(String colorName) {
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
        return new Color(245, 245, 245);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MasterMind());
    }
}
