package maze.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import maze.logic.Position.Direction;

/**
 * Represents the state of a game.
 */
@SuppressWarnings("serial")
public class GameState implements Serializable{
	/**
	 * Enumerate the difficulty of game:
	 * EASY - Dragon can´t move, MEDIUM - Dragon can move and sleep, HARD - Dragon can olny move
	 */
	public enum Dificulty {EASY, MEDIUM, HARD}
	private Dificulty dificulty;
	private Maze maze;
	private transient Hero hero;
	private transient Sword sword;
	private boolean isFinished;
	private transient ArrayList<Dragon> dragons; 

	/**
	 * Constructor of GameState.
	 * Initialize the board of maze and positions of elements of maze.
	 * @param boardOfMaze the board of game with elements inside.
	 * @param dificulty of game
	 */
	public GameState(char[][] boardOfMaze, Dificulty dificulty) {
		isFinished = false;
		this.dificulty = dificulty;
		initializeElementsOfGame(boardOfMaze);
		maze = new Maze(boardOfMaze);
	}
	
	/**
	 * Receive a board with elements inside, and search the hero, dragons and sword.
	 * After, initialize the elements with the positions found.
	 * @param boardOfMaze board received
	 */
	public void initializeElementsOfGame(char[][] boardOfMaze){
		dragons = new ArrayList<Dragon>();
		for(int i = 0;i < boardOfMaze.length;i++)
			for(int j = 0;j < boardOfMaze[0].length;j++){
				char square = boardOfMaze[i][j];
				switch(square){
				case 'H':
					hero = new Hero(new Position(i, j));
					break;
				case 'D':
					dragons.add(new Dragon(new Position(i, j)));
					break;
				case 'E':
					sword = new Sword(new Position(i, j));
					break;
				default:
					break;
				}
			}
		if(hero == null){
			hero = new Hero(new Position(0, 0));
			hero.isKilled();
		}
		if(sword == null){
			sword = new Sword(new Position(1, 1));
			sword.take();
		}
	}

	/**
	 * updates the state of game
	 */
	public void update(){

		if(heroFoundSword()){
			sword.take();
			hero.arm();
		}

		killDragonsOrHeroIfNecessary();

		if(dificulty.equals(Dificulty.MEDIUM))
			updateSleepingOfDragons();

		if(!dificulty.equals(Dificulty.EASY))
			updatePositionsOfDragons();

		killDragonsOrHeroIfNecessary();

		if(isGameFinished())
			isFinished = true;

	}
	
	private void killDragonsOrHeroIfNecessary(){
		for(Dragon dragon: dragons)
			if(heroIsNearToTheDragon(dragon) && dragon.isAlive())
				if(hero.isArmed()){
					dragon.isKilled();
					maze.setSquare(dragon.getPosition(), dragon.getSymbol());
				}
				else if(!dragon.isSleeping())
					hero.isKilled();	
	}

	private void updatePositionsOfDragons(){
		for(Dragon dragon: dragons)
			if(dragon.canMove()) 
				generateDragonNewMove(dragon);
	}

	private boolean heroIsNearToTheDragon(Dragon dragon){
		return hero.getPosition().positionsAreNearOfeachOther(dragon.getPosition());
	}

	private boolean heroFoundSword(){
		if(hero.getPosition().equals(sword.getPosition())) return true;
		else return false;
	}
	
	private void updateSleepingOfDragons(){
		Random r = new Random();
		for(Dragon dragon: dragons){
			if(dragon.isAlive()){
				int isSleeping = r.nextInt(3);
				if(isSleeping == 0)
					dragon.sleeps();
				else
					dragon.wakeUp();
				maze.setSquare(dragon.getPosition(), dragon.getSymbol());
			}
		}
	}
	
	private void generateDragonNewMove(Dragon dragon){
		Random r = new Random();
		int move;
		Direction dir = Direction.NONE;
		do{
			move = r.nextInt(5);
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
		}while(dragonMoveIsInvalid(dragon, dir));
		maze.setSquare(dragon.getPosition(), ' ');
		dragon.move(dir);
		maze.setSquare(dragon.getPosition(), dragon.getSymbol());
	}
	
	private boolean dragonMoveIsInvalid(Dragon dragon, Direction dir){
		Position dragonPos = dragon.getPosition();
		Position oldPos = dragonPos.clone();
		dragonPos.changePos(dir);
		char newSquare = maze.getSquare(dragonPos);
		if(newSquare == ' ' || dragonPos.equals(oldPos))
			return false;
		else
			return true;
	}
	
	private boolean isGameFinished(){
		if((aDragonWasKilled() && hero.getPosition().equals(maze.getExitPos()))
				|| !hero.isAlive())
			return true;
		return false;
	}
	
	/**
	 * Get hero.
	 * @return hero
	 */
	public Hero getHero(){
		return hero;
	}
	
	/**
	 * Get a ArrayList of dragons.
	 * @return dragons
	 */
	public ArrayList<Dragon> getDragons(){
		return dragons;
	}
	
	/**
	 * Get sword.
	 * @return sword
	 */
	public Sword getSword(){
		return sword;
	}

	/**
	 * Check if the game is finished.
	 * @return true if game is finished, false otherwise
	 */
	public boolean isFinished(){
		return isFinished;
	}

	/**
	 * get the game board.
	 * The game board is a maze with the elements represented.
	 * @return array bidimensional representing the board
	 */
	public char[][] getGameBoard(){
		char[][] gameBoard = maze.getBoard();
		if(!sword.wasTaken())
			gameBoard[sword.getPosition().getX()][sword.getPosition().getY()] = sword.getSymbol();
		for(Dragon dragon: dragons)
			if(dragon.getPosition().equals(sword.getPosition()) && !sword.wasTaken())
				gameBoard[dragon.getPosition().getX()][dragon.getPosition().getY()] = 'F';
		return gameBoard;
	}
	
	/**
	 * Get maze of game
	 * @return maze of game
	 */
	public Maze getMaze(){
		return maze;
	}

	/**
	 * Move hero one position in given direction.
	 * @param dir given direction
	 */
	public void moveHero(Direction dir){
		maze.setSquare(hero.getPosition(), ' ');
		hero.move(dir);
		maze.setSquare(hero.getPosition(), hero.getSymbol());
	}

	/**
	 * Check if the hero move is valid.
	 * @param dir direction of hero move
	 * @return true if the is valid, false otherwise.
	 */
	public boolean heroMoveIsValid(Direction dir){
		Position heroPos = hero.getPosition();
		heroPos.changePos(dir);
		char square = maze.getSquare(heroPos);
		if(square == 'X' || (square == 'S' && !aDragonWasKilled()))
			return false;
		else
			return true;
	}
	
	private boolean aDragonWasKilled(){
		for(Dragon dragon: dragons)
			if(!dragon.isAlive())
				return true;
		return false;
	}
}


