/**
 * BoardCell class
 * Holds info for each cell in the board including its adjacency list. 
 * 
 * @author Caleb Curran-Velasco
 * @author Joshua Ramirez Malerva
 * 
 * 10/9/2023
 */

package clueGame;

import java.util.HashSet;
import java.util.Set;

public class BoardCell {
	private int row;
	private int column;
	private char initial;
	private DoorDirection doorDirection;
	private boolean roomLabel;
	private boolean roomCenter;
	private char secretPassage;
	private boolean isDoorway;
//	public boolean isOccupied; 
//	public boolean isRoom;
	public Set<BoardCell> adjList;
	
	
	
	public BoardCell(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}
	
	public void setDetails(char[] detailsArr) {
		this.initial = detailsArr[0];
		this.roomLabel = false;
		this.roomCenter = false;
		this.isDoorway = false;
		this.doorDirection = DoorDirection.NONE;
		this.adjList = new HashSet<BoardCell>();
		
		if (detailsArr.length > 1) {			
			if (detailsArr[1] == '^') {
				this.doorDirection = DoorDirection.UP;
				this.isDoorway = true;
			} else if (detailsArr[1] == '<') {
				this.doorDirection = DoorDirection.LEFT;
				this.isDoorway = true;
			} else if (detailsArr[1] == '>') {
				this.doorDirection = DoorDirection.RIGHT;
				this.isDoorway = true;
			} else if (detailsArr[1] == 'v') {
				this.doorDirection = DoorDirection.DOWN;
				this.isDoorway = true;
			} else if (detailsArr[1] == '#') {
				this.roomLabel = true;
				
			} else if (detailsArr[1] == '*') {
				this.roomCenter = true;
			} else {
				this.secretPassage = detailsArr[1];
			}
		}
	}
	

	public void addAdj(BoardCell adj) {
		adjList.add(adj);
	}
	
	public boolean isDoorway() {
		return isDoorway;
	}

	public int getRow() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
	}

	public char getInitial() {
		return this.initial;
	}

	public DoorDirection getDoorDirection() {
		return this.doorDirection;
	}

	public boolean isLabel() {
		return this.roomLabel;
	}

	public boolean isRoomCenter() {
		return this.roomCenter;
	}

	public char getSecretPassage() {
		return this.secretPassage;
	}

	public Set<BoardCell> getAdjList() {
		return this.adjList;
	}

	public void setAdjList(Set<BoardCell> adjList) {
		this.adjList = adjList;
	}

	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", column=" + column + ", initial=" + initial + ", doorDirection="
				+ doorDirection + ", roomLabel=" + roomLabel + ", roomCenter=" + roomCenter + ", secretPassage="
				+ secretPassage + ", isDoorway=" + isDoorway + ", adjList=" + adjList + "]";
	}
	
	
}
