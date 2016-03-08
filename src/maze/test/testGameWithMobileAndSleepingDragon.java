package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;

import maze.logic.GameState;
import maze.logic.GameState.Dificulty;

public class testGameWithMobileAndSleepingDragon {
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
					{'X', ' ', ' ', 'H', 'S'},
					{'X', ' ', 'X', ' ', 'X'},
					{'X', 'E', ' ', 'D', 'X'},
					{'X', 'X', 'X', 'X', 'X'}};

	@Test
	public void testIsSleeping() {
		GameState gamest = new GameState(m1, Dificulty.MEDIUM);
		gamest.update();
		if(gamest.getDragons().get(0).isSleeping())
			assertEquals('d',gamest.getDragons().get(0).getSymbol());
		else
			assertEquals('D',gamest.getDragons().get(0).getSymbol());
		
	}
	

}
