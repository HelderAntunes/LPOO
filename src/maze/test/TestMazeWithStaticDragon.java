package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;
import maze.logic.GameState.Dificulty;
import maze.logic.Position.Direction;


public class TestMazeWithStaticDragon {
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
					{'X', ' ', ' ', 'H', 'S'},
					{'X', ' ', 'X', ' ', 'X'},
					{'X', 'E', ' ', 'D', 'X'},
					{'X', 'X', 'X', 'X', 'X'}};
	@Test
	public void testMoveHeroToFreeCell() {
		GameState gamest = new GameState(m1, Dificulty.EASY);
		assertEquals(new Position(1, 3), gamest.getHero().getPosition());
		gamest.moveHero(Direction.LEFT);
		assertEquals(new Position(1, 2), gamest.getHero().getPosition());
	}
	@Test
	public void testHeroDies() {
		GameState gamest = new GameState(m1, Dificulty.EASY);
		assertEquals(false, gamest.getHero().isArmed());
		gamest.moveHero(Direction.DOWN);
		gamest.update();
		assertEquals(false, gamest.getHero().isAlive());
	}
}


