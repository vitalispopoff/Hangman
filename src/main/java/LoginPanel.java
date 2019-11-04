import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class LoginPanel extends JPanel {

    private JLabel playersLabel;
    private JTextField player1, player2;
    private JButton reset, confirm;
   // private CreatePanel createPanel = new CreatePanel();
    private String currentPlayer1; //obecny gracz wymyslajacy haslo
    private String currentPlayer2;  //obecny gracz zgadujący hasło
   // private boolean played;
  //  private int pointsPlayer1;
   // private int pointsPlayer2;

    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    LoginPanel(){

        setLayout(null);
       // played = false;
        createFields();
        createButtons();
        add();
        actions();

    }

    private void createFields(){

        playersLabel = new JLabel();
        playersLabel.setText("Podaj graczy:");
        playersLabel.setBounds(100, 100, 300, 30);

        player1 = new JTextField();
        player1.setBounds(100, 150, 300, 30);
        player1.setText("Gracz 1");
        player1.setForeground(Color.gray);
        player1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                player1.setText("");
                player1.setForeground(Color.black);
            }
        });

        player2 = new JTextField();
        player2.setBounds(100, 200, 300, 30);
        player2.setText("Gracz 2");
        player2.setForeground(Color.gray);
        player2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                player2.setText("");
                player2.setForeground(Color.black);
            }
        });
    }

    private void createButtons(){

        reset = new JButton();
        reset.setText("Wyczyść");
        reset.setBounds(100, 250, 150, 30);

        confirm = new JButton();
        confirm.setText("OK");
        confirm.setBounds(250, 250, 150, 30);
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

       /* confirm.addActionListener(e -> {
            if(check()) {
                Datas.setNamePlayer1(player1.getText());
                Datas.setNamePlayer2(player2.getText());
                createPanel.setVisible(true);
                String player = Datas.getNamePlayer1();
                CreatePanel.label.setText(player + " wymyśla hasło: ");

                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Uzupełnij dane.");
            }
        });*/
    }

    public boolean check(){
        return getPlayer1().getText().length() != 0 && getPlayer2().getText().length() != 0;
    }

    JButton getConfirm() {
        return confirm;
    }

    public JTextField getPlayer1(){
        return player1;
    }

    public JTextField getPlayer2(){
        return player2;
    }

    public String getCurrentPlayer1() {
        return currentPlayer1;
    }

    public void setCurrentPlayer1(String currentPlayer1) {
        this.currentPlayer1 = currentPlayer1;
    }

    public String getCurrentPlayer2() {
        return currentPlayer2;
    }

    public void setCurrentPlayer2(String currentPlayer2) {
        this.currentPlayer2 = currentPlayer2;
    }
}