package maze.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import maze.logic.GameState;
import maze.logic.Position.Direction;

/**
 * LPOO 2015/16.
 * This examples demonstrates how to draw images, handle keyboard events, handle mouse events,
 * and implement simple animations with timers.
 */
@SuppressWarnings("serial")
public class GameGraphicMaze extends JPanel 
implements MouseListener, MouseMotionListener, KeyListener {
	
	private JFrame frameGame;
	JLabel atualStateOfProgram;
	
	// Coordinates of the bounding rectangle for drawing the image
	private int x = 0, y = 0;
	private int withSquare = 35;

	// in-memory representation of images of elements of game
	private BufferedImage hero;
	private BufferedImage dragon;
	private BufferedImage sword;
	private BufferedImage exit;
	private BufferedImage pathWithNoElements;
	private BufferedImage wall;
	private GameState gamest;
	
	public GameGraphicMaze(GameState gamest) {
		setBounds(0, 0, 794, 487);
		frameGame = new JFrame("Maze Game");
		frameGame.setResizable(false);
		frameGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frameGame.setPreferredSize(new Dimension(500, 500));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
		setLayout(null);

		this.gamest = gamest;

		try {
			hero =  ImageIO.read(new File("images/hero.jpg"));
			dragon =  ImageIO.read(new File("images/dragon.jpg"));
			sword =  ImageIO.read(new File("images/sword.jpg"));
			exit =  ImageIO.read(new File("images/exit.jpg"));
			pathWithNoElements =  ImageIO.read(new File("images/pathWithNoElements.jpg"));
			wall =  ImageIO.read(new File("images/wall.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		frameGame.getContentPane().setLayout(null);
		frameGame.getContentPane().add(this);

		atualStateOfProgram = new JLabel("Pode jogar!!!");
		atualStateOfProgram.setBounds(10, 442, 281, 14);
		add(atualStateOfProgram);
		frameGame.pack(); 
		frameGame.setVisible(true);
		requestFocus();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		char[][] gameBoard = gamest.getGameBoard();
		x = y = 0;
		for(int i = 0;i < gameBoard.length;i++)
			for(int j = 0;j < gameBoard.length;j++)
				if(j == gameBoard.length-1){
					x = 0;
					y += withSquare;
				}
				else{
					char c = gameBoard[i][j];
					if(c == 'H')
						drawElementOfGame(g, hero);
					else if(c == 'A')
						drawElementOfGame(g, hero);
					else if(c == 'D')
						drawElementOfGame(g, dragon);
					else if(c == 'd')
						drawElementOfGame(g, dragon);
					else if(c == 'E')
						drawElementOfGame(g, sword);
					else if(c == 'F')
						drawElementOfGame(g, dragon);
					else if(c == 'S')
						drawElementOfGame(g, exit);
					else if(c == ' ')
						drawElementOfGame(g, pathWithNoElements);
					else if(c == 'X')
						drawElementOfGame(g, wall);
					x += withSquare;
				}
	}

	private void drawElementOfGame(Graphics g, BufferedImage elementImage){
		g.drawImage(elementImage, x, y, x + withSquare - 1, y + withSquare - 1, 0, 0, elementImage.getWidth(), elementImage.getHeight(), null);
	}

	private void playGameRound(Direction dirMovimentHero){		
		if(gamest.heroMoveIsValid(dirMovimentHero)){
			gamest.moveHero(dirMovimentHero);
			gamest.update();

			if(gamest.isFinished()){
				if(gamest.getHero().isAlive())
					atualStateOfProgram.setText("Jogo terminou. O heroi consegui escapar:)");
				else
					atualStateOfProgram.setText("Jogo terminou. O her�i morreu :(");				
			}
			else
				atualStateOfProgram.setText("Pode jogar!");

		}
		else
			atualStateOfProgram.setText("Movimenta��o inv�lida! Tente de novo...");

		repaint();
	}

	// ignored mouse events
	public void mousePressed(MouseEvent e) {} 
	public void mouseDragged(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseMoved(MouseEvent arg0) { }
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(gamest.isFinished() == false){
			switch(e.getKeyCode()){
			case KeyEvent.VK_LEFT:
				playGameRound(Direction.LEFT);
				break;
			case KeyEvent.VK_RIGHT: 
				playGameRound(Direction.RIGHT);
				break;
			case KeyEvent.VK_UP:
				playGameRound(Direction.UP);
				break;
			case KeyEvent.VK_DOWN:
				playGameRound(Direction.DOWN);
				break;
			default:
				break;
			}	
		}
	}

	// Ignored keyboard events
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}

	// Main program.
	// Creates a frame containing the panel
	public static void main(String[] args) {
		JFrame f = new JFrame("Maze Game");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		f.setPreferredSize(new Dimension(500, 500));
		/*JPanel panel = new GameGraphicMaze();
		f.getContentPane().add(panel);
		f.pack(); 
		f.setVisible(true);
		panel.requestFocus(); // to receive keyboard events    */   
	}
}