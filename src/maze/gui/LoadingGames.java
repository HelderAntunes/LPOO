package maze.gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import maze.logic.GameState;


public class LoadingGames {
	ArrayList<GameState> savedGames = new ArrayList<GameState>();

	private JFrame frame;
	JButton btnLoadGame;

	/**
	 * Create the application.
	 */
	public LoadingGames() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnLoadGame = new JButton("Carregar jogo");
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObjectInputStream is = null;
				try {
					is = new ObjectInputStream(new FileInputStream("file.dat"));
					int totalGamesSaved = is.readInt();
					for(int i = 0;i < totalGamesSaved;i++){
						GameState gamest = (GameState) is.readObject();
						gamest.initializeElementsOfGame(gamest.getMaze().getBoard());
						savedGames.add(gamest);
					}
					frame.dispose();
					new GameMaze(savedGames.get(0));
				}
				catch (IOException | ClassNotFoundException e1) {

				}
				finally { if (is != null)
					try {
						is.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					} }

			}
		});
		btnLoadGame.setBounds(150, 73, 122, 44);
		frame.getContentPane().add(btnLoadGame);
	}

}
