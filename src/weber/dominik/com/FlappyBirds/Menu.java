package weber.dominik.com.FlappyBirds;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import weber.dominik.com.FlappyBirds.Game.GameState;



public class Menu extends MouseAdapter{
	/**
	 * breite des standartfeldes
	 */
	private final static int fieldWidth = 200;
	/**
	 * höhe des standartfeldes
	 */
	private final static int fieldHeight = 64;
	/**
	 * erklärungstext
	 */
	private final static String erklearString1 = "Benutze WASD um Gegnern auszuweichen";
	/**
	 * ist das spiel am laufen
	 */
	private boolean gamerunning = false;
	/**
	 * mainGame
	 */
	private Game game;
	/**
	 * erstellt das Menu
	 * @param game mainGame
	 */
	/**
	 * logo der Topomedics
	 */
	private Image topomedicsLogo;
	public Menu(Game game) {
		this.game = game;
		try {
			topomedicsLogo = ImageIO.read(new File("img/logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * überprüft ob sich die Maus in einem Feld befindet
	 */
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		
		switch(Game.gamestate){
		case Dead:
			if(mouseOver(mouseX, mouseY, 540, 150, fieldWidth, fieldHeight)) {
				game.restart();
				Game.gamestate = GameState.Playing;
			}else if(mouseOver(mouseX, mouseY, 540, 250, fieldWidth, fieldHeight)) {
				game.restart();
				Game.gamestate = GameState.MainMenu;
			}else if(mouseOver(mouseX, mouseY, 540, 350, fieldWidth, fieldHeight)) {
				System.exit(1);
			}
			break;
		case Help:
			if(mouseOver(mouseX, mouseY, 540, 350, fieldWidth, fieldHeight)) {
				if(gamerunning) {
					Game.gamestate = GameState.Paused;
				}else {
					Game.gamestate = GameState.MainMenu;
				}
			}
			break;
		case MainMenu:
			if(mouseOver(mouseX, mouseY, 540, 150, fieldWidth, fieldHeight)) {
				Game.gamestate = GameState.Playing;
			}else if(mouseOver(mouseX, mouseY, 540, 250, fieldWidth, fieldHeight)) {
				Game.gamestate = GameState.ChooseBird;
			}else if(mouseOver(mouseX, mouseY, 540, 350, fieldWidth, fieldHeight)) {
				Game.gamestate = GameState.Help;
			}else if(mouseOver(mouseX, mouseY, 540, 450, fieldWidth, fieldHeight)) {
				System.exit(1);
			}
			break;
		case Paused:
			if(mouseOver(mouseX, mouseY, 540, 150, fieldWidth, fieldHeight)) {
				Game.gamestate = GameState.Playing;
			}else if(mouseOver(mouseX, mouseY, 540, 250, fieldWidth, fieldHeight)) {
				game.restart();
				Game.gamestate = GameState.Playing;
			}else if(mouseOver(mouseX, mouseY, 540, 350, fieldWidth, fieldHeight)) {
				game.restart();
				Game.gamestate = GameState.MainMenu;
			}else if(mouseOver(mouseX, mouseY, 540, 450, fieldWidth, fieldHeight)) {
				Game.gamestate = GameState.Help;
			}else if(mouseOver(mouseX, mouseY, 540, 550, fieldWidth, fieldHeight)) {
				System.exit(1);
			}
			
			break;
		case Playing:
			break;
		case ChooseBird:
			
			for(int i = 0; i < 5; i++) {
				if(mouseOver(mouseX, mouseY,(i + 1) * 160 + i * 64 , 300, 64, 64)) {
					Game.setAktuellesSpielerbild(i);
					break;
				}
			}
			if(mouseOver(mouseX, mouseY, 540, 450, fieldWidth, fieldHeight)) {
				Game.gamestate = GameState.MainMenu;
				game.setCurrentPlayerImg();
			}
			else if(mouseOver(mouseX, mouseY, 540, 550, fieldWidth, fieldHeight)) {
				System.exit(1);
			}
			break;
		default:
			break;
			
		}
	}
		
	/**
	 * überprüft ob die Maus sich in einem Feld befindet
	 * @param mouseX X Position der Maus
	 * @param mouseY Y Position der Maus
	 * @param fieldX X Position des Feldes
	 * @param fieldY X Position des Feldes
	 * @param width breite des Feldes
	 * @param height höhe des Feldes
	 * @return true wenn sich die Maus im Feld befindet false wenn sich die Maus nicht im feld befiendet
	 */
	private boolean mouseOver(int mouseX, int mouseY, int fieldX, int fieldY, int width, int height) {
		if(mouseX > fieldX && mouseX < fieldX + width) {
			if(mouseY > fieldY && mouseY < fieldY + height) {
				return true;
			}else return false;
		}else return false;
	}
	

	/**
	 * berechnet die nötige position damit ein string zentriert ist
	 * @param s String der zentriert sein sollte
	 * @return xposition des zu zentrierendes Stringes
	 */
	private int getXForString(Graphics g, String s) {
		FontMetrics fontmetric = g.getFontMetrics();
		return (1280 - fontmetric.stringWidth(s)) / 2;		
	}
	/**
	 * zeichnet die verschiedenen menüs
	 */
	@SuppressWarnings("static-access")
	public void render(Graphics g) {
		
		
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		Font fnt3 = new Font("arial", 1, 20);
		
		
		
		switch(Game.gamestate) {
		case Dead:
			g.setFont(fnt);
			g.setColor(Color.black);
			g.fillRect(540, 150, fieldWidth, fieldHeight);
			g.fillRect(540, 250, fieldWidth, fieldHeight);
			g.fillRect(540, 350, fieldWidth, fieldHeight);
			
			g.setColor(Color.white);
			g.drawString("Dead", getXForString(g, "Dead"), 70);
			g.setFont(fnt2);
			g.drawRect(540, 150, fieldWidth, fieldHeight);
			g.drawString("Retry", getXForString(g, "Retry"), 190);
			g.drawRect(540, 250, fieldWidth, fieldHeight);
			g.drawString("Menu", getXForString(g, "Menu"), 290);
			g.drawRect(540, 350, fieldWidth, fieldHeight);
			g.drawString("Quit", getXForString(g, "Quit"), 390);
			gamerunning = false;
			break;
		case Help:
			g.setFont(fnt);
			
			g.setColor(Color.black);
			g.fillRect(540, 350, fieldWidth, fieldHeight);
			
			g.setColor(Color.white);
			g.drawString("Help", getXForString(g, "Help"), 70);
			g.setFont(fnt3);
			
			g.drawString(erklearString1, getXForString(g, erklearString1), 200);	//still to do
			g.setFont(fnt2);
			g.drawRect(540, 350, fieldWidth, fieldHeight);
			g.drawString("Back", getXForString(g, "Back"), 390);
			break;
		case MainMenu:
			g.setFont(fnt);
			
			g.setColor(Color.black);
			g.fillRect(540, 150, fieldWidth, fieldHeight);
			g.fillRect(540, 250, fieldWidth, fieldHeight);
			g.fillRect(540, 350, fieldWidth, fieldHeight);
			g.fillRect(540, 450, fieldWidth, fieldHeight);
			
			g.setColor(Color.white);
			g.drawString("Menü", getXForString(g, "Menu"), 70);
			g.setFont(fnt2);
			g.drawRect(540, 150, fieldWidth, fieldHeight);
			g.drawString("Play", getXForString(g, "Play"), 190);
			g.drawRect(540, 250, fieldWidth, fieldHeight);
			g.drawString("Skins", getXForString(g, "Skins"), 290);
			g.drawRect(540, 350, fieldWidth, fieldHeight);
			g.drawString("Help", getXForString(g, "Help"), 390);
			g.drawRect(540, 450, fieldWidth, fieldHeight);			
			g.drawString("Quit", getXForString(g, "Quit"), 490);
			gamerunning = false;
			break;
		case Paused:
			g.setFont(fnt);
			g.setColor(Color.black);
			g.fillRect(540, 150, fieldWidth, fieldHeight);
			g.fillRect(540, 250, fieldWidth, fieldHeight);
			g.fillRect(540, 350, fieldWidth, fieldHeight);
			g.fillRect(540, 450, fieldWidth, fieldHeight);
			g.fillRect(540, 550, fieldWidth, fieldHeight);
			g.setColor(Color.white);
			g.drawString("Paused", getXForString(g, "Paused"), 70);
			g.setFont(fnt2);
			g.drawRect(540, 150, fieldWidth, fieldHeight);
			g.drawString("Weiter", getXForString(g, "Weiter"), 190);
			g.drawRect(540, 250, fieldWidth, fieldHeight);
			g.drawString("restart", getXForString(g, "restart"), 290);
			g.drawRect(540, 350, fieldWidth, fieldHeight);
			g.drawString("Menu", getXForString(g, "Menu"), 390);
			g.drawRect(540, 450, fieldWidth, fieldHeight);
			g.drawString("Help", getXForString(g, "Help"), 490);
			g.drawRect(540, 550, fieldWidth, fieldHeight);
			g.drawString("Quit", getXForString(g, "Quit"), 590);
			gamerunning = true;
			
			break;
		case Playing:
			gamerunning = true;
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Score: " + game.getHandler().getScore(), 10, 50);
			break;
		case ChooseBird:
			g.setColor(Color.black);
			g.setFont(fnt2);
			Image[] birds = game.getBirdsImg();
			g.drawImage(game.getCurrentBirdImg(), 576, 50, 128, 128, null);
			for(int i = 0; i < birds.length; i++) {
				g.drawImage(birds[i], (i + 1) * 160 + i * 64 , 300, 64, 64, null);
			}
			
			g.fillRect(540, 450, fieldWidth, fieldHeight);
			g.fillRect(540, 550, fieldWidth, fieldHeight);
			
			g.setColor(Color.white);
			g.drawRect(540, 450, fieldWidth, fieldHeight);
			g.drawString("Menu", getXForString(g, "Menu"), 490);
			
			g.drawRect(540, 550, fieldWidth, fieldHeight);
			g.drawString("Quit", getXForString(g, "Quit"), 590);
			
		default:
			break;
		
		}
		g.drawImage(topomedicsLogo, 1170, 5, 88, 18, null);
		
	}
}
