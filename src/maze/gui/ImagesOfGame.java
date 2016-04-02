package maze.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import maze.logic.Position;

public class ImagesOfGame {

	private static BufferedImage hero;
	private static BufferedImage heroArmed;
	private static BufferedImage dragon;
	private static BufferedImage dragonSleeping;
	private static BufferedImage sword;
	private static BufferedImage exit;
	private static BufferedImage path;
	private static BufferedImage wall;
	private static BufferedImage cross;

	public ImagesOfGame(){
		if(hero == null)
			try {
				hero =  ImageIO.read(new File("images/hero.jpg"));
				heroArmed = ImageIO.read(new File("images/heroArmed.jpg"));
				dragon =  ImageIO.read(new File("images/dragon.jpg"));
				dragonSleeping = ImageIO.read(new File("images/dragonSleeping.jpg"));
				sword =  ImageIO.read(new File("images/sword.jpg"));
				exit =  ImageIO.read(new File("images/exit.jpg"));
				path =  ImageIO.read(new File("images/path.jpg"));
				wall =  ImageIO.read(new File("images/wall.jpg"));
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
