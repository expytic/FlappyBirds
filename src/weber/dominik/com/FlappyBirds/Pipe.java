package weber.dominik.com.FlappyBirds;

import java.awt.Color;
import java.awt.Graphics;

public class Pipe extends GameObject{
	/**
	 * true wenn diese röhre noch nicht durchquert wurde
	 */
	private boolean giveScore = true;
	/**
	 * erstellt die pipe 
	 * @param posX X Position
	 * @param posY Y Position
	 * @param width Breite
	 * @param height Höhe
	 * @param giveScore true wenn röhre noch nicht durchquert
	 */
	public Pipe(float posX, float posY, int width, int height, boolean giveScore) {
		super(posX, posY, width, height);
		this.giveScore = giveScore;
		velX = -2;
	}
	/**
	 * Game-Tick der Pipe
	 */
	@Override
	void tick(Handler handler) {
		posX += velX;
		posY += velY;
		if(posX + width < 0) handler.removeObject(this);
	}
	/**
	 * zeichnet die pipe im fenster
	 */
	@Override
	void render(Graphics g) {
		g.setColor(new Color(11,102,35));
		g.fillRect((int)posX, (int)posY, width, height);
		g.setColor(Color.black);
		g.drawRect((int)posX, (int)posY, width, height);
	}
	
	public void setGiveScore(boolean giveScore) {
		this.giveScore = giveScore;
	}
	public boolean getGiveScore() {
		return giveScore;
	}
	
	public int getWidth(){
		return width;
	}

}
