
package calculators;

import entry.Function;
import entry.Symbol;

/**
 * calculates result between :<br>
 * two operands and an operator e.g. 4+4,<br>
 * two operands and a function e.g. pow(5,6)<br>
 * one operand and a function e.g. sqrt(4) <br>
 * 
 * @author octavio
 *
 */
public class Calculator {
  /**
   * Calculates standard expression of two operands and an operator using {@linkplain Math}.
   * 
   * @param operand1 first float for operation
   * @param operator operator to apply on operands.
   * @param operand2 second float for operation
   * @return operand1 --> operator --> operand2 --> result
   * @throws InvalidExpression if operator does not exist.
   */
  public float calculate(float operand1, Symbol operator, float operand2)
      throws InvalidExpression {
    switch (operator) {
      case PLUS:
        return operand1 + operand2;
      case MINUS:
        return operand1 - operand2;
      case TIMES:
        return operand1 * operand2;
      case DIVIDE:
        if (operand2 == 0) {
          throw new InvalidExpression(Float.toString(operand1) + "      | " + operator.toString()
              + " | " + Float.toString(operand2));
        }
        return operand1 / operand2;
      default:
        throw new InvalidExpression(Float.toString(operand1) + ", " + operator.toString() + ", "
            + Float.toString(operand2));

    }

  }

  /**
   * applies function variables using {@linkplain Math}.
   * 
   * @param function enum representing function.
   * @param operand1 float to be calculated with function
   * @param operand2 float used only if function takes two variables
   * @return float result = function(operand1) or function(operand1,operand2).
   * @throws InvalidExpression if function does not exist
   */
  public float calculateFunction(Function function, float operand1, float operand2)
      throws InvalidExpression {
    switch (function) {
      case SIN:
        return (float) Math.sin(operand1);
      case COS:
        return (float) Math.cos(operand1);
      case TAN:
        return (float) Math.tan(operand1);
      case ASIN:
        return (float) Math.asin(operand1);
      case ACOS:
        return (float) Math.acos(operand1);
      case ATAN:
        return (float) Math.atan(operand1);
      case ATAN2:
        return (float) Math.atan2(operand1, operand2);
      case COSH:
        return (float) Math.cosh(operand1);
      case SINH:
        return (float) Math.sinh(operand1);
      case TANH:
        return (float) Math.tanh(operand1);
      case LOG:
        return (float) Math.log(operand1);
      case LOG10:
        return (float) Math.log10(operand1);
      case LOG1P:
        return (float) Math.log1p(operand1);
      case EXP:
        return (float) Math.exp(operand1);
      case EXPM1:
        return (float) Math.expm1(operand1);
      case CBRT:
        return (float) Math.cbrt(operand1);
      case SQRT:
        return (float) Math.sqrt(operand1);
      case HYPOT:
        return (float) Math.hypot(operand1, operand2);
      case SIGNUM:
        return (float) Math.signum(operand1);
      case ULP:
        return (float) Math.ulp(operand1);
      case ABS:
        return (float) Math.abs(operand1);
      case CEIL:
        return (float) Math.ceil(operand1);
      case FLOOR:
        return (float) Math.floor(operand1);
      case IEEEREMAINDER:
        return (float) Math.IEEEremainder(operand1, operand2);
      case POW:
        return (float) Math.pow(operand1, operand2);
      default:
        throw new InvalidExpression(Float.toString(operand1) + ", " + function.toString() + ", "
            + Float.toString(operand2));

    }

  }

}
