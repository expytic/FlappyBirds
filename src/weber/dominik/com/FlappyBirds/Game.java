package weber.dominik.com.FlappyBirds;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Game extends Canvas implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8437215203565536093L;
	
	/**
	 * aktueller spielstatus
	 */
	public static GameState gamestate; 
	/**
	 * thread des Spieles
	 */
	private Thread thread;
	/**
	 * Handler des Spieles
	 */
	private static Handler handler;
	/**
	 * status des Threads
	 */
	private boolean running;
	/**
	 * Menu des Spieles
	 */
	private Menu menu;
	/**
	 * Verschiedene Spielerbilder
	 */
	private static Image[] birdsImg;
	/**
	 * 
	 */
	private static int aktuellesSpielerbild = 0;
	/**
	 * Spielstatus
	 */
	public enum GameState {
		MainMenu,
		Playing,
		Help,
		Paused,
		ChooseBird,
		Dead;
	}
	/**
	 * erstellen des Games
	 */
	public Game() {
		gamestate = GameState.MainMenu;
		loadImage();
		handler = new Handler();
		new Window(this);
		menu = new Menu(this);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		
		running = true;
		
	}
	/**
	 * startet das Spiel neu
	 */
	public void restart() {
		handler = new Handler();
		KeyListener[] keyListener = this.getKeyListeners();
			if (keyListener[0] instanceof KeyInput) {
				((KeyInput) keyListener[0]).setHandler(handler);
		}
	}
	
	/**
	 * Started das Spiel richtig
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	/**
	 * stoppt das Spiel
	 */
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *	trennt Game-Ticks von Frames
	 */
	public void run() {
		System.out.println("started");
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {
				render();
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	/**
	 * Diese Methode regelt die Game-Ticks
	 */
	private void tick() {
		if(gamestate == GameState.Playing)	handler.tick();
	}
	/**
	 * Diese Methode regelt die Zeichnungen
	 */
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.darkGray);
		g.fillRect(-10, -10, 1400, 800);
		
		handler.render(g, menu);
		
		g.dispose();
		bs.show();
	}
	/**
	 * lädt alle nötigen bilder
	 */
	private void loadImage() {
		birdsImg = new Image[5];
		try {
			birdsImg[0] = ImageIO.read(new File("img/bird_blue.png"));
			birdsImg[1] = ImageIO.read(new File("img/bird_green.png"));
			birdsImg[2] = ImageIO.read(new File("img/bird_orange.png"));
			birdsImg[3] = ImageIO.read(new File("img/bird_red.png"));
			birdsImg[4] = ImageIO.read(new File("img/bird_yellow.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public static void setAktuellesSpielerbild(int aktuellesSpielerbild) {
		Game.aktuellesSpielerbild = aktuellesSpielerbild;
	}
	
	public Image[] getBirdsImg() {
		return birdsImg;
	}
	/**
	 * giebt aktuelles spielerbild zurück
	 * @return aktuelles Spielerbild
	 */
	public static Image getCurrentBirdImg() {
		return birdsImg[aktuellesSpielerbild];
	}
	
	public Handler getHandler() {
		return handler;
	}
	
	public void setCurrentPlayerImg() {
		handler.setBirdImage();
	}
	
	/**
	 * Mainmethode
	 * @param args keine nötig
	 */
	public static void main(String[] args) {
		new Game();
	}


}
