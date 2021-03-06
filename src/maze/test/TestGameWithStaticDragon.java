package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;
import maze.logic.GameState.Dificulty;
import maze.logic.Position.Direction;


public class TestGameWithStaticDragon {
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
					{'X', ' ', ' ', 'H', 'S'},
					{'X', ' ', 'X', ' ', 'X'},
					{'X', 'E', ' ', 'D', 'X'}, 
					{'X', 'X', 'X', 'X', 'X'}};
	
	char [][] m2 = {{'X', 'X', 'X', 'X', 'X'},
					{'X', ' ', 'H', ' ', 'S'},
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
	public void testMoveHeroToTheWall(){
		GameState gamest = new GameState(m1, Dificulty.EASY);
		assertEquals(false, gamest.heroMoveIsValid(Direction.UP));
	}
	@Test
	public void testHeroTakeSword(){
		GameState gamest = new GameState(m1, Dificulty.EASY);
		gamest.moveHero(Direction.LEFT);
		gamest.update();
		gamest.moveHero(Direction.LEFT);
		gamest.update();
		gamest.moveHero(Direction.DOWN);
		gamest.update();
		gamest.moveHero(Direction.DOWN);
		gamest.update();
		assertEquals(true, gamest.getHero().isArmed());
		assertEquals(true,gamest.getSword().wasTaken());
		assertEquals('A',gamest.getHero().getSymbol());
		assertEquals(' ',gamest.getSword().getSymbol());
	}
	@Test
	public void testHeroDies() {
		GameState gamest = new GameState(m1, Dificulty.EASY);
		assertEquals(false, gamest.getHero().isArmed());
		gamest.moveHero(Direction.DOWN);
		gamest.update();
		assertEquals(false, gamest.getHero().isAlive());
		assertEquals(' ', gamest.getHero().getSymbol());
	}
	@Test
	public void testDragonDies(){
		GameState gamest=new GameState(m1,Dificulty.EASY);
		gamest.moveHero(Direction.LEFT);
		gamest.update();
		gamest.moveHero(Direction.LEFT);
		gamest.update();
		gamest.moveHero(Direction.DOWN);
		gamest.update();
		gamest.moveHero(Direction.DOWN);
		gamest.update();
		gamest.moveHero(Direction.RIGHT);
		gamest.update();
		assertEquals(false,gamest.getDragons().get(0).isAlive());
	}
	
	@Test
	public void testVictory(){
		GameState gamest=new GameState(m1,Dificulty.EASY);
		gamest.moveHero(Direction.LEFT);
		gamest.update();
		gamest.moveHero(Direction.LEFT);
		gamest.update();
		gamest.moveHero(Direction.DOWN);
		gamest.update();
		gamest.moveHero(Direction.DOWN);
		gamest.update();
		gamest.moveHero(Direction.RIGHT);
		gamest.update();
		gamest.moveHero(Direction.RIGHT);
		gamest.update();
		gamest.moveHero(Direction.UP);
		gamest.update();
		gamest.moveHero(Direction.UP);
		gamest.update();
		gamest.moveHero(Direction.RIGHT);
		gamest.update();
		assertEquals(true,gamest.isFinished());
	}
	
	@Test
	public void testHeroNotAbleToExitIfNotTakeSword(){
		GameState gamest=new GameState(m1,Dificulty.EASY);
		assertEquals(false, gamest.heroMoveIsValid(Direction.RIGHT));
	}
	@Test
	public void testHeroArmedNotAbleToExitIfNotKillDragon(){
		GameState gamest=new GameState(m1,Dificulty.EASY);
		gamest.moveHero(Direction.LEFT);
		gamest.update();
		gamest.moveHero(Direction.LEFT);
		gamest.update();
		gamest.moveHero(Direction.DOWN);
		gamest.update();
		gamest.moveHero(Direction.DOWN);
		gamest.update();
		gamest.moveHero(Direction.UP);
		gamest.update();
		gamest.moveHero(Direction.UP);
		gamest.update();
		gamest.moveHero(Direction.RIGHT);
		gamest.update();
		gamest.moveHero(Direction.RIGHT);
		gamest.update();
		assertEquals(false, gamest.heroMoveIsValid(Direction.RIGHT));
	}
	
	@Test
	public void testGetGameBoard(){
		GameState gamest=new GameState(m1,Dificulty.EASY);
		gamest.moveHero(Direction.LEFT);
		gamest.update();
		boolean isEqual=true;
		for(int i=0;i<m2.length;i++)
			for(int j=0;j<m2.length;j++)
				if(m2[i][j]!=gamest.getGameBoard()[i][j]){
					isEqual=false;
					break;
				}
		assertEquals(true,isEqual);
	}
	
	
}


