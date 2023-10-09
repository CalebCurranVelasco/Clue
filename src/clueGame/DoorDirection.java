/**
 * DoorDirection enum
 * Enumerated class with direcitonal values for a door. 
 * 
 * @author Caleb Curran-Velasco
 * @author Joshua Ramirez Malerva
 * 
 * 10/9/2023
 */

package clueGame;

public enum DoorDirection {
	UP ("[^]"), DOWN ("[v]"), LEFT ("[<]"), RIGHT("[>]"), NONE("[X]");
	private String value;

	DoorDirection(String aValue) {
		value = aValue;
	}
	
	public String toString() {
		return value;
	}
}
