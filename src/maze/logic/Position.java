package maze.logic;

import java.io.Serializable;

/**
 * Represents the position of an element of maze.
 */
@SuppressWarnings("serial")
public class Position implements Serializable{
	
	/**
	 * Represents the four possibles directions of a move.
	 */
	public enum Direction {LEFT, RIGHT, DOWN, UP, NONE}

	private int x;
	private int y;
	
	/**
	 * Constructor of Position
	 * @param x a column of maze.
	 * @param y a row of maze.
	 */
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	/**
	 * Get x coordinate.
	 * @return coordinate x of a position
	 */
	public int getX(){
		return x;
	}
	/**
	 * Get y coordinate.
	 * @return coordinate y of a position
	 */
	public int getY(){
		return y;
	}
	
	public Position clone(){
		return new Position(x,y);
	}
	
	/**
	 * @return true if the position of 2 objects are the same, and false if not
	 */
	public boolean equals(Object p2){
		if(x == ((Position)p2).getX() && y == ((Position)p2).getY())
			return true;
		return false;
	}
	
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

	public boolean positionsAreNearOfeachOther(Position otherPos){
		int xD = otherPos.getX();
		int yD = otherPos.getY();

		if(x == xD && Math.abs(y-yD) <= 1)
			return true;
		if(y == yD && Math.abs(x-xD) <= 1)
			return true;

		return false;
	}
	
	/**
	 * Set coordinates x and y.
	 * @param x coordinate
	 * @param y coordinate
	 */
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Set coordinate x.
	 * @param x coordinate
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * Set coordinate y.
	 * @param y coordinate
	 */
	public void setY(int y){
		this.y = y;
	}
}
