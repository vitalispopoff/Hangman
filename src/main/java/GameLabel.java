import javax.swing.*;

public class GameLabel extends JLabel {

	static int[]
		xValues = {500, 200};

	public GameLabel(int index){
		super();
		setBounds(xValues[index], 150, 300, 30);
		setFont(LoginPanel.panelFont);
	}



}