
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;

import org.junit.jupiter.api.Test;

import stacks.EmptyStackException;
import stacks.NumStack;

/**
 * This class tests the {@link NumStack} class.
 * 
 * @author Octavio
 * 
 */

class NumStackTest {
  private NumStack numStack = new NumStack();

  /**
   * Test #1 <br>
   * check {@link NumStack} constructor.
   */
  @Before
  void createNumStack() {
    this.numStack = new NumStack();
  }

  /**
   * Test #2<br>
   * check if {@link NumStack#push(float)} pushes entry into stack.
   */
  @Test
  void numStackPushFunction() {
    float number = 4 / 2;
    this.numStack.push(number);

  }

  /**
   * Test #3 <br>
   * check if {@link NumStack#pop()} pops and returns number from stack.
   */
  @Test
  void numStackPopFunction() {
    float number = 4 / 2;
    float number1 = 6 / 2;
    this.numStack.push(number);
    this.numStack.push(number1);
    try {
      assertEquals(this.numStack.pop(), number1, "number popped is not equal");
      assertEquals(this.numStack.pop(), number, "number popped is not equal");

    } catch (EmptyStackException e) {
      fail("nothing pushed or popped incorrectly");
      e.printStackTrace();
    }

  }

  /**
   * Test #4 <br>
   * check if {@link NumStack#isEmpty()} returns true if stack is empty else false.<br>
   */
  @Test
  void numStackIsEmpty() {
    float number = 4 / 2;
    this.numStack.push(number);
    assertFalse(this.numStack.isEmpty(), "stack is empty when it shouldt be");
    try {
      this.numStack.pop();
      assertTrue(this.numStack.isEmpty(), "stack isn't empty when it should be");
    } catch (EmptyStackException e) {
      e.printStackTrace();
      fail("numstack should be empty after pop");
    }

  }

  /**
   * Test #5 <br>
   * check if {@link NumStack#isEmpty()} throws .<br>
   */
  @Test
  void numStackPopError() {
    try {
      this.numStack.pop();
      fail("should have thrown empty stack exception error");
    } catch (EmptyStackException e) {
      e.getMessage();
    }

  }
}
