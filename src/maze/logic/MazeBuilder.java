package maze.logic;

import java.util.Stack;

import java.util.Random;
import maze.logic.Position.Direction;

/**
 * Is responsible for build a random Maze.
 */
public class MazeBuilder implements IMazeBuilder{

	private char[][] maze;
	private char[][] visitedCells;
	private Stack<Position> pathHistory;
	private int n;
	private int rowAtualPos;
	private int columnAtualPos;

	/**
	 * Builds a random maze of size n x n, and places 1 hero, 1 sword and 1 dragon in maze.
	 * @param n size of size board
	 */
	public char[][] buildMaze(int n) {
		this.n = n;

		initLab();
		initAtualPos();
		defineExitPos();
		initVisitedCells();
		pathHistory = new Stack<Position>();
		pathHistory.push(new Position(rowAtualPos/2, columnAtualPos/2));

		while(true){
			if(allNeighborsCellsHasBeenVisited()){
				if(pathHistory.isEmpty())
					break;
				Position oldPos = pathHistory.pop();
				if(pathHistory.isEmpty())
					break;
				Position newPos = pathHistory.pop();	
				Direction newDir = getNewDir(oldPos, newPos);
				updateMaze(newDir);
			}
			else{
				Direction dir = getRandomDirection();
				if(directionIsValid(dir))
					updateMaze(dir);
			}
		}
		maze[rowAtualPos][columnAtualPos] = ' ';
		return new AddingCharactersToMaze().getMazeWithCharacters(maze); // add one dragon
	}
	
	private Direction getNewDir(Position oldPos, Position newPos){
		Direction newDir = null;
		if(oldPos.getY() == newPos.getY()+1)
			newDir = Direction.LEFT;
		else if(oldPos.getX() == newPos.getX()+1)
			newDir = Direction.UP;
		else if(oldPos.getY() == newPos.getY()-1)
			newDir = Direction.RIGHT;
		else
			newDir = Direction.DOWN;
		return newDir;
	}
	
	private boolean allNeighborsCellsHasBeenVisited(){
		int rowVisCell = rowAtualPos/2;
		int colVisCell = columnAtualPos/2;
		int lastPos = visitedCells.length-1;
		if(colVisCell > 0 && positonIsNotBeVisited(new Position(rowVisCell,colVisCell-1))) //check left
			return false;
		else if(rowVisCell > 0 && positonIsNotBeVisited(new Position(rowVisCell-1,colVisCell))) // check up
			return false;
		else if(colVisCell < lastPos && positonIsNotBeVisited(new Position(rowVisCell,colVisCell+1))) //check right
			return false;
		else if(rowVisCell < lastPos && positonIsNotBeVisited(new Position(rowVisCell+1,colVisCell))) //check down
			return false;
		return true;
	}
	
	private void updateMaze(Direction dir){
		int rowVisCell = rowAtualPos/2;
		int colVisCell = columnAtualPos/2;
		maze[rowAtualPos][columnAtualPos] = ' ';
		switch(dir){
		case LEFT:
			maze[rowAtualPos][columnAtualPos-1] = ' ';
			maze[rowAtualPos][columnAtualPos-2] = '+';
			visitedCells[rowVisCell][colVisCell-1] = '+';
			columnAtualPos -= 2;
			break;
		case UP:
			maze[rowAtualPos-1][columnAtualPos] = ' ';
			maze[rowAtualPos-2][columnAtualPos] = '+';
			visitedCells[rowVisCell-1][colVisCell] = '+';
			rowAtualPos -= 2;
			break;
		case RIGHT:
			maze[rowAtualPos][columnAtualPos+1] = ' ';
			maze[rowAtualPos][columnAtualPos+2] = '+';
			visitedCells[rowVisCell][colVisCell+1] = '+';
			columnAtualPos += 2;
			break;
		case DOWN:
			maze[rowAtualPos+1][columnAtualPos] = ' ';
			maze[rowAtualPos+2][columnAtualPos] = '+';
			visitedCells[rowVisCell+1][colVisCell] = '+';
			rowAtualPos += 2;
			break;
		default:
			break;
		}
		pathHistory.push(new Position(rowAtualPos/2,columnAtualPos/2));
	}
	
