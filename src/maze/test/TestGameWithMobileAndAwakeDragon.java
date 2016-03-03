package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;
import maze.logic.GameState.Dificulty;
import maze.logic.Position.Direction;

public class TestGameWithMobileAndAwakeDragon {
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
					{'X', ' ', ' ', 'H', 'S'},
					{'X', ' ', 'X', ' ', 'X'},
					{'X', 'E', ' ', 'D', 'X'},
					{'X', 'X', 'X', 'X', 'X'}};
	@Test
	public void testRandomMoveOfDragon() {
		GameState gamest = new GameState(m1, Dificulty.HARD);
		Position oldPosDragon = gamest.getDragon().getPosition();
		gamest.moveHero(Direction.LEFT);
		gamest.update();
		Position newPosDragon = gamest.getDragon().getPosition();
		assertEquals(false, oldPosDragon.equals(newPosDragon));
	}

}
