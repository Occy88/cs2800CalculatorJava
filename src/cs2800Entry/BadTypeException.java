package cs2800Entry;

/**
 * This class is the {@link BadTypeException} class.<br>
 * Exception to throw when {@link Type} values do not match expectation.<br>
 * 
 * @author Octavio
 * 
 */
public class BadTypeException extends Exception {
	/**
	 * error message when exception is thrown
	 */
	private String message;
	/**
	 * constructs error message from two {@link Type} values that don't match
	 * @param typeA
	 * @param typeB
	 */
	public BadTypeException(Type typeA,Type typeB) {
		this.message="Types are not equal Exception: "+typeA+','+typeB;
	}
	/**
	 * @return {@link #message}
	 */
	public String getMessage() {
		return this.message;
	}
	
}
