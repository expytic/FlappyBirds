package weber.dominik.com.FlappyBirds;

import java.util.Random;

public class Spawner {
	/**
	 * Handler des spiels
	 */
	private Handler handler;
	/**
	 * erstellt den Spawner des Spiels
	 * @param handler Handler des spiels
	 */
	public Spawner(Handler handler){
		this.handler = handler;
	}
	/**
	 * Spawnt Pipes wenn diese genugweit entfernt sind
	 */
	public void tick() {
		if(1400 - getAbstandXnextP() > 400) {
			spawnpipes();
		}
	}
	
	/**
	 * erstellt 2 Pipes und fügt diese im den handler hinzu
	 */
	private void spawnpipes() {
		int abstand = 200;
		Random r = new Random();
		int heightPipe1 = 50 + r.nextInt(420);
		int heightPipe2 = 720 - abstand - heightPipe1;
		handler.addObject(new Pipe(1400, 0, 200, heightPipe1, true));
		handler.addObject(new Pipe(1400, abstand + heightPipe1, 200, heightPipe2, false));
	}
	/**
	 * Findet die Weitentferteste Pipe
	 * @return X Position der Weitenfertesten Pipe
	 */
	private float getAbstandXnextP() {
		float biggestX = 0;
		for(int i = 0; i < handler.getObjectsSize(); i++) {
			GameObject obj = handler.getObject(i);
			if(biggestX < obj.getPosX()){
				biggestX = obj.getPosX();
			}
		}
		return biggestX;
	}
}
