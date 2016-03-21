package maze.gui;

import maze.logic.GameState.Dificulty;

public class Utilities {
	
	public Dificulty chooseDificulty(int indexSelected){
		switch(indexSelected){
		case 0:
			return Dificulty.EASY;
		case 1:
			return Dificulty.MEDIUM;
		case 2:
			return Dificulty.HARD;
		default:
			return Dificulty.EASY;
		}
	}
	
	public int chooseBoardDimensions(String text) throws InvalidBoardDimensions{
		if(isNumeric(text)){
			int boardDimensions = Integer.parseInt(text);  
			if(boardDimensions >= 5 && boardDimensions%2 != 0)
				return boardDimensions;
			else
				throw new InvalidBoardDimensions("Dimensao do tabuleiro invalida.");
		}
		throw new InvalidBoardDimensions("Introduza um número em dimensao do tabuleiro.");
	}
	
	public int getNumberOfDragons(String text) throws InvalidNumberOfDragons{
		if(isNumeric(text)){
			int nDragons = Integer.parseInt(text);  
			if(nDragons >= 1)
				return nDragons;
			else
				throw new InvalidNumberOfDragons("Numero de dragoes invalido.");
		}
		throw new InvalidNumberOfDragons("Introduza um número em Numero de dragoes.");
	}
	
	public boolean isNumeric(String str)  {  
		try  {  
			Integer.parseInt(str);  
		}  
		catch(NumberFormatException nfe)  {  
			return false;  
		}  
		return true;  
	}
	
}
