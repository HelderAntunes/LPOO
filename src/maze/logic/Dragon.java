package maze.logic;

/**
 * Represents a dragon.
 */
public class Dragon extends CharacterOfGame{
	
	/**
	 * Constructor of Dragon.
	 * @param pos initial position of dragon.
	 */
	public Dragon(Position pos) {
		super(pos);
	}
	
	/**
	 * Get symbol(represented by a char) of dragon.
	 * Return 'D' if hero is alive, and ' ' if is dead.
	 */
	public char getSymbol(){
		if(isAlive())
			return 'D';
		else
			return ' ';
	}
}
