import java.awt.EventQueue;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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

        LoginPanel loginPanel = new LoginPanel();
        CreatePanel createPanel = new CreatePanel();
        HangJPanel hangJPanel = new HangJPanel(createPanel, loginPanel);
        ResultPanel resultPanel = new ResultPanel(hangJPanel);

        ResultPanel.setLocal();
        resultPanel.animation();

    }


}

