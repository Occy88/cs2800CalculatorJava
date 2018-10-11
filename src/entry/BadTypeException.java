
package entry;

/**
 * This class is the {@link BadTypeException} class.<br>
 * Exception to throw when {@link Type} values do not match expectation.<br>
 * 
 * @author Octavio
 * 
 */
public class BadTypeException extends Exception {
  /**
   * don't know what this is for.
   */
  private static final long serialVersionUID = 1059793397667955810L;
  /**
   * error message when exception is thrown.
   */
  private String message;

  /**
   * constructs error message from two {@link Type} values that don't match.
   * 
   * @param typeA which caused error
   * @param typeB which caused error
   */
  public BadTypeException(Type typeA, Type typeB) {
    this.message = "Types are not equal Exception: " + typeA + ',' + typeB;
  }

  /**
   * get generated error message.
   * @return {@link #message}
   */
  public String getMessage() {
    return this.message;
  }

}
