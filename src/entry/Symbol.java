
package entry;

/**
 * this class holds all the possible symbols the calculator recognises <br>
 * it also contains helpful functions returning information about symbols.
 * 
 * @author octavio
 *
 */
public enum Symbol {
  LEFT_BRACKET, RIGHT_BRACKET, POWER, TIMES, DIVIDE, PLUS, MINUS, INVALID;

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

  /**
   * this function returns the precedence of available symbols.
   * 
   * @param symbol
   *        to test
   * @return the precedence of the symbol
   * @throws BadSymbolException
   *         the parameter entered is not a symbol
   */
  public static int getPrecedence(Symbol symbol) throws BadSymbolException {
    switch (symbol) {
      case RIGHT_BRACKET:// not needed for reverse polish
        return 5;
      case LEFT_BRACKET:// not needed for reverse polish
        return 5;
      case POWER:
        return 4;

      case TIMES:
        return 3;

      case DIVIDE:
        return 3;

      case PLUS:
        return 2;

      case MINUS:
        return 2;
      default:
        throw new BadSymbolException(symbol.toString());

    }

  }

  /**
   * function to check if a symbol is an operator as '(' and ')' are not.
   * 
   * @param symbol
   *        the symbol to test
   * @return boolean symbol is an operator
   * @throws BadSymbolException
   *         the symbol sent is not a symbol
   */
  public static boolean isOperator(Symbol symbol) throws BadSymbolException {
    switch (symbol) {
      case POWER:
        return true;
      case TIMES:
        return true;
      case DIVIDE:
        return true;
      case PLUS:
        return true;
      case MINUS:
        return true;
      case LEFT_BRACKET:
        return false;
      case RIGHT_BRACKET:
        return false;
      default:
        throw new BadSymbolException(symbol.toString());

    }

  }

  /**
   * function to return left associativity of symbol as a boolean.
   * 
   * @param symbol
   *        to test
   * @return boolean is the symbol left associative
   * @throws BadSymbolException
   *         symbol tested is not a symbol
   */
  public static boolean isLeftAssociative(Symbol symbol) throws BadSymbolException {
    switch (symbol) {
      case POWER:
        return false;
      case TIMES:
        return true;
      case DIVIDE:
        return true;
      case PLUS:
        return true;
      case MINUS:
        return true;
      default:
        throw new BadSymbolException(symbol.toString());

    }

  }
}
