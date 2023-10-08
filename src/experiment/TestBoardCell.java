package experiment;

import java.util.HashSet;
import java.util.Set;

public class TestBoardCell {
	
	private int row;
	private int column;
//	private TestBoardCell cell;
	private Set<TestBoardCell> adjList;
	private boolean isOccupied;
	private boolean isRoom;
	
	
	public TestBoardCell(int row, int column) {
		this.row = row;
		this.column = column;
		adjList = new HashSet<TestBoardCell>();
	}
	
	public void setRoom(boolean bool) {
		isRoom = bool;
		
		
	}
	
	public boolean getRoom() {
		return isRoom;
		
	}
	
	public void setOccupied(boolean bool) {
		isOccupied = bool;
		
	}
	
	public boolean getOccupied() {
		return isOccupied;
		
	}

	public void addAdjacency(TestBoardCell cell) {
		adjList.add(cell);
	}
	
	public Set<TestBoardCell> getAdjList() {
		return adjList;
	}

}
