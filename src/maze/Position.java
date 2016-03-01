package maze;

public class Position {
	public enum Direction {LEFT, RIGHT, DOWN, UP}

	private int x;
	private int y;

	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public Position clone(){
		return new Position(x,y);
	}

	public void changePos(Direction dir) {
		switch(dir){
		case LEFT:
			x -= 1;
			break;
		case RIGHT:
			x += 1;
			break;
		case DOWN:
			y += 1;
			break;
		case UP:
			y -= 1;
			break;
		default: // criar exceção para invalid intput!!!!!!!!!!!!! 
			break;

		}
	}
}
