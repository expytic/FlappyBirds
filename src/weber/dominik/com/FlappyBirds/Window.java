package weber.dominik.com.FlappyBirds;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{
	private final int width = 1280;
	private final int height = 720;

	private static final long serialVersionUID = 2474038862145463208L;
	/**
	 * erstellt ein Fenster indem das Spiel dann Dargestellt wird
	 * @param game dieses Spiel
	 */
	public Window(Game game) {
		JFrame frame = new JFrame("FlappyBird V4.0");
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
}
