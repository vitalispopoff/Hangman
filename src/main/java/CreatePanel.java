import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CreatePanel extends JPanel {

    private JLabel label;
    private JComboBox<String> categories;
    private JTextField word;
    private JButton reset, confirm;
    private String message;

    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    CreatePanel(){ //panel do wymyślania hasła

        setLayout(null);

        createFields();
        createButtons();
        add();
        actions();
    }

    private void createFields(){

        label = new JLabel();
        label.setBounds(100, 100, 300, 30);

        String[] categoriesOptions = {"--wybierz--", "zwierzę", "roślina", "pierwiastek", "przedmiot", "pojazd", "państwo", "miasto", "inne"};

        categories = new JComboBox<>(categoriesOptions);
        categories.setBounds(100,150,300,30);

        word = new JTextField();
        word.setBounds(100, 200, 300, 30);
        setHint();
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
            setHint();
            categories.setSelectedIndex(0);
        });

        confirm.addActionListener(e -> {
            if(!check()) {
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
            message = "Niedozwolone hasło.";
        return isEmpty && isIncorrect;
    }

    void setHint(){
        word.setText("Hasło");
        word.setForeground(Color.gray);
        word.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                word.setText("");
                word.setForeground(Color.black);
            }
        });
    }

    JButton getConfirm() {
        return confirm;
    }

    JLabel getLabel(){
        return label;
    }

    JTextField getWord(){
        return word;
    }

    JComboBox getCategories() {
        return this.categories;
    }

    // public static void setLabelText(String textForLabel){
    //   label.setText(textForLabel);
    //}
}