import javax.swing.*;
import java.awt.*;

class MyFrame extends JFrame {

    MyFrame() {

        setSize(500,500);
        setTitle("Szubienica");
        setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        int posX = width / 2 - getWidth() / 2;
        int posY = height / 2 - getHeight() / 2;

        setLocation(posX, posY);

        LoginPanel loginPanel = new LoginPanel();
        CreatePanel createPanel = new CreatePanel();
        GuessPanel guessPanel = new GuessPanel();
        ResultPanel resultPanel = new ResultPanel();

        add(loginPanel);

        loginPanel.getConfirm().addActionListener(e -> {
            add(createPanel);
        });

        createPanel.getConfirm().addActionListener(e ->{
            add(guessPanel);
        });

        guessPanel.getConfirm().addActionListener(e ->{
            add(resultPanel);
        });

        resultPanel.getConfirm().addActionListener( e -> {
            if(guessPanel.check())
                dispose();
        });
    }
}