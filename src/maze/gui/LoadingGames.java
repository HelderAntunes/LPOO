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
import javax.swing.JLabel;


public class LoadingGames {
	ArrayList<GameState> savedGames = new ArrayList<GameState>();

	private JFrame frame;
	private JButton btnLoadGame1;
	private JButton btnLoadGame2;
	private JButton btnLoadGame3;
	private JButton btnLoadGame4;
	private JButton btnLoadGame5;
	private JButton btnNextGames;
	private JButton btnPreviousGames;
	private JLabel lblGame1;
	private JLabel lblGame2;
	private JLabel lblGame3;
	private JLabel lblGame4;
	private JLabel lblGame5;

	private int atualPage = 1;

	/**
	 * Create the application.
	 */
	public LoadingGames() {
		readGames();
		initialize();
		btnPreviousGames.setEnabled(false);
		if(savedGames.size() <= 5)
			btnNextGames.setEnabled(false);
		for(int i = 1;i <= savedGames.size() && i <= 5;i++){
			if(i == 1)
				lblGame1.setText("jogo " + i);
			else if(i == 2)
				lblGame2.setText("jogo " + i);
			else if(i == 3)
				lblGame3.setText("jogo " + i);
			else if(i == 4)
				lblGame4.setText("jogo " + i);
			else if(i == 5)
				lblGame5.setText("jogo " + i);
		}
		updateButtonsOfLoadGames();
		frame.setVisible(true);
	}

