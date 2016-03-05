package maze.cli;

import java.util.Scanner;
import maze.logic.GameState;
import maze.logic.Position.Direction;
import maze.logic.GameState.Dificulty;
import maze.logic.MazeBuilder;

/**
 * Represents a cycle of game.
 */
public class GameCycle {

	/**
	 * Constructor of GameCycle.
	 * Contains the cycle of game.
	 */
	public GameCycle() {
		Scanner s = new Scanner(System.in);

		GameState gamest = prepareGame(s);

		while(gamest.isFinished() == false)
			playGameRound(gamest, s);

		exitGame(gamest);

		s.close();
	}
	
	public GameState prepareGame(Scanner s){
		Dificulty dificulty = chooseDificulty(s);
		int n = chooseBoardDimensions(s);
		char[][] boardOfMaze = new MazeBuilder().getRandomMaze(n);
		GameState gamest = new GameState(boardOfMaze, dificulty);
		return gamest;
	}
	
	public Dificulty chooseDificulty(Scanner s){
		Dificulty dificulty = null;

		while(dificulty == null){
			System.out.println("Selecione a dificuldade pretendida:");
			System.out.println("1 -> Facil(dragao parado)");
			System.out.println("2 -> Medio(dragao com movimentao e pode adormecer)");
			System.out.println("3 -> Dificil(dragao com movimentacao e sempre acordado)");
			System.out.print(">> ");

			int d = s.nextInt();
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
			default:
				System.out.println("Opcao invalida!!!");
				break;
			}
		}
		return dificulty;
	}
	
	public int chooseBoardDimensions(Scanner s){
		System.out.print("Escolha as dimensoes do tabuleiro N x N, em que N é ímpar e N >= 7: ");
		int n;
		while(true){
			if(s.hasNextInt()){
				n = s.nextInt();
				if(n%2 != 0) break;
				else System.out.print("Escolha inválida, tente de novo: ");	
			}
			else System.out.print("Escolha inválida, tente de novo: ");		
		}
		return n;
	}
	
	public void playGameRound(GameState gamest, Scanner s){
		char[][] gameBoard = gamest.getGameBoard();
		printBoard(gameBoard);

		Direction dirMovimentHero = chooseHeroMoviment(s);

		if(gamest.heroMoveIsValid(dirMovimentHero)){
			gamest.moveHero(dirMovimentHero);
			gamest.update();
		}
		else
			System.out.println("Movimentacao invalida. Tente de novo...");
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
	
	public Direction chooseHeroMoviment(Scanner s){
		int move;
		Direction dir = null;

		System.out.println("Movimento do heroi:");
		System.out.println("1 -> esquerda");
		System.out.println("2 -> direita");
		System.out.println("3 -> cima");
		System.out.println("4 -> baixo");

		while(dir == null){
			System.out.print(">> ");
			move = s.nextInt();
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
				System.out.println("Opcao invalida");
				break;
			}
		}
		return dir;
	}

	public void exitGame(GameState gamest){
		if(gamest.getHero().isAlive())
			System.out.println("Jogo terminou. O heroi consegui escapar!!!" );
		else
			System.out.println("Jogo terminou. O heroi morreu :(" );
	}
}
