import java.awt.Color;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel {

	public void start() {
		
		JFrame frame = new JFrame("");
		
		frame.setLocation(450, 100);
		frame.setSize(1000, 700);
		frame.setBackground(Color.BLACK);

		JPanel panel = new JPanel();
		JLabel label = new JLabel("Your text here");

		panel.add(label);

		frame.add(panel);
		frame.setVisible(true);
		
		
		
		
	}

}
