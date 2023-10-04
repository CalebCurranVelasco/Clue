package experiment;

import java.util.LinkedHashSet;
import java.util.Set;

public class TestBoardCell {
	
	private int row;
	private int column;
	private TestBoardCell cell;
	private Set<TestBoardCell> adjacencyMatrix;
	
	
	
	public TestBoardCell(int row, int column) {
		cell.row = row;
		cell.column = column;
	
	}



	public void addAdjacency(TestBoardCell cell) {
		adjacencyMatrix = new LinkedHashSet<TestBoardCell>();
		adjacencyMatrix.add(cell);
		
		
	}
	
	
	
	
	

}
