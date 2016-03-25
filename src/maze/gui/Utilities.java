package maze.gui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import maze.logic.GameState;
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
	
	public ArrayList<GameState> readGames(){
		ObjectInputStream is = null;
		ArrayList<GameState> savedGames = new ArrayList<GameState>();
		try {
			is = new ObjectInputStream(new FileInputStream("file.dat"));
			int totalGamesSaved = is.readInt();
			for(int i = 0;i < totalGamesSaved;i++){
				GameState game = (GameState) is.readObject();
				game.initializeElementsOfGame(game.getMaze().getBoard());
				savedGames.add(game);
			}
		}
		catch (IOException | ClassNotFoundException e1) {
			System.out.println("erro ao ler");
		}
		finally { if (is != null)
			try {
				is.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			} }
		return savedGames;
	}
	
	public void saveGames(ArrayList<GameState> games){
		ObjectOutputStream os= null;
		try {
			os= new ObjectOutputStream(new FileOutputStream("file.dat"));
			os.writeInt(games.size());
			for(GameState game: games)
				os.writeObject(game);
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		finally { 
			if (os!= null)
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 
		}
	}
	
}
