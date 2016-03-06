package maze.logic;

/**
 * Represents a element of maze.
 * The element may be simply a object(like a sword), or character of game(like an hero).
 */
public class MazeElement {
	
	protected Position position;
	
	/**
	 * Constructor of MazeElement.
	 * @param position initial position of element of maze.
	 */
	public MazeElement(Position position) {
		this.position = position;
	}
	
	/**
	 * @return the atual position of element
	 */
	public Position getPosition(){
		return position.clone();
	}
	
}
