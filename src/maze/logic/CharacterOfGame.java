package maze.logic;

import maze.logic.Position.Direction;

/**
 * Represents a character of game.
 * A character of game is a living being: in this game, a hero and dragon.
 */
public class CharacterOfGame extends MazeElement{
	
	private boolean isAlive;
	
	/**
	 * Constructor of CharacterOfGame.
	 * @param pos initial position of character.
	 */
	public CharacterOfGame(Position pos) {
		super(pos);
		isAlive = true;
	}
	
	/**
	 * @return true if character is alive, false otherwise
	 */
	public boolean isAlive(){
		return isAlive;
	}
	
	/**
	 * Kill the character.
	 */
	public void kill(){
		isAlive = false;
	}
	
	/**
	 * Move the character.
	 * The possible moves are one position to the left, right, up or down.
	 * @param dir direction of move(left, right, up or down).
	 */
	public void move(Direction dir){
		position.changePos(dir);
	}

}
