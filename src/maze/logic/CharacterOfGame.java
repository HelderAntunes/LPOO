package maze.logic;

import maze.logic.Position.Direction;

public class CharacterOfGame extends MazeElement{
	
	protected boolean isAlive;
	
	public CharacterOfGame(Position pos) {
		super(pos);
		isAlive = true;
	}
	
	/**
	 * @return true if character is alive, false otherwise
	 */
	public boolean isAlive(){
		return isAlive;
	}
	
	public void isKilled(){
		isAlive = false;
	}
	
	public void move(Direction dir){
		position.changePos(dir);
	}

}
