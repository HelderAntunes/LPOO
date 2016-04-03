package maze.logic;

public class MazeElement {
	
	protected Position position;

	public MazeElement(Position position) {
		this.position = position;
	}
	
	/**
	 * Get position of an element of game.
	 * @return the copy of the actual position of element
	 */
	public Position getPosition(){
		return position.clone();
	}
	
}
