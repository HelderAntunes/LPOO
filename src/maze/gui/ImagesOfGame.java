package maze.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import maze.logic.Position;

public class ImagesOfGame {
	
	private BufferedImage hero;
	private BufferedImage heroArmed;
	private BufferedImage dragon;
	private BufferedImage dragonSleeping;
	private BufferedImage sword;
	private BufferedImage exit;
	private BufferedImage path;
	private BufferedImage wall;
	private BufferedImage cross;
	
	public ImagesOfGame(){
		try {
			hero =  ImageIO.read(new File("images/hero.png"));
			heroArmed = ImageIO.read(new File("images/heroArmed.png"));
			dragon =  ImageIO.read(new File("images/dragon.png"));
			dragonSleeping = ImageIO.read(new File("images/dragonSleeping.png"));
			sword =  ImageIO.read(new File("images/sword.png"));
			exit =  ImageIO.read(new File("images/exit.png"));
			path =  ImageIO.read(new File("images/path.png"));
			wall =  ImageIO.read(new File("images/wall.png"));
			cross = ImageIO.read(new File("images/cross.bmp"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getImage(char c){
		switch(c){
		case 'H':
			return hero;
		case 'A':
			return heroArmed;
		case 'D':
			return dragon;
		case 'd':
			return dragonSleeping;
		case 'F':
			return dragon;
		case 'E':
			return sword;
		case 'S':
			return exit;
		case ' ':
			return path;
		case 'X':
			return wall;
		default:
			return cross;
		}
	}
	
	public void drawElementOfGame(Graphics g, char c, int withSquare, Position position){
		BufferedImage image = getImage(c);
		g.drawImage(image, position.getX(), position.getY(), position.getX() + withSquare - 1, position.getY() + withSquare - 1, 0, 0, image.getWidth(), image.getHeight(), null);
	}
	
}
