package exceptions;

public class UndersizeException extends Exception {
	
	/**
	 * Undersized dictionary: Not enough total words.
	 */
	private static final long serialVersionUID = 2404712672892524235L;

	public UndersizeException() {
		super("Could not add Dictionary.\n Not enough words. Please try again");
	}
}