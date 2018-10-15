
package entry;

/**
 * this class holds all possible functions the calculator can handle <br>
 * also contains helpful functions to return attributes required.
 * 
 * @author octavio
 *
 */
public enum Function {

  SIN(1),
  COS(1),
  TAN(1),
  ASIN(1),
  ACOS(1),
  ATAN(1),
  ATAN2(2),
  COSH(1),
  SINH(1),
  TANH(1),
  LOG(1),
  LOG10(1),
  LOG1P(1),
  EXP(1),
  EXPM1(1),
  CBRT(1),
  SQRT(1),
  HYPOT(2),
  SIGNUM(1),
  ULP(1),
  ABS(1),
  CEIL(1),
  FLOOR(1),
  IEEEREMAINDER(2),
  POW(2),
  RINT(1),
  ROUND(1),
  TODEGREES(1),
  TORADIANS(1),
  INVALID(1);
  private int numberOfOperands = 0;

  Function(int numberOfOperands) {
    this.numberOfOperands = numberOfOperands;
  }

  /**
   * returns number of operands function takes.
   * 
   * @return int number of operands
   */
  public int getNumberOfOperands() {
    return this.numberOfOperands;
  }

  /**
   * returns the function that is associated with the input string.
   * 
   * @param string
   *        input string to test
   * @return Function that is associated with inputs
   * @throws BadSymbolException
   *         the input string is not a function
   */

  public static Function stringToFunction(String string) throws BadSymbolException {
    switch (string) {
      case "sin":
        return Function.SIN;
      case "cos":
        return Function.COS;
      case "tan":
        return Function.TAN;
      case "asin":
        return Function.ASIN;
      case "acos":
        return Function.ACOS;
      case "atan":
        return Function.ATAN;
      case "atan2":
        return Function.ATAN2;
      case "cosh":
        return Function.COSH;
      case "sinh":
        return Function.SINH;
      case "tanh":
        return Function.TANH;
      case "log":
        return Function.LOG;
      case "log10":
        return Function.LOG10;
      case "log1p":
        return Function.LOG1P;
      case "exp":
        return Function.EXP;
      case "expm1":
        return Function.EXPM1;
      case "cbrt":
        return Function.CBRT;
      case "sqrt":
        return Function.SQRT;
      case "hypot":
        return Function.HYPOT;
      case "signum":
        return Function.SIGNUM;
      case "ulp":
        return Function.ULP;
      case "abs":
        return Function.ABS;
      case "ceil1":
        return Function.CEIL;
      case "floor1":
        return Function.FLOOR;
      case "ieeeremainder":
        return Function.IEEEREMAINDER;
      case "pow":
        return Function.POW;
      case "rint":
        return Function.RINT;
      case "round":
        return Function.ROUND;
      case "todegrees":
        return Function.TODEGREES;
      case "toradians":
        return Function.TORADIANS;
      default:
        throw new BadSymbolException(string);

    }
  }

}
