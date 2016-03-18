package maze.logic;

/**
 * Represents a dragon.
 */
public class Dragon extends CharacterOfGame{
	private boolean isSleeping;
	/**
	 * Constructor of Dragon.
	 * @param pos initial position of dragon.
	 * the dragon starts awake.
	 */
	public Dragon(Position pos) {
		super(pos);
		isSleeping = false;
	}

	/**
	 * Get symbol(represented by a char) of dragon.
	 * Return 'D' if hero is alive,'d' if is sleeping and ' ' if is dead.
	 */
	public char getSymbol(){
		if(isAlive())
			if(isSleeping)
				return 'd'; 
			else
				return 'D';
		else
			return ' ';
	}
	/**
	 *Puts a dragon to sleep.
	 */
	public void sleeps(){
		isSleeping = true;
	}
	/**
	 * Wakes up a dragon.
	 */
	public void wakeUp(){
		isSleeping = false;
	}
	/**
	 * 
	 * @return the state of a dragon(true if he is sleeping or false if he is awake)
	 */
	public boolean isSleeping(){
		return isSleeping;
	}
	/**
	 * 
	 * @return if the dragon is awake, or false if he is sleeping
	 */
	public boolean canMove(){
		if(isSleeping == false && isAlive == true) return true;
		else return false; 
	}
}


