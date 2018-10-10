
package stacks;

/**
 * This class is the {@link EmptyStackException} class.<br>
 * Exception class thrown to prevent {@link Stack#entries} generating an outOfBounds error
 * 
 * @author Octavio
 * 
 */
public class EmptyStackException extends Exception {
  /**
   * will be able to describe this once I google it.
   */
  private static final long serialVersionUID = 2750441425183664674L;
  /**
   * exception message.
   */
  private String message;

  /**
   * exception constructor.
   */
  public EmptyStackException() {
    this.message = "Stack is empty ";
  }

  /**
   * returns {@link #message}.
   */
  public String getMessage() {
    return this.message;
  }

}
