package maze.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import maze.logic.AddingCharactersToMaze;
import maze.logic.GameState;
import maze.logic.MazeBuilder;
import maze.logic.Position.Direction;
import maze.logic.GameState.Dificulty;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class mazeGui {

	private JFrame frmJogoDoLabirinto;
	private JTextField fldMazeSize;
	private JTextField fldNumberOfDragons;
	private JComboBox<String> comboBoxTypeOfDragons;
	private JLabel dragonType;

	JLabel mazeSize;
	JLabel dragonsNumber;
	JButton btnGenerateMaze;
	JButton btnFinishGame;
	JTextArea textAreaStateMaze;
	JButton btnUp;
	JButton btnRight;
	JButton btnLeft;
	JButton btnDown;
	JLabel atualStateOfProgram;

	private GameState gamest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mazeGui window = new mazeGui();
					window.frmJogoDoLabirinto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mazeGui() {
		initialize();
	}


	private void playGameRound(Direction dirMovimentHero){		
		if(gamest.heroMoveIsValid(dirMovimentHero)){
			gamest.moveHero(dirMovimentHero);
			gamest.update();
			
			if(gamest.isFinished()){
				disableButtons();
				if(gamest.getHero().isAlive())
					atualStateOfProgram.setText("Jogo terminou. O heroi consegui escapar:)");
				else
					atualStateOfProgram.setText("Jogo terminou. O herói morreu :(");				
			}
			else
				atualStateOfProgram.setText("Pode jogar!");

		}
		else
			atualStateOfProgram.setText("Movimentação inválida! Tente de novo...");

		textAreaStateMaze.setText(gamest.toString());
	}

	public void disableButtons(){
		btnUp.setEnabled(false);
		btnRight.setEnabled(false);
		btnLeft.setEnabled(false);
		btnDown.setEnabled(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJogoDoLabirinto = new JFrame();
		frmJogoDoLabirinto.setResizable(false);
		frmJogoDoLabirinto.setTitle("Jogo do labirinto");
		frmJogoDoLabirinto.setBounds(100, 100, 573, 427);
		frmJogoDoLabirinto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJogoDoLabirinto.getContentPane().setLayout(null);

		mazeSize = new JLabel("Dimens\u00E3o do labirinto");
		mazeSize.setBounds(21, 22, 142, 14);
		frmJogoDoLabirinto.getContentPane().add(mazeSize);

		dragonsNumber = new JLabel("N\u00FAmero de drag\u00F5es");
		dragonsNumber.setBounds(21, 47, 120, 14);
		frmJogoDoLabirinto.getContentPane().add(dragonsNumber);

		fldMazeSize = new JTextField();
		fldMazeSize.setText("11");
		fldMazeSize.setBounds(162, 19, 86, 20);
		frmJogoDoLabirinto.getContentPane().add(fldMazeSize);
		fldMazeSize.setColumns(10);

		fldNumberOfDragons = new JTextField();
		fldNumberOfDragons.setText("1");
		fldNumberOfDragons.setBounds(162, 44, 86, 20);
		frmJogoDoLabirinto.getContentPane().add(fldNumberOfDragons);
		fldNumberOfDragons.setColumns(10);

		comboBoxTypeOfDragons = new JComboBox<String>();
		comboBoxTypeOfDragons.setModel(new DefaultComboBoxModel<String>(new String[] {"Est\u00E1ticos", "M\u00F3veis com adormecimento", "M\u00F3veis"}));
		comboBoxTypeOfDragons.setBounds(160, 69, 200, 20);
		frmJogoDoLabirinto.getContentPane().add(comboBoxTypeOfDragons);

		dragonType = new JLabel("Tipo de drag\u00F5es");
		dragonType.setBounds(21, 72, 113, 14);
		frmJogoDoLabirinto.getContentPane().add(dragonType);

		btnGenerateMaze = new JButton("Gerar novo labirinto");
		btnGenerateMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dificulty dificulty = chooseDificulty();
				int n = chooseBoardDimensions();
				int numDragons = getNumberOfDragons();
				char[][] boardOfMaze = new MazeBuilder().buildMaze(n);
				boardOfMaze = new AddingCharactersToMaze().addDragonsInMazeUntilNumDragons(boardOfMaze, numDragons);
				gamest = new GameState(boardOfMaze, dificulty);
				textAreaStateMaze.setText(gamest.toString());
				enableButtons();
				atualStateOfProgram.setText("Pode jogar!");
			}

			public int getNumberOfDragons(){
				return Integer.parseInt(fldNumberOfDragons.getText());
			}

			public int chooseBoardDimensions(){
				return Integer.parseInt(fldMazeSize.getText());
			}

			public Dificulty chooseDificulty(){
				int indexSelected = comboBoxTypeOfDragons.getSelectedIndex();
				switch(indexSelected){
				case 0:
					return Dificulty.EASY;
				case 1:
					return Dificulty.MEDIUM;
				case 2:
					return Dificulty.HARD;
				default:
					return Dificulty.EASY;
				}
			}

			public void enableButtons(){
				btnUp.setEnabled(true);
				btnRight.setEnabled(true);
				btnLeft.setEnabled(true);
				btnDown.setEnabled(true);
			}
		});
		btnGenerateMaze.setBounds(394, 18, 163, 23);
		frmJogoDoLabirinto.getContentPane().add(btnGenerateMaze);

		btnFinishGame = new JButton("Terminar programa");
		btnFinishGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnFinishGame.setBounds(394, 68, 163, 23);
		frmJogoDoLabirinto.getContentPane().add(btnFinishGame);

		textAreaStateMaze = new JTextArea();
		textAreaStateMaze.setFont(new Font("Courier New", Font.PLAIN, 13));
		textAreaStateMaze.setBounds(21, 118, 339, 245);
		frmJogoDoLabirinto.getContentPane().add(textAreaStateMaze);

		btnUp = new JButton("Cima");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playGameRound(Direction.UP);
			}
		});
		btnUp.setEnabled(false);
		btnUp.setBounds(424, 138, 89, 23);
		frmJogoDoLabirinto.getContentPane().add(btnUp);

		btnRight = new JButton("Direita");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playGameRound(Direction.RIGHT);
			}
		});
		btnRight.setEnabled(false);
		btnRight.setBounds(468, 172, 89, 23);
		frmJogoDoLabirinto.getContentPane().add(btnRight);

		btnLeft = new JButton("Esquerda");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playGameRound(Direction.LEFT);
			}
		});
		btnLeft.setEnabled(false);
		btnLeft.setBounds(369, 172, 89, 23);
		frmJogoDoLabirinto.getContentPane().add(btnLeft);

		btnDown = new JButton("Baixo");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playGameRound(Direction.DOWN);
			}
		});
		btnDown.setEnabled(false);
		btnDown.setBounds(424, 207, 89, 23);
		frmJogoDoLabirinto.getContentPane().add(btnDown);

		atualStateOfProgram = new JLabel("Pode gerar novo labirinto!");
		atualStateOfProgram.setBounds(20, 374, 364, 14);
		frmJogoDoLabirinto.getContentPane().add(atualStateOfProgram);
	}
}
