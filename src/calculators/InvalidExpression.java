package calculators;

/**
 * 
 * 
 * @author Octavio
 * 
 */
public class InvalidExpression extends Exception {
	/**
	 * error message when exception is thrown
	 */
	private String message;
	/**
	 * 
	 */
	public InvalidExpression(String expression) {
		this.message="Types are not equal Exception: "+expression;
	}
	/**
	 * @return {@link #message}
	 */
	public String getMessage() {
		return this.message;
	}
	
}