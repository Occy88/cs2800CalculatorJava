
package entry;

/**
 * This class is the {@link Entry} class.<br>
 * -javadoc to be complete-
 * 
 * @author Octavio
 * 
 */
public class Entry {

  /**
   * a float entered by the user.
   */
  private float number = 0;
  /**
   * no use for this as of now.
   */
  private String str;
  /**
   * defines {@linkplain Type} of argument held in {@linkplain entry} <br>
   * for throwing {@link BadTypeException} in:<br>
   * {@link #getString()}, {@link #getValue()}, {@link #getSymbol()}.
   */
  private Type type;
  /**
   * Function entered by the user.<br>
   * Stored as an enum.
   * 
   */
  private Function function = Function.INVALID;
  /**
   * Symbol entered by the user. <br>
   * stored as enum.
   */
  private Symbol symbol = Symbol.INVALID;

  /**
   * stores float into {@link #number}<br>
   * declares type for {@link #type}.
   * 
   * @param number
   *        entered by user.
   */
  public Entry(float number) {
    this.number = number;
    this.type = Type.NUMBER;
  }

  /**
   * stores string into {@link #str}<br>
   * declares type for {@link #type}.
   * 
   * @param string
   *        to be saved
   */
  public Entry(String string) {
    this.str = string;
    this.type = Type.STRING;
  }

  /**
   * Empty constructor <br>
   * used class existence test.
   */
  public Entry() {
  }

  /**
   * stores Symbol into {@link #symbol}<br>
   * declares Symbol type for {@link #type}.
   *
   * @param symbol
   *        to be held in the entry
   */
  public Entry(Symbol symbol) {
    this.symbol = symbol;
    this.type = Type.SYMBOL;
  }

  /**
   * stores {@linkplain Function} into {@link #symbol}<br>
   * declares {@linkplain Function} type for {@link #type}.
   *
   * @param function
   *        to be held in the entry
   */
  public Entry(Function function) {
    this.function = function;
    this.type = Type.FUNCTION;
  }

  /**
   * get the Type that represents data held in the entry.
   * 
   * @return {@link #type}
   */
  public Type getType() {
    return this.type;

  }

  /**
   * get the string held in data if the type is also a string.
   * 
   * @return str
   * @throws BadTypeException
   *         if the type held is not a string
   */
  public String getString() throws BadTypeException {
    if (type != Type.STRING) {
      throw new BadTypeException(Type.STRING, type);
    }
    return this.str;

  }

  /**
   * if the type held is a symbol returns the symbol.
   * 
   * @return {@link #symbol}
   * @throws BadTypeException
   *         if the type held is not a symbol.
   */

  public Symbol getSymbol() throws BadTypeException {
    if (type != Type.SYMBOL) {
      throw new BadTypeException(Type.SYMBOL, type);
    }
    return this.symbol;

  }

  /**
   * returns the float held in the entry if the type corresponds with a number.
   * 
   * @return {@link #number}
   * @throws BadTypeException
   *         if the type is not a number.
   */
  public float getValue() throws BadTypeException {
    if (type != Type.NUMBER) {
      throw new BadTypeException(Type.NUMBER, type);
    }
    return this.number;

  }

  /**
   * get the function held in the entry if the type is a function.
   * 
   * @return function
   * @throws BadTypeException
   *         if the type held is not a function.
   */
  public Function getFunction() throws BadTypeException {
    if (type != Type.FUNCTION) {
      throw new BadTypeException(Type.FUNCTION, type);
    }
    return this.function;
  }

  /**
   * constructs a string representing data held by the Entry .
   * 
   * @return {@link #str} constructed string.
   */
  public String toString() {
    String string = "";
    if (this.symbol != Symbol.INVALID) {
      string += this.symbol.toString();
    } else if (this.number != (float) 0) {
      string += Float.toString(this.number);
    } else if (this.function != Function.INVALID) {
      string += this.function.toString();
    }
    return string;
  }
}
