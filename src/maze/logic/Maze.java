package maze.logic;

/**
 * Represents the maze of the game.
 */
public class Maze {
	private char[][] board;
	private Position exitPos;
	
	/**
	 * Constructor of Maze.
	 * @param board 
	 */
	public Maze(char[][] board){
		this.board = board;
		exitPos = new Position(9, 5);
	}
	
	/**
	 * Get the symbol(represented by a char) of maze in given position.
	 * For example, if the char is 'X' there is a wall in given position.
	 * @param pos given position.
	 * @return the symbol in given position
	 */
	public char getSquare(Position pos){
		return board[pos.getY()][pos.getX()];
	}
	
	/**
	 * get the board of maze.
	 */
	public char[][] getBoard(){
		char[][] copyBoard = new char[board.length][board[0].length];
		for(int i = 0;i < board.length;i++){
			copyBoard[i] = board[i].clone();
		}
		return copyBoard;
	}
	
	/**
	 * Returns the position of exit of maze.
	 */
	public Position getExitPos(){
		return exitPos.clone();
	}

}
