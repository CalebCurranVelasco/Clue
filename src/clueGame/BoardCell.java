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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashSet;
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
	private boolean isDoorway;
	private boolean isOccupied; 
	private boolean isRoom;
	private boolean isSecretPassage;
	private Color color;
	private Set<BoardCell> adjList;
	
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
		this.isSecretPassage = false;
		this.isOccupied = false;
		this.isRoom = false;
		this.doorDirection = DoorDirection.NONE;
		this.adjList = new HashSet<BoardCell>();
		
		switch(initial) {
		case 'X':
			color = Color.RED;
			break;
		case 'W':
			color = Color.DARK_GRAY;
			break;
		default:
			color = Color.BLUE;
		
		}
		
		// change to switch
		if (detailsArr.length > 1) {
			switch (detailsArr[1]) {
			    case '^':
			        this.doorDirection = DoorDirection.UP;
			        this.isDoorway = true;
			        break;
			    case '<':
			        this.doorDirection = DoorDirection.LEFT;
			        this.isDoorway = true;
			        break;
			    case '>':
			        this.doorDirection = DoorDirection.RIGHT;
			        this.isDoorway = true;
			        break;
			    case 'v':
			        this.doorDirection = DoorDirection.DOWN;
			        this.isDoorway = true;
			        break;
			    case '#':
			        this.roomLabel = true;
			        break;
			    case '*':
			        this.roomCenter = true;
			        break;
			    default:
			        this.secretPassage = detailsArr[1];
			        this.isSecretPassage = true;
			        break;
			}
		}
	}
	
	public void draw(Graphics g, int size) {
		g.setColor(this.color);
		g.fillRect(column*size, row*size, size, size);
		
	}
	
	public void drawSecret(Graphics g, int size) {
		g.setColor(Color.GREEN);
		g.fillRect(column*size, row*size, size, size);
	}
	
	public void drawTarget(Graphics g, int size) {
		g.setColor(Color.magenta);
		g.fillRect(column*size, row*size, size, size);
		
	}

	public boolean isDoorway() {
		return isDoorway;
	}
	
	public boolean isSecretPassage() {
		return isSecretPassage;
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
	
	public void setOccupied(boolean bool) {
		isOccupied = bool;
	}
	
	public void addAdjacency(BoardCell cell) {
		this.adjList.add(cell);
	}
	

	public boolean isOccupied() {
		return isOccupied;
	}

	public boolean isRoom() {
		return isRoom;
	}

	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", column=" + column + ", initial=" + initial + ", doorDirection="
				+ doorDirection + ", roomLabel=" + roomLabel + ", roomCenter=" + roomCenter + ", isDoorway=" + isDoorway + ", isOccupied=" + isOccupied;
	}
	
	
}
