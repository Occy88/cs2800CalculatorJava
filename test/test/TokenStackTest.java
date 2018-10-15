
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import calculators.InvalidExpression;
import entry.BadTypeException;
import entry.Function;
import entry.Symbol;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import stacks.EmptyStackException;
import stacks.TokenStack;

/**
 * This class tests the {@link TokenStack} class.
 * 
 * @author Octavio
 * 
 */

class TokenStackTest {

  private TokenStack tokenStack = new TokenStack();

  /**
   * Test #0 <br>
   * check {@link TokenStack} existence.
   */
  @Before
  void createTokenStack() {
    this.tokenStack = new TokenStack();

  }

  /**
   * Test #1 <br>
   * {@linkplain TokenStack#TokenStack(String)} constructor with string to accept string expression.
   */
  @Test
  void acceptStringExpression() {
    String string = "string";
    this.tokenStack = new TokenStack(string);
  }

  /**
   * Test #2 <br>
   * if {@linkplain TokenStack#push(entry.Entry)} pushes Symbol (operator) and float (operand).
   */
  @Test
  void tokenStackPushSymbolFloat() {
    float float1 = 3 / 2;
    Symbol symbol = Symbol.DIVIDE;
    this.tokenStack.push(float1);
    this.tokenStack.push(symbol);
  }

  /**
   * Test #3 <br>
   * check if {@link TokenStack#pop()} pops string from stack.
   */
  @Test
  void tokenStackPopFunction() {
    float float1 = 3 / 2;
    this.tokenStack.push(float1);
    try {
      this.tokenStack.pop();
    } catch (EmptyStackException e) {
      fail("should not be empty or popped incorrectly.");
      e.printStackTrace();
    }

  }

  /**
   * Test #4<br>
   * check if {@link TokenStack#isEmpty()} returns true if stack is empty else false.
   *
   * 
   */
  @Test
  void tokenStackIsEmpty() {
    float float1 = 3 / 2;

    this.tokenStack.push(float1);
    assertFalse(this.tokenStack.isEmpty(), "stack is empty when it shouldt be");
    try {
      this.tokenStack.pop();
      assertTrue(this.tokenStack.isEmpty(), "stack isn't empty when it should be");
    } catch (EmptyStackException e) {
      fail("should not be empty push or pop failed");
      e.printStackTrace();
    }

  }

  /**
   * Test #5<br>
   * {@linkplain TokenStack#size()} get stack size test.
   */
  @Test
  void tokenStackSizeFunction() {
    float float1 = 3 / 2;

    this.tokenStack.push(float1);
    assertEquals(1, this.tokenStack.size(), "size returns wrong value");
    this.tokenStack.push(float1);
    assertEquals(2, this.tokenStack.size(), "size returns wrong value");
    try {
      this.tokenStack.pop();
      assertEquals(1, this.tokenStack.size(), "size returns wrong value");
    } catch (EmptyStackException e) {
      fail("should not be empty, push or pop failed");
      e.printStackTrace();
    }

  }

  // this test is redundant as nobody enters expressions with consistent blank characters.
  // it has been replaced.
  // /**
  // *Test #6<br>
  // * function that checks for parses expression, checks for float
  // * checks for symbol, pushes to stack, and throws if input is invalid;
  // *
  // */
  // @Test
  // void pushExpressionToStackFromString() {
  // TokenStack this.tokenStack=new TokenStack();
  // String expression1="string";
  // String expression2="1 2 + - / ( )"; // 7 valid variables
  //
  // try {
  // this.tokenStack.pushExpression(expression1);
  // fail("expected following expression to be thrown: "+expression1);
  // }catch (InvalidExpression ex) {};
  //
  // try {
  // this.tokenStack.setExpression(expression2);
  // this.tokenStack.pushUnformattedExpression(true);
  //
  // assertEquals(this.tokenStack.size(),7,"expected 7 variables
  // to be pushed in the following expression: "+expression2);
  // }catch (InvalidExpression ex) {
  // fail("expected following expression to be pushed:"+expression2);
  // };
  // }

  /**
   * Test 7# <br>
   * check if TokenStack can push a valid single string and reject an invalid single string <br>
   * i.e. ("+") accepted ("b") rejected,<br>
   * accepted can be a {@linkplain Sign}, {@linkplain Function} or {@linkplain Float}.
   * 
   */
  @Test
  void pushString() {

    try {

      this.tokenStack.pushString("pow");
      assertEquals(this.tokenStack.top().getFunction(), Function.POW);

    } catch (BadTypeException | EmptyStackException | InvalidExpression e) {
      fail("expected to push tokens with separator: " + e.getMessage());
    }

  }

