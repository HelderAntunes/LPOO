package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 433);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnJogarComLabirinto = new JButton("Jogar com labirinto aleat\u00F3rio");
		btnJogarComLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new RandomMaze();
			}
		});
		btnJogarComLabirinto.setBounds(133, 94, 211, 33);
		frame.getContentPane().add(btnJogarComLabirinto);
		
		JButton btnJogarComLabirinto_1 = new JButton("Jogar com labirinto manual");
		btnJogarComLabirinto_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManualMaze();
			}
		});
		btnJogarComLabirinto_1.setBounds(133, 187, 211, 33);
		frame.getContentPane().add(btnJogarComLabirinto_1);
		
		JButton btnTerminarPrograma = new JButton("Terminar programa");
		btnTerminarPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnTerminarPrograma.setBounds(133, 265, 211, 33);
		frame.getContentPane().add(btnTerminarPrograma);
		
		JLabel lblJogoDoLabrinto = new JLabel("       Jogo do labirinto");
		lblJogoDoLabrinto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblJogoDoLabrinto.setBounds(135, 27, 196, 21);
		frame.getContentPane().add(lblJogoDoLabrinto);
	}
}
