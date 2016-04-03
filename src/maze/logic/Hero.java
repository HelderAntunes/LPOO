package maze.logic;

public class Hero extends CharacterOfGame{
	private boolean isArmed;
	
	public Hero(Position pos){
		super(pos);
		isArmed = false;
	}
	
	/**
	 * @return true if hero is armed, false otherwise.
	 */
	public boolean isArmed(){
		return isArmed;
	}
	
	public void arm(){
		isArmed = true;
	}
	
	/**
	 * Get char representation of hero
	 * @return 'A' if is armed, 'H' if is disarmed, and ' ' if is dead.
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
