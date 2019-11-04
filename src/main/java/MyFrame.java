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

                createPanel.getLabel().setText(loginPanel.getPlayer1().getText());
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
                }

                hangJPanel.getConfirm().addActionListener(e1 -> {

                    System.out.println(hangJPanel.getPointPlayer1());
                    System.out.println(hangJPanel.getPointPlayer2());

                    if (hangJPanel.totalGamesCounter == 2) {
                        JOptionPane.showMessageDialog(null, "Punkty " +
                                loginPanel.getPlayer1().getText()+ ": " + hangJPanel.getPointPlayer1()
                                + "\nPunkty " + loginPanel.getPlayer2().getText()+": " + hangJPanel.getPointPlayer2());
                    } else {

                        if (hangJPanel.totalGamesCounter % 2 == 1) {
                            createPanel.getLabel().setText(loginPanel.getPlayer2().getText());
                        } else {
                            createPanel.getLabel().setText(loginPanel.getPlayer1().getText());
                        }


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