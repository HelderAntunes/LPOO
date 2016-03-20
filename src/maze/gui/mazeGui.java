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



public class MazeGui {

	private JFrame frmJogoDoLabirinto;
	private JTextField fldMazeSize;
	private JTextField fldNumberOfDragons;
	private JComboBox<String> comboBoxTypeOfDragons;
	private JLabel dragonType;

	private JLabel mazeSize;
	private JLabel dragonsNumber;
	private JButton btnGenerateMaze;
	private JButton btnFinishGame;
	private JLabel atualStateOfProgram;

	private GameState gamest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MazeGui window = new MazeGui();
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
	public MazeGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJogoDoLabirinto = new JFrame();
		frmJogoDoLabirinto.setResizable(false);
		frmJogoDoLabirinto.setTitle("Jogo do labirinto");
		frmJogoDoLabirinto.setBounds(100, 100, 573, 152);
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
				try{
					Dificulty dificulty = chooseDificulty();
					int n = chooseBoardDimensions();
					int numDragons = getNumberOfDragons();
					char[][] boardOfMaze = new MazeBuilder().buildMaze(n);
					boardOfMaze = new AddingCharactersToMaze().addDragonsInMazeUntilNumDragons(boardOfMaze, numDragons);
					gamest = new GameState(boardOfMaze, dificulty);
					new GameGraphicMaze(gamest);
				}
				catch(InvalidNumberOfDragons ind){
					atualStateOfProgram.setText(ind.getMessage());
				}
				catch(InvalidBoardDimensions ibd){
					atualStateOfProgram.setText(ibd.getMessage());
				}
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

		atualStateOfProgram = new JLabel("Pode gerar novo labirinto!");
		atualStateOfProgram.setBounds(21, 100, 364, 14);
		frmJogoDoLabirinto.getContentPane().add(atualStateOfProgram);
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
	
	public boolean isNumeric(String str)  {  
		try  {  
			Integer.parseInt(str);  
		}  
		catch(NumberFormatException nfe)  {  
			return false;  
		}  
		return true;  
	}
	
	public int chooseBoardDimensions() throws InvalidBoardDimensions{
		String text = fldMazeSize.getText();
		if(isNumeric(text)){
			int boardDimensions = Integer.parseInt(text);  
			if(boardDimensions >= 5 && boardDimensions%2 != 0)
				return boardDimensions;
			else
				throw new InvalidBoardDimensions("Dimensao do tabuleiro invalida.");
		}
		throw new InvalidBoardDimensions("Introduza um número em dimensao do tabuleiro.");
	}
	
	public int getNumberOfDragons() throws InvalidNumberOfDragons{
		String text = fldNumberOfDragons.getText();
		if(isNumeric(text)){
			int nDragons = Integer.parseInt(text);  
			if(nDragons >= 1)
				return nDragons;
			else
				throw new InvalidNumberOfDragons("Numero de dragoes invalido.");
		}
		throw new InvalidNumberOfDragons("Introduza um número em Numero de dragoes.");
	}
}
