import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

class MyFrame extends JFrame {

    MyFrame() {

        // setSize(500,500);
        setTitle("Szubienica");
        setResizable(false);
        URL iconURL = getClass().getResource("Hangman-game.png");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        int posX = width / 10 - getWidth() / 10;
        int posY = height / 10 - getHeight() / 10;

        setLocation(posX, posY);

        LoginPanel loginPanel = new LoginPanel();

        ResultPanel resultPanel = new ResultPanel();

        add(loginPanel);

        loginPanel.getConfirm().addActionListener(e -> {

            CreatePanel createPanel = new CreatePanel(loginPanel);
            if (loginPanel.check()) {
                add(createPanel);
                pack();
                remove(loginPanel);
                repaint();
                revalidate();
            }

            createPanel.getConfirm().addActionListener(e12 -> {

                HangJPanel hangJPanel = new HangJPanel(createPanel, loginPanel);
                if (createPanel.check()) {
                    add(hangJPanel);
                    pack();
                    remove(createPanel);
                    repaint();
                    revalidate();
                }

                hangJPanel.getConfirm().addActionListener(e1 -> {
                    add(createPanel);
                    pack();
                    remove(hangJPanel);
                    repaint();
                    revalidate();
                });
            });
        });

        pack();
    }
}