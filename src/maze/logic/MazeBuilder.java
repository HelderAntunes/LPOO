package maze.logic;

import java.util.Stack;

public class MazeBuilder {

	private char[][] lab;
	private char[][] visitedCells;
	private Stack<Integer> pathHistory;
	private int n;

	char[][] getRandomMaze(int n) {
		this.n = n;

		initLab();
		initVisitedCells();


		return lab;
	}

	private void initLab(){
		lab = new char[n][n];
		for(int i = 0;i < n;i++)
			for(int j = 0;j < n;j++)
				lab[i][j] = ' ';
		for(int i = 0;i < n;i += 2)
			for(int j = 0;j < n;j++)
				lab[i][j] = 'X';
		for(int j = 0;j < n;j += 2)
			for(int i = 0;i < n;i++)
				lab[i][j] = 'X';
	}

	private void initVisitedCells(){
		visitedCells = new char[(n-1)/2][(n-1)/2];
		for(int i = 0;i < visitedCells.length;i++)
			for(int j = 0;j < visitedCells.length;j++)
				visitedCells[i][j] = '.';
	}
}
