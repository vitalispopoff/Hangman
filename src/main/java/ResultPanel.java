
import javax.swing.*;
import java.awt.*;

class ResultPanel extends JPanel {

    private JButton confirm;
    private JLabel picture;
    private ImageIcon myPicture;
    private int pointPlayer1, pointPlayer2;

    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    ResultPanel(HangJPanel hangJPanel) { //ostatnie okno z końcowymi wynikami gry

        this.pointPlayer1 = hangJPanel.getPointPlayer1();
        this.pointPlayer2 = hangJPanel.getPointPlayer2();

        setLayout(null);
        setBackground(new Color(215,216,218));

        createLabels();
        createButtons();
        setPicture();
        add();
    }

    private void createLabels() {

        picture = new JLabel();
        picture.setBounds(0, 0, 500, 450);

    }

    private void createButtons() {

        confirm = new JButton();
        confirm.setText("Koniec");
        confirm.setBounds(200, 460, 100, 30);

    }

    private void setPicture() {

        if (pointPlayer1 == 1 && pointPlayer2 == 1) {
            try {
                myPicture = new ImageIcon(this.getClass().getResource("doublewon.gif"));
            } catch (Exception e) {
                System.out.println("Error");
            }
        } else if (pointPlayer1 == 1 || pointPlayer2 == 1){
            try {
                myPicture = new ImageIcon(this.getClass().getResource("wonlost.gif"));
            } catch (Exception e) {
                System.out.println("Error");
            }
        } else {
            try {
                myPicture = new ImageIcon(this.getClass().getResource("doublelost.gif"));
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

    JButton getConfirm() {
        return confirm;
    }
}

