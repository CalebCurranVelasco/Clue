package experiment;

import java.util.HashSet;
import java.util.Set;

public class TestBoard {
	
	public void testBoard() {
		
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
