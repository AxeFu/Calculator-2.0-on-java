import java.awt.*;
import javax.swing.*;

public class Panel extends JPanel {
    Panel() {
        setLayout(null);
        Font font = new Font("", Font.BOLD, 20);

        JTextField text = new JTextField("");
        text.setBounds(10,10, 230, 50);
        text.setEditable(false);
        text.setFont(font);
        add(text);

        String titles = "()C<789/456*123-.0=+";
        JButton[] buttons = new JButton[20];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[4*i + j] = new JButton(titles.charAt(4*i + j)+"");
                buttons[4*i + j].setBounds(10 + 60 * j, 70 + 60 * i, 50, 50);
                buttons[4*i + j].setFocusPainted(false);
                buttons[4*i + j].setFont(font);
                add(buttons[4*i + j]);
            }
        }
        for (int i = 0; i < 20; i++) { int I = i;
            if ((i < 2 || i > 3) && (i != 18)) buttons[i].addActionListener(JButton -> text.setText(text.getText() + buttons[I].getText()));
        }
        buttons[2].addActionListener(JButton -> text.setText(""));
        buttons[3].addActionListener(JButton -> {
            String msg = text.getText();
            text.setText("");
            for (int i = 0; i < msg.length()-1; i++) text.setText(text.getText() + msg.charAt(i));
        });
        buttons[18].addActionListener(JButton -> text.setText(Calculate.Result(text.getText())));
    }
}