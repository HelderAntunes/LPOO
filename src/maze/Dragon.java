package maze;

public class Dragon {
	Position pos;
	boolean isAlive;
	
	public Dragon(Position pos) {
		this.pos = pos.clone();
		isAlive = true;
	}
	
	public void killDragon(){
		isAlive = false;
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
	public Position getPosition(){
		return pos.clone();
	}

}
