
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import calculators.Calculator;
import calculators.InvalidExpression;
import entry.Function;
import entry.Symbol;

import org.junit.Before;
import org.junit.jupiter.api.Test;

/**
 * This class tests the {@link Calculator} class.<br>
 * Calculates expression in the for of (NUMBER, SYMBOL, NUMBER)
 * 
 * @author Octavio
 * 
 */

class CalculatorTest {
  private Calculator calc = new Calculator();
  private float operand1 = 4;
  private float operand2 = 2;

  /**
   * Test #0 <br>
   * initialise calculator class.
   */
  @Before
  void calculatorExistance() {
    this.calc = new Calculator();
  }

  /**
   * Test #1 <br>
   * test operator with two operands.
   */
  @Test
  void addition() {
    float result;
    try {
      result = calc.calculate(operand1, Symbol.PLUS, operand2);
      assertEquals(result, operand1 + operand2, "added Incorrectly");

    } catch (InvalidExpression e) {
      fail("expected expression to go through");
      e.printStackTrace();
    }

  }

  /**
   * Test #3<br>
   * test function with one or two valid operands.
   */
  @Test
  void functionTest() {
    float result;
    try {
      result = calc.calculateFunction(Function.POW, operand1, operand2);
      assertEquals(result, (float) Math.pow(operand1, operand2), "incorrect result");
    } catch (InvalidExpression e) {
      fail("expected expression to go through");
      e.printStackTrace();
    }

    try {
      result = calc.calculateFunction(Function.SIN, operand1, operand2);
      assertEquals(result, (float) Math.sin(operand1), "incorrect result");
    } catch (InvalidExpression e) {
      fail("expected expression to go through");
      e.printStackTrace();
    }

  }

  /**
   * Test #4<br>
   * test invalid entry exceptions.
   */
  @Test
  void exceptionTests() {
    float result;
    try {
      result = calc.calculateFunction(Function.INVALID, -operand1, operand2);
      fail("expected expression to be thrown as invalid: " + result);
    } catch (InvalidExpression e) {
      e.getMessage();
    }

    try {
      result = calc.calculate(operand1, Symbol.INVALID, operand2);
      fail("expected expression to be thrown as invalid: " + result);

    } catch (InvalidExpression e) {
      e.getMessage();
    }

  }

}