	private void updateButtonsOfLoadGames(){
		for(int i = 1;i <= 5;i++){
			if(i == 1)
				if(((atualPage-1)*5 + 0) >= savedGames.size())
					btnLoadGame1.setEnabled(false);
				else
					btnLoadGame1.setEnabled(true);
			else if(i == 2) 
				if(((atualPage-1)*5 + 1) >= savedGames.size())
					btnLoadGame2.setEnabled(false);
				else
					btnLoadGame2.setEnabled(true);
			else if(i == 3) 
				if(((atualPage-1)*5 + 2) >= savedGames.size())
					btnLoadGame3.setEnabled(false);
				else
					btnLoadGame3.setEnabled(true);
			else if(i == 4) 
				if(((atualPage-1)*5 + 3) >= savedGames.size())
					btnLoadGame4.setEnabled(false);
				else
					btnLoadGame4.setEnabled(true);
			else if(i == 5) 
				if(((atualPage-1)*5 + 4) >= savedGames.size())
					btnLoadGame5.setEnabled(false);
				else
					btnLoadGame5.setEnabled(true);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 527, 388);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnLoadGame1 = new JButton("Carregar jogo");
		btnLoadGame1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(((atualPage-1)*5 + 0) >= savedGames.size()){
					btnLoadGame1.setEnabled(false);
				}
				frame.dispose();
				new GameMaze(savedGames.get((atualPage-1)*5 + 0));
			}});
		btnLoadGame1.setBounds(302, 39, 124, 23);
		frame.getContentPane().add(btnLoadGame1);

		btnLoadGame2 = new JButton("Carregar jogo");
		btnLoadGame2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new GameMaze(savedGames.get((atualPage-1)*5 + 1));
			}
		});
		btnLoadGame2.setBounds(302, 88, 124, 23);
		frame.getContentPane().add(btnLoadGame2);

		btnLoadGame3 = new JButton("Carregar jogo");
		btnLoadGame3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new GameMaze(savedGames.get((atualPage-1)*5 + 2));
			}
		});
		btnLoadGame3.setBounds(302, 137, 124, 23);
		frame.getContentPane().add(btnLoadGame3);

		btnLoadGame4 = new JButton("Carregar jogo");
		btnLoadGame4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new GameMaze(savedGames.get((atualPage-1)*5 + 3));
			}
		});
		btnLoadGame4.setBounds(302, 189, 124, 23);
		frame.getContentPane().add(btnLoadGame4);

		btnLoadGame5 = new JButton("Carregar jogo");
		btnLoadGame5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new GameMaze(savedGames.get((atualPage-1)*5 + 4));
			}
		});
		btnLoadGame5.setBounds(302, 245, 124, 23);
		frame.getContentPane().add(btnLoadGame5);

		lblGame1 = new JLabel("Jogo inexistente");
		lblGame1.setBounds(104, 43, 91, 14);
		frame.getContentPane().add(lblGame1);

		lblGame2 = new JLabel("Jogo inexistente");
		lblGame2.setBounds(104, 92, 91, 14);
		frame.getContentPane().add(lblGame2);

		lblGame3 = new JLabel("Jogo inexistente");
		lblGame3.setBounds(104, 141, 91, 14);
		frame.getContentPane().add(lblGame3);

		lblGame4 = new JLabel("Jogo inexistente");
		lblGame4.setBounds(104, 193, 91, 14);
		frame.getContentPane().add(lblGame4);

		lblGame5 = new JLabel("Jogo inexistente");
		lblGame5.setBounds(104, 249, 91, 14);
		frame.getContentPane().add(lblGame5);

		btnNextGames = new JButton("Pr\u00F3ximos jogos");
		btnNextGames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnPreviousGames.setEnabled(true);
				atualPage++;
				for(int i = 0;i+(atualPage-1)*5 < savedGames.size() && i < 5;i++){
					if(i == 0)
						lblGame1.setText("jogo " + ((atualPage-1)*5 + 1));
					else if(i == 1)
						lblGame2.setText("jogo " + ((atualPage-1)*5 + 2));
					else if(i == 2)
						lblGame3.setText("jogo " + ((atualPage-1)*5 + 3));
					else if(i == 3)
						lblGame4.setText("jogo " + ((atualPage-1)*5 + 4));
					else if(i == 4)
						lblGame5.setText("jogo " + ((atualPage-1)*5 + 5));

					if(i+(atualPage-1)*5 == savedGames.size()-1){
						for(int j = i+1;j < 5;j++){
							if(j == 0)
								lblGame1.setText("Jogo inexistente");
							else if(j == 1)
								lblGame2.setText("Jogo inexistente");
							else if(j == 2)
								lblGame3.setText("Jogo inexistente");
							else if(j == 3)
								lblGame4.setText("Jogo inexistente");
							else if(j == 4)
								lblGame5.setText("Jogo inexistente");
						}
					}
				}
				if(savedGames.size() <= 5*atualPage)
					btnNextGames.setEnabled(false);
				updateButtonsOfLoadGames();
			}
		});
		btnNextGames.setBounds(285, 315, 141, 23);
		frame.getContentPane().add(btnNextGames);

		btnPreviousGames = new JButton("Jogos anteriores");
		btnPreviousGames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualPage--;
				for(int i = 0;i+(atualPage-1)*5 < savedGames.size() && i < 5;i++){
					if(i == 0)
						lblGame1.setText("jogo " + ((atualPage-1)*5 + 1));
					else if(i == 1)
						lblGame2.setText("jogo " + ((atualPage-1)*5 + 2));
					else if(i == 2)
						lblGame3.setText("jogo " + ((atualPage-1)*5 + 3));
					else if(i == 3)
						lblGame4.setText("jogo " + ((atualPage-1)*5 + 4));
					else if(i == 4)
						lblGame5.setText("jogo " + ((atualPage-1)*5 + 5));

					if(i+(atualPage-1)*5 == savedGames.size()-1){
						for(int j = i+1;j < 5;j++){
							if(j == 0)
								lblGame1.setText("Jogo inexistente");
							else if(j == 1)
								lblGame2.setText("Jogo inexistente");
							else if(j == 2)
								lblGame3.setText("Jogo inexistente");
							else if(j == 3)
								lblGame4.setText("Jogo inexistente");
							else if(j == 4)
								lblGame5.setText("Jogo inexistente");
						}
					}
				}
				if(atualPage == 1)
					btnPreviousGames.setEnabled(false);
				if(savedGames.size() <= 5*atualPage)
					btnNextGames.setEnabled(false);
				else
					btnNextGames.setEnabled(true);
				updateButtonsOfLoadGames();
			}
		});
		btnPreviousGames.setBounds(104, 315, 118, 23);
		frame.getContentPane().add(btnPreviousGames);
	}

	private void readGames(){
		ObjectInputStream is = null;
		try {
			is = new ObjectInputStream(new FileInputStream("file.dat"));
			int totalGamesSaved = is.readInt();
			for(int i = 0;i < totalGamesSaved;i++){
				GameState game = (GameState) is.readObject();
				game.initializeElementsOfGame(game.getMaze().getBoard());
				savedGames.add(game);
			}
		}
		catch (IOException | ClassNotFoundException e1) {
			System.out.println("erro ao ler");
		}
		finally { if (is != null)
			try {
				is.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			} }
	}
}
