import javax.swing.*;

public class HangJFrame extends JFrame {

    public HangJFrame(int x, int y, String title){

        setLocation(x,y);
        setTitle(title);
        setResizable(false);
        HangJPanel panel = new HangJPanel();
        add(panel);
        pack();
    }

}
