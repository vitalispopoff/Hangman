import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CreatePanel extends JPanel {

    public static JLabel label;
    private JComboBox<String> categories;
    private JTextField word;
    private JButton reset, confirm;
    private String message;
    private GuessPanel guessPanel = new GuessPanel();

    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    CreatePanel(){

        setLayout(null);

        createFields();
        createButtons();

        add();

        actions();
    }

    private void createFields(){

        label = new JLabel();
        label.setText("Gracz1 wymyśla hasło.");
        label.setBounds(100, 100, 300, 30);

        String[] categoriesOptions = {"--wybierz--", "zwierzę", "roślina", "pierwiastek", "przedmiot", "pojazd", "państwo", "miasto", "inne"};
        categories = new JComboBox<>(categoriesOptions);
        categories.setBounds(100,150,300,30);

        word = new JTextField();
        word.setBounds(100, 200, 300, 30);
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

        add(label);
        add(categories);
        add(word);
        add(reset);
        add(confirm);
    }

    private void actions(){
        reset.addActionListener(e -> {
            word.setText("");
            categories.setSelectedIndex(0);
        });

        confirm.addActionListener(e -> {
            if(check()) {
                Datas.setWord(word.getText());
                Datas.setCategory(Objects.requireNonNull(categories.getSelectedItem()).toString());
                GuessPanel.category.setText(Datas.getCategory());
                GuessPanel.word.setText(Datas.getWord());
                guessPanel.setVisible(true);
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, message);
            }
        });
    }

    boolean check(){
        boolean isEmpty = categories.getSelectedIndex() != 0 && word.getText().length() != 0;
        Pattern pattern = Pattern.compile("[A-ZĆŁÓŚŻŹa-zćłóśżź][a-ząćęłńóśżź]+");
        Matcher matcher = pattern.matcher(word.getText());
        boolean isIncorrect = matcher.matches();
        if(!isEmpty)
            message = "Uzupełnij dane.";
        else if(!isIncorrect)
            message = "Niedozwolone hasło. Tylko małe litery.";

        return isEmpty && isIncorrect;
    }

    JButton getConfirm() {
        return confirm;
    }

    public JLabel getLabel(){
        return label;
    }

   // public static void setLabelText(String textForLabel){
     //   label.setText(textForLabel);
    //}
}
