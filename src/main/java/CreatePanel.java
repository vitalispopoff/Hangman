import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CreatePanel extends JPanel {

    private JLabel playerLabel, imageLabel;
    private ImageIcon shhImage;
    private JComboBox<String> categories;
    private JTextField word;
    private JButton reset, confirm;
    private String message;
    private Font panelFont = new Font("Comic Sans MS", Font.PLAIN, 18);
    private Font hintFont = new Font("Comic Sans MS", Font.ITALIC, 18);

    CreatePanel(){ //panel do wymyślania hasła

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
        imageLabel.setBounds(500,150,200, 260);

        playerLabel = new JLabel();
        playerLabel.setBounds(200, 150, 300, 30);
        playerLabel.setFont(panelFont);

        String[] categoriesOptions = {"--wybierz--", "gra",  "hobby", "imię", "mebel",
                "miasto", "muzyka", "państwo", "pierwiastek",
                "pojazd", "potrawy", "przedmiot", "roślina", "rzeka",
                "sport", "zwierzę", "inne"};


        categories = new JComboBox<>(categoriesOptions);
        categories.setBounds(200,200,300,30);
        categories.setFont(panelFont);
        categories.setBorder(MyFrame.blackBorder());

        word = new HintTextField("Hasło");
        word.setFont(hintFont);
        word.setForeground(Color.gray);
        word.setBounds(200, 250, 300, 30);
        word.setToolTipText("Tylko małe litery polskiego alfabetu (wyłącznie pierwsza litera może być wielka)");
        word.setBorder(MyFrame.blackBorder());
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
        add(playerLabel);
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
    }

    boolean check(){
        boolean isNotEmpty = categories.getSelectedIndex() != 0 && word.getText().length() != 0;

        Pattern pattern = Pattern.compile("[A-ZĆŁÓŚŻŹa-zćłóśżź][a-ząćęłńóśżź]+");
        Matcher matcher = pattern.matcher(word.getText());
        boolean isCorrect = matcher.matches();

        boolean isProperLength = word.getText().length()<=32;

        if(!isNotEmpty)
            message = "Uzupełnij dane.";
        else if(!isCorrect)
            message = "Niedozwolone hasło.";
        else if(!isProperLength)
            message = "Zbyt długie hasło. \n Max 32 litery.";
        return isNotEmpty && isCorrect && isProperLength;
    }

    void setHint(){
        word.setText("Hasło");
        word.setFont(hintFont);
        word.setForeground(Color.gray);
        word.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                word.setText("");
                word.setForeground(Color.black);
                word.setFont(panelFont);
            }
        });
    }

    private void setImage(){
        try {
            shhImage = new ImageIcon(this.getClass().getResource("shh.png"));
        } catch (Exception e) {
            System.out.println("Error");
        }

        imageLabel.setIcon(shhImage);
    }

    String getMessage(){
        return message;
    }

    JButton getConfirm() {
        return confirm;
    }

    JButton getReset() {
        return reset;
    }

    JLabel getLabel(){
        return playerLabel;
    }

    JTextField getWord(){
        return word;
    }

    JComboBox getCategories() {
        return this.categories;
    }
}