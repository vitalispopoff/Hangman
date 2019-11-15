import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.security.Key;
import java.util.ArrayList;
import java.util.Objects;

class HangJPanel extends JPanel {

    private JLabel hangmanPicture;          //obrazek wisielca
    private JLabel guessingPlayerTitle;     //pokazuje który gracz obecnie odgaduje hasło
    private JLabel categoryField, wordToGuessField;    //kategoria i słowo do odgadnięcia
    private ArrayList<JButton> buttonList;  //klawiatura
    private JCheckBox categoryChbx;     //zaznaczanie pokazania kategorii
    private Color panelBackgroundColor = new Color(215, 216, 218);     //kolor tła dla JPanelu
    private String wordToGuess; //słowo do odgadnięcia
    private char[] wordToGuessChars;    //tablica znaków ze słowa do odgadnięcia
    private ArrayList<Integer> listOfIndexes = new ArrayList<>();   //na jakich miejscach pojawiły się litery
    private StringBuilder wordToGuessSB;    //do obróbki hasła
    private int pointsCounter = 0;          //punkty do zgadywania hasła/rysowania szubienicy
    private CreatePanel createPanel;    //do pobierania zmiennych z createPanelu (kategoria, hasło)
    private LoginPanel loginPanel;      //do pobierania danych o użytkowniku
    private String player1, player2;
    private static int pointPlayer1 = 0, pointPlayer2 = 0;
    private Font panelFont = new Font("Comic Sans MS", Font.PLAIN, 20);
    private JLabel winningPicture, loosingPicture;
    private JButton confirm;
    private JLabel finalWordField;
    private KeyListener kl;
    static int totalGamesCounter = 0;

    HangJPanel(CreatePanel createPanel, LoginPanel loginPanel) {

        this.createPanel = createPanel;
        this.loginPanel = loginPanel;
        this.wordToGuess = createPanel.getWord().getText();

        this.player1 = loginPanel.getPlayer1().getText();
        this.player2 = loginPanel.getPlayer2().getText();

        setLayout(null);
        setBackground(panelBackgroundColor);
        createHangmanPicture();
        add(hangmanPicture);
        createKeyboard();
        for (JButton b : buttonList) {
            add(b);
        }
        createGuessingPlayerTitle();
        add(guessingPlayerTitle);
        createCategoryField();
        add(categoryField);
        add(categoryChbx);
        createWordToGuessField();
        add(wordToGuessField);
        try {
            createWinningPicture();
        } catch (Exception ex) {
            System.out.println("Problem with file: won.gif");
        }
        add(winningPicture);
        try {
            createLoosingPicture();
        } catch (Exception ex) {
            System.out.println("Problem with file: lost.gif");
        }
        add(loosingPicture);
        createConfirmBtn();
        add(confirm);
        createFinalWordTitle();
        add(finalWordField);
        defineKeyListener();
        categoryChbx.addKeyListener(kl);
        for (JButton b : buttonList) {
            b.addKeyListener(kl);
        }

    }

    private void defineKeyListener() {
        kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int i = 0;
                if (e.isAltDown()) {
                    if (e.getKeyCode() == KeyEvent.VK_A)
                        buttonList.get(26).doClick();
                    else if (e.getKeyCode() == KeyEvent.VK_C)
                        buttonList.get(27).doClick();
                    else if (e.getKeyCode() == KeyEvent.VK_E)
                        buttonList.get(28).doClick();
                    else if (e.getKeyCode() == KeyEvent.VK_L)
                        buttonList.get(29).doClick();
                    else if (e.getKeyCode() == KeyEvent.VK_N)
                        buttonList.get(30).doClick();
                    else if (e.getKeyCode() == KeyEvent.VK_O)
                        buttonList.get(31).doClick();
                    else if (e.getKeyCode() == KeyEvent.VK_S)
                        buttonList.get(32).doClick();
                    else if (e.getKeyCode() == KeyEvent.VK_X)
                        buttonList.get(33).doClick();
                    else if (e.getKeyCode() == KeyEvent.VK_Z)
                        buttonList.get(34).doClick();
                } else {
                    for (i = 0; i < 26; i++) {
                        if (e.getKeyCode() == i + 65)
                            buttonList.get(i).doClick();
                    }
                }
            }



