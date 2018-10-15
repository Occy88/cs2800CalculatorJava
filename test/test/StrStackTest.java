
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;

import org.junit.jupiter.api.Test;

import stacks.EmptyStackException;
import stacks.StrStack;

/**
 * This class tests the {@link StrStack} class.
 * 
 * @author Octavio
 * 
 */

class StrStackTest {
  private StrStack strStack = new StrStack();

  /**
   * Test #1 <br>
   * check {@link StrStack} constructor.
   */
  @Before
  void createStrStack() {
    this.strStack = new StrStack();
  }

  /**
   * Test #2<br>
   * check if {@link StrStack#push(String)} pushes entry into stack.
   */
  @Test
  void strStackPushFunction() {
    String string = "string";
    this.strStack.push(string);

  }

  /**
   * Test #3 <br>
   * check if {@link StrStack#pop()} pops and returns string from stack.
   */
  @Test
  void strStackPopFunction() {
    String string = "string";
    String string1 = "string1";
    this.strStack.push(string);
    this.strStack.push(string1);
    try {
      assertEquals(this.strStack.pop(), string1, "string popped is not equal");
      assertEquals(this.strStack.pop(), string, "string popped is not equal");

    } catch (EmptyStackException e) {
      fail("nothing pushed or popped incorrectly");
      e.printStackTrace();
    }

  }

  /**
   * Test #4 <br>
   * check if {@link StrStack#isEmpty()} returns true if stack is empty else false.<br>
   */
  @Test
  void strStackIsEmpty() {
    String string = "string";
    this.strStack.push(string);
    assertFalse(this.strStack.isEmpty(), "stack is empty when it shouldt be");
    try {
      this.strStack.pop();
      assertTrue(this.strStack.isEmpty(), "stack isn't empty when it should be");
    } catch (EmptyStackException e) {
      e.printStackTrace();
      fail("numstack should be empty after pop");
    }

  }

  /**
   * Test #5 <br>
   * check if {@link StrStack#isEmpty()} throws .<br>
   */
  @Test
  void strStackPopError() {
    try {
      this.strStack.pop();
      fail("should have thrown empty stack exception error");
    } catch (EmptyStackException e) {
      e.getMessage();
    }

  }
}
