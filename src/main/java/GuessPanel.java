
import javax.swing.*;

class GuessPanel extends JPanel {

    static JLabel player, category, word;
    private JTextField guess;
    private JButton reset, confirm;
    private ResultPanel resultPanel = new ResultPanel();

    GuessPanel(){

        setLayout(null);
        createFields();
        createButtons();
        add();
        actions();

    }

    private void createFields(){

        player = new JLabel();
        player.setBounds(100,50,300,30);

        category = new JLabel();
        category.setText(Datas.getCategory());
        category.setBounds(100, 100, 300, 30);

        word = new JLabel();
        word.setText(Datas.getWord());
        word.setBounds(100, 150, 300, 30);

        guess = new JTextField();
        guess.setBounds(100, 200, 300, 30);
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

        add(player);
        add(category);
        add(word);
        add(guess);
        add(reset);
        add(confirm);
    }

    private void actions(){
        reset.addActionListener(e -> guess.setText(""));

        confirm.addActionListener(e -> {
            if(check()) {
                Datas.setPointPlayer2(won());
                ResultPanel.won = Datas.getPointPlayer2();
                resultPanel.setVisible(true);
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Uzupełnij dane.");
            }
        });
    }

    boolean check(){
        return guess.getText().length() != 0;
    }

    private int won(){
        if(guess.getText().equals(word.getText()))
            return 1;
        else
            return 0;
    }

    JButton getConfirm() {
        return confirm;
    }
}