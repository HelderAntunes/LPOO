package maze.logic;

/**
 * Represents a sword.
 */
public class Sword extends MazeElement{
	
	private boolean isTaken;
	
	/**
	 * Constructor of Sword.
	 * Initially the sword is not taken.
	 * @param pos initial position of sword.
	 */
	public Sword(Position pos) {
		super(pos);
		isTaken = false;
	}
	
	/**
	 * Take the sword.
	 * When the hero is in the same position of sword, the sword is taken.
	 */
	public void take(){
		isTaken = true;
	}
	
	/**
	 * @return true if sword was taken, false otherwise.
	 */
	public boolean wasTaken(){
		return isTaken;
	}
	
	/**
	 * Get symbol(represented by a char) of sword.
	 * Return ' ' if sword was taken, 'E' otherwise.
	 */
	public char getSymbol(){
		if(isTaken)
			return ' ';
		else
			return 'E';
	}
}
