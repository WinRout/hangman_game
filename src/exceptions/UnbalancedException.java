package exceptions;

public class UnbalancedException extends Exception {

	/**
	 * Unbalanced dictionary: Not enough long words.
	 */
	private static final long serialVersionUID = -1272810356473260443L;

	public UnbalancedException() {
		super("Could not add Dictionary.\n Long and short words are unbalanced. Please try again");
	}
}
