
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import calculators.InvalidExpression;
import calculators.RevPolishCalc;
import calculators.StandardCalc;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import stacks.TokenStack;

/**
 * This class tests the {@link RevPolishCalc} class.<br>
 * Calculates expression in the for of (NUMBER, SYMBOL, NUMBER)
 * 
 * @author Octavio
 * 
 */

// After every function in this test class,
// code was refactored to distinct functions
// dealing with every issue.

class StandardCalcTest {
  private StandardCalc calc = new StandardCalc();

  /**
   * Test #0<br>
   * {@linkplain StandardCalc} initialiser.
   */
  @Before
  void iniStandardCalc() {
    this.calc = new StandardCalc();
  }

  @Test
  /**
   * Test #1<br>
   * {@linkplain StandardCalc#calculateString(String)} test if string accepted<br>
   * and creates a {@linkplain TokenStack}, with the string, and trows exception <br>
   * if the string has invalid characters.
   */
  void testCalculateStringException() {
    String expression = "5+ 5/2";
    try {
      calc.calculateString(expression);
    } catch (InvalidExpression e) {
      fail("expected valid tokenstack to be pushed");
      e.printStackTrace();
    }
    expression = "pow(4.2/k)";
    try {
      calc.calculateString(expression);
      fail("expected invalid expression to be recognised");
    } catch (InvalidExpression e) {
      e.getMessage();
    }
  }

  @Test
  /**
   * Test #2<br>
   * {@linkplain StandardCalc#shuntingAlgorithm(TokenStack)}, <br>
   * Function test if receives a {@linkplain TokenStack}, <br>
   * if tokenStack contains number tokens, they are pushed to output,<br>
   * outputStack is reversed and sent to revPolish stack, <br>
   * float is returned.
   */
  void testShuntingAlgoNumberOccurance() {
    String expression = "5";
    try {
      float result = calc.calculateString(expression);
      assertEquals(result, (float) 5, "expected 5 to be returned");
    } catch (InvalidExpression e) {
      fail("expected valid tokenstack to be pushed");
      e.printStackTrace();
    }

  }

  /**
   * Test #3<br>
   * {@linkplain StandardCalc#shuntingAlgorithm(TokenStack)}, <br>
   * test if tokenStack contains function it is pushed directly to operator stack<br>
   * then if there are remaining items in the operator stack<br>
   * they are pushed onto the output queue.
   */
  void testShuntingAlgoFunctionOccurance() {
    String expression = "pow(5,2)";
    try {
      float result = calc.calculateString(expression);
      assertEquals(result, (float) 25, "expected 25 to be returned");
    } catch (InvalidExpression e) {
      fail("expected valid tokenstack to be pushed");

    }
  }

  @Test
  /**
   * Test #4<br>
   * {@linkplain StandardCalc#shuntingAlgorithm(TokenStack)}, <br>
   * test for symbol occurrence,<br>
   * these are tested against current item in operator stack,<br>
   * while item in operator stack is more important it is pushed to output<br>
   * finally symbol pushed to operator stack.
   */
  void testShuntingAlgoOperatorOccurenceAndImportance() {
    String expression = "pow 5-1*3,2";
    try {
      float result = calc.calculateString(expression);
      assertEquals(result, (float) 4, "expected 4 to be returned");
    } catch (InvalidExpression e) {
      fail("expected valid tokenstack to be pushed");
      e.printStackTrace();
    }

  }

  @Test
  /**
   * Test #5<br>
   * {@linkplain StandardCalc#shuntingAlgorithm(TokenStack)}, <br>
   * test if Bracket occurs and how to deal with it.
   */
  void testShuntingAlgoBracketOccurence() {
    String expression = "pow (5-1)*2,2";
    try {
      float result = calc.calculateString(expression);
      assertEquals(result, (float) 64, "expected 64 to be returned");
    } catch (InvalidExpression e) {
      fail("expected valid tokenstack to be pushed");
      e.printStackTrace();
    }

  }

}
