package weber.dominik.com.FlappyBirds;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	/**
	 * X Position des Objectes
	 */
	protected float posX;
	/**
	 * Y Position des Objectes
	 */
	protected float posY;
	/**
	 * X Geschwindigkeit des Objectes
	 */
	protected float velX;
	/**
	 * Y Geschwindigkeit des Objectes
	 */
	protected float velY;
	/**
	 * breite des Objectes
	 */
	protected int width;
	/**
	 * höhe des Objectes
	 */
	protected int height;
	/**
	 * führt den Gametick aus
	 * @param handler handler des spiels
	 */
	abstract void tick(Handler handler);
	/**
	 * zeichnet das GameObject 
	 * @param g Graphics des Spiels
	 */
	abstract void render(Graphics g);
	/**
	 * erstellen eines GameObjects
	 * @param posX X-Kordinate der Position
	 * @param posY Y-Kordinate der Position
	 * @param width Breite
	 * @param height Höhe
	 */
	public GameObject(Float posX, Float posY, int width, int height) {
		this.posX = posX;
		this.posY = posY;
		this.height = height;
		this.width = width;
	}
	/**
	 * 
	 * @return Rectangle vom GameObject
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)posX, (int)posY, width, height);
	}

	public float getPosX() {
		return posX;
	}
}
