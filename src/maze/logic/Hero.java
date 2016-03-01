package maze.logic;

/**
 * Represents a hero.
 */
public class Hero extends CharacterOfGame{
	private boolean isArmed;
	
	/**
	 * Constructor of Hero.
	 * @param pos initial position of hero.
	 */
	public Hero(Position pos){
		super(pos);
	}
	
	/**
	 * To find out if the hero is armed.
	 * @return true if hero is armed, false otherwise
	 */
	public boolean isArmed(){
		return isArmed;
	}
	
	/**
	 * Arm the hero.
	 */
	public void arm(){
		isArmed = true;
	}
	
	/**
	 * Get symbol(represented by a char) of hero.
	 * Return 'A' if hero is alive and armed, 'H' if is just alive, and ' ' if is dead.
	 */
	public char getSymbol(){
		if(isAlive())
			if(isArmed)
				return 'A';
			else
				return 'H';
		else
			return ' ';
	}
}
