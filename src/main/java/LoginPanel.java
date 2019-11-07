import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class LoginPanel extends JPanel{

    private JLabel playersLabel;
    private JTextField player1, player2;
    private JButton reset, confirm;
    static Font panelFont = new Font("Comic Sans MS", Font.PLAIN, 18);
    static Font hintFont = new Font("Comic Sans MS", Font.ITALIC, 18);


    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    LoginPanel(){ //panel wpisywania graczy

        setLayout(null);
        setBackground(new Color(215,216,218));
        createFields();
        createButtons();
        add();
        actions();

    }

    private void createFields(){

        playersLabel = new JLabel();
        playersLabel.setText("Podaj graczy:");
        playersLabel.setBounds(100, 100, 300, 30);
        playersLabel.setFont(panelFont);

        player1 = new HintTextField("Gracz 1");
        player1.setBounds(100, 150, 300, 30);
        player1.setFont(hintFont);
        player1.setForeground(Color.gray);

        player1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                player1.setText("");
                player1.setFont(panelFont);
                player1.setForeground(Color.black);
            }
        });



        player2 = new HintTextField("Gracz 2");
        player2.setBounds(100, 200, 300, 30);
        player2.setFont(hintFont);
        player2.setForeground(Color.gray);
        player2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                player2.setText("");
                player2.setFont(panelFont);
                player2.setForeground(Color.black);
            }
        });
    }

    private void createButtons(){

        reset = new JButton();
        reset.setText("Wyczyść");
        reset.setBounds(100, 250, 150, 30);
        reset.setFont(panelFont);

        confirm = new JButton();
        confirm.setText("OK");
        confirm.setBounds(250, 250, 150, 30);
        confirm.setFont(panelFont);
    }

    private void add(){

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

    boolean check(){
        return player1.getText().length() != 0 && player2.getText().length() != 0;
    }

    JButton getConfirm() {
        return confirm;
    }

    JTextField getPlayer1(){
        return player1;
    }

    JTextField getPlayer2(){
        return player2;
    }


}