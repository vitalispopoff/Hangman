import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class ResultPanel extends JPanel {

    private JButton confirm;

    ResultPanel() {

        setLayout(null);

        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("C:\\Users\\Hania\\IdeaProjects\\Hangman\\gifs\\lost.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert myPicture != null;
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        add(picLabel);

        createButtons();
        add();
        actions();
    }

    private void createButtons(){

        confirm = new JButton();
        confirm.setText(">>");
        confirm.setBounds(450, 400, 50, 30);

    }

    private void add(){
        add(confirm);
    }

    private void actions(){
        confirm.addActionListener(e -> {
            System.out.println(Datas.getNamePlayer2());
//            createPanel.setVisible(true);
//            String player = Datas.getNamePlayer2();
//            CreatePanel.label.setText(player + " wymyśla hasło: ");
//            setVisible(false);
        });
    }

    JButton getConfirm(){
        return confirm;
    }
}