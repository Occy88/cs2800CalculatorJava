
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import calculators.InvalidExpression;
import calculators.RevPolishCalc;
import calculators.StandardCalc;
import stacks.EmptyStackException;
import stacks.TokenStack;

/**
 * This class tests the {@link RevPolishCalc} class.<br>
 * Calculates expression in the for of (NUMBER, SYMBOL, NUMBER)
 * 
 * @author Octavio
 * 
 */

class StandardCalcTest {
  private StandardCalc calc = new StandardCalc();

  @Test
  void arithmeticErrorTest() {
    // try {
    // TokenStack tokenStack=new TokenStack();
    // tokenStack.pushUnformatedExpression("4**4");
    // float result = calc.shuntingAlgorithm(tokenStack);
    // String sresult=Float.toString(result);
    // System.out.println("result: "+Float.toString(result));
    // }catch (InvalidExpression e) {
    // tokenStack=new TokenStack();
    // System.out.println(e.getMessage());
    // }

  }

  @Test
  void testAlgorithm() {
    Scanner kbd = new Scanner(System.in);
    String expression;
    TokenStack tokenStack = new TokenStack();
    float result;

    while (true) {
      System.out.println("please enter expression: ");
      expression = kbd.nextLine();
      try {
        calc = new StandardCalc();
        // System.out.println("Pushing expression to stack");
        tokenStack.setExpression(expression);
        tokenStack.pushUnformatedExpression(true);
        tokenStack.print();
        // System.out.println("Senting expression to Algo");
        result = calc.shuntingAlgorithm(tokenStack);
        System.out.println("result: " + Float.toString(result));
      } catch (InvalidExpression e) {
        // TODO Auto-generated catch block
        tokenStack = new TokenStack();
        calc = new StandardCalc();
        System.out.println("error: " + e.getMessage());
      }

    }
  

  }

}
