/**
 * Room class
 * Holds information related to a room. 
 * 
 * @author Caleb Curran-Velasco
 * @author Joshua Ramirez Malerva
 * 
 * 10/9/2023
 */

package clueGame;

public class Room {
	private String name;
	private char label;
	private BoardCell centerCell;
	private BoardCell labelCell;
	
	public Room(String name, char label) {
		this.name = name;
		this.label = label;
	}
	
	public String getName() {
		return name;
	}
	
	public BoardCell getCenterCell() {
		return centerCell;
	}
	
	public BoardCell getLabelCell() {
		return labelCell;
	}

	@Override
	public String toString() {
		return "Room [name=" + name + ", label=" + label + "]";
	}
	
	

	
	
}