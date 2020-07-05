import javax.swing.*;
import java.awt.*;

class LoginPanel extends JPanel{

    private JLabel
            playersLabel,
            imageLabel;
    private ImageIcon
            headImage;
    private JTextField
            player1,
            player2;
    private JButton
            reset,
            confirm;
    private String
            message;
    static Font
            panelFont = new Font("Comic Sans MS", Font.PLAIN, 18),
            hintFont = new Font("Comic Sans MS", Font.ITALIC, 18);


    LoginPanel(){                                               //panel wpisywania graczy
        setLayout(null);
        setBackground(new Color(215,216,218));

        createFields();
        createButtons();
        setImage();

        add();
        actions();
    }

    private void createFields(){
        imageLabel = new JLabel();
        imageLabel.setBounds(20,400,200, 100);

        playersLabel = new JLabel();
        playersLabel.setText("Podaj graczy:");
        playersLabel.setBounds(200, 150, 300, 30);
        playersLabel.setFont(panelFont);

        player1 = new HintTextField("Gracz 1");
        player1.setBounds(200, 200, 300, 30);
        player1.setFont(hintFont);
        player1.setForeground(Color.gray);
        player1.setBorder(MyFrame.blackBorder());

        player2 = new HintTextField("Gracz 2");
        player2.setBounds(200, 250, 300, 30);
        player2.setFont(hintFont);
        player2.setForeground(Color.gray);
        player2.setBorder(MyFrame.blackBorder());
    }

    private void createButtons(){
        reset = new JButton();
        reset.setText("Wyczyść");
        reset.setBounds(200, 300, 150, 30);
        reset.setFont(panelFont);

        confirm = new JButton();
        confirm.setText("OK");
        confirm.setBounds(350, 300, 150, 30);
        confirm.setFont(panelFont);
    }

    private void add(){
        add(imageLabel);
        add(playersLabel);
        add(player1);
        add(player2);
        add(reset);
        add(confirm);
    }

    private void actions(){
        reset.addActionListener(e -> {
            player1.setText("");
            player2.setText("");
        });
    }

    private void setImage(){
        try {
            headImage = new ImageIcon(this.getClass().getResource("head.png"));
        }
        catch (Exception e) {System.out.println("Problem with picture: head.png");}

        imageLabel.setIcon(headImage);
    }

    boolean check(){
        boolean
                isEmpty = player1.getText().length() == 0 || player2.getText().length() == 0,
                isDifferent = !(player1.getText().equals(player2.getText()));

        if(isEmpty)
            message = "Uzupełnij dane.";
        else if(!isDifferent)
            message = "Nazwy graczy nie mogą być takie same.";

        return !isEmpty && isDifferent;
    }

    JButton getConfirm() {
        return confirm;
    }
    JButton getReset() {
        return reset;
    }

    JTextField getPlayer1(){
        return player1;
    }
    JTextField getPlayer2(){
        return player2;
    }

    String getMessage(){
        return message;
    }
}