package weber.dominik.com.FlappyBirds;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{


	private static final long serialVersionUID = 2474038862145463208L;
	/**
	 * erstellt ein Fenster indem das Spiel dann Dargestellt wird
	 * @param game dieses Spiel
	 */
	public Window(Game game) {
		JFrame frame = new JFrame("FlappyBird V4.0");
		frame.setPreferredSize(new Dimension(1280, 720));
		frame.setMaximumSize(new Dimension(1280, 720));
		frame.setMinimumSize(new Dimension(1280, 720));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
}
