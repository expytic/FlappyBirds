package weber.dominik.com.FlappyBirds;

import java.awt.Graphics;
import java.awt.Image;


import weber.dominik.com.FlappyBirds.Game.GameState;

public class Player extends GameObject{
	/**
	 * Handler des spiels
	 */
	private Handler handler;
	/**
	 * true wenn spieler tot false wenn spieler lebendig
	 */
	private boolean dead;
	/**
	 * Bild des Spielers
	 */
	private Image img;
	
	/**
	 * erstellt den Spieler
	 * @param posX X Position
	 * @param posY Y Position
	 * @param width Breite
	 * @param height Höhe
	 * @param handler handler des Spiels
	 */
	public Player(float posX, float posY, int width, int height, Handler handler, Image img) {
		super(posX, posY, width, height);
		this.handler = handler;
		this.img = img;
		velY = 5;
		dead = false;
	}
	/**
	 * Game-Tick des Players
	 */
	@Override
	void tick(Handler handler) {
		if(!dead) {
			calculateScore();
			posY += velY;
			if (posY < 0) posY = 0;
			if (posY > 650) posY = 650;
			velY += 0.25;
			if(velY < -7) velX = -7;
			if(velY > 7) velX = 7;
			dead = collision();			
		}else {
			Game.gamestate = GameState.Dead;
		}
	}

	/**
	 * Testet ob etwas mit dem Player Kolidiert
	 * @return true wenn etwas Kolidiert false wenn nicht
	 */
	public boolean collision() {
		if(posY <= 0 || posY >= 650) {
			return true;
		}
		
		for(int i = 0; i < handler.getObjectsSize(); i++) {
			GameObject tempObject = handler.getObject(i);
			if(tempObject instanceof Pipe) {
				if(getBounds().intersects(tempObject.getBounds())) return true;
			}
		}
		return false;
	}
	/**
	 * erhöht den Score wenn eine Röhre durchquert wurde
	 */
	private void calculateScore() {
		for(int i = 0; i < handler.getObjectsSize(); i++) {
			GameObject tempObject = handler.getObject(i);
			if(tempObject instanceof Pipe) {
				Pipe p = (Pipe)tempObject;
				if(p.getPosX() + p.getWidth() < posX && p.getGiveScore()) {
					p.setGiveScore(false);
					handler.setScore(handler.getScore() + 1);
				}
			}
		}
	}
	
	/**
	 * Zeichnet den spieler im Fenster
	 */
	@Override
	void render(Graphics g) {
		g.drawImage(img, (int)posX, (int)posY, width, height, null);
		//g.setColor(Color.red);			
		//g.drawRect((int)posX, (int)posY, width, height);					//hitboxanzeige
	}
	/**
	 * setzt die Vertikale Geschwindigkeit gegen unten
	 * @param VelY Vertikale Geschwindigkeit gegen unten
	 */
	public void setVelY(float VelY) {
		this.velY = VelY;
	}
	
	
	public void setImg(Image img) {
		this.img = img;
	}
	
}
