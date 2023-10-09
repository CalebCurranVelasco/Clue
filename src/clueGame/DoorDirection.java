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
