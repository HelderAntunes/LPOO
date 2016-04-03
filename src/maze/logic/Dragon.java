package maze.logic;

public class Dragon extends CharacterOfGame{
	private boolean isSleeping;
	
	public Dragon(Position pos) {
		super(pos);
		isSleeping = false;
	}
	
	/**
	 * Get char representation of dragon.
	 * @return 'd' if is sleeping, 'D' if is awake, an ' ' if is dead.
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

	public void sleeps(){
		isSleeping = true;
	}

	public void wakeUp(){
		isSleeping = false;
	}
	
	/**
	 * @return true if dragon is sleeping, false otherwise.
	 */
	public boolean isSleeping(){
		return isSleeping;
	}

	public boolean canMove(){
		if(isSleeping == false && isAlive == true) return true;
		else return false; 
	}
}


