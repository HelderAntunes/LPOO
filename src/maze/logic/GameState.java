package maze.logic;

import java.lang.Math;
import java.util.Random;

import maze.logic.Position.Direction;

/**
 * Represents the state of a game.
 */
public class GameState {
	public enum Dificulty {EASY, MEDIUM, HARD}
	private Dificulty dificulty;
	private Maze maze;
	private Hero hero;
	private Dragon dragon;
	private Sword sword;
	private boolean isFinished;

	/**
	 * Constructor of GameState.
	 * Initialize the board of maze and positions of elements of maze.
	 * @param boardOfMaze
	 */
	public GameState(char[][] boardOfMaze, Dificulty dificulty) {
		initializeMazeAndThePositionsOfElements(boardOfMaze);
		isFinished = false;
		this.dificulty = dificulty;
	}

	private void initializeMazeAndThePositionsOfElements(char[][] boardOfMaze){
		for(int i = 0;i < boardOfMaze.length;i++)
			for(int j = 0;j < boardOfMaze[0].length;j++){
				char square = boardOfMaze[i][j];
				switch(square){
				case 'H':
					hero = new Hero(new Position(i, j));
					break;
				case 'D':
					dragon = new Dragon(new Position(i, j));
					break;
				case 'E':
					sword = new Sword(new Position(i, j));
					break;
				default:
					break;
				}
				if(square != ' ' && square != 'X' && square != 'S')
					boardOfMaze[i][j] = ' ';
			}
		maze = new Maze(boardOfMaze);
	}


	/**
	 * updates the state of game. 
	 */
	public void update(){

		if(heroIsNearToTheDragon())
			if(hero.isArmed())
				dragon.kill();
			else{
				if(!dragon.isSleeping())
					hero.kill();
			}

		if(heroFoundSword()){
			sword.take();
			hero.arm();
		}

		if(dificulty.equals(Dificulty.MEDIUM))
			updateSleepingOfDragon();

		if(dragonCanMove())
			generateDragonNewMove();

		finishGameIfGameIsFinish();
	}

	/**
	 * Check if hero is near of dragon.
	 * @return true if the distance between the hero and dragon is 
	 * equal or less than 1 in horizontal or vertical direction
	 */
	private boolean heroIsNearToTheDragon(){
		int xH = hero.getPosition().getX();
		int yH = hero.getPosition().getY();
		int xD = dragon.getPosition().getX();
		int yD = dragon.getPosition().getY();

		if(xH == xD && Math.abs(yH-yD) <= 1)
			return true;
		if(yH == yD && Math.abs(xH-xD) <= 1)
			return true;

		return false;
	}

	/**
	 * Check if hero found the sword.
	 * @return true if the hero found the sword, false otherwise
	 */
	private boolean heroFoundSword(){
		if(hero.getPosition().equals(sword.getPosition()))
			return true;
		return false;
	}

	private void updateSleepingOfDragon(){
		Random r = new Random();
		int isSleeping = r.nextInt(2);
		if(isSleeping == 1)
			dragon.sleeps();
		else
			dragon.wakeUp();
	}

	private boolean dragonCanMove(){
		if(!dragon.isSleeping() && !dificulty.equals(Dificulty.EASY) && dragon.isAlive())
			return true;
		else
			return false;
	}

	private void generateDragonNewMove(){
		Random r = new Random();
		int move = r.nextInt(5);
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

		if(dragonMoveIsValid(dir))
			moveDragon(dir);
	}

	private boolean dragonMoveIsValid(Direction dir){
		Position dragonPos = dragon.getPosition();
		dragonPos.changePos(dir);
		char squareOfNewPos = maze.getSquare(dragonPos);
		if(squareOfNewPos == 'X' || squareOfNewPos == 'S')
			return false;
		else
			return true;
	}

	private void moveDragon(Direction dir){
		dragon.move(dir);
	}

	private void finishGameIfGameIsFinish(){
		if(!dragon.isAlive() && hero.getPosition().equals(maze.getExitPos())
				&& hero.isArmed()){
			isFinished = true;	
		}
		if(!hero.isAlive()){
			isFinished = true;
		}
	}


	/**
	 * Get Hero.
	 * @return hero
	 */
	public Hero getHero(){
		return hero;
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
		if(hero.getSymbol() != ' ')
			gameBoard[hero.getPosition().getX()][hero.getPosition().getY()] = hero.getSymbol();

		if(dragon.getPosition().equals(sword.getPosition()))
			gameBoard[dragon.getPosition().getX()][dragon.getPosition().getY()] = 'F';
		else{
			if(dragon.getSymbol() != ' ')
				gameBoard[dragon.getPosition().getX()][dragon.getPosition().getY()] = dragon.getSymbol();
			if(sword.getSymbol() != ' ')
				gameBoard[sword.getPosition().getX()][sword.getPosition().getY()] = sword.getSymbol();
		}

		return gameBoard.clone();
	}

	/**
	 * Move hero in given direction.
	 * @param dir given direction
	 */
	public void moveHero(Direction dir){
		hero.move(dir);
	}

	/**
	 * Check if the hero move is valid.
	 * @param dir direction of hero move
	 * @return true if the is valid, false otherwise.
	 */
	public boolean heroMoveIsValid(Direction dir){
		Position heroPos = hero.getPosition();
		heroPos.changePos(dir);
		char squareOfNewPos = maze.getSquare(heroPos);
		if(squareOfNewPos == 'X' || (squareOfNewPos == 'S' && dragon.isAlive()))
			return false;
		else
			return true;
	}
}


