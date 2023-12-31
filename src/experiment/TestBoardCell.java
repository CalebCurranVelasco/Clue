/**
 * TestBoardCell class
 * Is a cell in the board. Holds the value of row and column as well and whether it is occupied or a room.
 * 
 * @author Caleb Curran-Velasco
 * @author Joshua Ramirez Malerva
 * 
 * 10/8/2023
 */

package experiment;

import java.util.HashSet;
import java.util.Set;

public class TestBoardCell {
	
	private int row;
	private int column;
	public Set<TestBoardCell> adjList;
	public boolean isOccupied;
	public boolean isRoom;
	
	
	public TestBoardCell(int row, int column) {
		this.row = row;
		this.column = column;
		this.adjList = new HashSet<TestBoardCell>();
		this.isOccupied = false;
		this.isRoom = false;
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
