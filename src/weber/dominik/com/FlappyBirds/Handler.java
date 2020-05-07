package weber.dominik.com.FlappyBirds;

import java.awt.Graphics;
import java.util.ArrayList;

import weber.dominik.com.FlappyBirds.Game.GameState;


public class Handler {
	/**
	 * Liste aller GameObjects
	 */
	private ArrayList<GameObject> objects;
	/**
	 * Spawner des spiels
	 */
	private Spawner spawner;
	/**
	 * aktueller score des Spielers
	 */
	private int score = 0;
	/**
	 * erstellt den Handler und den Spieler
	 */
	public Handler() {
		objects = new ArrayList<GameObject>();
		spawner = new Spawner(this);
		objects.add(new Player(100,100, 48, 48, this, Game.getCurrentBirdImg()));
	}
	/**
	 * Ausführen der Game-Ticks von allen GameObject's
	 */
	public void tick() {
		spawner.tick();
		for(int i = objects.size() - 1; i >= 0; i--) {
			GameObject obj = objects.get(i);
			obj.tick(this);
		}
		
	}
	/**
	 * Ausführen des Zeichnens von allen GameObject's
	 * @param g Grapgics der Anwendung
	 * @param menu Menu der Anwendung
	 */
	public void render(Graphics g, Menu menu) {
		if(Game.gamestate == GameState.Playing || Game.gamestate == GameState.Paused || Game.gamestate == GameState.Dead) {
			for(GameObject obj : objects) {
				obj.render(g);
			}
		}
		menu.render(g);
	}
	/**
	 * Entfernt ein GameObject
	 * @param toRemove GameObject zum entfernen
	 */
	public void removeObject(GameObject toRemove) {
		objects.remove(toRemove);
	}
	/**
	 * fügt ein GameObject hinzu
	 * @param toAdd GameObject zum hinzufügen
	 */
	public void addObject(GameObject toAdd) {
		objects.add(toAdd);
	}
	/**
	 * giebt ein GameObject mit dem index zurück
	 * @param i index des zu bekommendes GameObject
	 * @return GameObject mit dem gegebenen index
	 */
	public GameObject getObject(int i) {
		return objects.get(i);
	}
	/**
	 * giebt die anzahl der GameObject's zurück
	 * @return anzahl der GameObject's
	 */
	public int getObjectsSize() {
		return objects.size();
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * aktuallisiert das aktuelle spielerbild
	 */
	public void setBirdImage() {
		for(GameObject obj : objects) {
			if (obj instanceof Player) {
				((Player)obj).setImg(Game.getCurrentBirdImg());;
			}
		}
	}
}
