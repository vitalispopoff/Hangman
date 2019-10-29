import javax.swing.*;

public class GuessPanel extends JPanel {

    private JLabel category, word;
    private JTextField guess;
    private JButton reset, confirm;

    GuessPanel(){

        setLayout(null);
        createFields();
        createButtons();
        add();
        actions();

    }

    private void createFields(){

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

        add(category);
        add(word);
        add(guess);
        add(reset);
        add(confirm);
    }

    private void actions(){
        reset.addActionListener(e -> {
            guess.setText("");
        });

        confirm.addActionListener(e -> {
            if(check()) {
                System.out.println("Done");
            } else {
                JOptionPane.showMessageDialog(null, "Uzupełnij dane.");
            }
        });
    }

    boolean check(){
        return guess.getText().length() != 0;
    }

    JButton getConfirm() {
        return confirm;
    }
}

