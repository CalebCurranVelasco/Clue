package experiment;

import java.util.LinkedHashSet;
import java.util.Set;

public class TestBoardCell {
	
	private int row;
	private int column;
	private TestBoardCell cell;
	private Set<TestBoardCell> adjacencyMatrix;
	private boolean test;
	
	
	
	public TestBoardCell(int row, int column) {
		cell.row = row;
		cell.column = column;
	
	}
	public void setRoom(boolean bool) {
		
		
	}
	
	public boolean isRoom() {
		return false;
		
	}
	
	public void setOccupied(boolean bool) {
		
	}
	
	public boolean getOccupied() {
		return false;
		
	}



	public void addAdjacency(TestBoardCell cell) {
		adjacencyMatrix = new LinkedHashSet<TestBoardCell>();
		adjacencyMatrix.add(cell);
		
	}
	
	Set<TestBoardCell> getAdjList() {
		return adjacencyMatrix;
	}
	
	
	
	

}
