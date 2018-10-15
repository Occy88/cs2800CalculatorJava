
package entry;

/**
 * this class holds all the possible symbols the calculator recognises <br>
 * it also contains helpful functions returning information about symbols.
 * 
 * @author octavio
 *
 */
public enum Symbol {
  LEFT_BRACKET(5, false, true),
  RIGHT_BRACKET(5, false, true),
  TIMES(3, true, true),
  DIVIDE(3, true, true),
  PLUS(2, true, true),
  MINUS(2, true, true),
  INVALID(1, false, false);
  private int precedence = 0;
  private boolean isOperator = false;
  private boolean isLeftAssociative = false;

  /**
   * constructor for available enums,<br>
   * isLeftAssociative only matters for implementation of more signs,<br>
   * isOperator defines if enum acts as operator and,<br>
   * precedence defines order of computation.<br>
   * 
   * @param precedence
   *        order of computation
   * @param isOperator
   *        if it's an operator
   * @param isLeftAssociative
   *        if it should be pushed in case same precedence.
   */
  Symbol(int precedence, boolean isOperator, boolean isLeftAssociative) {
    this.precedence = precedence;
    this.isOperator = isOperator;
    this.isLeftAssociative = isLeftAssociative;
  }

  /**
   * getter for {@linkplain #precedence}.
   * 
   * @return int precedence
   */
  public int getPrecedence() {
    return this.precedence;
  }

  /**
   * getter for {@linkplain #isLeftAssociative}.
   * 
   * @return boolean left associative
   */
  public boolean isLeftAssociative() {
    return this.isLeftAssociative;
  }

  /**
   * getter for {@linkplain #isOperator}.
   * 
   * @return boolean isOperator
   */
  public boolean isOperator() {
    return this.isOperator;
  }

  /**
   * function returns symbol associated with a character entered.
   * 
   * @param string
   *        to test as a symbol
   * @return symbol that is associated with the input
   * @throws BadSymbolException
   *         the input is not a symbol.
   */
  public static Symbol stringToSymbol(String string) throws BadSymbolException {
    switch (string) {
      case "(":
        return Symbol.LEFT_BRACKET;
      case ")":
        return Symbol.RIGHT_BRACKET;
      case "*":
        return Symbol.TIMES;
      case "/":
        return Symbol.DIVIDE;
      case "+":
        return Symbol.PLUS;
      case "-":
        return Symbol.MINUS;
      default:
        throw new BadSymbolException(string);

    }
  }
}
