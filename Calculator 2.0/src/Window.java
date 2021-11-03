import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {public static void main(String[] args) {SwingUtilities.invokeLater(Window::new);}
    Window() {
        super("Calc");
        setSize(250 + 6,370 + 34);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().createImage(getClass().getResource("calc.png")));
        setResizable(false);
        add(new Panel());
        setVisible(true);
    }
}