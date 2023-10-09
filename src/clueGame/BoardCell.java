package clueGame;

import java.util.Set;

import experiment.TestBoardCell;

public class BoardCell {
	private int row;
	private int column;
	private char initial;
	private DoorDirection doordirection;
	private boolean roomLabel;
	private boolean roomCenter;
	private char secretPassage;
//	public boolean isOccupied; 
//	public boolean isRoom;
	public Set<BoardCell> adjList;
	
	public void addAdj(BoardCell adj) {
		adjList.add(adj);
	}
	
}
