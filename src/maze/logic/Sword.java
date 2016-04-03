package maze.logic;

public class Sword extends MazeElement{

	private boolean isTaken;;

	public Sword(Position pos) {
		super(pos);
		isTaken = false;
	}

	public void take(){
		isTaken = true;
	}
	
	/**
	 * @return true if a Sword was taken
	 */
	public boolean wasTaken(){
		return isTaken;
	}
	
	/**
	 * Get the char representation of sword.
	 * @return ' ' if is taken, 'E' otherwise
	 */
	public char getSymbol(){
		if(isTaken)
			return ' ';
		else
			return 'E';
	}
	
	
}
