
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import entry.Entry;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import stacks.EmptyStackException;
import stacks.Stack;

/**
 * This class tests the {@link Stack} class.
 * 
 * @author Octavio
 * 
 */

class StackTest {
  private Stack stack = new Stack();

  /**
   * Test #1 <br>
   * Test to check for class {@link Stack} existence.
   */
  @Before
  void initiateStack() {
    this.stack = new Stack();
  }

  /**
   * Test #2 <br>
   * Test for function push({@link Entry}) in class {@link Stack}.
   */
  @Test
  void pushEntryExistance() {
    Entry entry = new Entry();
    this.stack.push(entry);
  }

  /**
   * Test #3<br>
   * Test for function size() returns number of entries in class {@link Stack}.
   */
  @Test
  void sizeReturnsIntegerEquivalentToNumberOfEntries() {
    int entriesPushed = 2;
    Entry entry = new Entry();
    this.stack.push(entry);
    this.stack.push(entry);
    int sizeReturned = this.stack.size();
    assertEquals(sizeReturned, entriesPushed, "size function incorrect return");

    stack = new Stack();
    entriesPushed = 1;
    entry = new Entry();
    this.stack.push(entry);
    sizeReturned = this.stack.size();
    assertEquals(sizeReturned, entriesPushed, "size function incorrect return");
    /*
     * required refactoring push function, adding a list, returning size of list.
     */

  }

  /**
   * Test #4<br>
   * Test for function pop() which returns and removes newest entry in class {@link Stack}.
   * 
   */
  @Test
  void popReturnsAndRemovesNewestEntry() {

    Entry entry = new Entry("entry");
    this.stack.push(entry);
    Entry returnedEntry;
    try {
      returnedEntry = this.stack.pop();
      assertEquals(entry, returnedEntry, "poped entry is not the same as pushed entry");
      assertEquals(0, this.stack.size(), "entry pused was not poped stack is not empty");
    } catch (EmptyStackException e) {
      fail("pushed or popped incorrectly " + e.getMessage());
      e.printStackTrace();
    }

  }

  /**
   * Test #5 <br>
   * Test for function top() which returns and keeps newest entry in class {@link Stack}.
   */
  @Test
  void popReturnsAndKeepsNewestEntry() throws EmptyStackException {

    Entry entry = new Entry("entry");
    this.stack.push(entry);
    Entry returnedEntry = this.stack.top();
    assertEquals(entry, returnedEntry, "returned entry is not the same as pushed entry");
    assertEquals(1, this.stack.size(), "entry pused waspoped stack is empty");

  }

  /**
   * Test #6 <br>
   * Test for function pop()for EmptyStackException in class {@link Stack}.
   */
  @Test
  void popEmptyStackExceptionTest() {
    try {
      this.stack.pop();
      fail("Expected EmptyStackException on pop()");
    } catch (EmptyStackException emptyStack) {
      emptyStack.getMessage();

    }

  }

  /**
   * Test #7 <br>
   * Test for function top() for EmptyStackException in class {@link Stack}.
   */
  @Test
  void topEmptyStackExceptionTest() {
    try {
      this.stack.top();
      fail("Expected EmptyStackException on top()");
    } catch (EmptyStackException emptyStack) {
      emptyStack.getMessage();

    }

  }

}
