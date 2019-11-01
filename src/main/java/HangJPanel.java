import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class HangJPanel extends JPanel {

    private JLabel hangmanPicture;
    private ArrayList<JButton> buttonList;
    private JLabel categoryTitle, categoryField, wordToGuessTitle, wordToGuessField;
    private JCheckBox categoryChbx;
    private Color panelBackgroundColor = new Color(173, 189, 211, 255);
    private String wordToGuess = "kotek"; //chwilowo
    char[] wordToGuessChars;
    private ArrayList<Integer> listOfIndexes = new ArrayList<>();
    StringBuilder wordToGuessSB;
    int pointsCounter = 0;

    public Dimension getPreferredSize() {
        return new Dimension(880, 630);
    }

    public HangJPanel() {

        setLayout(null);
        setBackground(panelBackgroundColor);

        try {
            createHangmanPicture("0.gif");
        } catch (IOException e) {
            System.out.println("No file found: 0.gif");
        }

        add(hangmanPicture);

        createKeyboard();
        for (JButton b : buttonList) {
            add(b);
        }

        createCategoryField();
        add(categoryTitle);
        add(categoryField);
        add(categoryChbx);

        createWordToGuessField();
        add(wordToGuessTitle);
        add(wordToGuessField);
    }

    //hasło zapisujemy do upperCase
    private String getWordToGuessUpperCase(String wordToGuess){
        return wordToGuess.toUpperCase();
    }

    private char[] getWordToGuessChars(String wordToGuess){
        return getWordToGuessUpperCase(wordToGuess).toCharArray();
    }

    //przetwarzamy hasło do StringBuildera dla wisielca
    public String wordToGuessPreparation(String wordToGuess){

        wordToGuessChars = getWordToGuessChars(wordToGuess);
        wordToGuessSB = new StringBuilder();
        for(int i = 0; i< wordToGuessChars.length; i++){
            wordToGuessSB.append(" ").append("_");
        }
        return wordToGuessSB.toString();
    }

    //zwraca listę indeksów wystąpień litery w haśle
    public ArrayList<Integer> getCharIndexes (char letter){
        for(int i = 0; i < wordToGuessChars.length; i++){
            if(wordToGuessChars[i] == letter){
                listOfIndexes.add(i);
            }
        }
        return listOfIndexes;
    }

    //dla tych indeksów trzeba zastąpić podkreślniki w passwordSB
    public String replaceLetters (char letter){
        ArrayList<Integer> listOfIndexes = getCharIndexes(letter);
        for(int index: listOfIndexes)
            wordToGuessSB.replace((index*2+1),(index*2+2),String.valueOf(letter));
        listOfIndexes.clear();
        return wordToGuessSB.toString();

    }

    public void createWordToGuessField(){

        wordToGuessTitle = new JLabel();
        wordToGuessTitle.setVisible(true);
        wordToGuessTitle.setBounds(50,250,200,80);
        wordToGuessTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
        wordToGuessTitle.setBackground(panelBackgroundColor);
        wordToGuessTitle.setText("HASŁO: ");

        wordToGuessField = new JLabel();
        wordToGuessField.setVisible(true);
        wordToGuessField.setBounds(250,250,200,80);
        wordToGuessField.setFont(new Font("Tahoma", Font.PLAIN, 25));
        wordToGuessField.setBackground(panelBackgroundColor);
        wordToGuessField.setText(wordToGuessPreparation(wordToGuess));

    }

    public void createCategoryField() {
        categoryTitle = new JLabel();
        categoryTitle.setVisible(false);
        categoryTitle.setBounds(50,150,200,80);
        categoryTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
        categoryTitle.setBackground(panelBackgroundColor);
        categoryTitle.setText("KATEGORIA: ");

        categoryField = new JLabel();
        categoryField.setVisible(false);
        categoryField.setBounds(250,150,400,80);
        categoryField.setFont(new Font("Tahoma", Font.PLAIN, 25));
        categoryField.setBackground(panelBackgroundColor);

        categoryChbx = new JCheckBox();
        categoryChbx.setText("Pokaż kategorię");
        categoryChbx.setBounds(50,150,200,80);
        categoryChbx.setFont(new Font("Tahoma", Font.PLAIN, 25));
        categoryChbx.setBackground(panelBackgroundColor);
        categoryChbx.setSelected(false);
        categoryChbx.addActionListener(e -> {

            categoryTitle.setVisible(true);
            categoryField.setText("Zwierzę");
            categoryField.setVisible(true);
            categoryChbx.setVisible(false);
        });

    }

    public void createKeyboard() {

        buttonList = new ArrayList<>();
        int butLocationX = 50;
        int butLocationY = 400;
        int butWidth = 60;
        int butHeight = 60;

        for (int i = 0; i < 26; i++) {
            buttonList.add(new JButton(((char) (i + 'A')) + ""));
        }
        //polskie znaki na klawiaturze
        buttonList.add(new JButton("Ą"));
        buttonList.add(new JButton("Ć"));
        buttonList.add(new JButton("Ę"));
        buttonList.add(new JButton("Ł"));
        buttonList.add(new JButton("Ń"));
        buttonList.add(new JButton("Ó"));
        buttonList.add(new JButton("Ś"));
        buttonList.add(new JButton("Ź"));
        buttonList.add(new JButton("Ż"));

        for (JButton b : buttonList) {

            if (butLocationX >= 50 + 13 * butWidth){
                if (butLocationY == 400) {
                    butLocationX = 50;
                    butLocationY += butHeight;
                }
                else {
                    butLocationX = 170;
                    butLocationY +=butHeight;
                }
            }

            b.setBackground(new Color(59, 89, 182));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setFont(new Font("Tahoma", Font.BOLD, 18));
            b.setBounds(butLocationX, butLocationY, butWidth, butHeight);
            butLocationX += butWidth;

            b.addActionListener(e -> {
                //ustawianie innego tła po wciśnięciu guzika
                b.setBackground(new Color(13, 18, 70, 255));
                //sprawdzanie jaka litera jest na guziku
                char letter = b.getText().charAt(0);
                //spr czy hasło zawiera tę literę
                boolean containsLetter = false;
                for(char c : getWordToGuessChars(wordToGuess)){
                    if(c == letter){
                        containsLetter = true;
                    }
                }
                //jeśli hasło zawiera literę z guzika to wpisujemy ją zamiast "_"
                if(containsLetter)
                    wordToGuessField.setText(replaceLetters(letter));
                //w przeciwnym wypadku - dodajemy punkt i podmieniamy obrazek
                else {
                    try{
                        setPointsCounterUp();
                    }
                    catch (IOException ex){
                        System.out.println("No picture exists");
                    }
                }

                b.setEnabled(false);
                //jeżeli hasło nie zawiera już "_" i punkty<10 - opcja "wygrana"
                if(!wordToGuessField.getText().contains("_") && getPointsCounter()<10){
                    JOptionPane.showMessageDialog(null,"Wygrałeś jedną grę");
                    for(JButton b1 : buttonList){
                        b1.setEnabled(true);
                        b1.setBackground(new Color(59, 89, 182));
                        try {
                            setHangmanPicture("0.gif");
                        }
                        catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                }
                //jeżeli punkty>10 - opcja "przegrana"
                else if (getPointsCounter() == 10){
                    JOptionPane.showMessageDialog(null,"Niestety przegrałeś");
                    for(JButton b1 : buttonList){
                        b1.setEnabled(true);
                        b1.setBackground(new Color(59, 89, 182));
                    }
                    try {
                        setHangmanPicture("0.gif");
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }

            });

        }

    }

    public void setPointsCounterUp() throws IOException {
        pointsCounter+=1;
        switch (pointsCounter) {
            case 1:
                setHangmanPicture("1.gif");
                break;
            case 2:
                setHangmanPicture("2.gif");
                break;
            case 3:
                setHangmanPicture("3.gif");
                break;
            case 4:
                setHangmanPicture("4.gif");
                break;
            case 5:
                setHangmanPicture("5.gif");
                break;
            case 6:
                setHangmanPicture("6.gif");
                break;
            case 7:
                setHangmanPicture("7.gif");
                break;
            case 8:
                setHangmanPicture("8.gif");
                break;
            case 9:
                setHangmanPicture("9.gif");
                break;
            case 10:
                setHangmanPicture("10.gif");
                break;
            default:
                System.out.println("Game is over");;
        }
    }

    public void createHangmanPicture(String picName) throws IOException {

        BufferedImage myPicture = ImageIO.read(this.getClass().getResource(picName));
        hangmanPicture = new JLabel(new ImageIcon(myPicture));
        hangmanPicture.setBounds(590, 50, 241, 299);
        hangmanPicture.setBorder(createNavyBlueBorder());

    }

    public void setHangmanPicture(String picName) throws IOException {
        ImageIcon myPicture = new ImageIcon(this.getClass().getResource(picName));
        hangmanPicture.setIcon((myPicture));
    }

    public static Border createNavyBlueBorder() {
        return BorderFactory.createLineBorder(new Color(59, 89, 182), 3);
    }

    public int getPointsCounter(){
        return pointsCounter;
    }


}