        @Override
        public void keyReleased (KeyEvent e){
        }
    }

    ;}

    private void createFinalWordTitle() {
        finalWordField = new JLabel();
        finalWordField.setText("<html>" + "Hasło: " + wordToGuess + "</html>");
        finalWordField.setVisible(false);
        finalWordField.setBounds(30, 450, 400, 40);
        finalWordField.setBackground(panelBackgroundColor);
        finalWordField.setFont(panelFont);
    }

    private void createGuessingPlayerTitle() {
        guessingPlayerTitle = new JLabel();
        guessingPlayerTitle.setVisible(true);
        guessingPlayerTitle.setBounds(20, 20, 300, 80);
        guessingPlayerTitle.setFont(panelFont);
        guessingPlayerTitle.setBackground(panelBackgroundColor);
    }

    private void createWordToGuessField() {
        wordToGuessField = new JLabel();
        wordToGuessField.setVisible(true);
        wordToGuessField.setBounds(20, 150, 400, 160);
        wordToGuessField.setFont(panelFont);
        wordToGuessField.setBackground(panelBackgroundColor);
        wordToGuessField.setText("<html>" + wordToGuessPreparation(wordToGuess) + "</html>");
    }

    private void createCategoryField() {
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
                b.setBackground(new Color(13, 18, 70, 255));
                char letter = b.getText().charAt(0);
                boolean containsLetter = false;
                for (char c : getWordToGuessChars(wordToGuess)) {
                    if (c == letter) {
                        containsLetter = true;
                    }
                }
                if (containsLetter)
                    wordToGuessField.setText("<html>" + replaceLetters(letter) + "</html>");
                else {
                    try {
                        setPointsCounterUp();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                b.setEnabled(false);

                //---------OPCJA WYGRANA----------------
                if (!wordToGuessField.getText().contains("_") && getPointsCounter() < 10) {
                    totalGamesCounter += 1;
                    setPointsCounterToZero();
                    setComponentsVisibility(winningPicture);
                    confirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    confirm.requestFocusInWindow();

                    String currentGuessingPlayer = getGuessingPlayerTitle().getText();
                    if (currentGuessingPlayer.equals(loginPanel.getPlayer1().getText() + " zgaduje"))
                        setPointPlayer1();
                    else
                        setPointPlayer2();

                }
                //---------OPCJA PRZEGRANA------------------
                else if (getPointsCounter() == 10) {
                    totalGamesCounter += 1;
                    setComponentsVisibility(loosingPicture);
                    setPointsCounterToZero();
                    confirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    confirm.requestFocusInWindow();
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

    private void createWinningPicture() {

        ImageIcon myPicture = new ImageIcon(this.getClass().getResource("won.gif"));
        winningPicture = new JLabel();
        winningPicture.setIcon(myPicture);
        winningPicture.setBounds(0, 0, 700, 450);
        hangmanPicture.setBorder(createNavyBlueBorder());
        winningPicture.setVisible(false);
    }

    private void createLoosingPicture() {
        ImageIcon myPicture = new ImageIcon(this.getClass().getResource("lost.gif"));
        loosingPicture = new JLabel();
        loosingPicture.setIcon(myPicture);
        loosingPicture.setBounds(0, 0, 700, 450);
        hangmanPicture.setBorder(createNavyBlueBorder());
        loosingPicture.setVisible(false);
    }

    private void createHangmanPicture() {
        try {
            BufferedImage myPicture = ImageIO.read(this.getClass().getResource("0.gif"));
            hangmanPicture = new JLabel(new ImageIcon(myPicture));
            hangmanPicture.setBounds(450, 20, 217, 270);
            hangmanPicture.setBorder(createNavyBlueBorder());
        } catch (Exception ex) {
            System.out.println("Problem with file: 0.gif.");
        }
    }

    private void setHangmanPicture(String picName) {
        ImageIcon myPicture = new ImageIcon(this.getClass().getResource(picName));
        hangmanPicture.setIcon((myPicture));
    }

    private void createConfirmBtn() {
        confirm = new JButton();
        confirm.setText(">>");
        confirm.setFont(panelFont);
        confirm.setBounds(600, 450, 80, 40);
        confirm.setVisible(false);
    }

    JButton getConfirm() {
        return confirm;
    }

    static Border createNavyBlueBorder() {
        return BorderFactory.createLineBorder(new Color(59, 89, 182), 3);
    }

    int getPointsCounter() {
        return pointsCounter;
    }

    void setPointsCounterToZero() {
        this.pointsCounter = 0;
    }

    private void setComponentsVisibility(JLabel picture) {
        hangmanPicture.setVisible(false);
        guessingPlayerTitle.setVisible(false);
        categoryField.setVisible(false);
        wordToGuessField.setVisible(false);
        for (JButton b : buttonList) {
            b.setVisible(false);
        }
        categoryChbx.setVisible(false);

        picture.setVisible(true);
        confirm.setVisible(true);
        finalWordField.setVisible(true);
    }


    private JLabel getGuessingPlayerTitle() {
        return guessingPlayerTitle;
    }

    void setCurrentPlayers() {
        if (totalGamesCounter % 2 == 1) {
            this.player1 = loginPanel.getPlayer2().getText();
            this.player2 = loginPanel.getPlayer1().getText();
        } else {
            this.player1 = loginPanel.getPlayer1().getText();
            this.player2 = loginPanel.getPlayer2().getText();
        }
        //ustawiamy kto ma zgadywać
        this.getGuessingPlayerTitle().setText(this.player2 + " zgaduje");
    }

    int getPointPlayer1() {
        return pointPlayer1;
    }

    private void setPointPlayer1() {
        HangJPanel.pointPlayer1 = 1;
    }

    int getPointPlayer2() {
        return pointPlayer2;
    }

    private void setPointPlayer2() {
        HangJPanel.pointPlayer2 = 1;
    }

    void setWordToGuess(String wordToGuess) {
        this.wordToGuess = wordToGuess;
    }

    //-----------OBSŁUGA HASŁA-------------------------------------
    private String getWordToGuessUpperCase(String wordToGuess) {
        return wordToGuess.toUpperCase();
    }

    private char[] getWordToGuessChars(String wordToGuess) {
        return getWordToGuessUpperCase(wordToGuess).toCharArray();
    }

    String wordToGuessPreparation(String wordToGuess) {
        wordToGuessChars = getWordToGuessChars(wordToGuess);
        wordToGuessSB = new StringBuilder();
        for (char w : wordToGuessChars)
            wordToGuessSB.append(" ").append("_");
        return wordToGuessSB.toString();
    }

    ArrayList<Integer> getCharIndexes(char letter) {
        for (int i = 0; i < wordToGuessChars.length; i++) {
            if (wordToGuessChars[i] == letter)
                listOfIndexes.add(i);
        }
        return listOfIndexes;
    }

    String replaceLetters(char letter) {
        ArrayList<Integer> listOfIndexes = getCharIndexes(letter);
        for (int index : listOfIndexes)
            wordToGuessSB.replace((index * 2 + 1), (index * 2 + 2), String.valueOf(letter));
        listOfIndexes.clear();
        return wordToGuessSB.toString();
    }
}

