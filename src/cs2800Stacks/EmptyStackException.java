package cs2800Stacks;

/**
 * This class is the {@link EmptyStackException} class.<br>
 * Exception class thrown to prevent {@link Stack#entries}
 * generating an outOfBounds error
 * @author Octavio
 * 
 */
public class EmptyStackException extends Exception {
	/**
	 * exception message
	 */
	private String message;
	/**
	 * 
	 */
	public EmptyStackException() {
		this.message="Stack is empty ";
	}
	/**
	 * returns {@link #message}
	 */
	public String getMessage() {
		return this.message;
	}
	

}
