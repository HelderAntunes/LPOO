package maze.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import maze.logic.GameState;
import maze.logic.GameState.Dificulty;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

@SuppressWarnings("serial")
public class ManualMaze  extends JPanel 
implements MouseListener, MouseMotionListener, KeyListener {

	public enum IconSelected {NONE, HERO, DRAGON, SWORD, EXIT, PATH, WALL} 

	private JFrame frame;
	private JTextField fldMazeSize;
	private JLabel lblTamanhoDoLabirinto;
	private JButton btnConstruirBordasDo;
	private JButton btnPlay;
	private JLabel lblHero;
	private JLabel lblDragon;
	private JLabel lblSword;
	private JLabel lblExit;
	private JLabel lblPath;
	private JLabel lblWall;
	private JLabel lblAtualIcon;
	private JLabel lblDragonType;
	private JLabel lblWarnings;
	private JComboBox<String> comboBoxTypeOfDragons;

	// Coordinates of the bounding rectangle for drawing the image
	private int x = 0, y = 0;
	private int withSquare;
	private int heightSquare;
	private int iconSize = 50;
	private int boardDimension;
	private Dificulty dificulty;
	private char board[][] = null;
	private final int boardSizeInFrame = 400;
	private IconSelected iconSelected = IconSelected.NONE;

	// in-memory representation of images of elements of game
	private BufferedImage hero;
	private BufferedImage dragon;
	private BufferedImage sword;
	private BufferedImage exit;
	private BufferedImage pathWithNoElements;
	private BufferedImage wall;
	private BufferedImage cross;


	public ManualMaze() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		setBounds(88, 75, 706, 411);
		frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		frame.setPreferredSize(new Dimension(800, 600));		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(this);
		frame.pack(); 
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
		setLayout(null);

		lblWarnings = new JLabel("");
		lblWarnings.setForeground(Color.RED);
		lblWarnings.setBounds(10, 546, 364, 14);
		frame.getContentPane().add(lblWarnings);

		lblTamanhoDoLabirinto = new JLabel("Tamanho do labirinto");
		lblTamanhoDoLabirinto.setBounds(10, 11, 131, 21);
		frame.getContentPane().add(lblTamanhoDoLabirinto);

		fldMazeSize = new JTextField();
		fldMazeSize.setText("11");
		fldMazeSize.setBounds(133, 11, 86, 21);
		frame.getContentPane().add(fldMazeSize);
		fldMazeSize.setColumns(10);

		btnConstruirBordasDo = new JButton("Construir bordas do labirinto");
		btnConstruirBordasDo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					lblWarnings.setText("");
					boardDimension = new Utilities().chooseBoardDimensions(fldMazeSize.getText());
					board = new char[boardDimension][boardDimension];
					setBoardEmpty();
					withSquare = boardSizeInFrame/boardDimension;
					heightSquare = boardSizeInFrame/boardDimension;
					repaint();
					btnPlay.setEnabled(true);
				} catch (InvalidBoardDimensions ibd) {
					lblWarnings.setText(ibd.getMessage());
				}
			}
		});
		btnConstruirBordasDo.setBounds(458, 23, 215, 41);
		frame.getContentPane().add(btnConstruirBordasDo);

		lblHero = new JLabel("Heroi");
		lblHero.setBounds(10, 105, 46, 14);
		frame.getContentPane().add(lblHero);

		lblDragon = new JLabel("Dragao");
		lblDragon.setBounds(10, 155, 46, 14);
		frame.getContentPane().add(lblDragon);

		lblSword = new JLabel("Espada");
		lblSword.setBounds(10, 205, 46, 14);
		frame.getContentPane().add(lblSword);

		lblExit = new JLabel("Saida");
		lblExit.setBounds(10, 255, 46, 14);
		frame.getContentPane().add(lblExit);

		lblPath = new JLabel("Caminho");
		lblPath.setBounds(10, 305, 68, 14);
		frame.getContentPane().add(lblPath);

		lblWall = new JLabel("Parede");
		lblWall.setBounds(10, 355, 46, 14);
		frame.getContentPane().add(lblWall);

		lblAtualIcon = new JLabel("Icone atual");
		lblAtualIcon.setBounds(10, 405, 68, 14);
		frame.getContentPane().add(lblAtualIcon);

		lblDragonType = new JLabel("Tipo de drag\u00F5es");
		lblDragonType.setBounds(10, 43, 113, 14);
		frame.getContentPane().add(lblDragonType);

		comboBoxTypeOfDragons = new JComboBox<String>();
		comboBoxTypeOfDragons.setModel(new DefaultComboBoxModel(new String[] {"Est\u00E1ticos", "M\u00F3veis com adormecimento", "M\u00F3veis"}));
		comboBoxTypeOfDragons.setBounds(133, 43, 200, 20);
		frame.getContentPane().add(comboBoxTypeOfDragons);

		btnPlay = new JButton("Come\u00E7ar a jogar");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				dificulty = new Utilities().chooseDificulty(comboBoxTypeOfDragons.getSelectedIndex());
				new GameMaze(new GameState(board, dificulty));
			}
		});
		btnPlay.setBounds(315, 524, 146, 23);
		frame.getContentPane().add(btnPlay);
		btnPlay.setEnabled(false);

		try {
			hero =  ImageIO.read(new File("images/hero.jpg"));
			dragon =  ImageIO.read(new File("images/dragon.jpg"));
			sword =  ImageIO.read(new File("images/sword.jpg"));
			exit =  ImageIO.read(new File("images/exit.jpg"));
			pathWithNoElements =  ImageIO.read(new File("images/pathWithNoElements.jpg"));
			wall =  ImageIO.read(new File("images/wall.jpg"));
			cross = ImageIO.read(new File("images/cross.bmp"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		requestFocus();
	}

	public void setBoardEmpty(){
		for(int i = 0;i < board.length;i++)
			for(int j = 0;j < board.length;j++)
				if(i == 0 || i == board.length-1 
				|| j == 0 || j == board.length-1)
					board[i][j] = 'X'; //wall
				else
					board[i][j] = ' '; //path
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		drawIconsLeftSide(g);
		if(board != null)
			drawBoard(g);
	}

	private void drawBoard(Graphics g){
		x = 150;
		y = 16;
		for(int i = 0;i < board.length;i++){
			for(int j = 0;j < board.length;j++){
				char c = board[i][j];
				if(c == 'H')
					drawElementOfGame(g, hero);
				else if(c == 'D')
					drawElementOfGame(g, dragon);
				else if(c == 'E')
					drawElementOfGame(g, sword);
				else if(c == 'S')
					drawElementOfGame(g, exit);
				else if(c == ' ')
					drawElementOfGame(g, pathWithNoElements);
				else if(c == 'X')
					drawElementOfGame(g, wall);
				else if(c == 'C')
					drawElementOfGame(g, cross);
				x += withSquare;
			}
			x = 150;
			y += heightSquare;
		}
	}

	private void drawElementOfGame(Graphics g, BufferedImage elementImage){
		g.drawImage(elementImage, x, y, x + withSquare - 1, y + heightSquare - 1, 0, 0, elementImage.getWidth(), elementImage.getHeight(), null);
	}

	public void drawIconsLeftSide(Graphics g){
		x = 5;
		y = 16;
		g.drawImage(hero, x, y, x + iconSize - 1, y + iconSize - 1, 0, 0, hero.getWidth(), hero.getHeight(), null);
		y += iconSize;
		g.drawImage(dragon, x, y, x + iconSize - 1, y + iconSize - 1, 0, 0, dragon.getWidth(), dragon.getHeight(), null);
		y += iconSize;
		g.drawImage(sword, x, y, x + iconSize - 1, y + iconSize - 1, 0, 0, sword.getWidth(), sword.getHeight(), null);
		y += iconSize;
		g.drawImage(exit, x, y, x + iconSize - 1, y + iconSize - 1, 0, 0, exit.getWidth(), exit.getHeight(), null);
		y += iconSize;
		g.drawImage(pathWithNoElements, x, y, x + iconSize - 1, y + iconSize - 1, 0, 0, pathWithNoElements.getWidth(), pathWithNoElements.getHeight(), null);
		y += iconSize;
		g.drawImage(wall, x, y, x + iconSize - 1, y + iconSize - 1, 0, 0, wall.getWidth(), wall.getHeight(), null);
		y += iconSize;
		if(iconSelected == IconSelected.NONE)
			g.drawImage(cross, x, y, x + iconSize - 1, y + iconSize - 1, 0, 0, cross.getWidth(), cross.getHeight(), null);
		else if(iconSelected == IconSelected.HERO)
			g.drawImage(hero, x, y, x + iconSize - 1, y + iconSize - 1, 0, 0, hero.getWidth(), hero.getHeight(), null);
		else if(iconSelected == IconSelected.DRAGON)
			g.drawImage(dragon, x, y, x + iconSize - 1, y + iconSize - 1, 0, 0, dragon.getWidth(), dragon.getHeight(), null);
		else if(iconSelected == IconSelected.SWORD)
			g.drawImage(sword, x, y, x + iconSize - 1, y + iconSize - 1, 0, 0, sword.getWidth(), sword.getHeight(), null);
		else if(iconSelected == IconSelected.EXIT)
			g.drawImage(exit, x, y, x + iconSize - 1, y + iconSize - 1, 0, 0, exit.getWidth(), exit.getHeight(), null);
		else if(iconSelected == IconSelected.PATH)
			g.drawImage(pathWithNoElements, x, y, x + iconSize - 1, y + iconSize - 1, 0, 0, pathWithNoElements.getWidth(), pathWithNoElements.getHeight(), null);
		else if(iconSelected == IconSelected.WALL)
			g.drawImage(wall, x, y, x + iconSize - 1, y + iconSize - 1, 0, 0, wall.getWidth(), wall.getHeight(), null);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		lblWarnings.setText("");
		int xm = e.getX();
		int ym = e.getY();

		// icons
		if(xm >= 5 && xm <= 54)
			if(ym >= 16 && ym <= 65)
				iconSelected = IconSelected.HERO;
			else if(ym >= 66 && ym <= 115)
				iconSelected = IconSelected.DRAGON;
			else if(ym >= 116 && ym <= 165)
				iconSelected = IconSelected.SWORD;
			else if(ym >= 166 && ym <= 215)
				iconSelected = IconSelected.EXIT;
			else if(ym >= 216 && ym <= 265)
				iconSelected = IconSelected.PATH;
			else if(ym >= 266 && ym <= 315)
				iconSelected = IconSelected.WALL;

		x = 150;
		y = 16;
		int i = (ym-y)/heightSquare;
		int j = (xm-x)/withSquare;
		if(i >= 0 && i < board.length
				&& j >= 0 && j < board.length){
			if(iconSelected == IconSelected.NONE){
				lblWarnings.setText("Nao selecionou nenhum icone!");
			}
			else{
				if(iconSelected == IconSelected.HERO)
					board[i][j] = 'H';
				else if(iconSelected == IconSelected.DRAGON)
					board[i][j] = 'D';
				else if(iconSelected == IconSelected.SWORD)
					board[i][j] = 'E';
				else if(iconSelected == IconSelected.EXIT)
					board[i][j] = 'S';
				else if(iconSelected == IconSelected.PATH)
					board[i][j] = ' ';
				else if(iconSelected == IconSelected.WALL)
					board[i][j] = 'X';
			}
		}
		repaint();
	}

	// ignored mouse events
	public void mouseDragged(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseMoved(MouseEvent arg0) { }
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	// Ignored keyboard events
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent arg0) {}
}
