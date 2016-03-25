package maze.gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import maze.logic.GameState;
import javax.swing.JLabel;

public class LoadingGames {

	private JFrame frame;
	private JButton btnLoadGame1;
	private JButton btnLoadGame2;
	private JButton btnLoadGame3;
	private JButton btnLoadGame4;
	private JButton btnLoadGame5;
	private JButton btnNextGames;
	private JButton btnPreviousGames;
	private JButton btnEraseGame1;
	private JButton btnEraseGame2;
	private JButton btnEraseGame3;
	private JButton btnEraseGame4;
	private JButton btnEraseGame5;
	private JLabel lblGame1;
	private JLabel lblGame2;
	private JLabel lblGame3;
	private JLabel lblGame4;
	private JLabel lblGame5;

	ArrayList<GameState> savedGames;
	private int atualPage = 1;

	public LoadingGames() {
		savedGames = new Utilities().readGames();
		initialize();
		updateStateOfButtonsAndLabels();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 527, 388);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnLoadGame1 = new JButton("Carregar jogo");
		btnLoadGame1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new GameMaze(savedGames.get((atualPage-1)*5 + 0));
			}});
		btnLoadGame1.setBounds(229, 39, 124, 23);
		frame.getContentPane().add(btnLoadGame1);

		btnLoadGame2 = new JButton("Carregar jogo");
		btnLoadGame2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new GameMaze(savedGames.get((atualPage-1)*5 + 1));
			}
		});
		btnLoadGame2.setBounds(229, 88, 124, 23);
		frame.getContentPane().add(btnLoadGame2);

		btnLoadGame3 = new JButton("Carregar jogo");
		btnLoadGame3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new GameMaze(savedGames.get((atualPage-1)*5 + 2));
			}
		});
		btnLoadGame3.setBounds(229, 137, 124, 23);
		frame.getContentPane().add(btnLoadGame3);

		btnLoadGame4 = new JButton("Carregar jogo");
		btnLoadGame4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new GameMaze(savedGames.get((atualPage-1)*5 + 3));
			}
		});
		btnLoadGame4.setBounds(229, 189, 124, 23);
		frame.getContentPane().add(btnLoadGame4);

		btnLoadGame5 = new JButton("Carregar jogo");
		btnLoadGame5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new GameMaze(savedGames.get((atualPage-1)*5 + 4));
			}
		});
		btnLoadGame5.setBounds(229, 245, 124, 23);
		frame.getContentPane().add(btnLoadGame5);

		btnEraseGame1 = new JButton("Eliminar jogo");
		btnEraseGame1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savedGames.remove((atualPage-1)*5 + 0);
				updateStateOfButtonsAndLabels();
				new Utilities().saveGames(savedGames);
			}
		});
		btnEraseGame1.setBounds(377, 39, 124, 23);
		frame.getContentPane().add(btnEraseGame1);

		btnEraseGame2 = new JButton("Eliminar jogo");
		btnEraseGame2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savedGames.remove((atualPage-1)*5 + 1);
				updateStateOfButtonsAndLabels();
				new Utilities().saveGames(savedGames);
			}
		});
		btnEraseGame2.setBounds(377, 88, 124, 23);
		frame.getContentPane().add(btnEraseGame2);

		btnEraseGame3 = new JButton("Eliminar jogo");
		btnEraseGame3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savedGames.remove((atualPage-1)*5 + 2);
				updateStateOfButtonsAndLabels();
				new Utilities().saveGames(savedGames);
			}
		});
		btnEraseGame3.setBounds(377, 137, 124, 23);
		frame.getContentPane().add(btnEraseGame3);

		btnEraseGame4 = new JButton("Eliminar jogo");
		btnEraseGame4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				savedGames.remove((atualPage-1)*5 + 3);
				updateStateOfButtonsAndLabels();
				new Utilities().saveGames(savedGames);
			}
		});
		btnEraseGame4.setBounds(377, 189, 124, 23);
		frame.getContentPane().add(btnEraseGame4);

		btnEraseGame5 = new JButton("Eliminar jogo");
		btnEraseGame5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savedGames.remove((atualPage-1)*5 + 4);
				updateStateOfButtonsAndLabels();
				new Utilities().saveGames(savedGames);
			}
		});
		btnEraseGame5.setBounds(377, 245, 124, 23);
		frame.getContentPane().add(btnEraseGame5);

		lblGame1 = new JLabel("Jogo inexistente");
		lblGame1.setBounds(52, 43, 141, 14);
		frame.getContentPane().add(lblGame1);

		lblGame2 = new JLabel("Jogo inexistente");
		lblGame2.setBounds(52, 92, 141, 14);
		frame.getContentPane().add(lblGame2);

		lblGame3 = new JLabel("Jogo inexistente");
		lblGame3.setBounds(52, 141, 141, 14);
		frame.getContentPane().add(lblGame3);

		lblGame4 = new JLabel("Jogo inexistente");
		lblGame4.setBounds(52, 193, 141, 14);
		frame.getContentPane().add(lblGame4);

		lblGame5 = new JLabel("Jogo inexistente");
		lblGame5.setBounds(52, 249, 141, 14);
		frame.getContentPane().add(lblGame5);

		btnNextGames = new JButton("Pr\u00F3ximos jogos");
		btnNextGames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnPreviousGames.setEnabled(true);
				atualPage++;
				updateStateOfButtonsAndLabels();
			}
		});
		btnNextGames.setBounds(285, 315, 141, 23);
		frame.getContentPane().add(btnNextGames);

		btnPreviousGames = new JButton("Jogos anteriores");
		btnPreviousGames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualPage--;
				updateStateOfButtonsAndLabels();
			}
		});
		btnPreviousGames.setBounds(104, 315, 118, 23);
		frame.getContentPane().add(btnPreviousGames);
	}

	private JLabel getLblGame(int i){
		switch(i){
		case 1:
			return lblGame1;
		case 2:
			return lblGame2;
		case 3:
			return lblGame3;
		case 4:
			return lblGame4;
		case 5:
			return lblGame5;
		default:
			return null;
		}
	}

	private JButton getBtnLoadGame(int i){
		switch(i){
		case 1:
			return btnLoadGame1;
		case 2:
			return btnLoadGame2;
		case 3:
			return btnLoadGame3;
		case 4:
			return btnLoadGame4;
		case 5:
			return btnLoadGame5;
		default:
			return null;
		}
	}

	private JButton getBtnEraseGame(int i){
		switch(i){
		case 1:
			return btnEraseGame1;
		case 2:
			return btnEraseGame2;
		case 3:
			return btnEraseGame3;
		case 4:
			return btnEraseGame4;
		case 5:
			return btnEraseGame5;
		default:
			return null;
		}
	}

	private void updateStateOfButtonsAndLabels(){
		// update labels
		for(int i = 1;i <= 5;i++){
			if(i+(atualPage-1)*5 <= savedGames.size())
				getLblGame(i).setText("jogo " + ((atualPage-1)*5 + i));
			else
				getLblGame(i).setText("Jogo inexistente");
		}
		// update next and previous buttons
		if(atualPage == 1)
			btnPreviousGames.setEnabled(false);
		if(savedGames.size() <= 5*atualPage)
			btnNextGames.setEnabled(false);
		else
			btnNextGames.setEnabled(true);
		// update buttons of load and erase games
		for(int i = 1;i <= 5;i++)
			if(((atualPage-1)*5 + i) > savedGames.size()){
				getBtnLoadGame(i).setEnabled(false);
				getBtnEraseGame(i).setEnabled(false);
			}
			else{
				getBtnLoadGame(i).setEnabled(true);
				getBtnEraseGame(i).setEnabled(true);
			}
	}
}