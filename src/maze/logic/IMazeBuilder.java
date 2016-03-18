package maze.logic;
/**
 * 
 * Represents the interface of MazeBuilder
 *
 */
public interface IMazeBuilder {
	public char[][] buildMaze(int size) throws IllegalArgumentException;
}