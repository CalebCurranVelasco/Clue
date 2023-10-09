package experiment;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestBoard {
	private TestBoardCell[][] grid;
	private Set<TestBoardCell> targets;
	private Set<TestBoardCell> visited;
	final static int COLS = 4;
	final static int ROWS = 4;
	
	public TestBoard() {
		this.grid = new TestBoardCell[ROWS][COLS];
		targets = new HashSet<TestBoardCell>();
		visited = new HashSet<TestBoardCell>();
		for (int i=0; i < ROWS; i++) {
			for (int j=0; j < COLS; j++) {
				grid[i][j] = new TestBoardCell(i, j);
			}
		}
		calculateAdjacencies();
	}
	
	public void calculateAdjacencies() {
		int [][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // left, right, up, down
		for (int i=0; i<ROWS; i++) {
			for (int j=0; j<COLS; j++) {
				TestBoardCell currCell = grid[i][j];
				// iterate through all four possible directions
				for (int k=0; k<directions.length; k++) {
					// update coordinates and see if legal
					int newRow = i + directions[k][0];
					int newCol = j + directions[k][1];
					if (newRow >= 0 && newCol >= 0 && newRow < ROWS && newCol < COLS) {
						currCell.adjList.add(grid[newRow][newCol]);
					}
				}
			}
		}
	}
	
	public Set<TestBoardCell> calcTargets(TestBoardCell startCell, int pathLength) {
		this.visited = new HashSet<TestBoardCell>();
		this.targets = new HashSet<TestBoardCell>();
		visited.add(startCell);
		findAllTargets(startCell, pathLength);
		return targets;
	}
	
	public void findAllTargets(TestBoardCell startCell, int pathLength) {
		Set<TestBoardCell> adjList = startCell.adjList;
		for (TestBoardCell cell : adjList) {
			if (visited.contains(cell) || cell.isOccupied) {
				continue;
			} else {
				visited.add(cell);
				if (pathLength == 1 || cell.isRoom) { // found target since adj cells are one cell away
					targets.add(cell);
					startCell.addAdjacency(cell);
				} else {
					findAllTargets(cell, pathLength-1); // recursive call 
				}
				visited.remove(cell);
			}
		}
	}
	
	public TestBoardCell getCell(int row, int column) {
		return grid[row][column];
	}
	
	
	public Set<TestBoardCell> getTargets() {
		return targets;
	}
}
