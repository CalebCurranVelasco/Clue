package experiment;

import java.util.HashSet;
import java.util.Set;

public class TestBoardCell {
	
	private int row;
	private int column;
	private TestBoardCell cell;
	private Set<TestBoardCell> adjacencyList;
	private boolean occupied;
	private boolean room;
	
	
	public TestBoardCell(int row, int column) {
		this.row = row;
		this.column = column;
		adjacencyList = new HashSet<TestBoardCell>();
	}
	
	public void setRoom(boolean bool) {
		room = bool;
		
		
	}
	
	public boolean getRoom() {
		return room;
		
	}
	
	public void setOccupied(boolean bool) {
		occupied = bool;
		
	}
	
	public boolean getOccupied() {
		return false;
		
	}

	public void addAdjacency(TestBoardCell cell) {
		adjacencyList.add(cell);
	}
	
	public Set<TestBoardCell> getAdjList() {
		return adjacencyList;
	}

}
