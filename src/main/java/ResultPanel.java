import javax.swing.*;
import java.awt.*;
import java.util.Random;

class ResultPanel extends JPanel {

    private JButton confirm;
    private JLabel picture;
    private ImageIcon myPicture;
    private int pointPlayer1, pointPlayer2;
    private Font panelFont = new Font("Comic Sans MS", Font.PLAIN, 18);

    //animation
    private static Random random = new Random();
    private Color[] colors = {new Color(0,255,0), new Color(255,0,0), new Color(0,0,255),
            new Color(255,255,0), new Color(255,0,255), new Color(0,255,255),
            new Color(175,255,0), new Color(0,175,255), new Color(255,0,175)};
    private static int dimension = 20;
    private static Balloons[] balloons = new Balloons[9];
    private static Rain[] rain = new Rain[300];

    static void setLocal(){

        for (int i = 0; i < balloons.length; i++) {
            balloons[i] = new Balloons();
        }

        for (Balloons balloon : balloons) {
            balloon.setLocationX(random.nextInt(700));
            balloon.setLocationY(450);
            balloon.setDirectionX(random.nextInt(2) + 1);
            balloon.setDirectionY(random.nextInt(5) + 1);
        }

        for (int i = 0; i < rain.length; i++) {
            rain[i] = new Rain();
        }

        for (Rain aRain : rain) {
            aRain.setStartX(random.nextInt(700));
            aRain.setStartY(random.nextInt(450));
            aRain.setEndX(aRain.getStartX() + 2);
            aRain.setEndY(aRain.getStartY() + 3);
        }
    }

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        if(pointPlayer1 == 1 && pointPlayer2 == 1) {
            for (int i = 0; i < balloons.length; i++) {
                graphics2D.setColor(colors[i]);
                graphics2D.fillOval(balloons[i].getLocationX(), balloons[i].getLocationY(), dimension, dimension);
            }
        } else {
            for (Rain aRain : rain) {
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawLine(aRain.getStartX(), aRain.getStartY(), aRain.getEndX(), aRain.getEndY());
            }
        }
    }

    void animation() {

        while (true) {

            for (Balloons balloon : balloons) {
                if (balloon.getLocationY() < 0)
                    balloon.setLocationY(450);
                if (balloon.getLocationX() < 0 || balloon.getLocationX() > 700) {
                    balloon.setLocationX(700);
                    balloon.setLocationX(random.nextInt(700));
                }
                balloon.setLocationX(balloon.getLocationX() - balloon.getDirectionX());
                balloon.setLocationY(balloon.getLocationY() - balloon.getDirectionY());
            }

            for (Rain aRain : rain) {
                if (aRain.getStartY() > 450) {
                    aRain.setStartY(0);
                    aRain.setEndY(3);
                }
                if (aRain.getStartX() > 700) {
                    aRain.setStartX(0);
                    aRain.setEndX(2);
                }
                aRain.setStartX(aRain.getStartX() + 2);
                aRain.setStartY(aRain.getStartY() + 3);
                aRain.setEndX(aRain.getEndX() + 2);
                aRain.setEndY(aRain.getEndY() + 3);
            }

            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    ResultPanel(HangJPanel hangJPanel) { //ostatnie okno z końcowymi wynikami gry

        this.pointPlayer1 = hangJPanel.getPointPlayer1();
        this.pointPlayer2 = hangJPanel.getPointPlayer2();

        setLayout(null);
        setBackground(new Color(215,216,218));

        createLabels();
        createButtons();
//        setPicture();
        add();
    }

    private void createLabels() {
        picture = new JLabel();
        picture.setBounds(0, 0, 700, 450);
    }

    private void createButtons() {
        confirm = new JButton();
        confirm.setText("Koniec");
        confirm.setFont(panelFont);
        confirm.setBounds(300, 450, 100, 40);
    }

//    private void setPicture() {
//        if (pointPlayer1 == 1 && pointPlayer2 == 1) {
//            try {
//                myPicture = new ImageIcon(this.getClass().getResource("doublewon.gif"));
//            } catch (Exception e) {
//                System.out.println("Problem with picture: doublewon.gif");
//            }
//        } else if (pointPlayer1 == 1 || pointPlayer2 == 1){
//            try {
//                myPicture = new ImageIcon(this.getClass().getResource("wonlost.gif"));
//            } catch (Exception e) {
//                System.out.println("Problem with picture: wonlost.gif");
//            }
//        } else {
//            try {
//                myPicture = new ImageIcon(this.getClass().getResource("doublelost.gif"));
//            } catch (Exception e) {
//                System.out.println("Problem with picture: doublelost.gif");
//            }
//        }
//        picture.setIcon((myPicture));
//    }

    private void add() {
        add(picture);
        add(confirm);
    }

    JButton getConfirm() {
        return confirm;
    }
}