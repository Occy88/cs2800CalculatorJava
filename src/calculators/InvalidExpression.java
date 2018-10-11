
package calculators;

/**
 * thrown if expression entered by user is deemed invalid.
 * @author Octavio
 * 
 */
public class InvalidExpression extends Exception {
  /**
   * still don't know what this is for.
   */
  private static final long serialVersionUID = -5320877730106383129L;
  /**
   * error message when exception is thrown.
   */
  private String message;

  /**
   *  constructor for exception.
   */
  public InvalidExpression(String expression) {
    this.message = expression;
  }

  /**
   * returns constructed message.
   * @return {@link #message}
   */
  public String getMessage() {
    return this.message;
  }

}
