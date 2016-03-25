package maze.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import maze.logic.AddingCharactersToMaze;
import maze.logic.GameState;
import maze.logic.MazeBuilder;
import maze.logic.GameState.Dificulty;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;



public class RandomMaze {
	
	private JComboBox<String> comboBoxTypeOfDragons;
	private JFrame frmJogoDoLabirinto;
	private JTextField fldMazeSize;
	private JTextField fldNumberOfDragons;
	private JLabel lbldragonType;
	private JLabel lblmazeSize;
	private JLabel lbldragonsNumber;
	private JLabel atualStateOfProgram;
	private JButton btnGenerateMaze;
	private GameState gamest;

	public RandomMaze() {
		initialize();
		frmJogoDoLabirinto.setVisible(true);
	}

	private void initialize() {
		frmJogoDoLabirinto = new JFrame();
		frmJogoDoLabirinto.setResizable(false);
		frmJogoDoLabirinto.setTitle("Jogo do labirinto");
		frmJogoDoLabirinto.setBounds(100, 100, 573, 145);
		frmJogoDoLabirinto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmJogoDoLabirinto.getContentPane().setLayout(null);

		lblmazeSize = new JLabel("Dimens\u00E3o do labirinto");
		lblmazeSize.setBounds(10, 22, 142, 14);
		frmJogoDoLabirinto.getContentPane().add(lblmazeSize);

		lbldragonsNumber = new JLabel("N\u00FAmero de drag\u00F5es");
		lbldragonsNumber.setBounds(10, 47, 120, 14);
		frmJogoDoLabirinto.getContentPane().add(lbldragonsNumber);

		fldMazeSize = new JTextField();
		fldMazeSize.setText("11");
		fldMazeSize.setBounds(140, 19, 86, 20);
		frmJogoDoLabirinto.getContentPane().add(fldMazeSize);
		fldMazeSize.setColumns(10);

		fldNumberOfDragons = new JTextField();
		fldNumberOfDragons.setText("1");
		fldNumberOfDragons.setBounds(140, 47, 86, 20);
		frmJogoDoLabirinto.getContentPane().add(fldNumberOfDragons);
		fldNumberOfDragons.setColumns(10);

		comboBoxTypeOfDragons = new JComboBox<String>();
		comboBoxTypeOfDragons.setModel(new DefaultComboBoxModel<String>(new String[] {"Est\u00E1ticos", "M\u00F3veis com adormecimento", "M\u00F3veis"}));
		comboBoxTypeOfDragons.setBounds(140, 69, 200, 20);
		frmJogoDoLabirinto.getContentPane().add(comboBoxTypeOfDragons);

		lbldragonType = new JLabel("Tipo de drag\u00F5es");
		lbldragonType.setBounds(10, 72, 113, 14);
		frmJogoDoLabirinto.getContentPane().add(lbldragonType);

		btnGenerateMaze = new JButton("Jogar em labirinto aleatorio");
		btnGenerateMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Dificulty dificulty = new Utilities().chooseDificulty(comboBoxTypeOfDragons.getSelectedIndex());
					int boardDimension = new Utilities().chooseBoardDimensions(fldMazeSize.getText());
					int numDragons = new Utilities().getNumberOfDragons(fldNumberOfDragons.getText());
					atualStateOfProgram.setText("");
					char[][] boardOfMaze = new MazeBuilder().buildMaze(boardDimension);
					boardOfMaze = new AddingCharactersToMaze().addDragonsInMazeUntilNumDragons(boardOfMaze, numDragons);
					gamest = new GameState(boardOfMaze, dificulty);
					frmJogoDoLabirinto.dispose();
					new GameMaze(gamest);
				}
				catch(InvalidNumberOfDragons ind){
					atualStateOfProgram.setText(ind.getMessage());
				}
				catch(InvalidBoardDimensions ibd){
					atualStateOfProgram.setText(ibd.getMessage());
				}
			}
		});
		btnGenerateMaze.setBounds(350, 31, 217, 46);
		frmJogoDoLabirinto.getContentPane().add(btnGenerateMaze);

		atualStateOfProgram = new JLabel("");
		atualStateOfProgram.setForeground(Color.RED);
		atualStateOfProgram.setBounds(21, 97, 364, 14);
		frmJogoDoLabirinto.getContentPane().add(atualStateOfProgram);
	}	
}
