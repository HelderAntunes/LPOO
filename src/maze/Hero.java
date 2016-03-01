package maze;

import maze.Position.Direction;

public class Hero {
	Position pos;
	boolean isArmed;
	boolean isAlive;
	public Hero(Position pos){
		this.pos = pos.clone() ;
		isArmed = false;
		isAlive = true;
	}
	
	public void atualizePosition(Direction dir){
		pos.changePos(dir);
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
	public boolean isArmed(){
		return isArmed;
	}
	
	public Position getPosition(){
		return pos.clone();
	}
	
	public void setPosition(Position newPos){
		this.pos = newPos.clone();
	}
	
	public void killHero(){
		isAlive = false;
	}
	
	public void arm(){
		isArmed = true;
	}

}
