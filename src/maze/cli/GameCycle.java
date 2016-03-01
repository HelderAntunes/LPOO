package maze.cli;

import java.util.Arrays;
import java.util.Scanner;
import maze.logic.GameState;
import maze.logic.Position.Direction;
import maze.logic.GameState.Dificulty;
/**
 * Represents a cycle of game.
 */
public class GameCycle {
	
	/**
	 * Constructor of GameCycle.
	 * Contains the cycle of game.
	 */
	public GameCycle() {
		System.out.println("Selecione a dificuldade pretendida:");
		System.out.println("1 -> Fácil(dragão parado)");
		System.out.println("2 -> Médio(dragão com movimentação e pode adormecer)");
		System.out.println("3 -> Díficil(dragão com movimentação e sempre acordado)");
		Scanner s = new Scanner(System.in);
		Dificulty dificulty = Dificulty.EASY;
		int d=s.nextInt();
		switch(d){
		case 1:
			dificulty = Dificulty.EASY;
			break;
		case 2:	
			dificulty = Dificulty.MEDIUM;
			break;
		case 3:
			dificulty = Dificulty.HARD;
			break;
		}
		char[][] boardOfMaze = createBoard();
		GameState gamest = new GameState(boardOfMaze);
		
		while(gamest.isFinished() == false){
			char[][] gameBoard = gamest.getGameBoard();
			printBoard(gameBoard);
			
			int move;
			System.out.println("Movimento do her�i:");
			System.out.println("1 -> esquerda");
			System.out.println("2 -> direita");
			System.out.println("3 -> cima");
			System.out.println("4 -> baixo");
			System.out.print(">> ");
			move = s.nextInt();
			
			Direction dir = Direction.NONE;
			switch(move){
			case 1:
				dir = Direction.LEFT;
				break;
			case 2:
				dir = Direction.RIGHT;
				break;
			case 3:
				dir = Direction.UP;
				break;
			case 4:
				dir = Direction.DOWN;
				break;
			default:
				break;
			}

			if(gamest.heroMoveIsValid(dir)){
				gamest.moveHero(dir);
				gamest.update();
			}
			else{
				System.out.println("Movimenta��o inv�lida. Tente de novo...");
			}
		}
		
		if(gamest.heroIsAlive())
			System.out.println("Jogo terminou. O her�i consegui escapar!!!" );
		else
			System.out.println("Jogo terminou. O her�i morreu :(" );
		
		s.close();
	}
	
	/**
	 * Receives a game board as argument ad print it.
	 * @param gameBoard
	 */
	public void printBoard(char[][] gameBoard){
		for(int i=0;i<gameBoard.length;i++)
			for(int j=0;j<gameBoard.length;j++)
				if(j==gameBoard.length-1)
					System.out.println(gameBoard[i][j]);
				else {
					System.out.print(gameBoard[i][j]);
					System.out.print(" ");
				}
	}
	
	/**
	 * Create the board of maze.
	 * @return the board of maze
	 */
	public static char[][] createBoard(){
		char[][] board=new char[10][10];
		char parede='X';
		char branco=' ';
		char saida='S';
		// set blank
		for(int i=1;i<9;i++)
			Arrays.fill(board[i], branco);

		// set walls
		for(int j=0;j<10;j++){
			board[0][j]=parede;
			board[9][j]=parede;
			board[j][0]=parede;
			board[j][9]=parede;
		}
		for(int p=2;p<9;p++)
			for(int l=2;l<4;l++){
				if(p==5)
					continue;
				else
					board[p][l]=parede;
			}
		for(int m=2;m<8;m++){
			if(m==5)
				continue;
			else
				board[m][5]=parede;
		}
		for(int n=2;n<8;n++){
			board[n][7]=parede;
		}
		
		board[5][9] = saida;

		return board;
	}
}
