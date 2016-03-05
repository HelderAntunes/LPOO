package maze.logic;

import java.util.Random;

public class AddingCharactersToMaze {
	char[][] maze;
	Position heroPos;
	Position dragonPos;
	Position swordPos;
	
	public char[][] getMazeWithCharacters(char[][] maze){
		this.maze = maze;
		swordPos = creatPosition();
		heroPos = creatPosition();
		do{
			dragonPos = creatPosition();
		}while(dragonPos.positionsAreNearOfeachOther(heroPos));
		
		this.maze[swordPos.getX()][swordPos.getY()] = 'E';
		this.maze[heroPos.getX()][heroPos.getY()] = 'H';
		this.maze[dragonPos.getX()][dragonPos.getY()] = 'D';
		
		return maze;
	}
	
	private Position creatPosition(){
		Position pos;
		do{
			pos = generateRandomPosition();
		}while(positionIsInvalid(pos));
		return pos;
	}

	private boolean positionIsInvalid(Position pos){
		if(maze[pos.getX()][pos.getY()] != ' ') return true;
		else return false;
	}
	
	private Position generateRandomPosition(){
		Random r = new Random();
		int row = r.nextInt(maze.length-2)+1;
		int column = r.nextInt(maze.length-2)+1;
		return new Position(row, column);
	}
}
