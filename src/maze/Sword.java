package maze;

public class Sword {
	Position pos;
	boolean isTaken;
	
	public Sword(Position pos) {
		this.pos = pos;
		isTaken = false;
	}
	
	public void take(){
		isTaken = true;
	}
	
	public boolean wasTaken(){
		return isTaken;
	}
	
	public Position getPosition(){
		return pos.clone();
	}
}
