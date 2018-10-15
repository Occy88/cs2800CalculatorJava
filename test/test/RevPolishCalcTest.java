
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import calculators.InvalidExpression;
import calculators.RevPolishCalc;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import stacks.TokenStack;

/**
 * This class tests the {@link RevPolishCalc} class.<br>
 * Calculates expression in post-fix notation using {@linkplain Entry} tokens pushed<br>
 * to the {@linkplain TokenStack} containing fields: (TYPE) (NUMBER, SYMBOL, FUNCTION)<br>
 * Tokens created from the expression are assumed to be valid<br>
 * given they are created by the {@linkplain TokenStack} class.
 * 
 * @author Octavio
 * 
 */

class RevPolishCalcTest {
  private RevPolishCalc calc = new RevPolishCalc();

  /**
   * Test #0<br>
   * initiate class.
   */
  @Before
  void initiateCalc() {
    this.calc = new RevPolishCalc();
  }

  @Test
  /**
   * Test #1<br>
   * save a string, and convert it to stack.
   */
  void calculateStringTest() {
    try {
      this.calc.calculateString("5,5,+");
    } catch (InvalidExpression e) {
      fail("expected string to be converted to stack");
      e.printStackTrace();
    }
    try {
      this.calc.calculateString("5,5,kil");
      fail("expected invalid string to be thrown");
    } catch (InvalidExpression e) {
      e.getMessage();
    }
  }

  @Test

  /**
   * Test #2<br>
   * test if result is popped correctly from stack if only one variable left,<br>
   * otherwise invalid expression.
   */
  void testAnswerPop() {
    try {
      assertEquals(this.calc.calculateString("5"), (float) 5,
          "expeted operator to be resolved with two popped operands.");
    } catch (InvalidExpression e) {
      fail("should have been converted and calculated");
      e.printStackTrace();
    }
    try {
      this.calc.calculateString("5,5");
      fail("should have been converted then two tokens left so no answer");

    } catch (InvalidExpression e) {
      e.getMessage();
    }
  }

  @Test

  /**
   * Test #3<br>
   * resolving an operator function.
   */
  void resolveOperatorTest() {
    try {
      assertEquals(this.calc.calculateString("5,5,+"), (float) 10,
          "expeted operator to be resolved with two popped operands.");
    } catch (InvalidExpression e) {
      fail("should have been converted and calculated");
      e.printStackTrace();
    }
  }

  @Test

  /**
   * Test #4<br>
   * resolving a function with one or two variables.
   */
  void resolveFunctionTest() {
    try {
      assertEquals(this.calc.calculateString("5,2,pow"), (float) 25,
          "expeted function to be resolved with two popped operands.");
    } catch (InvalidExpression e) {
      fail("should have been converted and calculated");
      e.printStackTrace();
    }
    try {
      assertEquals(this.calc.calculateString("5,sin"), (float) Math.sin(5),
          "expeted function to be resolved with one popped operands.");
    } catch (InvalidExpression e) {
      fail("should have been converted and calculated");
      e.printStackTrace();
    }
  }

  @Test

  /**
   * Test #5<br>
   * Testing all functionality combined.
   */
  void resolveEquationTest() {
    try {
      assertEquals(this.calc.calculateString("5,2,pow,5,+,sin"), (float) Math.sin(30),
          "expeted function to be resolved with two popped operands.");
    } catch (InvalidExpression e) {
      fail("should have been converted and calculated");
      e.printStackTrace();
    }
    try {
      assertEquals(this.calc.calculateString("5,sin"), (float) Math.sin(5),
          "expeted function to be resolved with one popped operands.");
    } catch (InvalidExpression e) {
      fail("should have been converted and calculated");
      e.printStackTrace();
    }
  }

}
