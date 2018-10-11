
package entry;

public class BadSymbolException extends Exception {
  /**
   * don't know what this is for.
   */
  private static final long serialVersionUID = -7097937337528433122L;
  /**
   * message constructed.
   */
  private String message;

  /**
   * exception constructor.
   * 
   * @param message
   *        containing reason for exception call
   */
  public BadSymbolException(String message) {
    this.message = message;
  }

  /**
   * message that caused error.
   * 
   * @return message constructed from error.
   */
  public String getMessage() {
    return "The following symbol created and error: " + this.message;
  }

}
