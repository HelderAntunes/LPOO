package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;
import maze.logic.GameState;
import maze.logic.GameState.Dificulty;

public class testGameWithMobileAndSleepingDragon {
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
			{'X', 'H', ' ', ' ', 'S'},
			{'X', ' ', 'X', ' ', 'X'},
			{'X', 'E', ' ', 'D', 'X'},
			{'X', 'X', 'X', 'X', 'X'}};

	@Test
	public void testIsSleeping() {
		GameState gamest = new GameState(m1, Dificulty.MEDIUM);
		boolean sucess = true;
		for(int i  = 0;i < 21;i++){
			gamest.update();
			if(gamest.getDragons().get(0).isSleeping()){
				if(gamest.getDragons().get(0).getSymbol() != 'd'){
					sucess = false;
					System.out.println(i + "sleeps!" + gamest.getDragons().get(0).getSymbol());
					break;
				}
			}
			else if(gamest.getDragons().get(0).getSymbol() != 'D'){
					sucess = false;
					System.out.println(i + " " + gamest.getDragons().get(0).getSymbol());
					break;
				}
		}
		assertEquals(true, sucess);
	} 


}
