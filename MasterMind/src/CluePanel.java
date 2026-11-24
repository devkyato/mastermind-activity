import javax.swing.*;
import java.awt.*;

public class CluePanel extends JPanel {
    JLabel[] clues;

    public CluePanel() {
        super(null);
        setBackground(new Color(235, 235, 235));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setPreferredSize(new Dimension(38, 38));
        setMinimumSize(new Dimension(38, 38));
        setMaximumSize(new Dimension(38, 38));

        clues = new JLabel[4];
        int i;
        for (i = 0; i < 4; i++) {
            clues[i] = new JLabel();
            clues[i].setOpaque(true);
            clues[i].setBackground(new Color(180, 180, 180));
            clues[i].setBorder(BorderFactory.createLineBorder(new Color(110, 110, 110), 1));
            clues[i].setBounds(6 + (i % 2) * 14, 6 + (i / 2) * 14, 12, 12);
            add(clues[i]);
        }
    }

    public void setClues(String[] guess, String[] secret) {
        boolean[] usedSecret = new boolean[4];
        boolean[] usedGuess = new boolean[4];
        int i;
        int j;

        for (i = 0; i < 4; i++) {
            clues[i].setBackground(new Color(180, 180, 180));

            if (guess[i].equals(secret[i])) {
                clues[i].setBackground(Color.BLACK);
                usedSecret[i] = true;
                usedGuess[i] = true;
            }
        }

        for (i = 0; i < 4; i++) {
            if (!usedGuess[i]) {
                for (j = 0; j < 4; j++) {
                    if (!usedSecret[j] && guess[i].equals(secret[j])) {
                        clues[i].setBackground(Color.WHITE);
                        usedSecret[j] = true;
                        usedGuess[i] = true;
                        break;
                    }
                }
            }
        }

        setToolTipText("Black = correct position, White = wrong position, Gray = wrong color");
    }
}
