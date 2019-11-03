import javax.swing.*;
import java.awt.*;

class ResultPanel extends JPanel {

    private JButton confirm;
    private JLabel picture;
    static int won;
    private ImageIcon myPicture;

    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    ResultPanel() {

        setLayout(null);

        createLabels();
        createButtons();
        setPicture();

        add();
        actions();
    }

    private void createLabels() {

        picture = new JLabel();
        picture.setBounds(0, 0, 500, 500);

    }

    private void createButtons() {

        confirm = new JButton();
        confirm.setText(">>");
        confirm.setBounds(450, 400, 50, 30);

    }

    private void setPicture() {

        if (won == 1) {
            try {
                myPicture = new ImageIcon(this.getClass().getResource("won.gif"));
            } catch (Exception e) {
                System.out.println("Error");
            }
        } else {
            try {
                myPicture = new ImageIcon(this.getClass().getResource("lost.gif"));
            } catch (Exception e) {
                System.out.println("Error");
            }
        }

        picture.setIcon((myPicture));
    }

    private void add() {

        add(picture);
        add(confirm);
    }

    private void actions() {
        confirm.addActionListener(e -> {
            System.out.println(won);
//            createPanel2.setVisible(true);
//            String player = Datas.getNamePlayer2();
//            CreatePanel2.label.setText(player + " wymyśla hasło: ");
//            setVisible(false);
        });
    }

    JButton getConfirm() {
        return confirm;
    }
}

