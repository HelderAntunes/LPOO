package maze.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import maze.logic.GameState;
import maze.logic.Position;
import maze.logic.Position.Direction;

@SuppressWarnings("serial")
public class GameMaze extends JPanel 
implements MouseListener, MouseMotionListener, KeyListener {

	private JFrame frameGame;
	private JButton btnSaveGame;

	private Position position = new Position(0,0);/**< position for draw. */
	private int sizeSquare;/**< size of a square for draw. */
	private final int boardSizeInFrame = 450;
	private ImagesOfGame images = new ImagesOfGame();
	private GameState gamest;
	private ArrayList<GameState> savedGames;

	public GameMaze(final GameState gamest) {
		setBounds(45, 35, 501, 453);
		
		frameGame = new JFrame("Maze Game");
		frameGame.setResizable(false);
		frameGame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		frameGame.setPreferredSize(new Dimension(600, 600));		
		frameGame.getContentPane().setLayout(null);
		frameGame.getContentPane().add(this);
		frameGame.pack(); 
		frameGame.setVisible(true);
		
		btnSaveGame = new JButton("Gravar jogo");
		btnSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				savedGames.add(gamest);
				new Utilities().saveGames(savedGames);
				requestFocus();
			}
		});
		btnSaveGame.setBounds(275, 514, 89, 23);
		frameGame.getContentPane().add(btnSaveGame);
		
		this.gamest = gamest;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
		setLayout(null);

		savedGames = new Utilities().readGames();
		sizeSquare = boardSizeInFrame/gamest.getGameBoard().length;

		requestFocus();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		char[][] gameBoard = gamest.getGameBoard();
		position.setXY(20, 0);
		for(int i = 0;i < gameBoard.length;i++){
			for(int j = 0;j < gameBoard.length;j++){
				char c = gameBoard[i][j];
				images.drawElementOfGame(g, c, sizeSquare, position);
				position.setX(position.getX()+sizeSquare);
			}
			position.setX(20);
			position.setY(position.getY()+sizeSquare);
		}
	}
	
	private void playGameRound(Direction dirMovimentHero){		
		if(gamest.heroMoveIsValid(dirMovimentHero)){
			gamest.moveHero(dirMovimentHero);
			gamest.update();
		}
		repaint();
	}
	
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
	
	// ignored mouse events
	public void mousePressed(MouseEvent e) {} 
	public void mouseDragged(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseMoved(MouseEvent arg0) { }
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	// Ignored keyboard events
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}