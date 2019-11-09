import org.junit.Assert;
import org.junit.Test;

public class CreatePanelTests {

    CreatePanel createPanel = new CreatePanel();

    @Test
    public void wordToGuessIsZeroLength(){
        createPanel.getWord().setText("");
        createPanel.getCategories().setSelectedIndex(1);

        Assert.assertEquals(createPanel.getMessage(),"Uzupełnij dane");
    }

    @Test
    public void categoryEqualsWybierz(){
        createPanel.getWord().setText("Aaaaa");
        createPanel.getCategories().setSelectedIndex(0);

        Assert.assertEquals(createPanel.getMessage(),"Uzupełnij dane");
    }

    @Test
    public void wordToGuessContainsNonAlphabets(){
        createPanel.getWord().setText("Aa123");
        createPanel.getCategories().setSelectedIndex(1);

        Assert.assertEquals(createPanel.getMessage(),"Niedozwolone hasło");
    }

    @Test
    public void wordToGuessContainsCapitalLetters(){
        createPanel.getWord().setText("AaA");
        createPanel.getCategories().setSelectedIndex(1);

        Assert.assertEquals(createPanel.getMessage(),"Niedozwolone hasło");
    }

    @Test
    public void wordToGuessTooLong(){
        createPanel.getWord().setText("Konstantynopolitańczykowianeczka1");
        createPanel.getCategories().setSelectedIndex(1);

        Assert.assertEquals(createPanel.getMessage(),"Zbyt długie hasło \n Max 32 litery");
    }

    @Test
    public void wordToGuessTooShort(){
        createPanel.getWord().setText("A");
        createPanel.getCategories().setSelectedIndex(1);

        Assert.assertEquals(createPanel.getMessage(),"Niedozwolone hasło");
    }
}
