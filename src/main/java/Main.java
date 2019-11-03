import java.awt.EventQueue;
import javax.swing.*;

public class Main {


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            MyFrame window;
            window = new MyFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
            SwingUtilities.updateComponentTreeUI(window);
        });
    }
}