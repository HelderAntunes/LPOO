package maze.logic;

/**
 * Represents the position of an element of maze.
 */
public class Position {
	
	/**
	 * Represents the four possibles directions of a move.
	 */
	public enum Direction {LEFT, RIGHT, DOWN, UP, NONE}

	private int x;
	private int y;
	
	/**
	 * Constructor of Position
	 * @param x a column of maze
	 * @param y a row of maze
	 */
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public Position clone(){
		return new Position(x,y);
	}
	
	public boolean equals(Object p2){
		if(x == ((Position)p2).getX() && y == ((Position)p2).getY())
			return true;
		return false;
	}
	
	/**
	 * Change the coordinates of the position.
	 * Can be a left, right, down or up move.
	 * @param dir direction of move
	 */
	public void changePos(Direction dir) {
		switch(dir){
		case LEFT:
			y -= 1;
			break;
		case RIGHT:
			y += 1;
			break;
		case DOWN:
			x += 1;
			break;
		case UP:
			x -= 1;
			break;
		default: 
			break;
		}
	}
}
