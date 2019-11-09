import org.junit.Assert;
import org.junit.Test;

public class LoginPanelTests {

    LoginPanel loginPanel = new LoginPanel();

    @Test
    public void checkForPlayer1NameIsEmpty(){
        loginPanel.getPlayer1().setText("");
        loginPanel.getPlayer2().setText("Player2");
        Assert.assertFalse(loginPanel.check());
    }

    @Test
    public void checkForPlayer2NameIsEmpty(){
        loginPanel.getPlayer1().setText("Player1");
        loginPanel.getPlayer2().setText("");
        Assert.assertFalse(loginPanel.check());
    }

    @Test
    public void checkForBothPlayersNamesAreEmpty(){
        loginPanel.getPlayer1().setText("");
        loginPanel.getPlayer2().setText("");
        Assert.assertFalse(loginPanel.check());
    }

    @Test
    public void checkForBothPlayersNamesAreFilled(){
        loginPanel.getPlayer1().setText("Player1");
        loginPanel.getPlayer2().setText("Player2");
        Assert.assertTrue(loginPanel.check());
    }
}