	private boolean directionIsValid(Direction dir){
		int r = rowAtualPos/2;
		int c = columnAtualPos/2;
		switch(dir){
		case LEFT:
			if(c > 0 && positonIsNotBeVisited(new Position(r, c-1)))
				return true;
			break;
		case UP:
			if(r > 0 && positonIsNotBeVisited(new Position(r-1, c)))
				return true;
			break;
		case RIGHT:
			if(c < visitedCells.length-1 && positonIsNotBeVisited(new Position(r, c+1)))
				return true;
			break;
		case DOWN:
			if(r < visitedCells.length-1 && positonIsNotBeVisited(new Position(r+1, c)))
				return true;
			break;
		default:
			break;
		}
		return false;
	}

	private boolean positonIsNotBeVisited(Position pos){
		if(visitedCells[pos.getX()][pos.getY()] != '+')
			return true;
		else
			return false;
	}

	private Direction getRandomDirection(){
		Random r = new Random();
		int d = r.nextInt(4);
		Direction dir = null;
		switch(d){
		case 0:
			dir = Direction.LEFT;
			break;
		case 1:
			dir = Direction.UP;
			break;
		case 2:
			dir = Direction.RIGHT;
			break;
		case 3:
			dir = Direction.DOWN;
			break;
		default:
			dir = Direction.DOWN;
			break;
		}
		return dir;
	}

	private void initLab(){
		maze = new char[n][n];
		for(int i = 0;i < n;i++)
			for(int j = 0;j < n;j++)
				maze[i][j] = ' ';
		for(int i = 0;i < n;i += 2)
			for(int j = 0;j < n;j++)
				maze[i][j] = 'X';
		for(int j = 0;j < n;j += 2)
			for(int i = 0;i < n;i++)
				maze[i][j] = 'X';
	}

	private void initAtualPos(){
		Random r = new Random();
		rowAtualPos = r.nextInt(n-1);
		if(rowAtualPos%2 == 0)
			rowAtualPos++;
		// if is on left or right side
		if(rowAtualPos == 1 || rowAtualPos == n-2){
			columnAtualPos = r.nextInt(n-1);
			if(columnAtualPos%2 == 0)
				columnAtualPos++;
		}
		else{ // if is on up or down side
			int zeroOrOne = r.nextInt(2);
			if(zeroOrOne == 0)
				columnAtualPos = 1;
			else
				columnAtualPos = n-2;
		}
		maze[rowAtualPos][columnAtualPos] = '+';
	}
	
	private void defineExitPos(){
		Random r = new Random();
		boolean sucess = false;
		int calcExitPos;
		do{
			calcExitPos = r.nextInt(4);
			switch(calcExitPos){
			case 0: // left
				if(columnAtualPos-1 == 0){
					maze[rowAtualPos][0] = 'S';
					sucess = true;
				}
				break;
			case 1: // right
				if(columnAtualPos+1 == n-1){
					maze[rowAtualPos][n-1] = 'S';
					sucess = true;
				}
				break;
			case 2: // down
				if(rowAtualPos+1 == n-1){
					maze[n-1][columnAtualPos] = 'S';
					sucess = true;
				}
				break;
			case 3: // up
				if(rowAtualPos-1 == 0){
					maze[0][columnAtualPos] = 'S';
					sucess = true;
				}
				break;
			default:
				break;
			}
		}while(!sucess);
	}

	private void initVisitedCells(){
		visitedCells = new char[(n-1)/2][(n-1)/2];
		for(int i = 0;i < visitedCells.length;i++)
			for(int j = 0;j < visitedCells.length;j++)
				visitedCells[i][j] = '.';
		visitedCells[rowAtualPos/2][columnAtualPos/2] = '+';
	}
}
