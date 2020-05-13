package weber.dominik.com.FlappyBirds;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import weber.dominik.com.FlappyBirds.Game.GameState;

public class KeyInput extends KeyAdapter{
	/**
	 * handeler des spiels
	 */
	private Handler handler;
	/**
	 * erstellt den KeyInput
	 * @param handler handeler des spiels
	 */
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	/**
	 * Keylistener der Anwendung für die betätigung ausgewählten Tasten
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i = 0; i < handler.getObjectsSize(); i++) {
			GameObject tempObject = handler.getObject(i);
			
			if(tempObject instanceof Player) {
				if(key == KeyEvent.VK_SPACE) ((Player) tempObject).setVelY(-7f);
				break;
			}
		}
	}
	/**
	 * Keylistener der Anwendung für das loslassen ausgewählten Tasten
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_ESCAPE) {
			if(Game.gamestate == GameState.Playing || Game.gamestate == GameState.Paused) {
				if(Game.gamestate == GameState.Playing) Game.gamestate = GameState.Paused;
				else Game.gamestate = GameState.Playing;
			}
		}
	}
	
	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}
