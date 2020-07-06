//	@formatter:off

import javax.swing.*;

public class GameButton extends JButton {

	static String[]
			buttonTexts = {"Wyczyść", "OK"};
	static int[]
		xValues = {200, 350};

	public GameButton (int index){
		super();
		setText(buttonTexts[0]);
		setBounds(xValues[0], 300, 150, 30);
		setFont(LoginPanel.panelFont);
	}



//	@formatter:on



}
