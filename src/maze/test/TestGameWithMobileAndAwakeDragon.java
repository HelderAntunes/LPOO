package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;
import maze.logic.GameState.Dificulty;
import java.util.ArrayList;

public class TestGameWithMobileAndAwakeDragon {
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
			{'X', 'H', ' ', ' ', 'S'},
			{'X', ' ', 'X', ' ', 'X'},
			{'X', 'E', ' ', 'D', 'X'},
			{'X', 'X', 'X', 'X', 'X'}};
	@Test
	public void testRandomMoveOfDragon() {
		GameState gamest = new GameState(m1, Dificulty.HARD);
		ArrayList<Dragon> dragons = gamest.getDragons();
		Dragon dragon = dragons.get(0);
		boolean dragonMoved = false;
		for(int i = 0;i < 10;i++){
			Position oldPosDragon = dragon.getPosition();
			gamest.update();
			Position newPosDragon = dragon.getPosition();
			if(oldPosDragon.equals(newPosDragon) == false){
				dragonMoved = true;
				break;
			}
		}
		assertEquals(true, dragonMoved);
	}

}
