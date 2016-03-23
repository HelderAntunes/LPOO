package maze.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import maze.logic.Position.Direction;

/**
 * Represents the state of a game.
 */
@SuppressWarnings("serial")
public class GameState implements Serializable{
	private Date date = new Date();
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
	 * @param boardOfMaze
	 */
	public GameState(char[][] boardOfMaze, Dificulty dificulty) {
		isFinished = false;
		this.dificulty = dificulty;
		initializeElementsOfGame(boardOfMaze);
		maze = new Maze(boardOfMaze);
	}

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
	 * updates the state of game. 
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
	/**
	 * Kills the a dragon if he is near to the armed hero, ou kills the hero if the hero is disarmed.
	 */
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

	/**
	 * Check if hero is near of dragon.
	 * @return true if the distance between the hero and dragon is 
	 * equal or less than 1 in horizontal or vertical direction
	 */
	private boolean heroIsNearToTheDragon(Dragon dragon){
		return hero.getPosition().positionsAreNearOfeachOther(dragon.getPosition());
	}

	/**
	 * Check if hero found the sword.
	 * @return true if the hero found the sword, false otherwise
	 */
	private boolean heroFoundSword(){
		if(hero.getPosition().equals(sword.getPosition())) return true;
		else return false;
	}
	/**
	 * Updates the state of a dragon(sleep or wake up).
	 */
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
	/**
	 * 
	 * @param dragon.
	 * Generates a random movement of the dragon.
	 * 
	 */
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
	/**
	 * 
	 * @param dragon.
	 * @param dir.
	 * @return false if the movement is valid,or true if invalid
	 */
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
	/**
	 * 
	 * @return true if the game is finished or false if not
	 */
	private boolean isGameFinished(){
		if((aDragonWasKilled() && hero.getPosition().equals(maze.getExitPos()))
				|| !hero.isAlive())
			return true;
		return false;
	}
	/**
	 * 
	 * @return the hero
	 */
	public Hero getHero(){
		return hero;
	}
	/**
	 * 
	 * @return an ArrayList that contains the dragons
	 */
	public ArrayList<Dragon> getDragons(){
		return dragons;
	}
	/**
	 * 
	 * @return the sword
	 */
	public Sword getSword(){
		return sword;
	}

	/**
	 * Check if the game is finished.
	 * @return true if game is finished, false other wise
	 */
	public boolean isFinished(){
		return isFinished;
	}


	/**
	 * get the game board.
	 * The game board is a maze with the elements represented.
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
	 * @return maze of game
	 */
	public Maze getMaze(){
		return maze;
	}

	/**
	 * Move hero in given direction.
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
	/**
	 * 
	 * @return true if the dragon is dead or false if is still alive
	 */
	private boolean aDragonWasKilled(){
		for(Dragon dragon: dragons)
			if(!dragon.isAlive())
				return true;
		return false;
	}
	/**
	 * returns the maze as a string.
	 */
	public String toString(){
		String s = "";
		char [][] gameBoard = getGameBoard();
		for(int i=0;i<gameBoard.length;i++)
			for(int j=0;j<gameBoard.length;j++)
				if(j==gameBoard.length-1)
					s = s + gameBoard[i][j] + "\n";
				else 
					s = s + gameBoard[i][j] + " ";
		return s;
	}

	public Date getDate() {
		return date;
	}
}


