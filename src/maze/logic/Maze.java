package maze.logic;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Maze implements Serializable{
	private char[][] board;
	private Position exitPos;
	
	public Maze(char[][] board){
		for(int i = 0;i < board.length;i++)
			for(int j = 0;j < board.length;j++)
				if(board[i][j] == 'S'){
					exitPos = new Position(i, j);
					break;
				}
		this.board = board;
		
	}
	
	public void setSquare(Position pos, char c){
		board[pos.getX()][pos.getY()] = c;
	}
	
	public char getSquare(Position pos){
		return board[pos.getX()][pos.getY()];
	}
	
	/**
	 * Get the board of maze.
	 * @return bidimentional array representing the board
	 */
	public char[][] getBoard(){
		char[][] copyBoard = new char[board.length][board[0].length];
		for(int i = 0;i < board.length;i++){
			copyBoard[i] = board[i].clone();
		}
		return copyBoard;
	}
	
	public Position getExitPos(){
		return exitPos.clone();
	}

}
