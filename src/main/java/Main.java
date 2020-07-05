import java.awt.EventQueue;
import javax.swing.*;

public class Main {


    public static void main(String[] args) {

        LoginPanel
                loginPanel = new LoginPanel();
        CreatePanel
                createPanel = new CreatePanel();
        HangJPanel
                hangJPanel = new HangJPanel(createPanel, loginPanel);
        ResultPanel
                resultPanel = new ResultPanel(hangJPanel);

        EventQueue.invokeLater(() -> {
            MyFrame
                    window = new MyFrame();

            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
            SwingUtilities.updateComponentTreeUI(window);
        });

        ResultPanel.setLocal();
        resultPanel.animation();
    }
}

