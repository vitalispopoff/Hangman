import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class HangJPanel extends JPanel {

    private JLabel hangmanPicture;          //obrazek wisielca
    private JLabel guessingPlayerTitle, guessingPlayer;     //pokazuje który gracz obecnie odgaduje hasło
    private JLabel categoryField, wordToGuessField;    //kategoria i słowo do odgadnięcia
    private ArrayList<JButton> buttonList;  //klawiatura
    private JCheckBox categoryChbx;     //zaznaczanie pokazania kategorii
    private Color panelBackgroundColor = new Color(215,216,218);     //kolor tła dla JPanelu
    private String wordToGuess; //słowo do odgadnięcia
    private char[] wordToGuessChars;    //tablica znaków ze słowa do odgadnięcia
    private ArrayList<Integer> listOfIndexes = new ArrayList<>();   //na jakich miejscach pojawiły się litery
    private StringBuilder wordToGuessSB;    //do obróbki hasła
    private int pointsCounter = 0;          //punkty do zgadywania hasła/rysowania szubienicy
    private CreatePanel createPanel;    //do pobierania zmiennych z createPanelu (kategoria, hasło)
    private LoginPanel loginPanel;      //do pobierania danych o użytkowniku
    private String player1, player2;
    private static int pointPlayer1=0, pointPlayer2=0;
    private Font panelFont = new Font("Comic Sans MS", Font.PLAIN, 20);
    private JLabel winningPicture, loosingPicture;
    private JButton confirm;
    static int totalGamesCounter = 0;

    public Dimension getPreferredSize() {
        return new Dimension(700, 500);
    }

    HangJPanel(CreatePanel createPanel, LoginPanel loginPanel) {

        this.createPanel = createPanel;
        this.loginPanel = loginPanel;
        this.wordToGuess = createPanel.getWord().getText();

        this.player1 = loginPanel.getPlayer1().getText();
        this.player2 = loginPanel.getPlayer2().getText();

        setLayout(null);
        setBackground(panelBackgroundColor);

        try {
            createHangmanPicture("0.gif");
        } catch (IOException e) {
            System.out.println("Problem with file 0.gif");
        }

        add(hangmanPicture);

        createKeyboard();
        for (JButton b : buttonList) {
            add(b);
        }

        createGuessingPlayerTitle();
        add(guessingPlayer);
        add(guessingPlayerTitle);

        createCategoryField();
        //add(categoryTitle);
        add(categoryField);
        add(categoryChbx);

        createWordToGuessField();
        //add(wordToGuessTitle);
        add(wordToGuessField);

        try {
            createWinningPicture("won.gif");
        } catch (Exception ex) {
            System.out.println("Problem with file won.gif");
        }
        add(winningPicture);

        try {
            createLoosingPicture("lost.gif");
        } catch (Exception ex) {
            System.out.println("Problem with file lost.gif");
        }
        add(loosingPicture);

        createConfirmBtn();
        add(confirm);
    }

//    public void setWordToGuess(String word) {
//        this.wordToGuess = word;
//    }

    private void createGuessingPlayerTitle() {

        guessingPlayer = new JLabel();
        guessingPlayer.setVisible(true);
        guessingPlayer.setBounds(20, 20, 90, 80);
        guessingPlayer.setFont(panelFont);
        guessingPlayer.setBackground(panelBackgroundColor);
        guessingPlayer.setText("Zgaduje: ");

        guessingPlayerTitle = new JLabel();
        guessingPlayerTitle.setVisible(true);
        guessingPlayerTitle.setBounds(115, 20, 300, 80);
        guessingPlayerTitle.setFont(panelFont);
        guessingPlayerTitle.setBackground(panelBackgroundColor);
        //guessingPlayerTitle.setText(player2 + " zgaduje hasło");
    }


    private void createWordToGuessField() {

//        wordToGuessTitle = new JLabel();
//        wordToGuessTitle.setVisible(true);
//        wordToGuessTitle.setBounds(50, 250, 200, 80);
//        wordToGuessTitle.setFont(panelFont);
//        wordToGuessTitle.setBackground(panelBackgroundColor);
//        wordToGuessTitle.setText("hasło: ");

        wordToGuessField = new JLabel();
        wordToGuessField.setVisible(true);
        wordToGuessField.setBounds(20, 150, 400, 160);
        wordToGuessField.setFont(panelFont);
        wordToGuessField.setBackground(panelBackgroundColor);
        wordToGuessField.setText("<html>" + wordToGuessPreparation(wordToGuess) + "</html>");

    }

    private void createCategoryField() {
//        categoryTitle = new JLabel();
//        categoryTitle.setVisible(false);
//        categoryTitle.setBounds(20, 100, 200, 80);
//        categoryTitle.setFont(panelFont);
//        categoryTitle.setBackground(panelBackgroundColor);
//        categoryTitle.setText("kategoria:");

        categoryField = new JLabel();
        categoryField.setVisible(false);
        categoryField.setBounds(20, 100, 400, 80);
        categoryField.setFont(panelFont);
        categoryField.setBackground(panelBackgroundColor);

        categoryChbx = new JCheckBox();
        categoryChbx.setText("Pokaż kategorię");
        categoryChbx.setBounds(20, 100, 200, 80);
        categoryChbx.setFont(panelFont);
        categoryChbx.setBackground(panelBackgroundColor);
        categoryChbx.setSelected(false);
        categoryChbx.addActionListener(e -> {

            //categoryTitle.setVisible(true);
            //kategoria jest ustawiana z createPanel
            categoryField.setText("Kategoria: " + Objects.requireNonNull(createPanel.getCategories().getSelectedItem()).toString());
            categoryField.setVisible(true);
            categoryChbx.setVisible(false);
        });
    }

    private void createKeyboard() {

        buttonList = new ArrayList<>();
        int butLocationX = 20;
        int butLocationY = 330;
        int butWidth = 50;
        int butHeight = 50;

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

            if (butLocationX >= 20 + 13 * butWidth) {
                if (butLocationY == 330) {
                    butLocationX = 20;
                    butLocationY += butHeight;
                } else {
                    butLocationX = 120;
                    butLocationY += butHeight;
                }
            }

            b.setBackground(new Color(59, 89, 182));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setFont(new Font("Tahoma", Font.BOLD, 16));
            b.setBounds(butLocationX, butLocationY, butWidth, butHeight);
            butLocationX += butWidth;

            b.addActionListener(e -> {
                //ustawianie innego tła po wciśnięciu guzika
                b.setBackground(new Color(13, 18, 70, 255));
                //sprawdzanie jaka litera jest na guziku
                char letter = b.getText().charAt(0);
                //spr czy hasło zawiera tę literę
                boolean containsLetter = false;
                for (char c : getWordToGuessChars(wordToGuess)) {
                    if (c == letter) {
                        containsLetter = true;
                    }
                }
                //jeśli hasło zawiera literę z guzika to wpisujemy ją zamiast "_"
                if (containsLetter)
                    wordToGuessField.setText("<html>" + replaceLetters(letter) + "</html>");
                    //w przeciwnym wypadku - dodajemy punkt i podmieniamy obrazek
                else {
                    try {
                        setPointsCounterUp();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                b.setEnabled(false);

                //---------OPCJA WYGRANA----------------
                //jeżeli hasło nie zawiera już "_" i punkty<10 - opcja "wygrana"
                if (!wordToGuessField.getText().contains("_") && getPointsCounter() < 10) {
                    totalGamesCounter += 1;

                    //ustawiamy punkty dla obecnego gracza
                    if (getGuessingPlayerTitle().getText().equals(loginPanel.getPlayer1().getText()))
                        setPointPlayer1(1);
                    else if (getGuessingPlayerTitle().getText().equals(loginPanel.getPlayer2().getText()))
                        setPointPlayer2(1);

                    //ustawiamy przyciski na klawiaturze
                    for (JButton b1 : buttonList) {
                        b1.setEnabled(true);
                        b1.setBackground(new Color(59, 89, 182));
                        try {
                            setHangmanPicture("0.gif");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    setComponentsVisibility(false);
                    winningPicture.setVisible(true);
                    confirm.setVisible(true);
                    setPointsCounterToZero();
                }

                //---------OPCJA PRZEGRANA------------------
                //jeżeli punkty>10 - opcja "przegrana"
                else if (getPointsCounter() == 10) {
                    
                    totalGamesCounter += 1;

                    for (JButton b1 : buttonList) {
                        b1.setEnabled(true);
                        b1.setBackground(new Color(59, 89, 182));
                    }
                    try {
                        setHangmanPicture("0.gif");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    setComponentsVisibility(false);
                    loosingPicture.setVisible(true);
                    confirm.setVisible(true);
                    setPointsCounterToZero();
                }

            });

        }

    }

    private void setPointsCounterUp() {
        pointsCounter += 1;
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
                System.out.println("Game is over");
        }
    }

    private void createWinningPicture(String picName) {

        ImageIcon myPicture = new ImageIcon(this.getClass().getResource(picName));
        winningPicture = new JLabel();
        winningPicture.setIcon(myPicture);
        winningPicture.setBounds(100, 0, 500, 450);
        hangmanPicture.setBorder(createNavyBlueBorder());
        winningPicture.setVisible(false);
    }

    private void createLoosingPicture(String picName) {
        ImageIcon myPicture = new ImageIcon(this.getClass().getResource(picName));
        loosingPicture = new JLabel();
        loosingPicture.setIcon(myPicture);
        loosingPicture.setBounds(100, 0, 500, 450);
        hangmanPicture.setBorder(createNavyBlueBorder());
        loosingPicture.setVisible(false);
    }

    private void createHangmanPicture(String picName) throws IOException {

        BufferedImage myPicture = ImageIO.read(this.getClass().getResource(picName));
        hangmanPicture = new JLabel(new ImageIcon(myPicture));
        hangmanPicture.setBounds(450, 20, 217, 270);
        hangmanPicture.setBorder(createNavyBlueBorder());

    }

    private void setHangmanPicture(String picName) {
        ImageIcon myPicture = new ImageIcon(this.getClass().getResource(picName));
        hangmanPicture.setIcon((myPicture));
    }

    private void createConfirmBtn() {
        confirm = new JButton();
        confirm.setText(">>");
        confirm.setFont(panelFont);
        //confirm.setBorder(createNavyBlueBorder());
        confirm.setBounds(520, 450, 80, 40);
        confirm.setVisible(false);
    }

    JButton getConfirm() {
        return confirm;
    }

    private static Border createNavyBlueBorder() {
        return BorderFactory.createLineBorder(new Color(59, 89, 182), 3);
    }

    private int getPointsCounter() {
        return pointsCounter;
    }

    private void setPointsCounterToZero() {
        this.pointsCounter = 0;
    }

    private void setComponentsVisibility(boolean value) {
        hangmanPicture.setVisible(value);
        guessingPlayer.setVisible(value);
        guessingPlayerTitle.setVisible(value);
        //categoryTitle.setVisible(value);
        categoryField.setVisible(value);
        //wordToGuessTitle.setVisible(value);
        wordToGuessField.setVisible(value);
        for (JButton b : buttonList) {
            b.setVisible(value);
        }
        categoryChbx.setVisible(value);
    }

    //-----------OBSŁUGA HASŁA-------------------------------------
    //hasło zapisujemy do upperCase
    private String getWordToGuessUpperCase(String wordToGuess) {
        return wordToGuess.toUpperCase();
    }

    private char[] getWordToGuessChars(String wordToGuess) {
        return getWordToGuessUpperCase(wordToGuess).toCharArray();
    }

    //przetwarzamy hasło do StringBuildera dla wisielca
    private String wordToGuessPreparation(String wordToGuess) {

        wordToGuessChars = getWordToGuessChars(wordToGuess);
        wordToGuessSB = new StringBuilder();
        for (char wordToGuessChar : wordToGuessChars) {
            wordToGuessSB.append(" ").append("_");
        }
        return wordToGuessSB.toString();
    }

    //zwraca listę indeksów wystąpień litery w haśle
    private ArrayList<Integer> getCharIndexes(char letter) {
        for (int i = 0; i < wordToGuessChars.length; i++) {
            if (wordToGuessChars[i] == letter) {
                listOfIndexes.add(i);
            }
        }
        return listOfIndexes;
    }

    //dla tych indeksów trzeba zastąpić podkreślniki w passwordSB
    private String replaceLetters(char letter) {
        ArrayList<Integer> listOfIndexes = getCharIndexes(letter);
        for (int index : listOfIndexes)
            wordToGuessSB.replace((index * 2 + 1), (index * 2 + 2), String.valueOf(letter));
        listOfIndexes.clear();
        return wordToGuessSB.toString();

    }

    private JLabel getGuessingPlayerTitle() {
        return guessingPlayerTitle;
    }

//    public String getPlayer1() {
//        return player1;
//    }
//
//    public String getPlayer2() {
//        return player2;
//    }

    void setCurrentPlayers() {
        if (totalGamesCounter % 2 == 1) {
            this.player1 = loginPanel.getPlayer2().getText();
            this.player2 = loginPanel.getPlayer1().getText();
        } else {
            this.player1 = loginPanel.getPlayer1().getText();
            this.player2 = loginPanel.getPlayer2().getText();
        }
        //ustawiamy kto ma zgadywać
        this.getGuessingPlayerTitle().setText(this.player2);

    }

    int getPointPlayer1() {
        return pointPlayer1;
    }

    private void setPointPlayer1(int pointPlayer1) {
        this.pointPlayer1 = pointPlayer1;
    }

    int getPointPlayer2() {
        return pointPlayer2;
    }

    private void setPointPlayer2(int pointPlayer2) {
        this.pointPlayer2 = pointPlayer2;
    }
}
