import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class LoginPanel extends JPanel {

    private JLabel playersLabel;
    private JTextField player1, player2;
    private JButton reset, confirm;
    private CreatePanel createPanel;

    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    LoginPanel(){

        setLayout(null);
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

        confirm.addActionListener(e -> {
            if(check()) {
//                createPanel.setVisible(true);
//                CreatePanel.label.setText(player1.getText() + " wymyśla hasło: ");
//                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Uzupełnij dane.");
            }
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