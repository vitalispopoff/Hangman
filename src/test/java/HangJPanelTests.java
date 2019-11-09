import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HangJPanelTests {

    HangJPanel hangJPanel = new HangJPanel(new CreatePanel(), new LoginPanel());

    @Test
    public void replaceLettersOK(){
        hangJPanel.setWordToGuess("Anna");
        Assert.assertEquals(hangJPanel.replaceLetters('A'),"A _ _ A ");
    }

    @Test
    public void replaceLettersOK2(){
        hangJPanel.setWordToGuess("hieronim");
        Assert.assertEquals(hangJPanel.replaceLetters('R'),"_ _ _ R _ _ _ _ ");
    }

    @Test
    public void letterIndexesOK(){
        hangJPanel.setWordToGuess("animacja");
        List<Integer> listOfIndexes = new ArrayList<>();
        listOfIndexes.add(0);
        listOfIndexes.add(4);
        listOfIndexes.add(7);
        Assert.assertEquals(hangJPanel.getCharIndexes('A'),listOfIndexes);
    }

    @Test
    public void wordToGuessPreparationOK(){
        hangJPanel.setWordToGuess("animacja");
        Assert.assertEquals(hangJPanel.wordToGuessPreparation("animacja"),"_ _ _ _ _ _ _ _ ");
    }
}