  /**
   * Test #8<br>
   * This tests a function that tests for a first float or function in a string,<br>
   * pushes if it is valid and removes it from the expression held in the class. <br>
   * e.g:<br>
   * "pow54bda" => push("pow") this.expression = "54bda" return true<br>
   * "43.1abc" => push(43.1) this.expression = "abc" return true<br>
   * "jk12l" => return false<br>
   * 
   */
  @Test
  void testIfFirstFunctionOrFloatIsPushed() {

    try {
      this.tokenStack.setExpression("powasdf");
      assertTrue(this.tokenStack.testAndPushNextFunctionOrFloat(false));
      assertEquals(this.tokenStack.pop().getFunction(), Function.POW, "expected pow function");

      this.tokenStack = new TokenStack();
      this.tokenStack.setExpression("sinhasdf");
      this.tokenStack.testAndPushNextFunctionOrFloat(false);
      assertEquals("asdf", this.tokenStack.getExpression(),
          "expected left over expression to be 'asdf' ");
      assertEquals(Function.SINH, this.tokenStack.top().getFunction(),
          "expected function sinh to be pushed");

      this.tokenStack = new TokenStack();
      this.tokenStack.setExpression("5powasdf");
      assertTrue(this.tokenStack.testAndPushNextFunctionOrFloat(false),
          "expected float to be pushed");

      this.tokenStack = new TokenStack();
      this.tokenStack.setExpression("5.2sinhasdf");
      this.tokenStack.testAndPushNextFunctionOrFloat(false);
      assertEquals("sinhasdf", this.tokenStack.getExpression(),
          "expected left over expression to be 'sinhasdf'");
      assertEquals((float) 5.2, this.tokenStack.top().getValue(),
          "expected float 5.2 to be pushed");

    } catch (BadTypeException | EmptyStackException e) {
      fail("unexpected exception caught in this test: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Test #9<br>
   * if there is an operator at the front of the expression,<br>
   * this function pushes the operator and removes it from the held expression,<br>
   * and returns a boolean signalling the valid push.<br>
   * e.g: "+ab" => push(+), this.expression="ab", return true.
   */
  @Test
  void testIfFirstOperatorIsPushed() {

    this.tokenStack.setExpression("+asdf");
    assertTrue(this.tokenStack.testAndPushNextOperator(false));

    this.tokenStack = new TokenStack();
    this.tokenStack.setExpression("-sinhasdf");
    this.tokenStack.testAndPushNextOperator(false);
    assertEquals(this.tokenStack.getExpression(), "sinhasdf");
    try {
      assertEquals(Symbol.MINUS, this.tokenStack.top().getSymbol());
    } catch (BadTypeException | EmptyStackException e) {
      fail("unexpected catch, should have pushed an operator.");
      e.printStackTrace();
    }

  }

  /**
   * Test #10<br>
   * Given a formatted expression, containing valid operators only, <br>
   * test if all valid symbols/functions/operators are pushed to the stack.
   * 
   */
  @Test
  void pushValidFormatedExpressionTest() {
    try {

      this.tokenStack.setExpression("+-pow5+4.2");
      this.tokenStack.pushUnformatedExpression(false);// 6 characters '+,-,pow,5,+,4.2'
      assertEquals(6, this.tokenStack.size());
    } catch (InvalidExpression e) {
      System.out.println(e.getMessage());
    }

  }

  /**
   * Test #11<br>
   * given a valid unformatted expression, test if each element is pushed.
   * 
   */
  @Test
  void pushValidUnformatedExpressionTest() {
    // reformatted due to later tests.
    try {

      this.tokenStack.setExpression("+3-2");
      this.tokenStack.pushUnformatedExpression(true);// 3 characters '3' '-' '2'
      assertEquals(3, this.tokenStack.size());
    } catch (InvalidExpression e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Test #12<br>
   * this is for post-fix expressions only, StandardFormat does not accept comma randomly,<br>
   * test if commas are accepted as a separator.
   */
  @Test
  void acceptCommaAsSeparator() {

    try {

      this.tokenStack.setExpression("+-13,5 Pow4,23");
      this.tokenStack.pushUnformatedExpression(false);// 7 variables (comma is just separator)
      this.tokenStack.print();
      assertEquals(7, this.tokenStack.size());
    } catch (InvalidExpression e) {
      fail(e.getMessage());
    }
  }

  /**
   * Test #13 test for cases when '+-' should operate as a sign not operator.<br>
   * if there is nothing or an operator or left bracket but not right bracket before a sign<br>
   * evaluate as float: <br>
   * -3 evaluates to: [-3]<br>
   * 3/+3 evaluates to: [3][/][3]<br>
   * (+3 evaluates to: [(][3]<br>
   */
  @Test
  void signEvaluateAsFloat() {
    try {

      this.tokenStack.setExpression("-3");
      this.tokenStack.pushUnformatedExpression(true);// formats to [-3]
      assertEquals(1, this.tokenStack.size());
      this.tokenStack = new TokenStack();
      this.tokenStack.setExpression("3/-3");
      this.tokenStack.pushUnformatedExpression(true);
      assertEquals(3, this.tokenStack.size());
      this.tokenStack = new TokenStack();
      this.tokenStack.setExpression("(-3");
      this.tokenStack.pushUnformatedExpression(true);
      assertEquals(2, this.tokenStack.size());
    } catch (InvalidExpression e) {
      fail(e.getMessage());
    }
  }

  /**
   * Test #14 <br>
   * test for cases when '+-' should be replaced.<br>
   * if there is a function after the sign and before there is nothing: <br>
   * -sin evaluates to: [-1][*][sin]
   */
  @Test
  void correctSignBeforeFunction() {
    try {
      this.tokenStack.setExpression("-sin");
      this.tokenStack.pushUnformatedExpression(true);
      assertEquals(3, this.tokenStack.size());

    } catch (InvalidExpression e) {
      fail(e.getMessage());
    }
  }

  /**
   * Test #15<br>
   * This test is to push a bracket after every function that takes two variables,<br>
   * and before every comma if it is an unformatted standard expression.<br>
   * i.e: <br>
   * "pow" resolves to: [pow][(]<br>
   * "," resolves to: [)] <br>
   * pow(5+2,2) resolves to: [pow][(][(][5][+][2][)][2][)] when pushed as tokens.<br>
   * sin(4) resolves to sin(4) nothing will be pushed as function takes one operator and no commas.
   */
  @Test
  void addBracketsAroundStandardExpressionIfCommaPushed() {
    try {
      this.tokenStack.setExpression("pow(pow 3+2,2,2)");
      this.tokenStack.pushUnformatedExpression(true);
      assertEquals(13, this.tokenStack.size());
    } catch (InvalidExpression e) {
      fail(e.getMessage());
    }
  }

}
