import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main {
/*<<<<<<< HEAD
    public static void main(String[] args) {
    *//*    EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                HangJFrame mainWindow = new HangJFrame(10,10,"WISIELEC");
                mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainWindow.setVisible(true);
            }
        });*//*

=======*/
/*>>>>>>> login*/

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            MyFrame window;
            window = new MyFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    }
}