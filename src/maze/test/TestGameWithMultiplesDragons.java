package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;
import maze.logic.AddingCharactersToMaze;
import maze.logic.MazeBuilder;

public class TestGameWithMultiplesDragons {
	private final int sizeMaze = 13;
	private final int numDragons = 5;
	@Test
	public void testAddingDragonsToMaze() {
		char m1[][] = new MazeBuilder().buildMaze(sizeMaze);
		char [][] m2 = new AddingCharactersToMaze().addDragonsInMazeUntilNumDragons(m1, numDragons);
		int numDragons = 0;
		for(int i = 0;i< m2.length;i++)
			for(int j = 0;j < m2[0].length;j++){
				if(m2[i][j] == 'D')
					numDragons++;
			}
		assertEquals(5, numDragons);
	}

}
