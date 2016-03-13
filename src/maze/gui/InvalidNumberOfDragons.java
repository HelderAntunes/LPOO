package maze.gui;

public class InvalidNumberOfDragons extends Exception{

	private static final long serialVersionUID = 1L;
	String error;
	
	public InvalidNumberOfDragons(String s){
		error = s;
	}
	
	public String getMessage(){
		return error;
	}

}
