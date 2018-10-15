
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import entry.Symbol;

import org.junit.Before;

import org.junit.jupiter.api.Test;

import stacks.EmptyStackException;
import stacks.OpStack;

/**
 * This class tests the {@link OpStack} class.
 * 
 * @author Octavio
 * 
 */

class OpStackTest {
  private OpStack opStack = new OpStack();

  /**
   * Test #1 <br>
   * check {@link OpStack} constructor.
   */
  @Before
  void createOpStack() {
    this.opStack = new OpStack();
  }

  /**
   * Test #2<br>
   * check if {@link OpStack#push(Symbol)} pushes entry into stack.
   */
  @Test
  void opStackPushFunction() {
    Symbol symbol = Symbol.TIMES;
    this.opStack.push(symbol);

  }

  /**
   * Test #3 <br>
   * check if {@link OpStack#pop()} pops and returns symbol from stack.
   */
  @Test
  void opStackPopFunction() {
    Symbol symbol = Symbol.TIMES;
    Symbol symbol1 = Symbol.DIVIDE;
    this.opStack.push(symbol);
    this.opStack.push(symbol1);
    try {
      assertEquals(this.opStack.pop(), symbol1, "symbol popped is not equal");
      assertEquals(this.opStack.pop(), symbol, "symbol popped is not equal");

    } catch (EmptyStackException e) {
      fail("nothing pushed or popped incorrectly");
      e.printStackTrace();
    }

  }

  /**
   * Test #4 <br>
   * check if {@link OpStack#isEmpty()} returns true if stack is empty else false.<br>
   */
  @Test
  void opStackIsEmpty() {
    Symbol symbol = Symbol.TIMES;
    this.opStack.push(symbol);
    assertFalse(this.opStack.isEmpty(), "stack is empty when it shouldt be");
    try {
      this.opStack.pop();
      assertTrue(this.opStack.isEmpty(), "stack isn't empty when it should be");
    } catch (EmptyStackException e) {
      e.printStackTrace();
      fail("numstack should be empty after pop");
    }

  }

  /**
   * Test #5 <br>
   * check if {@link OpStack#isEmpty()} throws .<br>
   */
  @Test
  void opStackPopError() {
    try {
      this.opStack.pop();
      fail("should have thrown empty stack exception error");
    } catch (EmptyStackException e) {
      e.getMessage();
    }

  }
}
