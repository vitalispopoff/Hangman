import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

class MyFrame extends JFrame {

    MyFrame() {

        setTitle("Szubienica");
        setResizable(false);
        URL iconURL = getClass().getResource("Hangman-game.png");
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        int posX = width / 10 - getWidth() / 10;
        int posY = height / 10 - getHeight() / 10;

        setLocation(posX, posY);

        LoginPanel loginPanel = new LoginPanel();

        add(loginPanel);

        loginPanel.getConfirm().addActionListener(e -> {

            CreatePanel createPanel = new CreatePanel();
            if (loginPanel.check()) {

                createPanel.getLabel().setText("Wymyśla: " + loginPanel.getPlayer1().getText());
                add(createPanel);
                pack();
                remove(loginPanel);
                repaint();
                revalidate();

            } else {
                JOptionPane.showMessageDialog(null, "Uzupełnij dane.");
            }

            createPanel.getConfirm().addActionListener(e12 -> {

                HangJPanel hangJPanel = new HangJPanel(createPanel, loginPanel);

                if (createPanel.check()) {
                    hangJPanel.setCurrentPlayers();
                    add(hangJPanel);
                    pack();
                    remove(createPanel);
                    repaint();
                    revalidate();
                } else {
                    JOptionPane.showMessageDialog(null, createPanel.getMessage());
                }

                hangJPanel.getConfirm().addActionListener(e1 -> {

                    if (HangJPanel.totalGamesCounter == 2) {
                        ResultPanel resultPanel = new ResultPanel(hangJPanel);
                        add(resultPanel);
                        pack();
                        remove(hangJPanel);
                        repaint();
                        revalidate();

                        resultPanel.getConfirm().addActionListener(e2 ->
                                dispose());
                    } else {
                        createPanel.getLabel().setText("Wymyśla: " + loginPanel.getPlayer2().getText());
                        createPanel.getCategories().setSelectedIndex(0);
                        createPanel.setHint();

                        add(createPanel);
                        pack();
                        remove(hangJPanel);
                        repaint();
                        revalidate();
                    }
                });
            });
        });

        pack();
    }
}