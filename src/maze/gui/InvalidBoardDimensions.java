package maze.gui;

public class InvalidBoardDimensions extends Exception{
	
	private static final long serialVersionUID = 1L;
	String error;
	
	public InvalidBoardDimensions(String s){
		error = s;
	}
	
	public String getMessage(){
		return error;
	}

}
