package clueGame;

public class BadConfigFormatException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String error;

	public BadConfigFormatException() {
		super("Bad Configuration File Error");
	}
	
	public BadConfigFormatException(String message) {
		super("The error: " + message);
		error = message;
	}
}
