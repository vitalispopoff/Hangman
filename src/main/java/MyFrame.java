
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

class MyFrame extends JFrame {

    LoginPanel loginPanel;
    CreatePanel createPanel;
    ResultPanel resultPanel;
    HangJPanel hangJPanel;

    MyFrame() {
        try {
            URL iconURL = getClass().getResource("Hangman-game.png");
            ImageIcon icon = new ImageIcon(iconURL);
            setIconImage(icon.getImage());
        }
        catch (Exception ex){
            System.out.println("Problem with picture: Hangman-game.png");
        }

        setSize(700,530);
        setTitle("Szubienica");
        setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        int posX = width / 2 - getWidth() / 2;
        int posY = height / 2 - getHeight() / 2;

        setLocation(posX, posY);
        loginPanel = new LoginPanel();

        getContentPane().add(loginPanel);

        loginPanel.getPlayer1().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    loginPanel.getConfirm().doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        loginPanel.getPlayer2().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    loginPanel.getConfirm().doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        loginPanel.getConfirm().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    loginPanel.getConfirm().doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        loginPanel.getReset().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    loginPanel.getReset().doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        loginPanel.getConfirm().addActionListener(e -> {

            createPanel = new CreatePanel();
            if (loginPanel.check()) {

                createPanel.getLabel().setText(loginPanel.getPlayer1().getText() + " wymyśla hasło");
                getContentPane().add(createPanel);
                getContentPane().remove(loginPanel);
                repaint();
                revalidate();

            } else {
                JOptionPane.showMessageDialog(null, loginPanel.getMessage());
            }

            createPanel.getWord().addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        createPanel.getConfirm().doClick();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {}
            });
            createPanel.getConfirm().addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        createPanel.getConfirm().doClick();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {}
            });
            createPanel.getReset().addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        createPanel.getReset().doClick();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {}
            });

            createPanel.getConfirm().addActionListener(e12 -> {

                hangJPanel = new HangJPanel(createPanel, loginPanel);

                if (createPanel.check()) {
                    hangJPanel.setCurrentPlayers();
                    getContentPane().add(hangJPanel);
                    getContentPane().remove(createPanel);
                    repaint();
                    revalidate();
                } else {
                    JOptionPane.showMessageDialog(null, createPanel.getMessage());
                }

                hangJPanel.getConfirm().addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(e.getKeyCode() == KeyEvent.VK_ENTER)
                            hangJPanel.getConfirm().doClick();}

                    @Override
                    public void keyReleased(KeyEvent e) {}
                });

                hangJPanel.getConfirm().addActionListener(e1 -> {

                    if (HangJPanel.totalGamesCounter == 2) {
                        resultPanel = new ResultPanel(hangJPanel);
                        getContentPane().add(resultPanel);
                        getContentPane().remove(hangJPanel);
                        repaint();
                        revalidate();

                        resultPanel.getConfirm().addKeyListener(new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent e) {

                            }

                            @Override
                            public void keyPressed(KeyEvent e) {
                                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                                    resultPanel.getConfirm().doClick();
                                }
                            }

                            @Override
                            public void keyReleased(KeyEvent e) {

                            }
                        });

                        resultPanel.getConfirm().addActionListener(e2 ->
                                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

                    } else {
                        createPanel.getLabel().setText(loginPanel.getPlayer2().getText() + " wymyśla hasło");
                        createPanel.getCategories().setSelectedIndex(0);
                        createPanel.getWord().setText("");

                        getContentPane().add(createPanel);
                        getContentPane().remove(hangJPanel);
                        repaint();
                        revalidate();
                    }
                });
            });
        });
    }


    static Border blackBorder(){
        return BorderFactory.createLineBorder(Color.black, 1);
    }

}