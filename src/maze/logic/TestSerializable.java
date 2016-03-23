package maze.logic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import maze.logic.GameState.Dificulty;

public class TestSerializable {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Dificulty dificulty = Dificulty.MEDIUM;
		char board[][] = new MazeBuilder().buildMaze(13);
		GameState gamest = new GameState(board, dificulty);

		ObjectOutputStream os= null;
		try {
			os= new ObjectOutputStream(new FileOutputStream("file.dat"));
			os.writeObject(gamest);
		}
		catch (IOException e) {
			
			System.out.println(e.getMessage());
		}
		finally { if (os!= null) os.close(); }
		
		ObjectInputStream is = null;
		try {
		is = new ObjectInputStream(new FileInputStream("file.dat"));
		gamest = (GameState) is.readObject();
		}
		catch (IOException e) {
			
		}
		finally { if (is != null) is.close(); }
		gamest.initializeElementsOfGame(gamest.getMaze().getBoard());
		if(gamest.getSword() == null){
			System.out.println("ee");
			return;
		}
		
		System.out.println(gamest.toString());
	}
}
