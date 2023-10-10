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

import java.util.Set;

import experiment.TestBoardCell;

public class BoardCell {
	private int row;
	private int column;
	private char initial;
	private DoorDirection doorDirection;
	private boolean roomLabel;
	private boolean roomCenter;
	private char secretPassage;
	private boolean isLabel;
//	public boolean isOccupied; 
//	public boolean isRoom;
	public Set<BoardCell> adjList;
	
	
	
	public BoardCell(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public void addAdj(BoardCell adj) {
		adjList.add(adj);
	}
	
	public boolean isDoorway() {
		return false;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public char getInitial() {
		return initial;
	}

	public DoorDirection getDoorDirection() {
		return doorDirection;
	}

	public boolean isRoomLabel() {
		return roomLabel;
	}

	public boolean isRoomCenter() {
		return roomCenter;
	}

	public char getSecretPassage() {
		return secretPassage;
	}
	
	public boolean isLabel() {
		return false;
	}

	public Set<BoardCell> getAdjList() {
		return adjList;
	}

	public void setAdjList(Set<BoardCell> adjList) {
		this.adjList = adjList;
	}
	
}
