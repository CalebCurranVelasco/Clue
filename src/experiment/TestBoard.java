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
	
	public void testBoard() {
		this.grid = new TestBoardCell[ROWS][COLS];
		targets = new HashSet<TestBoardCell>();
		visited = new HashSet<TestBoardCell>();
		initializeGrid();
		calculateAdjacencies();
	}
	
	private void initializeGrid() {
		for (int i=0; i < ROWS; i++) {
			for (int j=0; j < COLS; j++) {
				grid[i][j] = new TestBoardCell(i, j);
			}
		}
	}
	
	private void calculateAdjacencies() {
		for (int i=0; i<ROWS; i++) {
			for (int j=0; j<ROWS; j++) {
				// check if surrounding cells within bounds, if so, add to adjList
			}
		}
	}
	
	public void calcTargets(TestBoardCell startCell, int pathLength) {
		
	}
	
	public TestBoardCell getCell(int row, int column) {
		TestBoardCell newCell = new TestBoardCell(row, column);
		return newCell;
		
	}
	
	
	public Set<TestBoardCell> getTargets() {
		
		Set<TestBoardCell> newSet = new HashSet<TestBoardCell> ();
		return newSet;
		
	}
}
