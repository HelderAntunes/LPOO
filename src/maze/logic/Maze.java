package maze.logic;

import java.io.Serializable;

/**
 * Represents the maze of the game.
 */
@SuppressWarnings("serial")
public class Maze implements Serializable{
	private char[][] board;
	private Position exitPos;
	
	/**
	 * Constructor of Maze.
	 * @param board 
	 */
	public Maze(char[][] board){
		for(int i = 0;i < board.length;i++)
			for(int j = 0;j < board.length;j++)
				if(board[i][j] == 'S'){
					exitPos = new Position(i, j);
					break;
				}
		this.board = board;
		
	}
	/**
	 * Set a position of the maze with a character
	 * @param pos position
	 * @param c character
	 * 
	 */
	public void setSquare(Position pos, char c){
		board[pos.getX()][pos.getY()] = c;
	}
	
	/**
	 * Get the symbol(represented by a char) of maze in given position.
	 * For example, if the char is 'X' there is a wall in given position.
	 * @param pos given position.
	 * @return the symbol in given position
	 */
	public char getSquare(Position pos){
		return board[pos.getX()][pos.getY()];
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
	 * @return the copy of the maze exit position.
	 */
	public Position getExitPos(){
		return exitPos.clone();
	}

}
