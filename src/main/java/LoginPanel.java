import javax.swing.*;

class LoginPanel extends JPanel {

    private JLabel playersLabel;
    private JTextField player1, player2;
    private JButton reset, confirm;
    private CreatePanel createPanel = new CreatePanel();

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

        player2 = new JTextField();
        player2.setBounds(100, 200, 300, 30);
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
                Datas.setNamePlayer1(player1.getText());
                Datas.setNamePlayer2(player2.getText());
                createPanel.setVisible(true);
                String player = Datas.getNamePlayer1();
                CreatePanel.label.setText(player + " wymyśla hasło: ");

                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Uzupełnij dane.");
            }
        });
    }

    private boolean check(){
        return player1.getText().length() != 0 && player2.getText().length() != 0;
    }

    JButton getConfirm() {
        return confirm;
    }
}
