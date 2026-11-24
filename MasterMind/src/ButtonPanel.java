import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    JButton submitButton;
    JButton exitButton;

    public ButtonPanel() {
        super(new FlowLayout(FlowLayout.CENTER, 10, 0));
        setOpaque(false);

        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(90, 28));
        add(submitButton);

        exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(90, 28));
        add(exitButton);
    }
}
