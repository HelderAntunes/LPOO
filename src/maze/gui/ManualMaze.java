package maze.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import maze.logic.GameState;
import maze.logic.GameState.Dificulty;
import maze.logic.Position;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

@SuppressWarnings("serial")
public class ManualMaze  extends JPanel 
implements MouseListener, MouseMotionListener, KeyListener {

	private JFrame frame;
	private JTextField fldMazeSize;
	private JButton btnConstructEmptyMaze;
	private JButton btnPlay;
	private JLabel lblTamanhoDoLabirinto;
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

	private Position position = new Position(0,0);/**< position for draw. */
	private int sizeSquare;/**< size of a square for draw. */
	private final int iconSize = 50;
	private final int boardSizeInFrame = 400;
	private int boardDimension;
	private Dificulty dificulty;
	private char board[][] = null;
	private char iconSelected = 'N';
	private ImagesOfGame images = new ImagesOfGame();

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

		btnConstructEmptyMaze = new JButton("Construir bordas do labirinto");
		btnConstructEmptyMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					lblWarnings.setText("");
					boardDimension = new Utilities().chooseBoardDimensions(fldMazeSize.getText());
					board = new char[boardDimension][boardDimension];
					setBoardEmpty();
					sizeSquare = boardSizeInFrame/boardDimension;
					repaint();
					btnPlay.setEnabled(true);
				} catch (InvalidBoardDimensions ibd) {
					lblWarnings.setText(ibd.getMessage());
				}
			}
		});
		btnConstructEmptyMaze.setBounds(458, 23, 215, 41);
		frame.getContentPane().add(btnConstructEmptyMaze);

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
	
	public void drawIconsLeftSide(Graphics g){
		position.setXY(5, 6);
		images.drawElementOfGame(g, 'H', iconSize, position);// hero
		position.setY(position.getY()+iconSize);
		images.drawElementOfGame(g, 'D', iconSize, position);// dragon
		position.setY(position.getY()+iconSize);
		images.drawElementOfGame(g, 'E', iconSize, position);// sword
		position.setY(position.getY()+iconSize);
		images.drawElementOfGame(g, 'S', iconSize, position);// exit
		position.setY(position.getY()+iconSize);
		images.drawElementOfGame(g, ' ', iconSize, position);// path
		position.setY(position.getY()+iconSize);
		images.drawElementOfGame(g, 'X', iconSize, position);// wall
		position.setY(position.getY()+iconSize);
		images.drawElementOfGame(g, iconSelected, iconSize, position);
	}
	
	private void drawBoard(Graphics g){
		position.setXY(150, 16);
		for(int i = 0;i < board.length;i++){
			for(int j = 0;j < board.length;j++){
				char c = board[i][j];
				images.drawElementOfGame(g, c, sizeSquare, position);
				position.setX(position.getX()+sizeSquare);
			}
			position.setX(150);
			position.setY(position.getY()+sizeSquare);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		lblWarnings.setText("");
		int xm = e.getX();
		int ym = e.getY();

		// icons
		if(xm >= 5 && xm <= 54)
			if(ym >= 16 && ym <= 65)
				iconSelected = 'H'; // hero
			else if(ym >= 66 && ym <= 115)
				iconSelected = 'D'; // dragon
			else if(ym >= 116 && ym <= 165)
				iconSelected = 'E'; // sword
			else if(ym >= 166 && ym <= 215)
				iconSelected = 'S'; // exit
			else if(ym >= 216 && ym <= 265)
				iconSelected = ' '; // path
			else if(ym >= 266 && ym <= 315)
				iconSelected = 'X'; // wall
		
		// paint board
		position.setXY(150, 16);
		int i = (ym-position.getY())/sizeSquare;
		int j = (xm-position.getX())/sizeSquare;
		if(i >= 0 && i < board.length && j >= 0 && j < board.length)
			if(iconSelected == 'N')
				lblWarnings.setText("Nao selecionou nenhum icone!");
			else
				board[i][j] = iconSelected;
		
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
