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
	 * 
	 * @return coordinate x of a position
	 */
	public int getX(){
		return x;
	}
	/**
	 * 
	 * @return cordinate y of a position
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * @return a copy of Position(x,y)
	 */
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
	
	/**
	 * Change the coordinates of the position.
	 * Can be a left, right, down or up move.
	 * @param dir direction of move.
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
	/**
	 * 
	 * @param otherPos position of an object.
	 * @return true if the distance between two objects is less than a square,and false if not
	 */
	public boolean positionsAreNearOfeachOther(Position otherPos){
		int xD = otherPos.getX();
		int yD = otherPos.getY();

		if(x == xD && Math.abs(y-yD) <= 1)
			return true;
		if(y == yD && Math.abs(x-xD) <= 1)
			return true;

		return false;
	}
}
