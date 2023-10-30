package clueGame;

public class BadConfigFormatException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String error;

	public BadConfigFormatException() {
		super();
	}
	
	public BadConfigFormatException(String message) {
		super(message);
		error = message;
	}
}
