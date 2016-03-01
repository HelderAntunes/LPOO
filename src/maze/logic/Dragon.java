package maze.logic;

/**
 * Represents a dragon.
 */
public class Dragon extends CharacterOfGame{
	private boolean isInSamePosOfSword;
	private boolean isSleeping;
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
		if(isAlive()){
			if(isInSamePosOfSword)
				return 'F';
			else{
				if(isSleeping)
					return 'd';
				else
					return 'D';
			}
		}
		else
			return ' ';
	}
	
	public void setEqualsPosSword(boolean isInSamePos){
		isInSamePosOfSword = isInSamePos;
	}
	
	public void sleeps(){
		isSleeping=true;
	}
	
	public void wakeUp(){
		isSleeping=false;
	}
	
	public boolean isSleeping(){
		return isSleeping;
	}
	
	
}

	
