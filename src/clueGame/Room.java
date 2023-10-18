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
	private String roomName;
	private String roomLabel;
	private BoardCell centerCell;
	private BoardCell labelCell;
	
	public Room(String name, String label) {
		this.roomName = name;
		this.roomLabel = label;
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
	

	
	
}
